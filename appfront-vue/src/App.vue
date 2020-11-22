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
                <el-menu-item index="/login" class="menu-right">登录</el-menu-item>
                <el-menu-item index="/developer" class="menu-right">开发者</el-menu-item>
                <el-menu-item index="/hello" class="menu-right">欢迎</el-menu-item>
            </el-menu>
        </nav>
        <router-view />
    </div>
</template>

<script>
export default {
    name: 'app',
    data() {
        return {
            activeIndex: '/'
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
</style>
