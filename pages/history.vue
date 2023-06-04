<template>
  <!--  <v-main>-->
  <!--    <v-card>-->
  <!--      <v-card-title>-->
  <!--        История медкомиссий-->
  <!--        <v-spacer></v-spacer>-->
  <!--        <v-text-field-->
  <!--            v-model="search"-->
  <!--            append-icon="mdi-magnify"-->
  <!--            label="Поиск"-->
  <!--            single-line-->
  <!--            hide-details-->
  <!--        ></v-text-field>-->
  <!--      </v-card-title>-->
  <!--      <v-data-table-->
  <!--          :headers="headers"-->
  <!--          :items="desserts"-->
  <!--          :search="search"-->
  <!--      ></v-data-table>-->
  <!--    </v-card>-->
  <!--  </v-main>-->

  <v-main>
    <v-card>
      <v-card-title>
        <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="Поиск"
            single-line
            hide-details
        ></v-text-field>
      </v-card-title>
      <v-data-table
          :headers="headers"
          :items="tableList"
          :search="search"
      ></v-data-table>
    </v-card>
  </v-main>

</template>

<script>
export default {
  name: "history",
  data() {
    return {
      search: '',
      tableList: [
        {
          organization: "ООО \"Центр Медицинских услуг\"",
          date: "2023.04.27",
          status: "Завершено"
        },
        {
          organization: "ООО \"Медгард\"",
          date: "2022.04.26",
          status: "Завершено"
        },
        {
          organization: "ООО \"Улыбка\"",
          date: "2021.04.25",
          status: "Завершено"
        },
        {
          organization: "ООО \"Центр Медицинских услуг\"",
          date: "2020.04.24",
          status: "Завершено"
        },
        {
          organization: "ООО \"Медгард\"",
          date: "2021.04.24",
          status: "Отменено"
        },
      ],


      list: [
        {
          organization: {
            name: "ООО \"Центр Медицинских услуг\"",
            address: "ул. Белорусская, д. 99"
          },
          employee: {
            name: "Артём",
            surname: "Горбатюк",
            patronymic: "Андреевич",
          },
          date: "2023.04.27",
          status: "Завершено"
        },
        {
          organization: {
            name: "ООО \"Медгард\"",
            address: "ул. Белорусская, д. 100"
          },
          employee: {
            name: "Артём",
            surname: "Горбатюк",
            patronymic: "Андреевич",
          },
          date: "2022.04.26",
          status: "Завершено"
        },
        {
          organization: {
            name: "ООО \"Улыбка\"",
            address: "ул. Белорусская, д. 101"
          },
          employee: {
            name: "Артём",
            surname: "Горбатюк",
            patronymic: "Андреевич",
          },
          date: "2021.04.25",
          status: "Завершено"
        },
        {
          organization: {
            name: "ООО \"Центр Медицинских услуг\"",
            address: "ул. Белорусская, д. 99"
          },
          employee: {
            name: "Артём",
            surname: "Горбатюк",
            patronymic: "Андреевич",
          },
          date: "2020.04.24",
          status: "Завершено"
        },
        {
          organization: {
            name: "ООО \"Медгард\"",
            address: "ул. Белорусская, д. 100"
          },
          employee: {
            name: "Артём",
            surname: "Горбатюк",
            patronymic: "Андреевич",
          },
          date: "2021.04.24",
          status: "Отменено"
        },
      ],

      headers: [
        {
          text: 'Медицинская организация',
          align: 'start',
          value: 'organization',
        },
        {text: 'Дата', value: 'date'},
        {text: 'Статус', value: 'status'},
      ],
      desserts: [
        {
          name: 'Frozen Yogurt',
          calories: 159,
          fat: 6.0,
          carbs: 24,
          protein: 4.0,
          iron: 1,
        },
        {
          name: 'Ice cream sandwich',
          calories: 237,
          fat: 9.0,
          carbs: 37,
          protein: 4.3,
          iron: 1,
        },
        {
          name: 'Eclair',
          calories: 262,
          fat: 16.0,
          carbs: 23,
          protein: 6.0,
          iron: 7,
        },
        {
          name: 'Cupcake',
          calories: 305,
          fat: 3.7,
          carbs: 67,
          protein: 4.3,
          iron: 8,
        },
        {
          name: 'Gingerbread',
          calories: 356,
          fat: 16.0,
          carbs: 49,
          protein: 3.9,
          iron: 16,
        },
        {
          name: 'Jelly bean',
          calories: 375,
          fat: 0.0,
          carbs: 94,
          protein: 0.0,
          iron: 0,
        },
        {
          name: 'Lollipop',
          calories: 392,
          fat: 0.2,
          carbs: 98,
          protein: 0,
          iron: 2,
        },
        {
          name: 'Honeycomb',
          calories: 408,
          fat: 3.2,
          carbs: 87,
          protein: 6.5,
          iron: 45,
        },
        {
          name: 'Donut',
          calories: 452,
          fat: 25.0,
          carbs: 51,
          protein: 4.9,
          iron: 22,
        },
        {
          name: 'KitKat',
          calories: 518,
          fat: 26.0,
          carbs: 65,
          protein: 7,
          iron: 6,
        },
      ],
    }
  }
}
</script>

<style scoped>

</style>