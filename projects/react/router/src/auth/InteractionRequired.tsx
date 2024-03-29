import { useMsal, useIsAuthenticated } from '@azure/msal-react';
import { useNavigate, useLocation } from 'react-router-dom';
import { RedirectRequest } from '@azure/msal-browser';
import { InteractionRequiredDetails } from './InteractionRequiredDetails';

function InteractionRequired(){

    const { instance } = useMsal();
    const isAuthenticated = useIsAuthenticated();
    const {state} = useLocation();
    const navigate = useNavigate();

    let details: InteractionRequiredDetails | undefined = undefined;

    const isInteractionRequiredDetails = (input: any): input is InteractionRequiredDetails => {
        return (input as InteractionRequiredDetails).silentRequest !== undefined;
    }

    if(isAuthenticated){
        if(isInteractionRequiredDetails(state.interactionRequiredDetails)){
            details = state.interactionRequiredDetails;
        }else{
            //Log and send to home route
            console.log("unexpected: no failed silent request was provided via state");
            navigate("/", {replace: true});
        }
    }else{
        navigate("/", {replace: true});
    }
    
    if(details === undefined){
        throw new Error("unexpected: details of the failed request was undefined.");
    }

    const request: RedirectRequest = {
        scopes: details.silentRequest.scopes
    };

    const handleInteractive = async () => {
        
        //Using popup here for problem resolution to avoid dealing with redirect, events, handleRedirect etc....
        try{
            //We don't need the response
            await instance.acquireTokenPopup(request);
            navigate(-1);
        }catch(e){
            //We don't need to do anything here, but let's log the error
            console.log(e);
        }
        
        
    }
      
    return (
        <div className="flex flex-col items-center justify-center">
            <div className='text-white font-bold m-20'>Oops!  We ran into a problem trying to get a token.  Let's try and fix it!</div>
            <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" onClick={() => handleInteractive()}>Fix It!</button>
        </div>
    )
    
    
}


export default InteractionRequired;