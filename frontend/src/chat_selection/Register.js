import React from 'react';
import {useNavigate} from 'react-router-dom';

const Register = () => {
    const navigate = useNavigate();

    if (localStorage.getItem("token")) {
        return null
    } else {

        const navigateToRegisterPage = async () => {
            navigate('/register');
        };


        return (
            <a onClick={navigateToRegisterPage}>
                Register
            </a>
        );
    };
}
export default Register;