<template>
    <div id="shop" ref="scrollTopList">
        <div id="placehold"></div>
        <p v-for="t in 100" :key=t>{{t}}</p>
    </div>
</template>

<script>
    export default {
        name: 'Shop',
        data() {
            return {

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
</style>
