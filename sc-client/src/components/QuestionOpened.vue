<template>
    <div id="question">
        <h1>{{question.title}}</h1>
        <p>{{question.question}}</p>

        <div class="comment">
            <div style="border: 1px solid lightgray" class="comments" v-for="comment in answers">
                <h5>{{comment.userName}}</h5>
                <p>{{comment.contet}}</p>
            </div>

            <form>
            <v-textarea
                    v-model="content"
                    label="Одговор"
                    box
                    auto-grow
                    value=""
                    style="padding-top:10px;"
            ></v-textarea>

            <div class="publish">
                <v-btn @click="submit">Одговори</v-btn>
            </div>
            </form>
        </div>
    </div>
</template>

<script>
    export default {
        data: () => ({
            question:'',
            content:'',
            user:'',
            answers: []
        }),
        created () {
            this.getLoggedUser();
            this.getQuestionById();
        },
        methods: {
            getQuestionById:function () {
                this.$http.get("/api/user/question/"+this.$route.params.id)
                    .then((res)=>{
                        this.question = res.data;
                        this.answers = this.question.answers;
                    }).catch((e)=> console.log(e))
            },
            submit() {
                this.getLoggedUser();
                console.log(this.user)
                let question = {
                    'contet': this.content,
                    'userName': this.user.firstName
                };
                this.$http.post("/api/user/question/answer/save/"+this.$route.params.id, question)
                    .then(res => {
                        this.$router.push("/question/"+this.$route.params.id)
                    }).catch(e => {
                    alert("Something went wrong");
                })
            },
            getLoggedUser: function () {
                this.$http.get('/api/user/loggedUser')
                    .then((res) => {
                            this.user = res.data
                    }).catch(() => {
                    alert("No logged user")
                });
            }
        }
    }
</script>

<style scoped>
    #question{
        text-align:center;
        width:700px;
        margin:auto;
        margin-top:5px;
    }

    .comments{
        box-shadow: 1px 2px lightgrey;
        border-radius: 10px;
        margin-bottom:10px;
    }
</style>