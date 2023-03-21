import { useMsal, useIsAuthenticated } from '@azure/msal-react';
import { Navigate } from 'react-router-dom';

function InteractionRequired(){

    const { instance } = useMsal();
    const isAuthenticated = useIsAuthenticated();

    const loginRequest = {
        scopes: ["User.Read"]
    };

    const handleLogin = (loginType: string) => {
        if (loginType === "redirect") {
            instance.loginRedirect(loginRequest).catch(e => {
                console.log(e);
            });
        }
    }

    if(isAuthenticated){
        return <Navigate to="/" replace />;
    }else{
        return (
            <div className="flex dark:bg-[#282c34] bg-white align-middle flex-col min-h-screen">
                <div className="flex flex-col m-10 dark:bg-[#282c34] bg-white">
                    <h1 className="inline-block text-2xl sm:text-3xl font-extrabold text-slate-900 tracking-tight dark:text-slate-200">Sign In</h1>
                    <button></button>
                </div>
            </div>
        )
    }
    
}

export default InteractionRequired;