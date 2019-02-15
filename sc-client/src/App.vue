<template>
  <v-app>
    <v-content>
      <LoginNavBar/>
      <Header/>
      <router-view class="content"></router-view>
      <Footer/>
    </v-content>

  </v-app>
</template>

<script>
  import Header from "./components/Header";
  import Footer from "./components/Footer";
  import LoginNavBar from "./components/LoginNavBar"


  export default {
    name: 'App',
    components: {
      Header,
      Footer,
      LoginNavBar
    },
    data () {
      return {
        //
      }
    }
  }
</script>

<style>
  .content{
    min-height: 400px;
    padding-top:20px;
    padding-bottom:135px;
  }

  .loginRegisterToolbar{
    height:40px !important;
    background-color: #009688 !important;
    text-align: right;
  }

  .loginMenuItem{
    height: 27px;
  }

  .loginMenuItem a{
    color: white;
    text-decoration: none;
    font-size: 13px;
  }


</style>