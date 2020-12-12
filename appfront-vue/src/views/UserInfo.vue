<template>
    <div id="userinfo">
        <div id="placehold2"></div>
        <div>
            <el-avatar :size="300" :src="avatarURL"></el-avatar>
        </div>
    </div>
</template>

<script>
export default {
    name: "UserInfo",

    data() {
        return {
            userInfo: {
                avatarURL: require("../assets/avatars/testavatar.jpg"),
                name: "???"
            }

        };
    },

    methods: {

    },

    mounted() {
        document
            .querySelector("body")
            .setAttribute("style", "background-color:rgb(55, 55, 55)");
    },

    beforeDestroy() {
        document.querySelector("body").removeAttribute("style");
    },
};
</script>

<style scoped>
    #userinfo {
        width: 800px;
        margin: auto;
    }

    #placehold2 {
        margin-top: 25%;
    }

</style>
