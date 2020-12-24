<template>
    <div id="allgames">
        <div id="allgamelist">
            <el-button id="getallgames" plain type="primary" icon="el-icon-refresh" @click="getAllGames()">刷新列表</el-button>
            <el-button id="getallgames" plain type="primary" icon="el-icon-refresh" @click="tt()">tt</el-button>
            <h1>全部游戏</h1>
            <el-table border :data="tableData" borderstyle="width: 100%" id="tb">
                <el-table-column prop="title" label="名称" width="300">
                    <template v-slot="temp">
                        <el-link @click="gotoURL(temp.row.url)" id="gametitle">{{temp.row.title}}</el-link>
                    </template>
                </el-table-column>
                <el-table-column prop="date" label="发行日期" width="200"></el-table-column>
                <el-table-column prop="price" label="价格" width="100"></el-table-column>
                <el-table-column prop="type" label="类型" width="100"></el-table-column>
                <el-table-column prop="publisher" label="开发商" width="200"></el-table-column>
                <el-table-column prop="language" label="语言" width="100"></el-table-column>
                <el-table-column prop="rate" label="评分" width="70"></el-table-column>
                <el-table-column prop="abstract" label="简介"></el-table-column>
                <el-table-column label="获取游戏" width="120">
                    <template v-slot="temp">
                        <el-button v-if="temp.row.userhave=='false'" type="primary" icon="el-icon-shopping-cart-2" @click="buy(temp.row)">购买</el-button>
                        <a v-else href="http://localhost:8083/game/witcher" target="_blank"><el-button type="primary" icon="el-icon-download">下载</el-button></a>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script>
    import app from "../App"
    // 这个界面要和 developer 那边更新同步
    export default {
        name: "AllGames",

        data() {
            return {
                tableData: [
                    {
                        title: "The Witcher: Wild Hunt",
                        date: "2015 年 5 月 19 日",
                        price: 47,
                        type: "RPG",
                        publisher: "CD Projekt RED",
                        language: "English",
                        rate: 4.8,
                        abstract: "该作承接《巫师2：国王刺客》的剧情，那些想要利用杰洛特的人已经不在了。杰洛特寻求改变自己的生活，着手于新的个人使命，而世界的秩序也在悄然改变。2015年10月，获第33届金摇杆奖最佳剧情、最佳视觉设计、最佳游戏时刻，更获得了年度最佳游戏大奖。并获得IGN 2015年度最佳游戏。2016年其DLC“血与酒”获得了The Game Awards2016年年度“最佳游戏角色扮演游戏”奖。",
                        userhave: 'false', // 这里要从后端知道当前 user 有没有这个游戏
                        url: "/witcher3"
                    },
                ],
                rate: 124, // 用户打分

            }
        },

        methods: {
            buy(game) {
                if (!this.$root.loginStatus) {
                    this.$message.error("请登录")
                    return
                }
                if (game.price>this.$root.userInfo.price) {
                    this.$message.error("余额不足")
                    return
                }
                game.userhave=true
                this.$root.userInfo.money-=game.price
                alert(this.$root.userInfo.money)
                // 然后购买, 记得更新余额
            },
            tt(){
                alert(this.tableData[0].userhave)
            },

            getAllGames() {
                this.socket.send(
                '{"get_game_allGame":"true","name":"' +
                this.$root.userInfo.username +
                '"}')

                this.socket.onmessage = (evt) => {
                var str = evt.data
                var obj = JSON.parse(str)
                this.tableData = obj
                }
                // var str = "[{\"title\":\"123\",\"date\":\"2015 年 5 月 19 日\",\"price\":\"130.0\",\"type\":\"FPS\",\"publisher\":\"313e\",\"language\":\"中文 (繁体)\",\"rate\":\"4.8\",\"abstract\":\"wedf\",\"userhave\":\"falseurl:null},{\"title\":\"The Witcher: Wild Hunt\",\"date\":\"2020-12-24\",\"price\":47.0,\"type\":\"RPG\",\"publisher\":\"Rubbish publisher\",\"language\":\"English\",\"rate\":\"4.8\",\"abstract\":\"该作承接《巫师2：国王刺客》的剧情，那些想要利用杰洛特的人已经不在了。杰洛特寻求改变自己的生活，着手于新的个人使命，而世界的秩序也在悄然改变。2015年10月，获第33届金摇杆奖最佳剧情、最佳视觉设计、最佳游戏时刻，更获得了年度最佳游戏大奖。并获得IGN 2015年度最佳游戏。2016年其DLC“血与酒”获得了The Game Awards2016年年度“最佳游戏角色扮演游戏”奖。\",\"userhave\":\"falseurl: \"/witcher3\"}]"
                // var obj = JSON.parse(str)
                // this.tableData = obj
            },

            gotoURL(url) {
                this.$router.push(url)
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
    #allgames {
        width: 1600px;
        margin: auto;
    }

    h1 {
        text-align: center;
        color: azure;
    }

    #allgamelist {
        font-family: Georgia, 'Times New Roman', Times, serif;
    }

    #tb {
        background-color: rgba(55, 55, 55, 0.3) !important;
    }

    .el-form-item__label {
        font-size: 16px;
    }

    #gametitle {
        font-size: 18px !important;
    }

    #getallgames {
        float: right;
    }
</style>

<style>
    #allgamelist .el-table td,
    #allgamelist .el-table th,
    #allgamelist .el-table tr {
        text-align: center !important;
        color: aqua !important;
        font-size: 18px !important;
        background-color: transparent !important;
    }
</style>
