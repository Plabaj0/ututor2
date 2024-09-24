CREATE TABLE IF NOT EXISTS app_user
(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name      VARCHAR(100) NOT NULL UNIQUE,
    password  VARCHAR(100) NOT NULL,
    role      VARCHAR(50)  NOT NULL
);

CREATE TABLE IF NOT EXISTS chat_information
(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    threadId      VARCHAR(100) NOT NULL,
    assistantId   VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS developer_user_story
(
    chat_id  UUID,
    app_user_id UUID,
    FOREIGN KEY (chat_id) REFERENCES chat_information (id),
    FOREIGN KEY (app_user_id) REFERENCES app_user (id)
);