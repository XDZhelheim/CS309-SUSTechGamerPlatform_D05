<template>
    <div id="developer">
        <div id="gl">
            <el-button id="ag" plain type="primary" icon="el-icon-plus" @click="addGameFormVisible = true">添加新游戏</el-button>
            <a href="http://localhost:8083/log" target="_blank"><el-button id="sdk" plain type="primary" icon="el-icon-download" >下载用户信息</el-button></a>
            <el-button id="getgame" plain type="primary" icon="el-icon-refresh" @click="getGame()">刷新列表</el-button>
            <h1>管理游戏</h1>
            <el-table border :data="tableData" borderstyle="width: 100%" id="tb">
                <el-table-column prop="title" label="名称"></el-table-column>
                <el-table-column prop="date" label="发行日期"></el-table-column>
                <el-table-column prop="price" label="价格"></el-table-column>
                <el-table-column prop="type" label="类型"></el-table-column>
                <el-table-column prop="publisher" label="开发商"></el-table-column>
                <el-table-column prop="language" label="语言"></el-table-column>
                <el-table-column prop="abstract" label="简介" width="300"></el-table-column>
                <el-table-column prop="discount" label="折扣"></el-table-column>
                <el-table-column prop="op" label="编辑/删除" width="180">
                    <template slot-scope="scope">
                        <el-button icon="el-icon-edit-outline" plain type="primary" @click="editGameFormVisible = true; editGame(scope.$index);"></el-button>
                        <el-button icon="el-icon-delete" plain type="warning" @click="delGame(scope.$index)"></el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="op" label="上传游戏" width="100">
                    <el-upload
                        action=""
                        :show-file-list="false"
                        :http-request="submit">
                        <el-button icon="el-icon-upload2" plain type="success"></el-button>
                    </el-upload>
                </el-table-column>
            </el-table>
        </div>

        <div id="fm">
            <el-dialog title="添加游戏" :visible.sync="addGameFormVisible" :show-close="false" :lock-scroll="false">
                <el-form :label-position="labelPosition" label-width="100px" size="medium">
                    <el-form-item label="名称">
                        <el-input v-model="newGame.title" style="width:var(--itemLength)"></el-input>
                    </el-form-item>

                    <el-form-item label="发行日期">
                        <el-date-picker v-model="newGame.date" style="width:var(--itemLength)" type="date"
                            placeholder="选择日期" value-format="yyyy-MM-dd">
                        </el-date-picker>
                    </el-form-item>

                    <el-form-item label="价格">
                        <el-input-number v-model="newGame.price" style="width:var(--itemLength)" :min="0"
                            :precision="2" :step="1" :controls="false">
                        </el-input-number>
                    </el-form-item>

                    <el-form-item label="类型">
                        <el-select v-model="newGame.type" style="width:var(--itemLength)" placeholder="选择类型">
                            <el-option v-for="item in gameTypes" :key="item.value" :label="item.label" :value="item.value"></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="发行商">
                        <el-input v-model="newGame.publisher" style="width:var(--itemLength)"></el-input>
                    </el-form-item>

                    <el-form-item label="语言">
                        <el-select v-model="newGame.language" style="width:var(--itemLength)" placeholder="选择语言">
                            <el-option v-for="item in languages" :key="item.value" :label="item.label" :value="item.value"></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="简介">
                        <el-input v-model="newGame.abstract" style="width:var(--itemLength)" type="textarea" maxlength="200" :autosize="{minRows: 3}" show-word-limit></el-input>
                    </el-form-item>

                    <el-form-item label="折扣">
                        <el-input-number v-model="newGame.discount" style="width:var(--itemLength)" :min="0" :max="1" controls-position="right"
                            :precision="2" :step="0.05" :controls="true">
                        </el-input-number>
                    </el-form-item>
                </el-form>

                <div slot="footer" class="dialog-footer">
                    <el-button @click="addGameFormVisible = false; clear();">取消</el-button>
                    <el-button type="primary" @click="addGame()">确定</el-button>
                </div>
            </el-dialog>
        </div>

        <div id="edit">
            <el-dialog title="编辑游戏" :visible.sync="editGameFormVisible" :show-close="false" :lock-scroll="false">
                <el-form :label-position="labelPosition" label-width="100px" size="medium">
                    <el-form-item label="发行日期">
                        <el-date-picker v-model="tableData[editIndex].date" style="width:var(--itemLength)" type="date"
                            placeholder="选择日期" value-format="yyyy-MM-dd">
                        </el-date-picker>
                    </el-form-item>

                    <el-form-item label="价格">
                        <el-input-number v-model="tableData[editIndex].price" style="width:var(--itemLength)" :min="0"
                            :precision="2" :step="1" :controls="false">
                        </el-input-number>
                    </el-form-item>

                    <el-form-item label="类型">
                        <el-select v-model="tableData[editIndex].type" style="width:var(--itemLength)" placeholder="选择类型">
                            <el-option v-for="item in gameTypes" :key="item.value" :label="item.label" :value="item.value"></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="发行商">
                        <el-input v-model="tableData[editIndex].publisher" style="width:var(--itemLength)"></el-input>
                    </el-form-item>

                    <el-form-item label="语言">
                        <el-select v-model="tableData[editIndex].language" style="width:var(--itemLength)" placeholder="选择语言">
                            <el-option v-for="item in languages" :key="item.value" :label="item.label" :value="item.value"></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="简介">
                        <el-input v-model="tableData[editIndex].abstract" style="width:var(--itemLength)" type="textarea" maxlength="200" :autosize="{minRows: 3}" show-word-limit></el-input>
                    </el-form-item>

                    <el-form-item label="折扣">
                        <el-input-number v-model="tableData[editIndex].discount" style="width:var(--itemLength)" :min="0" :max="1" controls-position="right"
                            :precision="2" :step="0.05" :controls="true">
                        </el-input-number>
                    </el-form-item>
                </el-form>

                <div slot="footer" class="dialog-footer">
                    <el-button @click="editGameFormVisible = false; cancelEdit();">取消</el-button>
                    <el-button type="primary" @click="checkEdit()" >确定</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>


<script>
import app from "../App"
import axios from 'axios'

var ind = 122
    export default {
        name: 'developer',

        data() {
            return {
                tableData: [{
                    title: "The Witcher: Wild Hunt",
                    date: "2015-05-19",
                    price: 47,
                    type: "RPG",
                    publisher: "Rubbish publisher",
                    language: "English",
                    rate: 4.8,
                    abstract: "该作承接《巫师2：国王刺客》的剧情，那些想要利用杰洛特的人已经不在了。杰洛特寻求改变自己的生活，着手于新的个人使命，而世界的秩序也在悄然改变。2015年10月，获第33届金摇杆奖最佳剧情、最佳视觉设计、最佳游戏时刻，更获得了年度最佳游戏大奖。并获得IGN 2015年度最佳游戏。2016年其DLC“血与酒”获得了The Game Awards2016年年度“最佳游戏角色扮演游戏”奖。",
                    discount: 0.0,
                    AddDe_game: "false"
                },
                ],

                newGame: {
                    title: null,
                    date: null,
                    price: null,
                    type: null,
                    publisher: null,
                    language: null,
                    abstract: null,
                    discount: null,
                    AddDe_game: ''
                },

                addGameFormVisible: false,
                labelPosition: 'right',
                editGameFormVisible: false,
                editIndex: 0,
                tempGame: null,
                file: '',

                gameTypes: [
                    {
                        value: "RPG",
                        label: "RPG"
                    },
                    {
                        value: "FPS",
                        label: "FPS"
                    },
                    {
                        value: "ACT",
                        label: "ACT"
                    },
                    {
                        value: "RTS",
                        label: "RTS"
                    },
                    {
                        value: "MOBA",
                        label: "MOBA"
                    }
                ],

                languages: [
                    {
                        value: "中文 (简体)",
                        label: "中文 (简体)"
                    },
                    {
                        value: "中文 (繁体)",
                        label: "中文 (繁体)"
                    },
                    {
                        value: "English",
                        label: "English"
                    }
                ]
            }
        },

        methods: {
            delGame(index) {
                this.tableData[index].AddDe_game = "Delete"
                var sendMsg = JSON.stringify(this.tableData[index])
                this.socket.send(sendMsg)
                this.socket.onmessage = (evt) => {
                    // alert(evt.data)
                }
                this.tableData.splice(index, 1)
            },


            addGame() {
                if (this.check()) {
                    this.newGame.AddDe_game = "Add"
                    var sendMsg = JSON.stringify(this.newGame)
                    this.socket.send(sendMsg)
                    this.socket.onmessage = (evt) => {
                        // alert(evt.data)
                    }
                    this.tableData.push(this.newGame)
                    this.addGameFormVisible = false
                    this.clear()
                }
                else
                    this.errmsg()
            },

            clear() {
                this.newGame = {
                    title: null,
                    date: null,
                    price: null,
                    type: null,
                    publisher: null,
                    language: null,
                    abstract: null,
                    discount: null,
                    AddDe_game: ''
                }
            },

            editGame(index) {
                this.editIndex=index
                this.tempGame=this.clone(this.tableData[index])
            },

            check() {
                for (let key in this.newGame)
                    if (this.newGame[key]==null)
                        return false
                return true
            },

            checkEdit() {
                for (let i = 0; i < this.tableData.length; i++)
                    for (let key in this.tableData[i])
                        if (this.tableData[i][key]!=0 && (this.tableData[i][key]==null || this.tableData[i][key]=='')) {
                            this.errmsg()
                            return
                        }
                this.editGameFormVisible=false
                this.changeGame()
            },

            errmsg() {
                this.$message.error('无效输入')
            },

            changeGame(){
                var ind = this.editIndex
                this.tableData[ind].AddDe_game = "Change"
                var sendMsg = JSON.stringify(this.tableData[ind])
                this.socket.send(sendMsg)
                this.socket.onmessage = function(msg){
                    // alert(msg.data)
                }
            },

            getGame() {
                this.socket.send(
                '{"get_game_develop":"true"}')
                
                this.socket.onmessage = (evt) => {
                var str = evt.data
                var obj = JSON.parse(str)
                this.tableData = obj
                }
            },

            cancelEdit() {
                this.tableData.splice(this.editIndex, 1, this.tempGame)
            },

            clone(object) {
                return JSON.parse(JSON.stringify(object))
            },

            // getFile: function (event) {
            //     this.file = event.target.files[0]
            //     console.log(this.file)
            // },

            submit(params) {
                // event.preventDefault()
                let formData = new FormData()
                formData.append("file", params.file)
                axios.post('http://localhost:8083/upload/singlefile', formData)
                .then(function (response) {
                // alert(response.data)
                alert("上传成功!")
                console.log(response)
                })
                .catch(function (error) {
                alert("上传失败")
                console.log(error)
                })
            }

            // downloadSDK() {
            //     this.socket.send(
            //     '{"downloadSDK_user":"true"}')
            //     this.socket.onmessage = (evt) => {
            //     var str = evt.data
            //     }
            //     // 下载 SDK
            // }
        },

        mounted() {
             if (typeof WebSocket == "undefined"){
                console.log("不支持webSocket")
            } else {
                console.log("支持webSocket")
                var socketUrl ="http://localhost:8083/"+Math.floor(Math.random() * 10000)
                socketUrl = socketUrl.replace("https", "ws").replace("http", "ws")
                console.log(socketUrl)
                if (this.socket != null) {
                    this.socket.close()
                    this.socket = null
                }
                this.socket = new WebSocket(socketUrl)

                this.socket.onopen = function() {
                    console.log("websocket 打开")
                }
                this.socket.onmessage = function(msg) {
                }
                this.socket.onclose = function() {
                    console.log("关闭")
                }
                this.socket.onerror = function() {
                    console.log("错误")
                }
                //   this.socket.send(
                // '{"get_game_develop":"true"}')
                // this.socket.onmessage = (evt) => {
                // var str = evt.data
                // var obj = JSON.parse(str)
                // this.tableData = obj
                // }
            }

            document.querySelector('body').setAttribute('style', 'background-color:rgb(55, 55, 55)')
        },

        beforeDestroy() {
            document.querySelector('body').removeAttribute('style')
        }
    }


</script>

<style scoped>
    #developer {
        width: 1600px;
        margin: auto;
    }

    h1 {
        text-align: center;
        color: azure;
    }

    #gl {
        font-family: Georgia, 'Times New Roman', Times, serif;
    }

    #tb {
        background-color: rgba(55, 55, 55, 0.3) !important;
    }

    #ag {
        float: right;
    }

    #sdk, #getgame {
        float: right;
        margin-right: 10px;
    }

    .el-form-item__label {
        font-size: 16px;
    }

</style>

<style>
    #gl .el-table td,
    #gl .el-table th,
    #gl .el-table tr {
        text-align: center !important;
        color: aqua !important;
        font-size: 18px !important;
        background-color: transparent !important;
    }

    #fm .el-dialog, #edit .el-dialog {
        width: 800px !important;
    }

</style>
