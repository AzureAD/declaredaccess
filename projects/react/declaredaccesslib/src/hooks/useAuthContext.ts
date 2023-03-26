import { useContext } from "react";
import { IAuthContext, AuthContext } from "../context/AuthContext.js";

/**
 * Returns Msal Context values
 */
export const useAuthContext = (): IAuthContext => useContext(AuthContext);