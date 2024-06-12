import Login from '../pages/Login';
import Layout from "../components/Layout";
import Dashboard from "../pages/Dashboard";
import Signup from "../pages/Signup";

export const ROUTES = [
    {
        path: "/",
        element: <Layout/>,
        children: [

            {
                path: "",
                element: <Login/>,

            },

            {
                path: "/dashboard",
                element: <Dashboard/>,

            },

            {
                path: "/signup",
                element: <Signup/>,

            }

        ]

    }
]