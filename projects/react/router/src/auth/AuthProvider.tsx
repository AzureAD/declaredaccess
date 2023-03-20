import { PublicClientApplication } from "@azure/msal-browser";
import { Configuration } from "@azure/msal-browser/dist/config/Configuration";
import { MsalProvider } from "@azure/msal-react";
import { PropsWithChildren } from "react";
import { ApiClient } from "./ApiClient";
import { AuthContext, IAuthContext } from "./AuthContext";

export type AuthProviderProps = PropsWithChildren<{
    clientId: string,
    redirectUri: string,
    providerConfig?: any,

}>;

export function AuthProvider({clientId, redirectUri, providerConfig, children}: AuthProviderProps) : React.ReactElement {

    let msalConfig: Configuration;

    if(providerConfig !== undefined) {
        msalConfig = providerConfig as Configuration;
    }else{
        msalConfig = {
            auth: {
            clientId: clientId,
            authority: "https://login.microsoftonline.com/consumers",
            redirectUri: redirectUri
            },
            cache: {
            cacheLocation: "sessionStorage", // This configures where your cache will be stored
            storeAuthStateInCookie: false, // Set this to "true" if you are having issues on IE11 or Edge
            }
        };
    }

    
    const msalInstance = new PublicClientApplication(msalConfig);
    const apis = [{endpoint: "https://graph.microsoft.com"}];
    const apiClient = new ApiClient(msalInstance, apis );

    const contextValue : IAuthContext = {
        apis : apis,
        navigateToOnInteractionNeeded : "/authproblem",
        apiClient: apiClient
    };

    return (
        <AuthContext.Provider value={contextValue}>
        <MsalProvider instance={msalInstance}>
            {children}
        </MsalProvider>
        </AuthContext.Provider>
    )

}