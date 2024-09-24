import React from 'react';
import {useNavigate} from "react-router-dom";
import Login from "../chat_selection/Login";
import DailyTips from "./DailyTips";
import './DashboardPage.css';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faBars, faPenToSquare, faRectangleXmark, faUser, faWindowMaximize} from "@fortawesome/free-solid-svg-icons";
import ShowSideChat from "../dashboard/sideChatSelection";

function DashboardPage() {
    const navigate = useNavigate();

    const ChatSelectPage = () => {
        navigate("/selectChat");
    }
    const registerPage = () => {
        navigate("/register");
    }

    return (
        <div>
            <div className="container1">
                <div className="specific-div item1">
                    <FontAwesomeIcon icon={faBars} className="icon"/>
                    <ShowSideChat/>
                </div>
            </div>
            <div className="container2">
                <div className="specific-div item2">
                    <FontAwesomeIcon icon={faRectangleXmark} className="icon"/>
                    < Login/>
                </div>
            </div>
                <div className="container3">
                <div className="specific-div item3" onClick={registerPage}>
                    <FontAwesomeIcon icon={faUser} className="icon"/> register
                </div>
            </div>
            <div className="container4">
                <div className="specific-div item4" onClick={ChatSelectPage}>
                    <FontAwesomeIcon icon={faWindowMaximize} className="icon"/> start chat
                </div>
            </div>
            <div className="container5">
                <div className="specific-div item5"><FontAwesomeIcon icon={faPenToSquare}/>
                    DailyTips
                    < DailyTips/>
                </div>
            </div>
        </div>
    );
}

export default DashboardPage;