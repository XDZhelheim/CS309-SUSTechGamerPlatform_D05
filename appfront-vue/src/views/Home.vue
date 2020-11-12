<template>
    <div id="home">
        <div id="placehold"></div>

        <div id="recent-and-poster">
            <el-row :gutter="20" type="flex" align="top">
                <el-col id="recent" span="6">
                    <el-row id="r-game" v-for="game in recentgames" :key=game>
                        <el-image :src="game">
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
    </div>
</template>

<script>
    export default {
        name: 'Home',
        data() {
            return {
                activeIndex: '/shop',
                bannerHeight: '',
                recentgames: [
                    require('../assets/recentgames/test.jpg'),
                    require('../assets/recentgames/test.jpg'),
                    require('../assets/recentgames/test.jpg')
                ],
                posters: [
                    require('../assets/recentgames/test.jpg'),
                    require('../assets/recentgames/test.jpg'),
                    require('../assets/recentgames/test.jpg'),
                    require('../assets/recentgames/test.jpg')
                ]
            }
        },
        methods: {
            imgLoad () {
                this.$nextTick(() => {
                    this.bannerHeight = this.$refs.bannerHeight[0].height
                    console.log(this.$refs)
                })
            }
        },
        mounted() {
            document.querySelector('body').setAttribute('style', 'background-color:rgb(55, 55, 55)')
            this.imgLoad()
            window.addEventListener('resize', () => {
                this.bannerHeight = this.$refs.bannerHeight[0].height
                this.imgLoad()
            }, false)
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

</style>
