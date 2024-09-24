const OpenAI = require("openai");

const openai = new OpenAI({
    apiKey: process.env.REACT_APP_OPENAI_API_KEY,
    dangerouslyAllowBrowser: true
});

async function createAssistant(instruction) {
    const assistant = await openai.beta.assistants.create({
        name: "English Tutor",
        instructions: instruction,
        model: "gpt-4o",
    });
    return assistant.id
}

async function createThread() {
    const thread = await openai.beta.threads.create();
    return thread.id
}


async function sendMessage(content, threadId, assistantId) {
    await openai.beta.threads.messages.create(threadId, {
        role: "user",
        content: content
    });

    const run = await openai.beta.threads.runs.create(threadId, {
        assistant_id: assistantId,
    });

    let runStatus;
    do {
        await new Promise(resolve => setTimeout(resolve, 1500));


        runStatus = await openai.beta.threads.runs.retrieve(threadId, run.id);
    } while (runStatus.status !== "completed");
}

const showMessages = async (threadId, handleNewMessage) => {
    try {
        let messages = await openai.beta.threads.messages.list(threadId);

        for (const msg of messages.data) {
            if (msg.content[0].text.value) {
                if (msg.role === "assistant") {
                    console.log(msg.content[0].text.value[0]);
                    handleNewMessage(msg.content[0].text.value);
                    return msg.content[0].text.value;
                }
            } else {
                console.log("Message content format is unexpected:", msg);
            }
        }

        return null;
    } catch (error) {
        console.error("Error fetching messages:", error);
    }
};

const getUserMessages = async (threadId) => {
    let messages = await openai.beta.threads.messages.list(threadId);
    let messageData = [];
    let i = 0;

    for (const msg of messages.data) {
        if (msg.content[0].text.value) {
            if (msg.role === "user") {
                i++;
                messageData.push("messages: " + i + " content: " + msg.content[0].text.value);
            }
        }
    }
    messageData.pop();
    return messageData;
}

const getAllMessages = async (threadId) => {
    let messages = await openai.beta.threads.messages.list(threadId);
    let messageData = [];

    for (const msg of messages.data) {
        if (msg.content[0].text.value) {
            messageData.push(msg.content[0].text.value)
        }
    }
    return messageData;
}

// const waitForRunCompletion = async (threadId, runId) => {
//
//     console.log("runStatus: " + runStatus.status)
//     if (runStatus.status === "completed") {
//         console.log("runStatus: " + runStatus.status)
//         let messages = await openai.beta.threads.messages.list(threadId);
//         for (const msg of messages.data) {
//             if (msg.role === "assistant") {
//                 console.log(msg.content[0].text.value)
//                 return msg.content[0].text.value;
//             }
//         }
//     } else {
//         throw new Error('Run not completed');
//     }
// };
//
// return new Promise((resolve, reject) => {
//     setTimeout(async () => {
//         try {
//             const result = await waitForRunCompletion(threadId, run.id);
//             resolve(result);
//         } catch (error) {
//             reject(error);
//         }
//     }, 2000);
// });

module.exports = {
    createAssistant,
    createThread,
    sendMessage,
    showMessages,
    getUserMessages,
    getAllMessages,
}