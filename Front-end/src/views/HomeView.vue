<template>
<div>
  <header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <header-nav/>
      </nav>
    </header>
      

    <main>
      <section>
        <br>
        <router-view/>
      </section>

      <aside>
        <aside-view/>
      </aside>

    </main>


</div>
</template>

<script>

import HeaderNav from '../components/common/HeaderNav.vue'
import AsideView from './AsideView.vue'

export default {
  name: 'HomeView',
  components: {
    HeaderNav,
    AsideView
  },
  created(){
    console.log('start')
    this.$store.dispatch('getRanking')
    this.$store.dispatch('getFollow');
  }
}
</script>

<style scoped>

main{
  display: flex;
  margin: 10px;
  height: auto;
  min-height: 80vh;
}

section{
  /* background-color: rgb(163, 222, 222); */
  width: 80vw;
  float: left;
  padding: 100px 0px;
  
}
header{
position:fixed;
width:100%;
top:0;
left:0;
z-index: 1;
}

aside{
  float:right;
  /* position:fixed;
  top: 100px;
  right: 10px; */
} 



</style>
