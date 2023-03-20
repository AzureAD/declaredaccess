# Design Notes

## Requirements

- Get tokens required to complete specific API and inject them ahead of making the request
  - Handle token request failures based on sub_error code (message_only, etc...)
- Handle Conditional Access challenges from resource servers protected by AAD
  - 
  - Make a new silent token request using the provided challenge
- Detect when there is no current account and redirect to sign in
- Provide out of the box UI experiences
  - (Likely controversial, but we want end users to have the consistent user experiences around using Microsoft Identity)

## In/Out of Scope statements

- Microsoft work accounts (work or school accounts) only
- Single account application only
- Multiple APIs supported
- Microsoft as the authorization server only (No using a microsoft account to sign in store state and then adding other account to access other data belonging to the human being)
- Admin consent has been performed
- Sign in always prefers SSO
- Don't worry about multi-cloud
- Don't worry about multi-tenant (apps that access data in more than one tenant)