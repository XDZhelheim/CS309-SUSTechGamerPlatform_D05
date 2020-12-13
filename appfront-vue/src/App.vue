<template>
    <div id="app">
        <nav>
            <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
            router>
                <el-menu-item index="/">首页</el-menu-item>
                <el-menu-item index="/shop">游戏商城</el-menu-item>
                <el-menu-item @click="myGamesVisible=true">我的游戏</el-menu-item>
                <!-- <el-menu-item index="/community">游戏社区</el-menu-item>
                <el-menu-item index="/customer-service">客服反馈</el-menu-item> -->
                <el-menu-item v-if="loginStatus==false" @click="loginFormVisible = true" class="menu-right">登录</el-menu-item>
                <el-menu-item v-else @click="infoVisible = true" class="menu-right"><el-avatar :size="40" :src="userInfo.avatarURL"></el-avatar></el-menu-item>
                <el-menu-item index="/developer" class="menu-right">开发者</el-menu-item>
                <el-menu-item index="/hello" class="menu-right">欢迎</el-menu-item>
            </el-menu>
        </nav>
        <div id="placehold"></div>
        <router-view />

        <div id="login">
            <el-dialog title="登录" :visible.sync="loginFormVisible" top="30vh" :show-close="false" :lock-scroll="false">
                <el-form :label-position="labelPosition" label-width="100px" size="medium">
                    <el-form-item label="用户名">
                        <el-input v-model="loginForm.username" style="width:var(--itemLength)" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"></el-input>
                    </el-form-item>

                    <el-form-item label="密码">
                        <el-input show-password v-model="loginForm.password" style="width:var(--itemLength)" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button plain type="primary" style="float: left" @click="loginFormVisible = false; signinFormVisible=true; clear();">注册</el-button>
                    <el-button plain type="warning" @click="loginFormVisible = false; clear();">取消</el-button>
                    <el-button plain type="primary" @click="loginFormVisible = false; login();">登录</el-button>
                </div>
            </el-dialog>
        </div>

        <div id="signin">
            <el-dialog title="注册" :visible.sync="signinFormVisible" top="30vh" :show-close="false" :lock-scroll="false">
                <el-form :label-position="labelPosition" label-width="100px" size="medium">
                    <el-form-item label="用户名">
                        <el-input v-model="name" style="width:var(--itemLength)" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"></el-input>
                    </el-form-item>

                    <el-form-item label="密码">
                        <el-input show-password v-model="password" style="width:var(--itemLength)" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"></el-input>
                    </el-form-item>

                    <el-form-item label="确认密码">
                        <el-input show-password v-model="confirmPassword" style="width:var(--itemLength)" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button plain type="warning" @click="signinFormVisible = false; clear();">取消</el-button>
                    <el-button plain type="primary" @click="signinFormVisible = false;">注册</el-button>
                    <el-button plain type="primary" style="float: left" @click="loginFormVisible= true; signinFormVisible=false; clear();">登录</el-button>
                </div>
            </el-dialog>
        </div>

        <div id="info">
            <el-dialog title="个人信息" :visible.sync="infoVisible" top="30vh" :lock-scroll="false">
                <el-row>
                    <el-col :span="2"><p></p></el-col>

                    <el-col :span="8">
                        <el-row type="flex" justify="center">
                            <el-avatar :size="150" :src="userInfo.avatarURL"></el-avatar>
                        </el-row>
                        <el-row type="flex" justify="center">
                            <el-upload
                                class="avatar-uploader"
                                action="uploadAvatarAdd"
                                :show-file-list="false"
                                :on-success="handleAvatarSuccess"
                                :before-upload="beforeAvatarUpload">
                                <p id="p-upload">上传头像</p>
                            </el-upload>
                        </el-row>
                    </el-col>

                    <el-col :span="2"><p></p></el-col>

                    <el-col :span="12">
                        <el-row>
                            <p class="p-username">用户名:</p>
                            <p class="p-username">{{userInfo.username}}</p>
                        </el-row>
                        <el-row>
                            <p class="p-username">账户余额:</p>
                            <p class="p-username">{{userInfo.money}}</p>
                        </el-row>
                    </el-col>
                </el-row>
            </el-dialog>
        </div>

        <el-drawer
        title="我的游戏"
        :visible.sync="myGamesVisible"
        direction="ltr"
        size="20%">
            <p v-for="game in myGames" :key="game" class="p-mygames">
                <el-link :underline="false" @click="gotoURL(game.gameURL)">{{game.gametitle}}</el-link>
            </p>
        </el-drawer>

    </div>
</template>

<script>
export default {
    name: 'app',
    data() {
        return {
            activeIndex: '/',
            loginFormVisible: false,
            signinFormVisible: false,
            infoVisible: false,
            myGamesVisible: false,
            labelPosition: 'right',
            name: null,
            password: null,
            confirmPassword: null,
            loginForm: {
                username: '',
                password: ''
            },
            loginStatus: true, // 这里是调试 暂时赋值
            userInfo: {
                avatarURL: require("./assets/avatars/testavatar.jpg"),
                username: "Test User Name",
                money: 123
            },
            uploadAvatarAdd: "#", // 后端处理上传头像的地址
            myGames: [
                {
                    gametitle: "The Witcher: Wild Hunt",
                    gameURL: "/GamePage"
                },
                {
                    gametitle: "原神",
                    gameURL: "/shop"
                }
            ]
        }
    },

    methods: {
        clear() {
            this.name=null
            this.password=null
            this.confirmPassword=null
        },

        handleAvatarSuccess(res, file) {
            this.imageUrl = URL.createObjectURL(file.raw)
        },

        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg'
            const isLt2M = file.size / 1024 / 1024 < 2

            if (!isJPG) {
            this.$message.error('上传头像图片只能是 JPG 格式!')
            }
            if (!isLt2M) {
            this.$message.error('上传头像图片大小不能超过 2MB!')
            }
            return isJPG && isLt2M
        },

        gotoURL(url) {
            this.$router.push(url)
            this.myGamesVisible=false
        }

    }

}
</script>

<style scoped>
    nav {
        position: fixed;
        top: 0%;
        left: 0%;
        width: 100%;
        z-index: 999;
    }

    .el-menu-item.menu-right {
        float: right;
    }

    #placehold {
        height: 61px;
        margin: 0;
        padding: 0;
    }

    .p-username {
        color: aqua;
        font-size: 1.2em;
    }

    #p-upload {
        color: aqua;
    }

    .p-mygames {
        margin-left: 10%;
        font-size: 1.2em; /* 行间距 */
    }

    .el-link {
        font-size: 16px;
        color: aqua;
    }

</style>

<style>
    #app {
        font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
    }

    :root {
        --itemLength: 90%;
    }

    /* .el-button--primary.is-plain,
    .el-button--default.is-plain {
        background-color: transparent !important;
    } */

    .el-button {
        background-color: transparent !important;
    }

    .el-input--medium .el-input__inner, .el-input--medium .el-textarea__inner, .el-input--medium .el-input__count {
        background-color: transparent !important;
        border-color: aqua !important;
        color: aqua !important;
    }

    .el-dialog {
        background: rgb(55, 55, 55) !important;
        background-size: cover !important;
        border-radius: 20px !important;
        width: 500px !important;
        border: solid thin lightgoldenrodyellow;
    }

    #info .el-dialog {
        height: 300px !important;
    }

    .el-dialog__title {
        color: white !important;
    }

    .el-form-item__label {
        font-size: 16px !important;
        color: white !important;
    }

    .el-tabs__item {
        color:white !important;
    }

    .el-drawer__header {
        font-size: 20px !important;
        color: aqua !important;
    }

    .el-drawer.ltr {
        background-color: rgb(80, 80, 80) !important;
    }

</style>
