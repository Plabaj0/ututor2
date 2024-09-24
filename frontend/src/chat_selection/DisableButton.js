let choseAi = [];
let choseLevel = [];
let isButtonDisabled = false;

const levelOnClick = (buttonID) => {
    const level = document.getElementById(buttonID);
    if (choseLevel.length === 0) {
        choseLevel.push(level);
        level.style.background = 'white';
    } else {
        choseLevel[0].style.background = '#ece5e5';
        choseLevel.length = 0;
        choseLevel.push(level);
        level.style.background = 'white';
    }
    disableButton();
};

const aiOnClick = (ai) => {
    const elementAi = document.getElementById(ai);

    if (choseAi.length === 0) {
        choseAi.push(elementAi);
        elementAi.style.border = '4px dashed yellow';
    } else {
        choseAi[0].style.border = '0px';
        choseAi.length = 0;
        choseAi.push(elementAi);
        elementAi.style.border = '4px dashed yellow';
    }
    disableButton();
};

const disableButton = () => {
    if (choseLevel.length === 1 && choseAi.length === 1) {
        return isButtonDisabled = true;
    } else {
        return isButtonDisabled = false;
    }
};

const aiOnClickValue = () => {
    return choseAi[0].id
}

const levelOnClickValue = () => {
    return choseLevel[0].id
}

export { levelOnClick,disableButton, aiOnClick, aiOnClickValue, levelOnClickValue };