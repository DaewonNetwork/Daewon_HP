// import axios from 'axios';
//
// const API_URL = 'http://localhost:8080/api/logout';
//
// const logout = () => {
//     const user = JSON.parse(localStorage.getItem('refreshToken'));
//     if (user && user.refreshToken) {
//         axios.post(API_URL, {}, {
//             headers: {
//                 'Authorization': 'Bearer ' + user.refreshToken
//             }
//         }).then(() => {
//             localStorage.removeItem('user');
//         }).catch(error => {
//             console.error("Logout error", error);
//         });
//     } else {
//         localStorage.removeItem('user');
//     }
// };
//
// export default {
//     logout
// };