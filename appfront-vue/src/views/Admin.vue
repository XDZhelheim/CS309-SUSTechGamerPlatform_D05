<template>
    <div id="Admin">
        <div id="gl">
            <el-button id="ag" plain type="primary" icon="el-icon-plus" @click="addGameFormVisible = true">添加用户</el-button>
            
            <h1>管理员界面</h1>
            <el-table border :data="tableData" borderstyle="width: 100%" id="tb">
                <el-table-column prop="userID" label="用户ID"></el-table-column>
                <el-table-column prop="name" label="用户名称"></el-table-column>
                <el-table-column prop="identity" label="用户权限"></el-table-column>
                <el-table-column prop="password" label="用户密码"></el-table-column>
                <el-table-column prop="phone" label="手机号"></el-table-column>
                <el-table-column prop="mail" label="邮箱"></el-table-column>
                
                <el-table-column prop="op" label="编辑/删除" width="180">
                    <template slot-scope="scope">
                        <el-button icon="el-icon-edit-outline" plain type="primary" @click="editGameFormVisible = true; editGame(scope.$index);"></el-button>
                        <el-button icon="el-icon-delete" plain type="warning" @click="delGame(scope.$index)"></el-button>
                    </template>
                
                </el-table-column>
            </el-table>
        </div>

        <div id="fm">
            <el-dialog title="添加用户" :visible.sync="addGameFormVisible" :show-close="false" :lock-scroll="false">
                <el-form :label-position="labelPosition" label-width="100px" size="medium">
                    <el-form-item label="用户ID">
                        <el-input v-model="user.userID" style="width:var(--itemLength)"></el-input>
                    </el-form-item>

                    <el-form-item label="用户名称">
                        <el-input v-model="user.name" style="width:var(--itemLength)"></el-input>
                    </el-form-item>

                    <el-form-item label="用户权限">
                       <el-select v-model="user.identity" style="width:var(--itemLength)" placeholder="选择权限">
                            <el-option v-for="item in Identity" :key="item.value" :label="item.label" :value="item.value"></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="用户密码">
                        <el-input v-model="user.password" style="width:var(--itemLength)"></el-input>
                    </el-form-item>

                    <el-form-item label="手机号">
                        <el-input v-model="user.phone" style="width:var(--itemLength)"></el-input>
                    </el-form-item>

                    <el-form-item label="邮箱">
                        <el-input v-model="user.mail" style="width:var(--itemLength)"></el-input>
                    </el-form-item>
                </el-form>

                <div slot="footer" class="dialog-footer">
                    <el-button @click="addGameFormVisible = false; clear();">取消</el-button>
                    <el-button type="primary" @click="addGame()">确定</el-button>
                </div>
            </el-dialog>
        </div>

        <div id="edit">
            <el-dialog title="编辑用户" :visible.sync="editGameFormVisible" :show-close="false" :lock-scroll="false">
                <el-form :label-position="labelPosition" label-width="100px" size="medium">
                    <el-form-item label="用户ID">
                        <el-input v-model="user.userID" style="width:var(--itemLength)"></el-input>
                    </el-form-item>

                    <el-form-item label="用户名称">
                        <el-input v-model="user.name" style="width:var(--itemLength)"></el-input>
                    </el-form-item>

                    <el-form-item label="用户权限">
                       <el-select v-model="user.identity" style="width:var(--itemLength)" placeholder="选择权限">
                            <el-option v-for="item in Identity" :key="item.value" :label="item.label" :value="item.value"></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="用户密码">
                        <el-input v-model="user.password" style="width:var(--itemLength)"></el-input>
                    </el-form-item>

                    <el-form-item label="手机号">
                        <el-input v-model="user.phone" style="width:var(--itemLength)"></el-input>
                    </el-form-item>

                    <el-form-item label="邮箱">
                        <el-input v-model="user.mail" style="width:var(--itemLength)"></el-input>
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
        name: 'Admin',
        data() {
            return {
                tableData: [{
                    userID: "10000000",
                    name: "董正",
                    Identity: "管理员",
                    password: "542b542b",
                    phone: "18054285428",
                    mail: "2b.mail.com",
                    
                }],

                user: {
                    userID: null,
                    name: null,
                    identity: null,
                    password: null,
                    phone: null,
                    mail: null,                   
                },

                addGameFormVisible: false,
                labelPosition: 'right',
                editGameFormVisible: false,
                editIndex: 0,
                tempGame: null,
                uploadGameAdd: '#', // 上传游戏的地址


                Identity: [
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
            delGame(index) {
                this.tableData.splice(index, 1)
            },

            addGame() {
                this.tableData.push(this.user)
                this.addGameFormVisible = false
                this.clear()
            },

            clear() {
                this.user = {
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

            downloadSDK() {
                // 下载 SDK
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
        float: right;
    }

    #sdk {
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
