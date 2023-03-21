import { useMsal, useIsAuthenticated } from '@azure/msal-react';
import { Navigate, useLocation } from 'react-router-dom';
//import logoDark from '../assets/ms-symbollockup_signin_dark_short.svg';
import logoLight from '../assets/ms-symbollockup_signin_light_short.svg';

function SignIn(){

    const { instance } = useMsal();
    const isAuthenticated = useIsAuthenticated();
    const {state} = useLocation();

    const loginRequest = {
        scopes: ["User.Read"]
    };

    const handleLogin = () => {
        instance.loginRedirect(loginRequest).catch(e => {
            console.log(e);
        });
    }

    if(state !== undefined){
        if(!isAuthenticated){
            if(state){
                window.history.replaceState({}, document.title)
                handleLogin();
            }
        }
    }

    if(isAuthenticated){
        return <Navigate to="/" replace />;
    }else{
        return (
            <div className="flex items-center justify-center h-screen">
                <img src={logoLight} className="" onClick={() => handleLogin()} alt="Sign in button" />
            </div>
        )
    }
    
}

export default SignIn;