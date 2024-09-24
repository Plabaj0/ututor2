import React from 'react';
import ReactDOM from 'react-dom/client';
import ChoseChat from './chat_selection/Page';
import ChatPage from './conversation_chat/ChatPage';
import {createBrowserRouter, RouterProvider} from 'react-router-dom';
import LoginPage from "./login/LoginPage";
import RegisterPage from "./login/RegisterPage";
import DashboardPage from "./dashboard/DashboardPage";

const router = createBrowserRouter([
    {
        path: "/",
        element: <DashboardPage/>,
    },
    {
        path: "/selectChat",
        element: <ChoseChat/>,
    },
    {
        path: "/chat/:name",
        element: <ChatPage/>
    },
    {
        path: "/login",
        element: <LoginPage/>
    },
    {
        path: "/register",
        element: <RegisterPage/>
    }

]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <RouterProvider router={router}/>
    </React.StrictMode>
);