<template>
    <div id="question">
        <h1 >{{question.title}}</h1>
        <p>{{question.question}}</p>
    </div>
</template>

<script>
    export default {
        data () {
            return {
                question:{}
            }
        },
        created () {
            this.getQuestionById();
        },
        methods: {
            getQuestionById:function () {
                this.$http.get("/api/user/question/"+this.$route.params.id)
                    .then((res)=>{
                        console.log(res)
                        this.question = res.data
                    }).catch((e)=> console.log(e))
            }
        }
    }
</script>

<style scoped>

</style>