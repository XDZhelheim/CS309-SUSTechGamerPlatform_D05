<template>
    <div id="home">
        <header>
            <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b">
            <el-menu-item index="1">游戏商城</el-menu-item>
            <el-menu-item index="2">我的游戏</el-menu-item>
            <el-menu-item index="3">游戏社区</el-menu-item>
            <el-menu-item index="4">客服反馈</el-menu-item>
            <el-menu-item index="6" class="menu-right">登录</el-menu-item>
            <el-menu-item index="5" class="menu-right">开发者</el-menu-item>
            </el-menu>
        </header>

        <div id="placehold"></div>

        <div id="recent-and-poster">
            <el-row :gutter="20" type="flex" align="top">
                <el-col id="recent" span="6">
                    <el-row>
                        <el-image :src="recentgames[0]">
                            <div slot="placeholder" class="image-slot">
                                LOADING<span class="dot">...</span>
                            </div>
                            <div slot="error" class="image-slot">
                                <i class="el-icon-picture-outline"></i>
                            </div>
                        </el-image>
                    </el-row>
                    <el-row>
                        <el-image :src="recentgames[0]">
                            <div slot="placeholder" class="image-slot">
                                LOADING<span class="dot">...</span>
                            </div>
                            <div slot="error" class="image-slot">
                                <i class="el-icon-picture-outline"></i>
                            </div>
                        </el-image>
                    </el-row>
                    <el-row>
                        <el-image :src="recentgames[0]">
                            <div slot="placeholder" class="image-slot">
                                LOADING<span class="dot">...</span>
                            </div>
                            <div slot="error" class="image-slot">
                                <i class="el-icon-picture-outline"></i>
                            </div>
                        </el-image>
                    </el-row>
                </el-col>

                <el-col id="poster" span="18">
                    <el-carousel height="375px" indicator-position="outside">
                        <el-carousel-item v-for="poster in posters" :key="poster">
                            <el-image :src="poster">
                                <div slot="placeholder" class="image-slot">
                                    LOADING<span class="dot">...</span>
                                </div>
                                <div slot="error" class="image-slot">
                                    <i class="el-icon-picture-outline"></i>
                                </div>
                            </el-image>
                        </el-carousel-item>
                    </el-carousel>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'Home',
        data() {
            return {
                activeIndex: '1',
                recentgames: [require('../assets/recentgames/test.jpg')],
                posters: [require('../assets/recentgames/test.jpg'), require('../assets/recentgames/test.jpg'), require('../assets/recentgames/test.jpg')]
            }
        },
        methods: {

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
    header {
        position: fixed;
        top: 0%;
        left: 0%;
        width: 100%;
    }

    .el-row--flex.is-justify-end {
        height: 61px;
    }

    .el-menu-item.menu-right {
        float: right;
    }

    #recent-and-poster {
        margin: 5px;
    }

    #placehold {
        height: 61px;
        margin: 0;
        padding: 0;
    }

    /* .el-row, .el-col {
        border: solid thin white;
    } */

</style>
