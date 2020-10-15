<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <p>
      For a guide and recipes on how to configure / customize this project,<br>
      check out the
      <a href="https://cli.vuejs.org" target="_blank" rel="noopener">vue-cli documentation</a>.
    </p>
    <h1 id="sgp">
      SUSTech Gamer Platform
    </h1>
    <h3>Team</h3>
    <ul>
      <li>董正</li>
      <li>刘瑞龙</li>
      <li>田野</li>
      <li>崔俞崧</li>
      <li>王宇辰</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus">
h3 {
  font-family: Georgia, 'Times New Roman', Times, serif;
}

ul
  list-style-type none
  padding 0

li
  display inline-block
  margin 0 10px

a
  color #42b983

#sgp {
  font-family 'Source Han Serif CN', 'Times New Roman', serif
}

</style>
