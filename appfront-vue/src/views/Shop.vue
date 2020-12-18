<template>
    <div id="shop" ref="scrollTopList">
        <div id="placehold"></div>
        <div class="exploring">

            <div class="upview">
                <el-row :gutter="20">
                    <el-col :span="6" class="g1">
                        <el-image :src="src0.url" height="20" width="20">
                            <div slot="placeholder" class="image-slot">
                                LOADING<span class="dot">...</span>
                            </div>
                            <div slot="error" class="image-slot">
                                <i class="el-icon-picture-outline"></i>
                            </div>
                        </el-image>
                        <center><p id="defaultfont">上古卷轴5</p></center>
                    </el-col>
                    <el-col :span="6" class="g2">
                        <el-image :src="src1.url" height="20" width="20">
                            <div slot="placeholder" class="image-slot">
                                LOADING<span class="dot">...</span>
                            </div>
                            <div slot="error" class="image-slot">
                                <i class="el-icon-picture-outline"></i>
                            </div>
                        </el-image>
                        <center><p id="defaultfont">荒野大镖客2</p></center>
                    </el-col>
                    <el-col :span="6" class="g3">
                        <el-image :src="src2.url" height="20" width="20">
                            <div slot="placeholder" class="image-slot">
                                LOADING<span class="dot">...</span>
                            </div>
                            <div slot="error" class="image-slot">
                                <i class="el-icon-picture-outline"></i>
                            </div>
                        </el-image>
                        <center><p id="defaultfont">原神</p></center>
                    </el-col>
                    <el-col :span="6" class="g4">
                        <el-image :src="src3.url" height="20" width="20">
                            <div slot="placeholder" class="image-slot">
                                LOADING<span class="dot">...</span>
                            </div>
                            <div slot="error" class="image-slot">
                                <i class="el-icon-picture-outline"></i>
                            </div>
                        </el-image>
                        <center><p id="defaultfont">荣耀战魂</p></center>
                    </el-col>
                </el-row>
            </div>

            <div class="mainview">
                <el-image :src="expl.url" height="20" width="20">
                    <div slot="placeholder" class="image-slot">
                        LOADING<span class="dot">...</span>
                    </div>
                    <div slot="error" class="image-slot">
                        <i class="el-icon-picture-outline"></i>
                    </div>
                </el-image>
            </div>

        <div class="description">
            <el-card class="box-card">
                <center><p id="defaultfont">上古卷轴5</p></center>
                <div v-for="o in 4" :key="o" class="text item" body-style="{ padding: '0px' }">
                    {{'评论内容 ' + o }}
                </div>
                <div>
                    <p>赛博朋克2077yyds！</p>
                </div>
            </el-card>
        </div>

            <div class="downview">
                <el-row :gutter="20">
                    <el-col :span="6" class="g5">
                        <center><p id="defaultfont">杀手2</p></center>
                        <el-image :src="src4.url" height="20" width="20">
                            <div slot="placeholder" class="image-slot">
                                LOADING<span class="dot">...</span>
                            </div>
                            <div slot="error" class="image-slot">
                                <i class="el-icon-picture-outline"></i>
                            </div>
                        </el-image>
                    </el-col>
                    <el-col :span="6" class="g6">
                        <center><p id="defaultfont">神之浩劫</p></center>
                        <el-image :src="src5.url" height="20" width="20">
                            <div slot="placeholder" class="image-slot">
                                LOADING<span class="dot">...</span>
                            </div>
                            <div slot="error" class="image-slot">
                                <i class="el-icon-picture-outline"></i>
                            </div>
                        </el-image>
                    </el-col>
                    <el-col :span="6" class="g7">
                        <center><p id="defaultfont">最终幻想14</p></center>
                        <el-image :src="src6.url" height="20" width="20">
                            <div slot="placeholder" class="image-slot">
                                LOADING<span class="dot">...</span>
                            </div>
                            <div slot="error" class="image-slot">
                                <i class="el-icon-picture-outline"></i>
                            </div>
                        </el-image>
                    </el-col>
                    <el-col :span="6" class="g8">
                        <center><p id="defaultfont">古剑奇谭3</p></center>
                        <el-image :src="src7.url" height="20" width="20">
                            <div slot="placeholder" class="image-slot">
                                LOADING<span class="dot">...</span>
                            </div>
                            <div slot="error" class="image-slot">
                                <i class="el-icon-picture-outline"></i>
                            </div>
                        </el-image>
                    </el-col>
                </el-row>
            </div>

        </div>
    </div>
</template>

<script>

    export default {
        a:22,
        name: 'shop',
       


        data() {
            return {
                bannerHeight: '',
                back: { url: require('../assets/shop/0.jpg') },
                expl: { url: require('../assets/shop/0.jpg') },
                src0: { url: require('../assets/shop/1.jpg') },
                src1: { url: require('../assets/shop/2.jpg') },
                src2: { url: require('../assets/shop/3.png') },
                src3: { url: require('../assets/shop/4.jpg') },
                src4: { url: require('../assets/shop/5.jpg') },
                src5: { url: require('../assets/shop/6.jpg') },
                src6: { url: require('../assets/shop/7.jpg') },
                src7: { url: require('../assets/shop/8.jpg') }
            }
        },

        methods: {
                
            throttle(func, wait) {
                let lastTime = null
                let timeout
                return () => {
                    let context = this
                    let now = new Date()
                    let arg = arguments
                    if (now - lastTime - wait > 0) {
                        if (timeout) {
                            clearTimeout(timeout)
                            timeout = null
                        }
                        func.apply(context, arg)
                        lastTime = now
                    } else if (!timeout) {
                        timeout = setTimeout(() => {
                            func.apply(context, arg)
                        }, wait)
                    }
                }
            }
        },


        handleScroll(){
            let scrollTop = this.$refs.scrollTopList.scrollTop
            let clientHeight = this.$refs.scrollTopList.clientHeight
            let scrollHeight = this.$refs.scrollTopList.scrollHeight
            let height = 50 // 根据项目实际定义
            if (scrollTop +clientHeight >= scrollHeight - height) {
            if (this.pageSize > this.total) {
                return false
            } else {
                this.pageSize = this.pageSize +10 // 显示条数新增
                this.getpageList() // 请求列表list 接口方法
            }
            } else {
                return false
            }
        },

        mounted() {
            document.querySelector('body').setAttribute('style', 'background-color:rgb(55, 55, 55)')
            this.$refs.scrollTopList.addEventListener('scroll',this.throttle(this.handleScroll,500),true)
        },

        beforeDestroy() {
            document.querySelector('body').removeAttribute('style')
        }
    }
</script>

<style scoped>
    #placehold {
        height: 61px;
        margin: 0;
        padding: 0;
    }
    
    #defaultfont{
        color:white;
    }
    .el-image{
        height:auto;
        width:auto;
    }
    .el-card {
        background-color: rgb(45, 45, 45);
        width:1000px;
    }
    .mainview {
        position:relative;
        left:-350px;
        height: 1000px;
        width: 745px;
        margin:auto;
    }
    .description{
        position:relative;
        top:-995px;
        left:400px;
        margin:auto;
    }
    .box-card {
        height: 410px;
        width: 500px;
        margin: auto;
        color:white;
    }
    .downview {
        top: -965px;
        position:relative;
    }
    .el-row {
        margin-bottom: 20px;
        &:last-child
    {margin-bottom: 0;}
    }

    .el-col {
    border-radius: 4px;
    }

    .text {
        font-size: 14px;
    }

    .item {
        padding: 18px 0;
    }

    .box-card {
        width: 480px;
    }
</style>
