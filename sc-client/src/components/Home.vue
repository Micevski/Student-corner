<template>
  <div class="home">
    <v-carousel><router-link v-for="(item,i) in articles" :to="'/article/'+item.id">
      <v-carousel-item
              :key="i"
              :src="item.thumbnail"
      ></v-carousel-item>
    </router-link>
    </v-carousel>

    <div id="articles" v-for="article in articles" class="card">
      <a :href="'/#/article/'+article.id">
        <v-card>
          <v-img :src="article.thumbnail"></v-img>
        </v-card>
        <v-card-title class="v-card__title--primary">
          <div>
            <h3 class="headline mb-0">{{article.title}}</h3>
          </div>
        </v-card-title>
        <p>{{article.articleText.substring(1,50)}} ...</p>
      </a>
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

  #articles{
    width:330px;
    display:inline-block;
    margin: 40px;
  }

  #articles a{
    text-decoration: none;
    color: black;
  }

</style>
