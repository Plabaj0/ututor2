import React, {useEffect, useRef, useState} from 'react';
import getNameFromJwt from "../utils/GetNameFromJwt";
import apiGetWithAuth from "../api_controller/ApiGetWithAuth";
import Modal from 'react-modal';
import "./ChatPage.css"
import {
    createAssistant,
    createThread, getAllMessages,
    getUserMessages,
    sendMessage,
    showMessages
} from "../assistant_api/Api";
import {faPencil, faSpoon} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

Modal.setAppElement('#root');

const DashboardPage = ({ threadId }) => {
    const [modalIsOpen, setModalIsOpen] = useState(false);
    const [updatedMessages, setUpdatedMessages] = useState([]);
    const [splitedMessages, setSplitedMessages] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const openModal = () => {
        setModalIsOpen(true);
    };

    const closeModal = () => {
        setModalIsOpen(false);
    };

    const StartAnalyzing = async () => {
        setLoading(true);
        setError(null);

        try {
            let response = await getUserMessages(threadId);
            let str_response = response.join(', ');
            console.log('Initial messages:', str_response);

            const threadResponse = await createThread();
            const assistantResponse = await createAssistant(
                "Your task will be to analyze the sentences in terms of grammar and tense\n" +
                "rules:\n" +
                "-each rule must be separated by such characters /*/\n" +
                "-first message is the user's message\n" +
                "-the second one is the user's sentence that you corrected if sentence is correct say it\n" +
                "-indicate the error made by the user and briefly explain how to correct it separate each error by characters %%\n" +
                "-provide a few words that will enrich the sentence but dont repeat this sentence \n" +
                "example:\n" +
                "the message you get: messages: 1 content: I usually play video games but also with my friends and we play a lot of fun board games and having fun, messages: 2 content: yes I usually playing games so it's my favorite,\n" +
                "your output:\n" +
                "I usually play video games but also with my friends and we play a lot of fun board games and having fun /*/ " +
                "I usually play video games, but I also play a lot of fun board games with my friends. We have a lot of fun. /*/ " +
                "Error: Missing appropriate commas to separate different parts of the sentence.\n %%" +
                "Error: Unclear phrasing (\"but also with my friends\" and \"and having fun\"). %% \n" +
                "Correction: Using the correct tense (\"having fun\" should be \"we have a lot of fun\"). %% " +
                "/*/ yes I usually playing games so it's my favorite activity. " +
                "/*/ Yes, I usually play games, so it's my favorite activity. /*/\n" +
                "Error: Missing a capital letter at the beginning of the sentence.\n %%" +
                "Error: Missing appropriate commas.\n %%" +
                "Error: Incorrect tense usage (\"playing games\" should be \"play games\"). %%" +
                "Enriched words: 'understand,' 'comprehend,' 'appreciate'. "
            );

            console.log('Created thread ID:', threadResponse);
            console.log('Created assistant:', assistantResponse);

            await sendMessage(str_response, threadResponse, assistantResponse);

            const updatedMessages = await getAllMessages(threadResponse);
            const joinedMessages = updatedMessages.join(" ");
            const splitedMessages = joinedMessages.split("/*/");

            const furtherSplittedMessages = splitedMessages.map(message => message.split("%%"));

            setUpdatedMessages(updatedMessages);
            setSplitedMessages(furtherSplittedMessages);
            openModal();
            console.log('Updated messages:', updatedMessages);
        } catch (error) {
            setError("Error during analysis: " + error.message);
            console.error("Error during analysis:", error);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <button onClick={StartAnalyzing} disabled={loading} className="sameas">
                {loading ? 'Analyzing...' : 'Start Analyzing'}
            </button>
            {error && <p>{error}</p>}
            <Modal isOpen={modalIsOpen} onRequestClose={closeModal}>
                <h2>Analysis Results</h2>
                <button onClick={closeModal}>Close</button>
                <div>
                    {splitedMessages.map((messageGroup, groupIndex) => (
                        <div key={groupIndex}>
                            {messageGroup.filter(subMessage => subMessage.trim() !== '').map((subMessage, index) => (
                                <p className={
                                    groupIndex % 3 === 0 ? 'error-message' :
                                        groupIndex % 3 === 1 ? 'success-message' :
                                            'user-error-message'
                                } key={index}>
                                    {subMessage}
                                </p>
                            ))}
                        </div>
                    ))}
                </div>
            </Modal>
        </div>
    );
};

const Message = ({messages}) => {
    const [displayedMessages, setDisplayedMessages] = useState(messages.map(() => ''));

    useEffect(() => {
        if (messages.length > 0) {
            const newMessages = [...messages];
            const lastIndex = messages.length - 1;

            for (let i = 0; i < lastIndex; i++) {
                newMessages[i] = messages[i];
            }
            let currentMessage = '';
            messages[lastIndex].split('').forEach((char, charIndex) => {
                setTimeout(() => {
                    currentMessage += char;
                    setDisplayedMessages(prev => {
                        const updatedMessages = [...prev];
                        updatedMessages[lastIndex] = currentMessage;
                        return updatedMessages;
                    });
                }, charIndex * 60);
            });
        }
    }, [messages]);

    return (
        <div>
            {displayedMessages.map((msg, index) => (
                <div
                    id={`index-${index}`}
                    key={index}
                    className={`message-bubble ${index % 2 === 0 ? 'received' : 'sent'}`}
                    style={{display: index === 0 ? 'none' : 'block'}}
                >
                    {msg}
                </div>
            ))}
        </div>
    );
};

function ChatComponent() {
    const name = getNameFromJwt();
    const [threadId, setThreadId] = useState(null);
    const [assistantId, setAssistantId] = useState(null);
    const [messages, setMessages] = useState([]);
    const [userMessages, UserSetMessages] = useState([]);
    const hasShownMessages = useRef(false);
    const [voice, setVoice] = useState(true);
    let speech1 = new SpeechSynthesisUtterance()
    let voices = window.speechSynthesis.getVoices();
    let selectedVoice = voices.find(voice => voice.name === 'Google UK English Male' && voice.lang === 'en-GB');

    var speech = true;
    window.SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;


    const inputMessage1 = document.getElementById('inputMessage1');

    const handleVoiceRecognition = () => {
        const recognition = new (window.SpeechRecognition || window.webkitSpeechRecognition)();
        recognition.lang = 'en-US';
        recognition.interimResults = true;

        recognition.addEventListener('result', (e) => {
            const transcript = Array.from(e.results)
                .map(result => result[0])
                .map(result => result.transcript)

            inputMessage1.innerHTML = transcript;
        });

        if (speech === true) {
            recognition.start();
        }
        recognition.addEventListener('end', () => {
            if (speech === true) {
                handleVoiceSendButton(inputMessage1.value)
            }
        });
    };

    window.speechSynthesis.onvoiceschanged = () => {
        voices = window.speechSynthesis.getVoices();
        selectedVoice = voices.find(voice => voice.name === 'Google UK English Male' && voice.lang === 'en-GB');
    };


    const handleNewMessage = (newMessage) => {
        setMessages((prevMessages) => [...prevMessages, newMessage]);
        speech1.voice = selectedVoice;
        speech1.text = newMessage
        window.speechSynthesis.speak(speech1)
    };

    useEffect(() => {
        const initializeChat = async () => {
            try {
                const response = await apiGetWithAuth(`http://localhost:8081/api/v1/chats/${name}/ChatInformation`);
                setThreadId(response.threadId);
                setAssistantId(response.assistantId);

                if (!hasShownMessages.current) {
                    await showMessages(response.threadId, handleNewMessage);
                    hasShownMessages.current = true;
                }
            } catch (error) {
                console.error('Error initializing chat:', error);
            }
        };

        initializeChat();
    }, [name]);

    const handleVoiceSendButton = async (inputMessage) => {
        try {

            await sendMessage(inputMessage, threadId, assistantId);
            await showMessages(threadId, handleNewMessage);
            document.getElementById("inputMessage").value = '';
        } catch (error) {
            console.error('Error sending message:', error);
        }
    };

    const handleSendButton = async () => {
        try {
            let inputMessage = document.getElementById("inputMessage").value;
            UserSetMessages(inputMessage)
            await sendMessage(inputMessage, threadId, assistantId);
            await showMessages(threadId, handleNewMessage);
            document.getElementById("inputMessage").value = '';
        } catch (error) {
            console.error('Error sending message:', error);
        }
    };

    const changeVoiceIsOn = () => {
        setVoice(prevVoice => !prevVoice);
    }

    return (
        <div className="chat-container">
            <div id="newMessageBox" className="new-message-box">
                <h1 className="chat-header">Hello {name}</h1>
                <Message messages={messages} class="message-component"/>
            </div>
            <button onClick={changeVoiceIsOn} className="voice-toggle-button">
                {voice ? <FontAwesomeIcon icon={faSpoon} /> : <FontAwesomeIcon icon={faPencil} />}
            </button>
            {voice ? (
                <div className="voice-input-container">
                    <textarea id="inputMessage1" className="voice-textarea"></textarea>
                    <button onClick={handleVoiceRecognition} className="voice-start-button">Start Voice</button>
                </div>
            ) : (
                <div className="text-input-container">
                    <input id="inputMessage" type="text" className="text-input"/>
                    <button onClick={handleSendButton} className="send-button">Send</button>
                </div>
            )}
            <DashboardPage threadId={threadId} class="dashboard-page"/>
        </div>
    );
}

export default ChatComponent;