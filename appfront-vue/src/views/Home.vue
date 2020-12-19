<template>
    <div id="home">

        <header>
            <el-row type="flex" align="middle">
                <el-col span="12">
                    <h1>SUSTech Gamer</h1>
                </el-col>
                <el-col push="7" span="6">
                    <p>{{date}}</p>
                </el-col>
            </el-row>
        </header>

        <div id="recent-and-poster" style="clear: both">
            <el-row :gutter="20" type="flex" align="top">
                <el-col id="recent" span="6">
                    <el-row id="r-game" v-for="game in recentgames" :key=game>
                        <el-image :src="game.url" @click="gotoURL(game.gameURL)">
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
                    <el-carousel :height="bannerHeight+'px'">
                        <el-carousel-item v-for="poster in posters" :key="poster">
                            <img ref="bannerHeight" :src="poster" alt="" @load="imgLoad" style="width: 100%">
                        </el-carousel-item>
                    </el-carousel>
                </el-col>
            </el-row>
        </div>

        <div id="rank">
            <el-row id="rank-nav" type="flex" align="middle">
                <el-col span="2">
                    <span id="rank-nav-text">排行榜</span>
                </el-col>
                <el-col span="22">
                    <el-tabs v-model="activeRank" @tab-click="handleClick">
                        <el-tab-pane label="今日" name="day"></el-tab-pane>
                        <el-tab-pane label="本周" name="week"></el-tab-pane>
                        <el-tab-pane label="本月" name="month"></el-tab-pane>
                        <el-tab-pane label="今年" name="year"></el-tab-pane>
                        <el-tab-pane label="总榜" name="total"></el-tab-pane>
                    </el-tabs>
                </el-col>
            </el-row>

            <el-row id="rank-pic" :gutter="10" type="flex" justify="center">
                <el-col :span="rank_pic_span" v-for="game in rank" :key=game>
                    <el-image :src="game.url">
                        <div slot="placeholder" class="image-slot">
                            LOADING<span class="dot">...</span>
                        </div>
                        <div slot="error" class="image-slot">
                            <i class="el-icon-picture-outline"></i>
                        </div>
                    </el-image>
                </el-col>
            </el-row>

            <el-row id="rank-text" :gutter="10" type="flex" justify="center">
                <el-col :span="rank_pic_span" v-for="(game, index) in rank" :key=game>
                    <p class="rank-text-p">{{game.name}}</p>
                    <p class="rank-text-p">{{index+1}}</p>
                </el-col>
            </el-row>
        </div>

        <div id="recommend">
            <el-row :gutter="15">
                <el-col :span="8" id="rec-onsale">
                    <el-row>
                        <span class="rec-text">优惠</span>
                    </el-row>
                    <el-row>
                        <el-image :src="onsale[0].url">
                            <div slot="placeholder" class="image-slot">
                                LOADING<span class="dot">...</span>
                            </div>
                            <div slot="error" class="image-slot">
                                <i class="el-icon-picture-outline"></i>
                            </div>
                        </el-image>
                    </el-row>
                    <el-row :gutter="5">
                        <el-col :span="12">
                            <el-image :src="onsale[1].url">
                                <div slot="placeholder" class="image-slot">
                                    LOADING<span class="dot">...</span>
                                </div>
                                <div slot="error" class="image-slot">
                                    <i class="el-icon-picture-outline"></i>
                                </div>
                            </el-image>
                        </el-col>
                        <el-col :span="12">
                            <el-image :src="onsale[2].url">
                                <div slot="placeholder" class="image-slot">
                                    LOADING<span class="dot">...</span>
                                </div>
                                <div slot="error" class="image-slot">
                                    <i class="el-icon-picture-outline"></i>
                                </div>
                            </el-image>
                        </el-col>
                    </el-row>
                </el-col>

                <el-col :span="8" id="rec-new">
                    <el-row>
                        <span class="rec-text">新品</span>
                    </el-row>
                    <el-row :gutter="5">
                        <el-col :span="18">
                            <el-row>
                                <el-image :src="newgames[0].url">
                                    <div slot="placeholder" class="image-slot">
                                        LOADING<span class="dot">...</span>
                                    </div>
                                    <div slot="error" class="image-slot">
                                        <i class="el-icon-picture-outline"></i>
                                    </div>
                                </el-image>
                            </el-row>
                            <el-row>
                                <el-image :src="newgames[1].url">
                                    <div slot="placeholder" class="image-slot">
                                        LOADING<span class="dot">...</span>
                                    </div>
                                    <div slot="error" class="image-slot">
                                        <i class="el-icon-picture-outline"></i>
                                    </div>
                                </el-image>
                            </el-row>
                        </el-col>
                        <el-col :span="6">
                            <el-row>
                                <el-image :src="newgames[2].url">
                                    <div slot="placeholder" class="image-slot">
                                        LOADING<span class="dot">...</span>
                                    </div>
                                    <div slot="error" class="image-slot">
                                        <i class="el-icon-picture-outline"></i>
                                    </div>
                                </el-image>
                            </el-row>
                            <el-row>
                                <el-image :src="newgames[3].url">
                                    <div slot="placeholder" class="image-slot">
                                        LOADING<span class="dot">...</span>
                                    </div>
                                    <div slot="error" class="image-slot">
                                        <i class="el-icon-picture-outline"></i>
                                    </div>
                                </el-image>
                            </el-row>
                            <el-row>
                                <el-image :src="newgames[4].url">
                                    <div slot="placeholder" class="image-slot">
                                        LOADING<span class="dot">...</span>
                                    </div>
                                    <div slot="error" class="image-slot">
                                        <i class="el-icon-picture-outline"></i>
                                    </div>
                                </el-image>
                            </el-row>
                        </el-col>
                    </el-row>
                </el-col>

                <el-col :span="8" id="rec-test">
                    <el-row>
                        <span class="rec-text">测试</span>
                    </el-row>
                    <el-row>
                        <el-image :src="testgames[0].url">
                            <div slot="placeholder" class="image-slot">
                                LOADING<span class="dot">...</span>
                            </div>
                            <div slot="error" class="image-slot">
                                <i class="el-icon-picture-outline"></i>
                            </div>
                        </el-image>
                    </el-row>
                    <el-row :gutter="5">
                        <el-col :span="12">
                            <el-image :src="testgames[1].url">
                                <div slot="placeholder" class="image-slot">
                                    LOADING<span class="dot">...</span>
                                </div>
                                <div slot="error" class="image-slot">
                                    <i class="el-icon-picture-outline"></i>
                                </div>
                            </el-image>
                        </el-col>
                        <el-col :span="12">
                            <el-image :src="testgames[2].url">
                                <div slot="placeholder" class="image-slot">
                                    LOADING<span class="dot">...</span>
                                </div>
                                <div slot="error" class="image-slot">
                                    <i class="el-icon-picture-outline"></i>
                                </div>
                            </el-image>
                        </el-col>
                    </el-row>
                </el-col>
            </el-row>

            <el-row :gutter="15">
                <el-col :span="8" v-for="index in 3" :key=index>
                    <el-row type="flex" justify="center">
                        <el-button plain type="primary" icon="el-icon-more">更多</el-button>
                    </el-row>
                </el-col>
            </el-row>
        </div>

        <footer>
            <p>SUSTech Gamer</p>
            <p>作者: 董正 刘瑞龙 王宇辰 崔俞崧 田野</p>
        </footer>

    </div>
</template>

<script>
    export default {
        name: 'Home',
        data() {
            return {
                bannerHeight: '',
                recentgames: [
                    {url: require('../assets/recentgames/witcher.jpg'), name: 'The Witcher: Wild Hunt', gameURL: "/witcher3"},
                    {url: require('../assets/recentgames/bf3.jpg'), name: 'Battlefield 3', gameURL: "/"},
                    {url: require('../assets/recentgames/codmw.jpeg'), name: 'Call of Duty: Modern Warfare', gameURL: "/"}
                ],
                posters: [
                    require('../assets/poster/sekiro.jpg'),
                    require('../assets/poster/God-of-War-4.jpg'),
                    require('../assets/poster/zelda.jpg'),
                    require('../assets/poster/witcher.jpg')
                ],
                rank: [
                    {url: require('../assets/rank/csgo.jpg'), name: 'CS: GO'},
                    {url: require('../assets/rank/dota2.jpg'), name: 'DOTA 2'},
                    {url: require('../assets/rank/among-us.jpg'), name: 'AMOUNG US'},
                    {url: require('../assets/rank/demons-souls.jpg'), name: 'Demon\'s Souls'},
                    {url: require('../assets/rank/ttf2.png'), name: 'TITANFALL 2'},
                    {url: require('../assets/rank/codbocw.jpg'), name: 'Call of Duty: BOCW'}
                ],
                onsale: [
                    {url: require('../assets/onsale/fallguys.jpg'), name: 'Fall Guys'},
                    {url: require('../assets/onsale/witcher.jpg'), name: 'The Witcher: Wild Hunt'},
                    {url: require('../assets/onsale/gta5.jpg'), name: 'Grand Theft Auto 5'},
                ],
                newgames: [
                    {url: require('../assets/newgames/ac-valhalla.jpg'), name: 'Assassin\'s Creed: Valhalla'},
                    {url: require('../assets/newgames/watchdogslegion.jpg'), name: 'Watch Dogs: Legion'},
                    {url: require('../assets/newgames/demonssouls-square.jpg'), name: 'Demon\'s Souls'},
                    {url: require('../assets/newgames/bocw-square.jpg'), name: 'Call of Duty: BOCW'},
                    {url: require('../assets/newgames/ghostrunner.jpg'), name: 'GhostRunner'},
                ],
                testgames: [
                    {url: require('../assets/testgames/ff16-2.jpg'), name: 'Final Fantasy XVI'},
                    {url: require('../assets/testgames/yuanshen.jpg'), name: '原神'},
                    {url: require('../assets/testgames/dyinglight2.jpg'), name: 'Dying Light 2'},
                ],
                rank_pic_span: 0,
                activeRank: 'day',
                date: ''
            }
        },

        methods: {
            imgLoad () {
                this.$nextTick(() => {
                    this.bannerHeight = this.$refs.bannerHeight[0].height
                    console.log(this.$refs)
                })
            },

            cal_rank_pic_span() {
                this.rank_pic_span=Math.floor(24/this.rank.length)
            },

            showDate() {
                this.date=new Date().toString().substring(0, 24)
            },

            gotoURL(url) {
                this.$router.push(url)
            }
        },

        mounted() {
            document.querySelector('body').setAttribute('style', 'background-color:rgb(55, 55, 55)')
            this.imgLoad()
            window.addEventListener('resize', () => {
                this.bannerHeight = this.$refs.bannerHeight[0].height
                this.imgLoad()
            }, false)
            this.cal_rank_pic_span()
            setInterval(this.showDate, 1000)
        },

        beforeDestroy() {
            document.querySelector('body').removeAttribute('style')
        }
    }
</script>

<style scoped>
    #home {
        width: 1200px;
        margin: auto;
    }

    header {
        color: aqua;
        font-family: Georgia, 'Times New Roman', Times, serif;
        font-size: 1.2rem;
        margin: 5px;
    }

    #recent-and-poster {
        margin: 5px;
    }

    #r-game:nth-child(2), #r-game:nth-child(3) {
        margin-top: 7px;
    }

    .image-slot {
        color: white;
        text-align: center;
    }

    .rank-text-p {
        color: white;
        text-align: center;
        margin: 0;
        padding: 0;
    }

    /* rank nav color */
    #rank-nav-text {
        color: white;
        padding-left: 12px;
    }

    .rec-text {
        color: white;
    }

    footer {
        color: white;
        text-align: center;
        margin-top: 50px;
    }

    #test {
        width: 50px;
    }

</style>
