import {jwtDecode} from "jwt-decode";

const useName = () => {
    const token = localStorage.getItem("token");
    if (token === null){
        return null
    }
    const decoded = jwtDecode(token);
    return decoded.sub;
}

export default useName;