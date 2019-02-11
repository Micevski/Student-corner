<template>
    <v-toolbar>
        <v-toolbar-side-icon><img class="logo" src="../assets/Eductation-icon.jpg"></v-toolbar-side-icon>
        <v-toolbar-title>Студентски Агол</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-toolbar-items centered class="hidden-sm-and-down">
            <v-btn active flat><a href="/">Почетна</a></v-btn>
            <v-btn flat><a href="/#/uslugi">Услуги</a></v-btn>
            <v-btn flat><a href="/#/popusti">Попусти</a></v-btn>
            <v-btn flat><a href="/#/novosti">Новости</a></v-btn>
            <v-btn flat><a href="/#/register">Регистрирај се</a></v-btn>
        </v-toolbar-items>
        
    </v-toolbar>

    
</template>

<script>
    export default {
        data: () => ({
            works: "Header"
        })
    }
</script>

<style>

    .logo {
        width: 90%;
        height: 90%;
    }

</style>
