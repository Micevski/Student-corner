<template>
    <v-toolbar>
        <v-toolbar-side-icon><a href="/"><img class="logo" src="../assets/Education-icon.png"></a></v-toolbar-side-icon>
        <v-toolbar-title><a href="/">Студентски Агол</a></v-toolbar-title>
        <v-spacer></v-spacer>
        <v-toolbar-items centered class="hidden-sm-and-down">
            <v-btn active flat><a href="/">Почетна</a></v-btn>
            <v-btn v-on:click="services()" flat>Услуги</v-btn>
            <v-btn v-on:click="offers()" flat>Попусти</v-btn>
            <v-btn v-on:click="news()" flat>Новости</v-btn>
        </v-toolbar-items>
        
    </v-toolbar>

    
</template>

<script>
    export default {
        data: () => ({
            works: "Header"
        }),

        methods: {
            services: function () {
                this.$router.push('/uslugi')
            },

            offers: function () {
                this.$router.push('/popusti')
            },

            news: function () {
                this.$router.push('/novosti')
            }
        }
    }
</script>

<style>
    .v-btn__content a{
        text-decoration: none;
        color: #424242;
    }
    .v-toolbar__title a{
        text-decoration:none;
        color: #424242;
    }
    .logo {
        width: 90%;
        height: 90%;
    }

</style>
