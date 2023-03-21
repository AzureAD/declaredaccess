import { ReadyCheck } from "./ReadyCheck";
//import { useIsAuthenticated } from "@azure/msal-react";

export class AccountReadyCheck implements ReadyCheck {

    checkIfReady(): Promise<boolean> {
        //const isAuthenticated = useIsAuthenticated();
        return Promise.resolve(true);
    }

}