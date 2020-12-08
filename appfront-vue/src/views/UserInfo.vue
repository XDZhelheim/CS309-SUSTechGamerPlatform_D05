<template>
    <div>

    </div>
</template>

<script>
    export default {
        name: 'UserInfo',

        mounted() {
            document.querySelector('body').setAttribute('style', 'background-color:rgb(55, 55, 55)')
        },

        beforeDestroy() {
            document.querySelector('body').removeAttribute('style')
        }
    }
</script>

<style scoped>

</style>
