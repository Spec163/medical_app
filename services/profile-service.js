import axios from 'axios';
import authHeader from '@/services/auth-header';

const API_URL = 'http://localhost:8088';
const PUBLIC_API_URL = API_URL + '/api/v1/public'

class ProfileService {
    getEmployeeInfo() {
        return axios
            .get(PUBLIC_API_URL + '/employee/info', {headers: authHeader()})
            .then(response => {
                return response.data;
            });
    };

    getActiveMeeting() {
        return axios
            .get(PUBLIC_API_URL + `/meetings/employee/active`, {headers: authHeader()})
            .then(response => {
                return response.data
            })
    };
}


export default new ProfileService();