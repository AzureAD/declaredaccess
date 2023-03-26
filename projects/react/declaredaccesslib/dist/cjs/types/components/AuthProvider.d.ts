import { PropsWithChildren } from "react";
import { NavigateFunction } from "react-router-dom";
import { API } from "../context/ApiClient.js";
import { TenantAlias } from "../context/TenantAlias.js";
export type AuthProviderProps = PropsWithChildren<{
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
export declare function AuthProvider({ clientId, redirectUri, navigate, tenantAlias, apis, providerConfig, children }: AuthProviderProps): React.ReactElement;
