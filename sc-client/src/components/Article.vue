<template>
    <h3></h3>
    <img src=""></img>
    <p></p>
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
                console.log("vlezi")
                this.$http.get("/api/public/article")
                    .then((res)=>this.articles = res.data)
            }
        }
    }
</script>

<style scoped>

</style>