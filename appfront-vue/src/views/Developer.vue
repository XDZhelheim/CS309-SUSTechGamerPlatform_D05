<template>
    <div id="developer">
        <div id="gl">
            <h1>管理游戏</h1>
            <el-table border :data="tableData" borderstyle="width: 100%" id="tb">
                <el-table-column prop="title" label="名称"></el-table-column>
                <el-table-column prop="date" label="发行日期"></el-table-column>
                <el-table-column prop="price" label="价格"></el-table-column>
                <el-table-column prop="type" label="类型"></el-table-column>
                <el-table-column prop="publisher" label="发行商"></el-table-column>
                <el-table-column prop="language" label="语言"></el-table-column>
                <el-table-column prop="abstract" label="简介"></el-table-column>
                <el-table-column prop="op" label="编辑/删除" width="180">
                    <template slot-scope="scope">
                        <el-button icon="el-icon-edit-outline" plain type="primary" @click="editGameFormVisible = true; editGame(scope.$index);"></el-button>
                        <el-button icon="el-icon-delete" plain type="warning" @click="delGame(scope.$index)"></el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>

        <div id="ag">
            <el-button plain type="primary" icon="el-icon-plus" @click="addGameFormVisible = true">添加新游戏</el-button>
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
                    <el-form-item label="名称">
                        <el-input v-model="tableData[editIndex].title" style="width:var(--itemLength)"></el-input>
                    </el-form-item>

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
                </el-form>

                <div slot="footer" class="dialog-footer">
                    <el-button @click="editGameFormVisible = false; cancelEdit();">取消</el-button>
                    <el-button type="primary" @click="editGameFormVisible = false">确定</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'community',
        data() {
            return {
                tableData: [{
                    title: "原神",
                    date: "2020-09-15",
                    price: 666.66,
                    type: "RPG",
                    publisher: "MiHoYo",
                    language: "中文 (简体)",
                    abstract: "sb游戏"
                }],

                newGame: {
                    title: null,
                    date: null,
                    price: null,
                    type: null,
                    publisher: null,
                    language: null,
                    abstract: null
                },

                addGameFormVisible: false,
                labelPosition: 'right',
                editGameFormVisible: false,
                editIndex: 0,
                tempGame: null,

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
                this.tableData.splice(index, 1)
            },

            addGame() {
                if (this.check()) {
                    this.tableData.push(this.newGame)
                    this.addGameFormVisible = false
                    this.clear()
                } else
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
                    abstract: null
                }
            },

            editGame(index) {
                this.editIndex=index
                this.tempGame=this.clone(this.tableData[index])
            },

            cancelEdit() {
                this.tableData.splice(this.editIndex, 1, this.tempGame)
            },

            clone(object) {
                return JSON.parse(JSON.stringify(object))
            },

            check() {
                // for (let key in this.newGame)
                //     if (this.newGame[key] == null)
                //         return false
                // for (let i = 0; i < this.tableData.length; i++) {
                //     if (this.tableData[i].title == this.newGame.title && this.tableData[i].movieHall ==
                //         this.newGame.movieHall && this.tableData[i].type != this.newGame.type)
                //         return false
                // }
                return true
            },

            errmsg() {
                this.$message.error('Invalid input! Please check your input.')
            }
        },

        mounted() {
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
        margin-top: 2.5em;
        text-align: center;
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
