import React from 'react';
import {levelOnClick} from "./DisableButton";

const levelsContainer = () => {
    return (
        <div>
            <button className="level" id="A1" onClick={() => levelOnClick('A1')}>A1
            </button>
            <button className="level" id="B1" onClick={() => levelOnClick('B1')}>B1</button>
            <button className="level" id="C1" onClick={() => levelOnClick('C1')}>C1</button>
        </div>
    )
}


export default levelsContainer;