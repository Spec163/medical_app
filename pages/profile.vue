<template>
  <v-container>
    <v-col cols="10" xl="6" lg="8" md="8" sm="11" xs="11">
      <v-flex xs12 sm6 class="my-3 text-center">
        <div class="subheading mb-xl-4 mb-md-4 mb-sm-2">
          Добавьте или измените дату прохождения медкомиссии:
        </div>
        <v-date-picker
            v-model="selectedDate"
            :min="startDate"
            :max="endDate"
            :allowed-dates="allowedDates"
            :events="arrayEvents"
            locale="ru-ru"
            event-color="red"
        ></v-date-picker>
        <v-btn
            class="mt-xl-8 mt-md-6 mt-sm-4 "
            color="primary"
            @click="createMeeting()"
        >
          Записаться
        </v-btn>
      </v-flex>
    </v-col>
  </v-container>
</template>

<script>
export default {
  name: "profile",
  data() {
    return {
      startDate: null,
      endDate: null,
      selectedDate: new Date().toISOString().substr(0, 10),
      arrayEvents: null,
    }
  },
  created() {
    /*
    todo: запрашиваем несколько экшенов:
   - активный Schedule и заполняем доступные для выбора даты
   - активную встречу
   */

    // this.$store.dispatch('')
  },
  computed: {
    fillMeetings() {

    }
  },
  methods: {
    allowedDates: val => parseInt(val.split('-')[2], 10) % 2 === 0,
    createMeeting() {
      console.log('Вызываем action и отправляем данный о записи == ', this.selectedDate)
    }
  }
}
</script>

<style scoped>

</style>