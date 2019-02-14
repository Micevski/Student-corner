<template>
    <div>
        <h1>Поставени прашања</h1>
    <div id="questions" v-for="question in questions">
        <a :href="'/#/question/'+question.id">
        <div>
            <h3 class="headline mb-0">{{question.title}}</h3>
        </div>
        <div>
            <p>{{question.question.substring(0,30)}}</p>
            <h6>Поставено од: {{question.userName}}</h6>
        </div>
        </a>
    </div>
    </div>
</template>

<script>
    export default {
        data () {
            return {
                questions:{}
            }
        },
        created () {
            this.getQuestions();
        },
        methods: {
            getQuestions:function () {
                this.$http.get("/api/user/question")
                    .then((res)=>{
                        console.log(res)
                        this.questions = res.data
                    }).catch((e)=> console.log(e))
            }
        }
    }
</script>

<style scoped>
    a{
        text-decoration:none;
        color:black;
    }
    a:hover{
        color:dimgrey;
    }
    #questions{
        border:1px solid lightgray;
        width:70%;
        text-align:center;
        margin:auto;
        margin-bottom:17px;
        box-shadow: 2px 4px lightgrey;
        border-radius: 10px;
    }
    h1{
        text-align:center;
        margin-bottom:30px;
    }
</style>