import ApiLogin from "../api_controller/Login";
import "./Login.css";
import {useNavigate} from "react-router-dom";
import {useState} from "react";

const sendDataToBackend = async (name, password, navigate) => {
    const data = {
        name: name,
        password: password
    };

    try {
        await ApiLogin("http://localhost:8081/api/v1/user/login", data);

        navigate('/');
    } catch (error) {
        console.error("Login failed", error);
    }
}

const ReturnForm = () => {
    const [name, setName] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async () => {
        await sendDataToBackend(name, password, navigate);
    };

    if (localStorage.getItem("token")) {
        return (
            <h1>Now you can use the website without any restrictions</h1>
        );
    } else {
        return (
            <>
                <input
                    id="login"
                    placeholder="login"
                    className="input"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                />
                <input
                    id="password"
                    type="password"
                    placeholder="password"
                    className="input"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <button onClick={handleSubmit} className="button">Submit</button>
            </>
        );
    }
}

export default ReturnForm;