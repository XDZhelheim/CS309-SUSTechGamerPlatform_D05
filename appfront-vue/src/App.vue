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
                <el-menu-item index="/my-games">我的游戏</el-menu-item>
                <el-menu-item index="/community">游戏社区</el-menu-item>
                <el-menu-item index="/customer-service">客服反馈</el-menu-item>
                <el-menu-item @click="loginFormVisible = true" class="menu-right">登录</el-menu-item>
                <el-menu-item index="/developer" class="menu-right">开发者</el-menu-item>
                <el-menu-item index="/hello" class="menu-right">欢迎</el-menu-item>
            </el-menu>
        </nav>
        <router-view />

        <div id="login">
            <el-dialog title="登录" :visible.sync="loginFormVisible" top="30vh" :show-close="false" :lock-scroll="false">
                <el-form :label-position="labelPosition" label-width="100px" size="medium">
                    <el-form-item label="用户名">
                        <el-input v-model="name" style="width:var(--itemLength)" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"></el-input>
                    </el-form-item>

                    <el-form-item label="密码">
                        <el-input show-password v-model="password" style="width:var(--itemLength)" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"></el-input>
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
            labelPosition: 'right',
            name: null,
            password: null,
            confirmPassword: null,
            loginStatus: false
        }
    },

    methods: {
        clear() {
            this.name=null
            this.password=null
            this.confirmPassword=null
        },

        login() {
            this.loginStatus=true
        },

        register() {

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

    .el-input--medium .el-input__inner {
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
</style>
