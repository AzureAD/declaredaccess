/// <reference types="react" />
import { PropsWithChildren, ReactElement } from 'react';
import { NavigateFunction } from 'react-router-dom';
import { IPublicClientApplication, AuthError } from '@azure/msal-browser';

type API = {
    endpoint: string;
    aadAppId?: string;
};
interface IApiClient {
    fetch(input: RequestInfo | URL, init?: RequestInit): Promise<Response>;
}
declare class ApiClient implements IApiClient {
    msal: IPublicClientApplication;
    apis: API[];
    navigate: NavigateFunction;
    constructor(msal: IPublicClientApplication, apis: API[], navigate: NavigateFunction);
    fetch(input: RequestInfo | URL, init?: RequestInit): Promise<Response>;
    isURL(input: RequestInfo | URL): input is URL;
    isRequest(input: string | Request): input is Request;
    isAuthError(input: any): input is AuthError;
}

/**
 *  Tenant Aliases
 */
type TenantAlias = 
/** @member: use when your app is configured to support all Microsoft Identity sign in audiences (work or school and personal accounts) */
"common" | 
/** @member: use when your app is configured to support organization Microsoft Identity sign in audiences (work or school) */
"organizations" | 
/** @member: use when your app is configured to support consumer Microsoft Identity sign in audiences (personal accounts) */
"consumers";

type AuthProviderProps = PropsWithChildren<{
    clientId: string;
    redirectUri: string;
    navigate: NavigateFunction;
    tenantAlias?: TenantAlias;
    apis?: API[];
    providerConfig?: any;
}>;
/**
 *
 * @param clientId: required: The client id (app id) associated with the app registration in Microsoft Identity (AAD)
 * @param redirectUri: required: The redirect uri associated with the app registration in Microsoft Identity (AAD)
 * @param apis: optional: An array of API type that should include any APIs that you will call in your application that are protected by AAD.  Defaults to https://graph.microsoft.com
 * @param providerConfig: optional: Additional provider configuration (reserved)
 * @returns
 */
declare function AuthProvider({ clientId, redirectUri, navigate, tenantAlias, apis, providerConfig, children }: AuthProviderProps): React.ReactElement;

interface IAuthContext {
    apis: API[];
    navigateToOnInteractionNeeded: string;
    apiClient: IApiClient | undefined;
}

/**
 * Returns Msal Context values
 */
declare const useAuthContext: () => IAuthContext;

type InteractionRequiredProps = {
    buttonText?: string;
    buttonClassName?: string;
    messageText?: string;
    messageClassName?: string;
};
declare function InteractionRequired({ buttonText, messageText, buttonClassName, messageClassName }: InteractionRequiredProps): JSX.Element;

type SignInProps = {
    buttonText?: string;
    buttonClassName?: string;
    messageText?: string;
    messageClassName?: string;
};
declare function SignIn({ buttonText, buttonClassName, messageClassName, messageText }: SignInProps): JSX.Element;

type SignInOutButtonProps = {
    signInButtonText?: string;
    signOutButtonText?: string;
    className?: string;
};
declare function SignInOutButton({ signInButtonText, signOutButtonText, className }: SignInOutButtonProps): JSX.Element;

type ReadyCheckRouteProps = {
    signInRoute?: string;
    interactionRoute?: string;
};
declare function ReadyCheckRoute({ signInRoute }: {
    signInRoute?: string | undefined;
}): ReactElement | any;

export { API, ApiClient, AuthProvider, IAuthContext, InteractionRequired, InteractionRequiredProps, ReadyCheckRoute, ReadyCheckRouteProps, SignIn, SignInOutButton, SignInOutButtonProps, SignInProps, TenantAlias, useAuthContext };
