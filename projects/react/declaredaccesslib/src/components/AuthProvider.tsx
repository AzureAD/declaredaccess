import { PublicClientApplication } from "@azure/msal-browser";
import { Configuration } from "@azure/msal-browser/dist/config/Configuration.js";
import { MsalProvider } from "@azure/msal-react";
import { PropsWithChildren } from "react";
import { NavigateFunction } from "react-router-dom";
import { ApiClient, API } from "../context/ApiClient.js";
import { AuthContext, IAuthContext } from "../context/AuthContext.js";
import { TenantAlias } from "../context/TenantAlias.js";

export type AuthProviderProps = PropsWithChildren<{
    clientId: string,
    redirectUri: string,
    navigate: NavigateFunction,
    tenantAlias?: TenantAlias,
    apis?: API[],
    
    providerConfig?: any,

}>;

/**
 * 
 * @param clientId: required: The client id (app id) associated with the app registration in Microsoft Identity (AAD)
 * @param redirectUri: required: The redirect uri associated with the app registration in Microsoft Identity (AAD)
 * @param apis: optional: An array of API type that should include any APIs that you will call in your application that are protected by AAD.  Defaults to https://graph.microsoft.com
 * @param providerConfig: optional: Additional provider configuration (reserved)
 * @returns 
 */
export function AuthProvider({clientId, redirectUri, navigate, tenantAlias="common", apis = [{endpoint: "https://graph.microsoft.com"}], providerConfig, children}: AuthProviderProps) : React.ReactElement {

    let msalConfig: Configuration;

    if(providerConfig !== undefined) {
        msalConfig = providerConfig as Configuration;
    }else{
        msalConfig = {
            auth: {
            clientId: clientId,
            authority: "https://login.microsoftonline.com/".concat(tenantAlias),
            redirectUri: redirectUri
            },
            cache: {
            cacheLocation: "sessionStorage", // This configures where your cache will be stored
            storeAuthStateInCookie: false, // Set this to "true" if you are having issues on IE11 or Edge
            }
        };
    }

    
    const msalInstance = new PublicClientApplication(msalConfig);
    const apiClient = new ApiClient(msalInstance, apis, navigate);

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