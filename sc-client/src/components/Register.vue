<template>
    <form>
        <div class='registerForm'>

            <h1 style="text-align:center">РЕГИСТРАЦИЈА</h1>
            <v-text-field
                    v-model="name"
                    :error-messages="nameErrors"
                    label="Име"
                    required
                    @input="$v.name.$touch()"
                    @blur="$v.name.$touch()"
            ></v-text-field>

            <v-text-field
                    v-model="lastName"
                    :error-messages="lastNameErrors"
                    label="Презиме"
                    required
                    @input="$v.lastName.$touch()"
                    @blur="$v.lastName.$touch()"
            ></v-text-field>

            <v-text-field
                    v-model="email"
                    :error-messages="emailErrors"
                    label="Е-маил"
                    required
                    @input="$v.email.$touch()"
                    @blur="$v.email.$touch()"
            ></v-text-field>

            <v-text-field
                    v-model="password"
                    :error-messages="passwordErrors"
                    :type="'password'"
                    label="Лозинка"
                    required
                    @input="$v.password.$touch()"
                    @blur="$v.password.$touch()"
            ></v-text-field>

            <v-select
                    v-model="select"
                    :items="items"
                    :error-messages="selectErrors"
                    label="Факултет"
                    required
                    @change="$v.select.$touch()"
                    @blur="$v.select.$touch()"
            ></v-select>
            <v-checkbox
                    v-model="checkbox"
                    :error-messages="checkboxErrors"
                    label="Дали се согласувате со правилата на користење ?"
                    required
                    @change="$v.checkbox.$touch()"
                    @blur="$v.checkbox.$touch()"
            ></v-checkbox>

            <div class="chooseButtons">
                <v-btn @click="submit">Потврди</v-btn>
                <v-btn @click="clear">Откажи</v-btn>
            </div>

        </div>
    </form>
</template>

<script>
    import {validationMixin} from 'vuelidate'
    import {email, required} from 'vuelidate/lib/validators'


    export default {
        mixins: [validationMixin],

        validations: {
            name: {required},
            password: {required},
            lastName: {required},
            email: {required, email},
            select: {required},
            checkbox: {
                checked(val) {
                    return val
                }
            }
        },

        data: () => ({
            name: '',
            lastName: '',
            email: '',
            password: '',
            select: null,
            items: [
                'Иден студент',
                'Архитектонски факултет',
                'Градежен факултет',
                'Економски факултет',
                'Машински факултет',
                'Медицински факултет',
                'Педагошки факултет „Св. Климент Охридски“',
                'Правен факултет „Јустинијан Први“',
                'Природно-математички факултет',
                'Стоматолошки факултет',
                'Технолошко-металуршки факултет',
                'Факултет за ветеринарна медицина',
                'Факултет за дизајн и технологии на мебел и ентериер',
                'Факултет за драмски уметности',
                'Факултет за електротехника и информациски технологии',
                'Факултет за земјоделски науки и храна',
                'Факултет за информатички науки и компјутерско инженерство',
                'Факултет за ликовни уметности',
                'Факултет за музичка уметност',
                'Факултет за физичко образование, спорт и здравје',
                'Фармацевтски факултет',
                'Филозофски факултет',
                'Филолошки факултет „Блаже Конески“',
                'Шумарски факултет'
            ],
            checkbox: false,
        }),

    computed: {
      checkboxErrors () {
        const errors = []
        if (!this.$v.checkbox.$dirty) return errors
        !this.$v.checkbox.checked && errors.push('Морате да се согласите со правилата за користење !')
        return errors
      },
      selectErrors () {
        const errors = []
        if (!this.$v.select.$dirty) return errors
        !this.$v.select.required && errors.push('Одберете факултет доколку сте студент, доколку не сте одберете „Иден Студент“')
        return errors
      },
      nameErrors () {
        const errors = []
        if (!this.$v.name.$dirty) return errors
        !this.$v.name.required && errors.push('Името е задолжително.')
        return errors
      },
      lastNameErrors () {
        const errors = []
        if (!this.$v.lastName.$dirty) return errors
        !this.$v.lastName.required && errors.push('Презимето е задолжително.')
        return errors
      },
      passwordErrors () {
        const errors = []
        if (!this.$v.password.$dirty) return errors
        !this.$v.password.required && errors.push('Внесете лозинка !')
        return errors
      },
      emailErrors () {
        const errors = []
        if (!this.$v.email.$dirty) return errors
        !this.$v.email.email && errors.push('Мора да внесете валидна е-маил адреса.')
        !this.$v.email.required && errors.push('Е-маил адресата е задолжителна.')
        return errors
      }
    },

        methods: {
            submit() {

                this.$v.$touch()
                let user = {
                    'email': this.email,
                    'password': this.password,
                    'name': this.name,
                    'surname': this.lastName,
                    'faculty': this.faculty
                }
                this.$http.post("/api/admin/register", user)
                    .then(res => {
                        /* eslint-disable no-console */
                        console.log(res);
                        /* eslint-enable no-console */
                    }).catch(e => {
                        //so da se desava na ne uspesen register
                })
            },
            clear() {
                this.$v.$reset()
                this.name = ''
                this.email = ''
                this.select = null
                this.checkbox = false
            }
        }
    }
</script>

<style>
    .registerForm {
        width: 38%;
        margin: auto;
        min-width: 350px;
    }

    .chooseButtons {
        margin-left: 24%
    }
</style>