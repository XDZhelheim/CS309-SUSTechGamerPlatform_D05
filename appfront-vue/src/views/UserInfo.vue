<template>
    <div>

    </div>
</template>

<script>
    export default {
        name: 'UserInfo',

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

</style>
