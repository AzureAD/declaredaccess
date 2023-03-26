import { useContext } from "react";
import { IAuthContext, AuthContext } from "./AuthContext";

/**
 * Returns Msal Context values
 */
export const useAuthContext = (): IAuthContext => useContext(AuthContext);