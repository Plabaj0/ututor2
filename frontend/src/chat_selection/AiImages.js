import React from 'react';
import smallAi from "../utils/images/smallAi.png";
import mediumAi from "../utils/images/mediumAi.png";
import hardAi from "../utils/images/hardAi.png";
import {aiOnClick} from "./DisableButton";


const AiSelector = () => {

    return (
        <>
            <style>
                {`
                .aiImg {
                    border-radius: 15px;
                    margin: 10px;

                }
            `}
            </style>
            <img
                src={smallAi}
                className="aiImg"
                id="small"
                width="250px"
                height="300px"
                onClick={() => aiOnClick('small')}
                alt="Small AI"
            />
            <img
                src={mediumAi}
                className="aiImg"
                id="medium"
                width="250px"
                height="300px"
                onClick={() => aiOnClick('medium')}
                alt="Medium AI"
            />
            <img
                src={hardAi}
                className="aiImg"
                id="large"
                width="250px"
                height="300px"
                onClick={() => aiOnClick('large')}
                alt="Hard AI"
            />
        </>
    );
};

export default AiSelector;