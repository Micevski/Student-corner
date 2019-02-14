<template>
    <form>
        <div class="newPost">
            <h2 style="text-align:center">Додади нова објава</h2>

            <v-text-field
                    v-model="postTitle"
                    :error-messages="postTitleErrors"
                    label="Наслов"
                    required
                    @input="$v.postTitle.$touch()"
                    @blur="$v.postTitle.$touch()"
            ></v-text-field>

            <v-select
                    v-model="select"
                    :items="items"
                    :error-messages="selectErrors"
                    label="Одберете категорија"
                    required
                    @change="$v.select.$touch()"
                    @blur="$v.select.$touch()"
            ></v-select>

            <v-text-field
                    v-model="postThumbnail"
                    :error-messages="postThumbnailErrors"
                    label="Линк од слика"
                    required
                    @input="$v.postThumbnail.$touch()"
                    @blur="$v.postThumbnail.$touch()"
            ></v-text-field>

            <v-textarea
                    v-model="postContent"
                    :error-messages="postContentErrors"
                    label="Содржина"
                    required
                    @input="$v.postContent.$touch()"
                    @blur="$v.postContent.$touch()"
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
    import { required, email } from 'vuelidate/lib/validators'

    export default {
        mixins: [validationMixin],

        validations: {
            postTitle: { required, email },
            postContent: { required },
            postThumbnail: { required },
            select: { required },
            checkbox: {
                checked(val) {
                    return val
                }
            }
        },

        data: () => ({
            postTitle: "",
            postContent: "",
            postThumbnail: "",
            select: null,
            items: [
                'Новости',
                'Услуги',
                'Попусти'
            ]
        }),

        computed: {
            postTitleErrors () {
                const errors = []
                if (!this.$v.postTitle.$dirty) return errors
                !this.$v.postTitle.required && errors.push('Внесете наслов.')
                return errors
            },
            postContentErrors (){
                const errors = []
                if (!this.$v.postContent.$dirty) return errors
                !this.$v.postContent.required && errors.push('Внесете ја содржината на објавата.')
                return errors
            },
            postThumbnailErrors () {
                const errors = []
                if (!this.$v.postThumbnail.$dirty) return errors
                //!this.$v.postThumbnail.link && errors.push('Мора да внесете валидна URL адреса.')
                !this.$v.postThumbnail.required && errors.push('Сликата на објавата е задолжителна.')
                return errors
            },
            selectErrors() {
                const errors = []
                if (!this.$v.select.$dirty) return errors
                !this.$v.select.required && errors.push('Категоријата е задолжителна.')
                return errors
            }
        },

        methods: {
            submit() {

                this.$v.$touch()
                if(this.select ==='Новости')
                    this.select='NEWS'
                if(this.select === 'Услуги')
                    this.select = 'SERVICES'
                if(this.select === 'Попусти')
                    this.select = 'OFFERS'
                let article = {
                    'title': this.postTitle,
                    'content': this.postContent,
                    'thumbnail': this.postThumbnail,
                    'category': this.select
                }
                console.log(this.select)
                this.$http.post("/api/admin/article/save", article)
                    .then(res => {
                        /* eslint-disable no-console */
                        console.log(res);
                        /* eslint-enable no-console */
                    }).catch(e => {
                    //so da se desava na ne uspesen register
                })
            }
        }
    }
</script>

<style scoped>
    .newPost {
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