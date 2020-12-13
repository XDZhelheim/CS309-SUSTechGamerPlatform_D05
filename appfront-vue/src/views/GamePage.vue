<template>
    <div id="gamepage">
        <div id="pic-video">
            <el-carousel :height="bannerHeight+'px'" type="card" indicator-position="none" :autoplay="false" arrow="always">
                <el-carousel-item v-for="pic in gamepics" :key="pic">
                    <img ref="bannerHeight" :src="pic" alt="" @load="imgLoad" style="width: 100%">
                </el-carousel-item>
            </el-carousel>
        </div>

        <div id="gameinfo">
            <h2>

            </h2>
        </div>

        <div id="score">

        </div>

        <div id="comments">

        </div>
    </div>
</template>

<script>
    export default {
        name: "gamepage",

        data() {
            return {
                bannerHeight: '',
                gameinfo: {
                    title: "The Witcher: Wild Hunt",
                    date: "2015",
                    price: 47,
                    type: "RPG",
                    publisher: "CD Project Red",
                    language: "English",
                    abstract: "nb游戏"
                },

                gamepics: [
                    require('../assets/poster/sekiro.jpg'),
                    require('../assets/poster/God-of-War-4.jpg'),
                    require('../assets/poster/zelda.jpg'),
                    require('../assets/poster/witcher.jpg')
                ],

                gamevideos: [

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
    #gamepage {
        width: 1200px;
        margin: auto;
        color: aqua;
    }
</style>
