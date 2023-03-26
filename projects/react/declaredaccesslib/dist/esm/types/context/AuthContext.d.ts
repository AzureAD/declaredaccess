import * as React from "react";
import { IApiClient, API } from "./ApiClient.js";
export interface IAuthContext {
    apis: API[];
    navigateToOnInteractionNeeded: string;
    apiClient: IApiClient | undefined;
}
export declare const AuthContext: React.Context<IAuthContext>;
export declare const AuthConsumer: React.Consumer<IAuthContext>;
