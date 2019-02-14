<template>
    <div>
    <div id="questions" v-for="question in questions">
        <a :href="'/#/question/'+question.id">
            <v-card-title class="v-card__title--primary">
                <div>
                    <h3 class="headline mb-0">{{question.title}}</h3>
                </div>
            </v-card-title>
            <div>
                <p>{{question.question.substring(0,30)}}</p>
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

</style>