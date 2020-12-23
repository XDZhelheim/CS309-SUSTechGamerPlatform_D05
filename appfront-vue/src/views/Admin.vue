<template>
    <div id="admin">
        <div id="ul">
            <el-button id="au" plain type="primary" icon="el-icon-plus" @click="addUserFormVisible = true">添加用户</el-button>
            <el-button id="sdk" plain type="primary" icon="el-icon-download" @click="getUser()">getUser</el-button>

            <h1>管理用户</h1>
            <el-table border :data="tableData" borderstyle="width: 100%" id="tb">
                <el-table-column prop="username" label="用户名称"></el-table-column>
                <el-table-column prop="password" label="用户密码"></el-table-column>
                <el-table-column prop="usertype" label="用户类型"></el-table-column>
                <el-table-column prop="createDate" label="创建日期"></el-table-column>
                <el-table-column prop="mail" label="邮箱"></el-table-column>
                <el-table-column prop="money" label="账户余额"></el-table-column>

                <el-table-column prop="op" label="编辑/删除" width="180">
                    <template slot-scope="scope">
                        <el-button icon="el-icon-edit-outline" plain type="primary" @click="editUserFormVisible = true; editUser(scope.$index);"></el-button>
                        <el-button icon="el-icon-delete" plain type="warning" @click="delUser(scope.$index)"></el-button>
                    </template>

                </el-table-column>
            </el-table>
        </div>

        <div id="fm">
            <el-dialog title="添加用户" :visible.sync="addUserFormVisible" :show-close="false" :lock-scroll="false">
                <el-form :label-position="labelPosition" label-width="100px" size="medium">
                    <el-form-item label="用户名称">
                        <el-input v-model="newUser.username" style="width:var(--itemLength)"></el-input>
                    </el-form-item>

                    <el-form-item label="用户密码">
                        <el-input v-model="newUser.password" style="width:var(--itemLength)"></el-input>
                    </el-form-item>

                    <el-form-item label="用户类型">
                       <el-select v-model="newUser.usertype" style="width:var(--itemLength)" placeholder="选择类型">
                            <el-option v-for="item in userTypes" :key="item.value" :label="item.label" :value="item.value"></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="创建日期">
                        <el-date-picker v-model="newUser.createDate" style="width:var(--itemLength)" type="date"
                            placeholder="选择日期" value-format="yyyy-MM-dd">
                        </el-date-picker>
                    </el-form-item>

                    <el-form-item label="邮箱">
                        <el-input v-model="newUser.mail" style="width:var(--itemLength)"></el-input>
                    </el-form-item>

                    <el-form-item label="余额">
                        <el-input-number v-model="newUser.money" style="width:var(--itemLength)" :min="0"
                            :precision="2" :step="1" :controls="false">
                        </el-input-number>
                    </el-form-item>
                </el-form>

                <div slot="footer" class="dialog-footer">
                    <el-button @click="addUserFormVisible = false; clear();">取消</el-button>
                    <el-button type="primary" @click="addUser()">确定</el-button>
                </div>
            </el-dialog>
        </div>

        <div id="edit">
            <el-dialog title="编辑用户" :visible.sync="editUserFormVisible" :show-close="false" :lock-scroll="false">
                <el-form :label-position="labelPosition" label-width="100px" size="medium">
                    <el-form-item label="用户名称">
                        <el-input v-model="tableData[editIndex].username" style="width:var(--itemLength)"></el-input>
                    </el-form-item>

                    <el-form-item label="用户密码">
                        <el-input v-model="tableData[editIndex].password" style="width:var(--itemLength)"></el-input>
                    </el-form-item>

                    <el-form-item label="用户类型">
                       <el-select v-model="tableData[editIndex].usertype" style="width:var(--itemLength)" placeholder="选择类型">
                            <el-option v-for="item in userTypes" :key="item.value" :label="item.label" :value="item.value"></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="创建日期">
                        <el-date-picker v-model="tableData[editIndex].createDate" style="width:var(--itemLength)" type="date"
                            placeholder="选择日期" value-format="yyyy-MM-dd">
                        </el-date-picker>
                    </el-form-item>

                    <el-form-item label="邮箱">
                        <el-input v-model="tableData[editIndex].mail" style="width:var(--itemLength)"></el-input>
                    </el-form-item>

                    <el-form-item label="余额">
                        <el-input-number v-model="tableData[editIndex].money" style="width:var(--itemLength)" :min="0"
                            :precision="2" :step="1" :controls="false">
                        </el-input-number>
                    </el-form-item>
                </el-form>

                <div slot="footer" class="dialog-footer">
                    <el-button @click="editUserFormVisible = false; cancelEdit();">取消</el-button>
                    <el-button type="primary" @click="checkEdit()">确定</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'Admin',
        data() {
            return {
                tableData: [{
                    username: "test user",
                    password: "123456",
                    usertype: "用户",
                    createDate: "2020-12-19",
                    mail: "xxx@mail.com",
                    money: 0,
                    AddDe_user: "Add"
                }],

                newUser: {
                    username: null,
                    password: null,
                    usertype: null,
                    createDate: null,
                    mail: null,
                    money: null,
                    AddDe_user:"Add"
                },

                addUserFormVisible: false,
                labelPosition: 'right',
                editUserFormVisible: false,
                editIndex: 0,
                tempUser: null,

                userTypes: [
                    {
                        value: "用户",
                        label: "用户"
                    },
                    {
                        value: "开发者",
                        label: "开发者"
                    },
                    {
                        value: "管理员",
                        label: "管理员"
                    }
                ]
            }
        },

        methods: {
            delUser(index) {
                this.tableData[index].AddDe_user = "Delete"
                var sendMsg = JSON.stringify(this.tableData[index])
                this.socket.send(sendMsg)
                this.socket.onmessage = (evt) => {
                    alert(evt.data)
                }
                this.tableData.splice(index, 1)
            },

            addUser() {
                if (this.check()) {
                    this.newUser.AddDe_user = "Add"
                    var sendMsg = JSON.stringify(this.newUser)
                    this.socket.send(sendMsg)
                    this.socket.onmessage = (evt) => {
                        alert(evt.data)
                    }
                    this.tableData.push(this.newUser)
                    this.addUserFormVisible = false
                    this.clear()
                }
               
                else
                    this.errmsg()
            },

            clear() {
                this.newUser = {
                    username: null,
                    password: null,
                    usertype: null,
                    createDate: null,
                    mail: null,
                    money: null
                }
            },

            editUser(index) {
                this.editIndex=index
                this.tempUser=this.clone(this.tableData[index])
            },
   

            check() {
                for (let key in this.newUser)
                    if (this.newUser[key] == null)
                        return false
                return true
            },

            checkEdit() {
                for (let i = 0; i < this.tableData.length; i++)
                    for (let key in this.tableData[i])
                        if (this.tableData[i][key]==null || this.tableData[i][key]=='') {
                            this.errmsg()
                            return
                        }
                this.changeUser()
                this.editUserFormVisible=false
            },

            errmsg() {
                this.$message.error('无效输入')
            },

            changeUser(){
                var ind = this.editIndex
                this.tableData[ind].AddDe_user = "Change"
                var sendMsg = JSON.stringify(this.tableData[ind])
                this.socket.send(sendMsg)
                this.socket.onmessage = function(msg){
                    alert(msg.data)
                }
            },

            getUser() {
                 this.socket.send(
                '{"get_user_develop":"true"}')
                this.socket.onmessage = (evt) => {
                var str = evt.data
                var obj = JSON.parse(str)
                this.tableData = obj
                }
            },

            cancelEdit() {
                this.tableData.splice(this.editIndex, 1, this.tempUser)
            },

            clone(object) {
                return JSON.parse(JSON.stringify(object))
            }

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
            }
            document.querySelector('body').setAttribute('style', 'background-color:rgb(55, 55, 55)')
        },

        beforeDestroy() {
            document.querySelector('body').removeAttribute('style')
        }
    }


</script>

<style scoped>
    #admin {
        width: 1600px;
        margin: auto;
    }

    h1 {
        text-align: center;
        color: azure;
    }

    #ul {
        font-family: Georgia, 'Times New Roman', Times, serif;
    }

    #tb {
        background-color: rgba(55, 55, 55, 0.3) !important;
    }

    #au {
        float: right;
    }

    .el-form-item__label {
        font-size: 16px;
    }

</style>

<style>
    #ul .el-table td,
    #ul .el-table th,
    #ul .el-table tr {
        text-align: center !important;
        color: aqua !important;
        font-size: 18px !important;
        background-color: transparent !important;
    }

    #fm .el-dialog, #edit .el-dialog {
        width: 800px !important;
    }

</style>
