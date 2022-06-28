<template>
  <div id="app">
    <div class="main">
       <router-view></router-view> 
    </div>
    <footer>
      <footer-view/>
    </footer>
  </div>
</template>


<script>
import FooterView from '@/components/common/FooterView.vue'
export default {
    components: {
    FooterView,
}
}
</script>


<style>

.main{
  /* flex: 0 1 auto; */
  /* background-color: rgb(235, 204, 199); */
  
}

footer{
  /* flex: 0 1 60px; */
  /* background-color: #a6bcd2; */
  width: 100%;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  font-family: Jua;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;

  display: flex;
  flex-flow: column;
  align-items: stretch;
}

nav {
  padding: 30px;
}

nav a {
  font-size: 25px;
  /* font-weight: bold; */
  color: #e7e7e7;
}

nav a.router-link-exact-active {
  color: #42b983;
}

</style>
