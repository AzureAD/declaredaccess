import { IPublicClientApplication } from "@azure/msal-browser";
import * as React from "react";
import { IApiClient } from "./ApiClient";

export type API = {
    endpoint: string,
    aadAppId?: string
}

export interface IAuthContext {
    apis: API[],
    navigateToOnInteractionNeeded: string,
    apiClient: IApiClient | undefined
}

/*
 * Default context implementation
 */
const defaultAuthContext: IAuthContext = {
    apis : [{endpoint: "https://graph.microsoft.com"}],
    navigateToOnInteractionNeeded : "/authproblem",
    apiClient: undefined
};

export const AuthContext = React.createContext<IAuthContext>(
    defaultAuthContext
);
export const AuthConsumer = AuthContext.Consumer;