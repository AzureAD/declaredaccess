import { OIDC_DEFAULT_SCOPES } from '@azure/msal-common';
import { useMsal, useIsAuthenticated } from '@azure/msal-react';
import { Navigate, useLocation } from 'react-router-dom';

export type SignInProps = {
    buttonText? : string,
    buttonClassName? : string,
    messageText? : string,
    messageClassName? : string
};

export function SignIn({buttonText = "Sign In", buttonClassName = "", messageClassName = "", messageText="Sign in to get started with the app!"}: SignInProps){

    const { instance } = useMsal();
    const isAuthenticated = useIsAuthenticated();
    const {state} = useLocation();

    const loginRequest = {
        scopes: OIDC_DEFAULT_SCOPES.concat(["https://graph.microsoft.com//.default"])
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
            <>
            <div className={messageClassName}>{messageText}</div>
            <button className={buttonClassName} onClick={() => handleLogin()} >{buttonText}</button>
            </>
        )
    }
    
}

export default SignIn;