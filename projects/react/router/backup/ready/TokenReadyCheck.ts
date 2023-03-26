import { ReadyCheck } from "./ReadyCheck";

/**
 * Defaults to Bearer for scheme if not provided
 * 
 * TODO: scheme should be a enum
 * TODO: ideally we'd know which scopes were authorizable 
 *       for each Microsoft account type (Assume Same for now)
 */
export type TokenReadyCheckParams = {
    scopes: string;
    scheme?: string;
}

export class TokenReadyCheck implements ReadyCheck {
    navigateToGetReady: string = "";
    isReady: boolean = false;
    tokenReadyParameters: TokenReadyCheckParams;

    checkIfReady(): Promise<boolean> {
        this.isReady = true;
        return Promise.resolve(true);

    }

    constructor(params: TokenReadyCheckParams){
        this.tokenReadyParameters = params;
    }

}

