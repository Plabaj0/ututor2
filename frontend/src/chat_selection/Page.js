import React from "react";
import StartButton from "./StartButton";
import startTitle from "../utils/images/startTitle.png";
import subtitle from "../utils/images/subtitle.png";
import AiImages from "./AiImages";
import LevelButtons from "./LevelButtons";
import Login from "./Login";
import "./Page.css"

const MyComponent = () => {
    return (
        <div>
            <div className="front-container">
            </div>
            <div className="button-container">
                <LevelButtons/>
            </div>
            <br/>
            <div className="img-container">
                <AiImages/>
            </div>
            <div className="placeholder-container">
                <StartButton/>
            </div>
        </div>
    );
};

export default MyComponent;