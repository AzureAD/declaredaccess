import { SilentRequest } from "@azure/msal-browser";

/**
 * Details of the silent request that failed to use in resolution via interaction by the end user
 * 
 * silentRequest: The request that failed
 * subErrorCode: An error code returned by STS which indicates whether the error can be resolved and the level of difficulty for the end user
 * challenge: Any challenge returned by the token endpoint relative to conditional access policy (expressed as a claims request)
 */
export type InteractionRequiredDetails = {
    silentRequest: SilentRequest,
    subErrorCode: any,
    challenge: any
}