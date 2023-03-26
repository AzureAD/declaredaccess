import { useMsal, useIsAuthenticated } from "@azure/msal-react";
import { useNavigate } from "react-router-dom";

export type SignInOutButtonProps = {
    signInButtonText? : string,
    signOutButtonText? : string,
    className? : string
}

export function SignInOutButton({signInButtonText = "Sign In", 
                            signOutButtonText = "Sign Out",
                            className = ""} : SignInOutButtonProps) {

    const { instance } = useMsal();
    const isAuthenticated = useIsAuthenticated();
    const navigate = useNavigate();
    let buttonText: string;

    if (isAuthenticated){
        buttonText = signOutButtonText;
    }else{
        buttonText = signInButtonText;
    }

    const signInOut = () => {
    
        if(isAuthenticated){
            instance.logoutRedirect().catch(e => {
                console.log(e);
            });
        }else{
            navigate("/signin", { replace: true, state: { pressed: true}} );
        }
    };

    return (
        <button onClick={() => signInOut()} className={className}>{buttonText}</button>
    );

}