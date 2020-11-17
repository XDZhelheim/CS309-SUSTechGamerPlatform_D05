<template>
    <div id="home">
        <div id="placehold"></div>

        <div id="recent-and-poster">
            <el-row :gutter="20" type="flex" align="top">
                <el-col id="recent" span="6">
                    <el-row id="r-game" v-for="game in recentgames" :key=game>
                        <el-image :src="game.url">
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
                        <el-image :src="posters[0]">
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
                            <el-image :src="posters[0]">
                                <div slot="placeholder" class="image-slot">
                                    LOADING<span class="dot">...</span>
                                </div>
                                <div slot="error" class="image-slot">
                                    <i class="el-icon-picture-outline"></i>
                                </div>
                            </el-image>
                        </el-col>
                        <el-col :span="12">
                            <el-image :src="posters[0]">
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
                                <el-image :src="posters[0]">
                                    <div slot="placeholder" class="image-slot">
                                        LOADING<span class="dot">...</span>
                                    </div>
                                    <div slot="error" class="image-slot">
                                        <i class="el-icon-picture-outline"></i>
                                    </div>
                                </el-image>
                            </el-row>
                            <el-row :gutter="5">
                                <el-col :span="16">
                                    <el-image :src="posters[0]">
                                        <div slot="placeholder" class="image-slot">
                                            LOADING<span class="dot">...</span>
                                        </div>
                                        <div slot="error" class="image-slot">
                                            <i class="el-icon-picture-outline"></i>
                                        </div>
                                    </el-image>
                                </el-col>
                                <el-col :span="8">
                                    <el-image :src="posters[0]">
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
                        <el-col :span="6">
                            <el-row>
                                <el-image :src="posters[0]">
                                    <div slot="placeholder" class="image-slot">
                                        LOADING<span class="dot">...</span>
                                    </div>
                                    <div slot="error" class="image-slot">
                                        <i class="el-icon-picture-outline"></i>
                                    </div>
                                </el-image>
                            </el-row>
                            <el-row>
                                <el-image :src="posters[0]">
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
                        <el-image :src="posters[0]">
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
                            <el-image :src="posters[0]">
                                <div slot="placeholder" class="image-slot">
                                    LOADING<span class="dot">...</span>
                                </div>
                                <div slot="error" class="image-slot">
                                    <i class="el-icon-picture-outline"></i>
                                </div>
                            </el-image>
                        </el-col>
                        <el-col :span="12">
                            <el-image :src="posters[0]">
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
            <p>Designed by 我哥哥</p>
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
                    {url: require('../assets/recentgames/test.jpg'), name: '爬'},
                    {url: require('../assets/recentgames/test.jpg'), name: '爬'},
                    {url: require('../assets/recentgames/test.jpg'), name: '爬爬爬爬'}
                ],
                posters: [
                    require('../assets/recentgames/test.jpg'),
                    require('../assets/recentgames/test.jpg'),
                    require('../assets/recentgames/test.jpg'),
                    require('../assets/recentgames/test.jpg')
                ],
                rank: [
                    {url: require('../assets/recentgames/test.jpg'), name: '爬'},
                    {url: require('../assets/recentgames/test.jpg'), name: '爬'},
                    {url: require('../assets/recentgames/test.jpg'), name: '爬'},
                    {url: require('../assets/recentgames/test.jpg'), name: '爬'},
                    {url: require('../assets/recentgames/test.jpg'), name: '爬'},
                    {url: require('../assets/recentgames/test.jpg'), name: '爬'}
                ],
                rank_pic_span: 0,
                activeRank: 'day'
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
        },

        beforeDestroy() {
            document.querySelector('body').removeAttribute('style')
        }
    }
</script>

<style scoped>
    #recent-and-poster {
        margin: 5px;
    }

    #placehold {
        height: 61px;
        margin: 0;
        padding: 0;
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

    .el-button--primary.is-plain {
        background-color: transparent;
    }

    .rec-text {
        color: white;
    }

    footer {
        color: white;
        text-align: center;
        margin-top: 50px;
    }

</style>
