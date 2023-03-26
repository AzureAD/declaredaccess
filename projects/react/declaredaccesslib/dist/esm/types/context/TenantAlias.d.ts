/**
 *  Tenant Aliases
 */
export type TenantAlias = 
/** @member: use when your app is configured to support all Microsoft Identity sign in audiences (work or school and personal accounts) */
"common" | 
/** @member: use when your app is configured to support organization Microsoft Identity sign in audiences (work or school) */
"organizations" | 
/** @member: use when your app is configured to support consumer Microsoft Identity sign in audiences (personal accounts) */
"consumers";
