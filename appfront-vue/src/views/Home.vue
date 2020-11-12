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
                    <span>排行榜</span>
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

            <el-row id="rank-pic" :gutter="10">
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

            <el-row id="rank-text" :gutter="10">
                <el-col :span="rank_pic_span" v-for="(game, index) in rank" :key=game>
                    <p class="rank-text-p">{{index+1}}</p>
                    <p class="rank-text-p">{{game.name}}</p>
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
                bannerHeight: '',
                recentgames: [
                    {url: require('../assets/recentgames/test.jpg'), name: '爬'},
                    {url: require('../assets/recentgames/test.jpg'), name: '爬'},
                    {url: require('../assets/recentgames/test.jpg'), name: '爬'}
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
                this.rank_pic_span = 24 / this.rank.length
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

<style>
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

    .el-tabs__item.is-top {
        color: white;
    }

</style>
