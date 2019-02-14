<template>
    <div id="article">
        <h1 >{{article.title}}</h1>
        <img :src="article.thumbnail"></img>
        <p>{{article.articleText}}</p>
    </div>
</template>

<script>
    export default {
        data () {
            return {
                article:{}
            }
        },
        created () {
            this.getArticleById();
        },
        methods: {
            getArticleById:function () {
                this.$http.get("/api/public/article/"+this.$route.params.id)
                    .then((res)=>{
                        console.log(res)
                        this.article = res.data
                    }).catch((e)=> console.log(e))
            }
        }
    }
</script>

<style scoped>
    #article{
        text-align:center;
        width:1000px;
        margin:auto;
        margin-top:5px;
    }
    #article img{
        min-width:200px;
        max-width:700px;
        max-height:430px;
        margin-top:15px;
        margin-bottom: 5px;
    }

</style>