<template>
  <v-container>
    <v-main>
        <!--    <v-col cols="10" xl="6" lg="8" md="8" sm="11" xs="11">-->
        <!--      <v-flex xs12 sm6 class="my-3 text-center">-->
        <!--        <div class="subheading mb-xl-4 mb-md-4 mb-sm-2">-->
        <!--          Добавьте или измените дату прохождения медкомиссии:-->
        <!--        </div>-->
        <!--        <v-date-picker-->
        <!--            v-model="selectedDate"-->
        <!--            :min="startDate"-->
        <!--            :max="endDate"-->
        <!--            :allowed-dates="allowedDates"-->
        <!--            :events="arrayEvents"-->
        <!--            locale="ru-ru"-->
        <!--            event-color="red"-->
        <!--        ></v-date-picker>-->
        <!--        <v-btn-->
        <!--            class="mt-xl-8 mt-md-6 mt-sm-4 "-->
        <!--            color="primary"-->
        <!--            @click="createMeeting()"-->
        <!--        >-->
        <!--          Записаться-->
        <!--        </v-btn>-->
        <!--      </v-flex>-->
        <!--    </v-col>-->

        <v-row class="justify-center">
          <v-col cols="5" class="">
            <v-img max-width="250px" max-height="250px" :src="'/avatar.png'"></v-img>
          </v-col>
          <v-col cols="7">
            <v-card flat>
              <v-card-title>
                {{ employee.surname }} {{ employee.name }} {{ employee.patronymic }}
              </v-card-title>
              <v-card-title>
                Отдел: {{ employee.department }}
              </v-card-title>
              <v-card-title>
                Должность: {{ employee.jobTitle }}
              </v-card-title>
            </v-card>
          </v-col>
        </v-row>
        <v-row>
          <v-card flat>
            <v-card-title>
              Данные о медкомиссии
            </v-card-title>
            <v-card-text>
<!--              Дата: {{ getActiveMeeting.availableDate.date }}-->
            </v-card-text>
            <v-card-text>
              Статус: {{  }}
            </v-card-text>
            <v-card-actions>
              <v-btn
                  width="100px"
                  rounded
                  color="primary"
                  dark
                  @click="changeMeeting"
              >
                <span>Изменить</span>
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-row>
        <v-row>
          <v-card flat>
            <v-card-title>
              Данные о медицинской организации:
            </v-card-title>
            <v-card-text>
              Организация: {{ organization.name }}
            </v-card-text>
            <v-card-text>
              Адрес: {{ organization.address }}
            </v-card-text>
            <v-card-text>
              Телефон: {{ organization.phoneNumber }}
            </v-card-text>
          </v-card>
        </v-row>
    </v-main>
  </v-container>
</template>

<script>
// import {mapGetters} from 'vuex'

export default {
  name: "profile",
  data() {
    return {
      startDate: null,
      endDate: null,
      selectedDate: new Date().toISOString().substr(0, 10),
      arrayEvents: null,
      organization: {
        name: 'ООО "Центр Медицинских услуг"',
        address: 'ул. Белорусская, д. 99',
        phoneNumber: '+7555555555'
      },
      meeting: {
        date: '2023-04-22',
        status: 'APPROVED'
      },
      employee: {
        name: 'Артём',
        surname: 'Горбатюк',
        patronymic: 'Андреевич',
        jobTitle: 'Разработчик',
        department: 'Отдел развития персонала'
      }
    }
  },
  computed: {
    // ...mapGetters(['GET_EMPLOYEE_INFO', 'GET_ACTIVE_MEETING']),
    // getEmployeeInfo() {
    //   return this.GET_EMPLOYEE_INFO;
    // },
    // getActiveMeeting() {
    //   return this.GET_ACTIVE_MEETING;
    // },
  },
  methods: {
    // ...mapActions([
    //   'SET_EMPLOYEE_INFO', 'SET_ACTIVE_MEETING'
    // ]),
    allowedDates: val => parseInt(val.split('-')[2], 10) % 2 === 0,
    createMeeting() {
      console.log('Вызываем action и отправляем данный о записи == ', this.selectedDate)
    },
    changeMeeting() {
      console.log('Test1:: ', this.$store.getters.GET_ACTIVE_MEETING)
      // todo: call to change date
    }
  },
  created() {
    this.$store.dispatch('SET_EMPLOYEE_INFO');
    this.$store.dispatch('SET_ACTIVE_MEETING');
  },
}
</script>

<style scoped>

</style>