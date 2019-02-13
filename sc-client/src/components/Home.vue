<template>
  <div class="home">
    <v-carousel><a href="#">
      <v-carousel-item
              v-for="(item,i) in articles"
              :key="i"
              :src="item.thumbnail"
      ></v-carousel-item>
    </a>
    </v-carousel>

    <div id="articles" v-for="article in articles" class="card">
      <v-card>
        <v-img :src="article.thumbnail"></v-img>
      </v-card>
      <v-card-title class="v-card__title--primary">
        <div>
          <h3 class="headline mb-0">{{article.title}}</h3>
        </div>
      </v-card-title>
    </div>

  </div>
</template>

<script>
  export default {
    data () {
      return {
        articles:[]
      }
    },
    created () {
      this.getAllArticles();
      console.log(articles)
    },
    methods: {
      getAllArticles:function () {
        this.$http.get("/api/public/article")
                .then((res)=>this.articles = res.data)
      }
    }
  }
</script>

<style scoped>
.content{

    padding-top:0px;
    padding-bottom: 20px;
    margin-bottom: 150px;
  }
</style>
