import {largeAi1, mediumAi1, smallAi1} from "../utils/Instructions";
import {aiOnClickValue, levelOnClickValue} from "../chat_selection/DisableButton";

const aiFunctions = {
    small: smallAi1,
    medium: mediumAi1,
    large: largeAi1,
};

export const getInstruction = () => {
    let topicHolder = document.getElementById('topic');
    let topicInformation = topicHolder.value;
    let chosenLevel = levelOnClickValue();
    let chosenAi = aiOnClickValue();

    console.log(topicInformation)
    console.log(chosenLevel)
    console.log(chosenAi)

    if (chosenAi === "small") {
        console.log("1");
        return aiFunctions["small"](chosenLevel, topicInformation);
    } else if (chosenAi === "medium") {
        console.log("2");
        return aiFunctions["medium"](chosenLevel, topicInformation);
    } else if (chosenAi === "large") {
        console.log("3");
        return aiFunctions["large"](chosenLevel, topicInformation);
    }
};

export default {getInstruction}