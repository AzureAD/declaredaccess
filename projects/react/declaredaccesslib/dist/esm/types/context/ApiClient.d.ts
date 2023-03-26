import { AuthError, IPublicClientApplication } from "@azure/msal-browser";
import { NavigateFunction } from "react-router-dom";
export type API = {
    endpoint: string;
    aadAppId?: string;
};
export interface IApiClient {
    fetch(input: RequestInfo | URL, init?: RequestInit): Promise<Response>;
}
export declare class ApiClient implements IApiClient {
    msal: IPublicClientApplication;
    apis: API[];
    navigate: NavigateFunction;
    constructor(msal: IPublicClientApplication, apis: API[], navigate: NavigateFunction);
    fetch(input: RequestInfo | URL, init?: RequestInit): Promise<Response>;
    isURL(input: RequestInfo | URL): input is URL;
    isRequest(input: string | Request): input is Request;
    isAuthError(input: any): input is AuthError;
}
