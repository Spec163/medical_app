export default function authHeader() {
    const user = process.client ? JSON.parse(localStorage.getItem('user')) : null;

    if (user && user.token) {
        return {Authorization: 'Bearer ' + user.token};
    } else {
        return {};
    }
}