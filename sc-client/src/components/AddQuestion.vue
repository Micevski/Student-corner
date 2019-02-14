<template>
    <form>
        <div class="addQuestion">
            <h2 style="text-align:center">Постави прашање</h2>

            <v-text-field
                    v-model="questionTitle"
                    :error-messages="questionTitleErrors"
                    label="Наслов на прашањето"
                    required
                    @input="$v.questionTitle.$touch()"
                    @blur="$v.questionTitle.$touch()"
            ></v-text-field>

            <v-textarea
                    v-model="questionContent"
                    :error-messages="questionContentErrors"
                    label="Содржина"
                    required
                    @input="$v.questionContent.$touch()"
                    @blur="$v.questionContent.$touch()"
                    box
                    auto-grow
                    value=""
                    style="padding-top:10px;"
            ></v-textarea>

            <div class="publish">
                <v-btn @click="submit">Објави</v-btn>
            </div>
        </div>
    </form>
</template>

<script>
    import { validationMixin } from 'vuelidate'
    import { required } from 'vuelidate/lib/validators'

    export default {
        mixins: [validationMixin],

        validations: {
            questionTitle: { required },
            questionContent: { required },
        },

        data: () => ({
            questionTitle: "",
            questionContent: "",
            user:""
        }),

        computed: {
            questionTitleErrors () {
                const errors = []
                if (!this.$v.questionTitle.$dirty) return errors
                !this.$v.questionTitle.required && errors.push('Внесете наслов.')
                return errors
            },
            questionContentErrors (){
                const errors = []
                if (!this.$v.questionContent.$dirty) return errors
                !this.$v.questionContent.required && errors.push('Внесете ја содржината.')
                return errors
            }
        },

        methods: {
            submit() {
                this.getLoggedUser()
                this.$v.$touch()
                let question = {
                    'title': this.questionTitle,
                    'question': this.questionContent,
                    'userName': this.user.firstName
                }
                this.$http.post("/api/user/question", question)
                    .then(res => {
                        this.$router.push("/question/"+res.data.id)
                    }).catch(e => {
                    alert("Something went wrong");
                })
            },
            getLoggedUser: function () {
                this.$http.get('/api/user/loggedUser')
                    .then((res) => {
                        let status = JSON.parse(res.status);
                        if (status == 200) {
                            this.user = res.data
                        }
                    }).catch(() => {
                        alert("No logged user")
                });
            }
        }
    }
</script>

<style scoped>
    .addQuestion {
        width: 38%;
        margin: auto;
        min-width: 350px;
    }

    .publish{
        padding-top:10px;
    }

    .publish button{
        width:200px;
        margin-left: 30%
    }
</style>