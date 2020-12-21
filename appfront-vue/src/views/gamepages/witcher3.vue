<template>
    <div id="gamepage">
        <div id="pic-video">
            <el-carousel :height="bannerHeight+'px'" type="card" indicator-position="none" :autoplay="false" arrow="always">
                <el-carousel-item v-for="pic in gamepics" :key="pic">
                    <img ref="bannerHeight" :src="pic" alt="" @load="imgLoad" style="width: 100%">
                </el-carousel-item>
                <el-carousel-item v-for="vid in gamevideos" :key="vid">
                    <video ref="bannerHeight" :src="vid" controls="controls"></video>
                </el-carousel-item>
            </el-carousel>
        </div>

        <el-divider></el-divider>

        <div id="gameinfo">
            <el-row :gutter="10">
                <el-col span="14">
                    <el-image :src="gamepics[2]">
                        <div slot="placeholder" class="image-slot">
                            LOADING<span class="dot">...</span>
                        </div>
                        <div slot="error" class="image-slot">
                            <i class="el-icon-picture-outline"></i>
                        </div>
                    </el-image>
                </el-col>
                <el-col span="2"><el-divider direction="vertical" id="divider1"></el-divider></el-col>
                <el-col span="8">
                    <p id="p-gametitle">{{gameinfo.title}}</p>
                    <p class="p-gameinfo">发行日期: {{gameinfo.date}}</p>
                    <p class="p-gameinfo">制作: {{gameinfo.publisher}}</p>
                    <p class="p-gameinfo">价格: {{gameinfo.price}} ￥</p>
                    <p id="p-abstract">{{gameinfo.abstract}}</p>
                    <p id="p-placehold"></p>
                    <el-button v-if="have==false" type="primary" icon="el-icon-shopping-cart-2" @click="buy()">购买</el-button>
                    <el-button v-else type="primary" icon="el-icon-download" @click="download()"><a href="http://localhost:8083/game/witcher" target="_blank">下载</a></el-button>
                    <el-rate v-if="have==false" id="rate"
                        v-model="gameinfo.rate"
                        show-score
                        disabled
                        text-color="#ff9900"
                        score-template="{value}">
                    </el-rate>
                    <el-rate v-else id="rate" @change="commitRate"
                        v-model="rate"
                        allow-half
                        text-color="#ff9900"
                        score-template="{value}">
                    </el-rate>
                </el-col>
            </el-row>
        </div>

        <el-divider></el-divider>

        <div id="comments">
            <h1>评论</h1>
            <div v-for="usercomment in comments" :key="usercomment">
                <el-row type="flex" align="middle">
                    <el-col span="4" offset="2">
                        <el-link @click="reply_reply(usercomment.username)">{{usercomment.username}}</el-link>
                    </el-col>
                    <el-col span="2"><el-divider direction="vertical" id="divider2"></el-divider></el-col>
                    <el-col>
                        <p>
                            {{usercomment.comment}}
                            <span id="up-down-vote">
                                <el-link @click="vote(usercomment, true)">赞</el-link>: {{usercomment.upVoteNum}}
                                <el-link @click="vote(usercomment, false)">踩</el-link>: {{usercomment.downVoteNum}}
                            </span>
                        </p>
                    </el-col>
                </el-row>

                <el-row type="flex" justify="end">
                    <el-divider id="divider3"></el-divider>
                </el-row>
            </div>

            <div v-if="have">
                <el-col offset="2" span="22">
                    <el-input v-model="currentusercomment" type="textarea" maxlength="200" :autosize="{minRows: 5}" show-word-limit></el-input>
                </el-col>
                <el-button id="commitcomment" type="primary" icon="el-icon-check" @click="commitComment()">提交评论</el-button>
            </div>
        </div>
    </div>
</template>

<script>
    import app from "../../App"
    export default {
        name: "gamepage",

        data() {
            return {
                bannerHeight: '',
                have: false, // 用户是不是已经买了，从后端获取
                gameinfo: {
                    title: "The Witcher: Wild Hunt",
                    date: "2015 年 5 月 19 日",
                    price: 47,
                    type: "RPG",
                    publisher: "CD Projekt RED",
                    language: "English",
                    rate: 4.8,
                    abstract: "该作承接《巫师2：国王刺客》的剧情，那些想要利用杰洛特的人已经不在了。杰洛特寻求改变自己的生活，着手于新的个人使命，而世界的秩序也在悄然改变。2015年10月，获第33届金摇杆奖最佳剧情、最佳视觉设计、最佳游戏时刻，更获得了年度最佳游戏大奖。并获得IGN 2015年度最佳游戏。2016年其DLC“血与酒”获得了The Game Awards2016年年度“最佳游戏角色扮演游戏”奖。"
                },

                rate: 0, // 用户打分
                currentusercomment: "", // 用户评论

                gamepics: [
                    require('../../assets/gamedetailpics/witcher3/1.jpg'),
                    require('../../assets/gamedetailpics/witcher3/2.jpg'),
                    require('../../assets/gamedetailpics/witcher3/3.jpg'),
                    require('../../assets/gamedetailpics/witcher3/4.jpg')
                ],

                gamevideos: [
                    require('../../assets/videos/witcher3.mp4'),
                ],

                comments: [
                    {
                        username: "Test User",
                        comment: "我觉得彳亍我觉得彳亍我觉得彳亍我觉得彳亍我觉得彳亍我觉得彳亍我觉得彳亍我觉得彳亍我觉得彳亍我觉得彳亍我觉得彳亍我觉得彳亍我觉得彳亍我觉得彳亍我觉得彳亍",
                        upVoteNum: 0,
                        downVoteNum: 0
                    },
                    {
                        username: "Test User 2",
                        comment: "我觉得8彳亍我觉得8彳亍我觉得8彳亍我觉得8彳亍我觉得8彳亍我觉得8彳亍我觉得8彳亍我觉得8彳亍我觉得8彳亍我觉得8彳亍我觉得8彳亍我觉得8彳亍我觉得8彳亍我觉得8彳亍我觉得8彳亍我觉得8彳亍我觉得8彳亍",
                        upVoteNum: 0,
                        downVoteNum: 0
                    }
                ]

            }
        },

        methods: {
            imgLoad () {
                this.$nextTick(() => {
                    this.bannerHeight = this.$refs.bannerHeight[0].height
                    console.log(this.$refs)
                })
            },

            buy() {
                if (!this.$root.loginStatus) {
                    this.$message.error("请登录")
                    return
                }
                if (this.gameinfo.price>this.$root.userInfo.money) {
                    this.$message.error("余额不足")
                    return
                }
                this.socket.send(
                '{"buy_game_gamepage":"true","name":"' +
                this.$root.userInfo.username +
                '","game_name":"' +
                this.gameinfo.title +
                '"}')
                this.socket.onmessage = (evt) => {
                    if (evt.data=="success"){
                        this.have = true
                    } else {
                        this.have = false
                    }
                }
                        this.have = true

                this.$root.userInfo.money-=this.gameinfo.price
                // 买游戏，扣钱
            },


            commitRate() {
                this.socket.send(
                '{"commit_rate":"true","name":"' +
                 this.$root.userInfo.username +
                '","game_name":"' +
                 this.gameinfo.title +
                 '","score":"' +
                 this.rate +
                '"}')
                this.socket.onmessage = (evt) => {
                }
                // 把 this.rate 提交到后端
            },

            reply_reply(username) {
                this.currentusercomment="回复 @"+username+":\n"+this.currentusercomment
            },

            commitComment() {
                this.socket.send(
                '{"commit_rate":"true","name":"' +
                 this.$root.userInfo.username +
                '","game_name":"' +
                 this.gameinfo.title +
                 '","comment":"' +
                 this.currentusercomment +
                '"}')
                this.socket.onmessage = (evt) => {
                }
                let newCommit={
                    username: this.$root.userInfo.username,
                    comment: this.currentusercomment
                }
                this.comments.push(newCommit)
            },

            vote(usercomment, control) {
                if (!this.$root.loginStatus) {
                    this.$message.error("请登录")
                    return
                }
                if (control)
                    usercomment.upVoteNum+=1
                else
                    usercomment.downVoteNum+=1
            },

        },

        get_comment(){
            this.socket.send(
                '{"get_comment":"true","name":"' +
                 this.userInfo.username +
                '","game_name":"' +
                 this.gameinfo.title +
                 '","comment":"' +
                 this.currentusercomment +
            '"}')
            
            this.socket.onmessage = (evt) => {
                var str = evt.data
                var obj = JSON.parse(str)
                this.tableData = obj
            }
        },

        get_rate(){
            this.socket.send(
                '{"get_rate":"true","name":"' +
                 this.userInfo.username +
                '","game_name":"' +
                 this.gameinfo.title +
                 '","comment":"' +
                 this.currentusercomment +
            '"}')
            
            this.socket.onmessage = (evt) => {
                var str = evt.data
                this.gameinfo.rate = str
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
            this.imgLoad()
            window.addEventListener('resize', () => {
                this.bannerHeight = this.$refs.bannerHeight[0].height
                this.imgLoad()
            }, false)
            this.rate=this.gameinfo.rate
        },

        beforeDestroy() {
            document.querySelector('body').removeAttribute('style')
        }
    }
</script>

<style scoped>
    #gamepage {
        width: 1200px;
        margin: auto;
        color: aqua;
    }

    video {
        width: 600px;
        height: 337.5px;
    }

    .el-divider--vertical#divider1 {
        height: 390px;
        margin-left: 40px;
    }

    .el-divider--vertical#divider2 {
        height: 30px;
    }

    .el-divider#divider3 {
        width: 1100px;
    }

    #p-gametitle {
        font-family: Georgia, 'Times New Roman', Times, serif;
        font-size: 2em;
        margin-top: 0;
        margin-bottom: 20px;
    }

    .p-gameinfo {
        font-size: 1.2em;
    }

    #p-abstract {
        font-size: 0.9em;
    }

    #p-placehold {
        margin-top: 30px;
    }

    #rate {
        float: right;
        margin-top: 10px;
    }

    #commitcomment {
        margin-top: 20px;
        float: right;
    }

    #up-down-vote {
        float: right;
    }

</style>

<style>
    .el-textarea__inner, .el-input__count {
        background-color: transparent !important;
        border-color: aqua !important;
        color: aqua !important;
    }
</style>
