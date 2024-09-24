import React from 'react';
import {useNavigate} from 'react-router-dom';


const Login = () => {
    const navigate = useNavigate();

    const handleLogout = () => {
        console.log('Logging out with token:', localStorage.getItem("token"));
        localStorage.removeItem("token");
        console.log('Logging out with token:', localStorage.getItem("token"));
        navigate('/login');
    };

    const navigateToLoginPage = async () => {
        navigate('/login');
    };
    if (localStorage.getItem("token")) {

        return <a onClick={handleLogout}> log out </a>;
    } else {

        return (
            <div className="login">
                <a onClick={navigateToLoginPage}>
                    Login
                </a>
            </div>
        );
    }
    ;
}
export default Login;