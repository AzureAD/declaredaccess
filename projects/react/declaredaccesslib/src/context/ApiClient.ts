import { AuthError, IPublicClientApplication } from "@azure/msal-browser";
import { NavigateFunction } from "react-router-dom";

export type API = {
    endpoint: string,
    aadAppId?: string
}

export interface IApiClient {
    fetch(input: RequestInfo | URL, init?: RequestInit) : Promise<Response>;
}

export class ApiClient implements IApiClient{

    msal: IPublicClientApplication;
    apis: API[];
    navigate: NavigateFunction;

    constructor(msal: IPublicClientApplication, apis: API[], navigate: NavigateFunction){
        this.msal = msal;
        this.apis = apis;
        this.navigate = navigate;
    }

    public async fetch(input: RequestInfo | URL, init?: RequestInit) : Promise<Response> {
        
        const {fetch: originalFetch} = window;
        const inputIsUrl = this.isURL(input);
        let resourceEndpointDomain = "";
        
        let request : Request | undefined;
        let requestString: string | undefined;
        let requestURL: URL | undefined;

        //Find matching domain of the request in apis list
        //if no match do simply submit the request

        if(inputIsUrl){
            resourceEndpointDomain = (input as URL).hostname;
                requestURL = input as URL;
                requestString = requestURL.toString();
        }else{
            if(this.isRequest(input)){
                resourceEndpointDomain = new URL((input as Request).url).hostname;
                request = input as Request;
            }else{
                resourceEndpointDomain = new URL(input).hostname;
                requestString = input as string;
            }
        }
        
        //TODO: includes is case sensitive... ensure same case....
        const match: API | undefined = this.apis.find(element => element.endpoint.includes(resourceEndpointDomain));
        let scope: string;
    
        if(match !== undefined){
            if(match.aadAppId !== undefined){
                scope = match.aadAppId;
            }else{
                scope = match?.endpoint;
            }
        }else{
            return originalFetch(input, init);
        }

        scope = scope.concat("/.default");

        //Invalid scope for testing:
        //scope = scope.concat("");
        
        const tokenRequest = {
            scopes: [scope],
            account: this.msal.getAllAccounts()[0]
        };

        let apiResponse : Response | undefined;

        try{
            const tokenResponse = await this.msal.acquireTokenSilent(tokenRequest);
            const accessToken = tokenResponse.accessToken;
            const authorizationHeaderValue: string = "Bearer ".concat(accessToken);
            if(request !== undefined){
                request.headers.append("Authorization", authorizationHeaderValue);
                apiResponse = await originalFetch(request);
            }
            else {
                let headersInit: HeadersInit = {'Authorization': authorizationHeaderValue};
                let requestInit: RequestInit = { 'headers': headersInit}
                if(requestString !== undefined){
                    apiResponse = await originalFetch(requestString, requestInit);
                }else{
                    //throw this should never happen
                    throw new Error("unexpected: no requestString");
                }
            }

            if(apiResponse !== undefined){
                return apiResponse
            }else{
                throw new Error("unexpected: no api response");
            }
            
            
        }catch(e: any) {
            //TODO: Handle errors from acquire token silent.... including retry
            if(this.isAuthError(e)){
                let error = e as AuthError;
                let state = {
                    interactionRequiredDetails: {
                        silentRequest: tokenRequest,
                        subErrorCode: error.subError,
                        challenge: undefined
                    }
                };
                
                this.navigate("/interactionrequired", {replace:true, state:state});
            }
            throw e;
        }

    }

    isURL(input: RequestInfo | URL): input is URL {
        return (input as URL).href !== undefined;
    }

    isRequest(input: string | Request): input is Request {
        return (input as Request).body !== undefined;
    }

    isAuthError(input: any): input is AuthError {
        return (input as AuthError).errorCode !== undefined;
    }

}
