import React, {useEffect, useState} from "react";
import ApiPostWithAuth from "../api_controller/ApiPostWithAuth";
import {createAssistant, createThread, sendMessage} from "../assistant_api/Api";
import {getInstruction} from "../conversation_chat/InstructionHandler";
import getNameFromJwt from "../utils/GetNameFromJwt";
import {useNavigate} from "react-router-dom";


const StartingFunction = () => {
    const [isButtonDisabled, setIsButtonDisabled] = useState(false);
    const navigate = useNavigate();
    const name = getNameFromJwt();

    const handleStart = async () => {
        setIsButtonDisabled(true);
        try {
            const threadId = await createThread();
            const assistantId = await createAssistant(getInstruction());

            const data = {
                assistantId,
                threadId,
            };

            await ApiPostWithAuth(`http://localhost:8081/api/v1/developer/${name}/newChat`, data);
            await sendMessage("start first", threadId, assistantId);
            navigate(`/chat/${name}`);
        } catch (error) {
            console.error("Error starting chat:", error);
            setIsButtonDisabled(false);
        }
    };

    return (
        <>
            <button
                className="start-button"
                id="start-button"
                disabled={isButtonDisabled}
                onClick={handleStart}
            >
                Start
            </button>
            <input
                className="topic-holder"
                id="topic"
                placeholder="Topic..."
            />
        </>
    );
};

export default StartingFunction;