import React, { useState } from 'react';
import sendPost from '../api_controller/ApiPost';
import "./Register.css"

const sendRegisterDataToBackend = async (data, setError) => {
    try {
        await sendPost('http://localhost:8081/api/v1/user/newUser', data);
    } catch (error) {
        setError('An error occurred while registering. Please try again.');
    }
}

const ReturnRegisterForm = () => {
    const [name, setName] = useState('');
    const [password, setPassword] = useState('');
    const [repeatedPassword, setRepeatedPassword] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (password !== repeatedPassword) {
            setError('Passwords do not match.');
            return;
        }

        const data = {
            name: name,
            password: password
        };

        console.log("Form data:", data); // Log form data
        await sendRegisterDataToBackend(data, setError);
    }

    return (
        <form onSubmit={handleSubmit} className="form">
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
            <input
                id="repeatedPassword"
                type="password"
                className="input"
                placeholder="repeat password"
                value={repeatedPassword}
                onChange={(e) => setRepeatedPassword(e.target.value)}
            />
            <button type="submit" className="button">Submit</button>
            {error && <p className="paragraf" style={{ color: 'red' }}>{error}</p>}
        </form>
    );
}

export default ReturnRegisterForm;