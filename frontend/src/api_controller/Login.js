const login = async (url, data) => {
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });

    if (response.ok) {
        const data = await response.json();
        localStorage.setItem('token', data.token);
    } else {
        console.error('error');
    }
};

export default login;