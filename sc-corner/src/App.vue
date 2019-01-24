<template>
  <v-app>
    <v-content>
      <Header/>
      <Body/>
      <Footer/>
    </v-content>
  </v-app>
</template>

<script>
import Body from './components/HomeBody'
import Header from "./components/Header";
import Footer from "./components/Footer";

export default {
  name: 'App',
  components: {
    Footer,
    Header,
    Body
  },
  data () {
    return {
      //
    }
  }
}
</script>
