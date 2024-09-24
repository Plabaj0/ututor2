import {useEffect, useState} from "react";
import ApiGetWithAuth from "../api_controller/ApiGetWithAuth";
import getNameFromJwt from "../utils/GetNameFromJwt";

function ShowSideChat() {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const name = getNameFromJwt();

    useEffect(() => {
        const getData = async () => {
            if (localStorage.getItem("token") === null) {
                setLoading(false);
                setError("No data found, try to login");
                return;
            }

            try {
                const response = await ApiGetWithAuth(`http://localhost:8081/api/v1/developer/${name}/selectChat`);
                setData(response);
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };

        getData();
    }, [name]);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error: {error}</p>;

    return (
        <div>
            <div className="scroll-container">
                {data.map((item, index) => (
                    <a key={index} className="scroll-item">{item.id}</a>
                ))}
            </div>
        </div>
    );
}

export default ShowSideChat;