<template>
  <div id="app">
    <!--<img src="./assets/logo.png">-->
    <div id="nav">
      <router-link to="/">hello</router-link>
      <router-link to="/test">test</router-link>
      <router-link to="/si_vue">si_test</router-link>
    </div>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
