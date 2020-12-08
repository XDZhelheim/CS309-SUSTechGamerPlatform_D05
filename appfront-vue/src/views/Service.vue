<template>
    <div class="community">
        <div id="placehold"></div>
        <div>
            <div class="mainview" align="center">
                <el-image :src="expl.url" height="20" width="20">
                    <div slot="placeholder" class="image-slot">
                        LOADING<span class="dot">...</span>
                    </div>
                    <div slot="error" class="image-slot">
                        <i class="el-icon-picture-outline"></i>
                    </div>
                </el-image>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'community',
        data() {
            return {
                bannerHeight: '',
                expl: { url: require('../assets/all/0.jpg') },

            }
        },

        mounted() {
            document.querySelector('body').setAttribute('style', 'background-color:rgb(55, 55, 55)')
            this.$refs.scrollTopList.addEventListener('scroll', this.throttle(this.handleScroll, 500), true)
        }
    }


</script>
