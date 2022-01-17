# Declared Access Overview

This project/repository is an experimental collection of libraries designed to simplify the use of identity (Identity Providers, Authorization Servers, etc...) in applications by:

* Allowing developers to declare their authentication and authorization needs for applications as a whole and for specific features or submodules
* Encapsulating the interaction with identity services in the library code
* Handling via HTTP request/response interception:
  * Injecting required security tokens to access APIs
  * Responding to identity challenges from APIs on behalf of the developer
* Pro-actively acquiring the tokens necessary for the operation of your app
* Tracking issues relative to access on behalf of the application and only optionally requiring the application to explicitly start an identity related UI operation.

><b>Nothing here is "done", in fact we're just starting.  We're actively looking for your input/feedback about our ideas and implementation across different platforms and scenarios.  Please consider watching the project by clicking the Watch button above.  Consider submitting your suggestions/thoughts/feedback as issues.  We'll be setting up appropriate tags, but don't want to make the process onerous... if you have any idea we encourage you to just scribble it out.  Identity folks can be a bit wonkish, but this is safe place, so don't feel you have to have everything "figured out", "thought out", etc....</b>


## Imperative vs. Declarative

While there are academic definitions of imperative and declarative programming... Here we'll try to give a more practical definition:

<b>Imperative</b>: Perform a series of prescribed steps to achieve a desired outcome

<b>Declarative</b>: Say what your desired outcome is and don't worry about how's it's accomplished

To date, most auth (authentication and/or authorization) libraries are designed to be used imperatively, including those from Microsoft (ADAL, MSAL, etc...).  ADAL and MSAL are oAuth Libraries, they don't explicitly support OpenId Connect in most cases.  Even with this limited scope Microsoft identity services provide numerous protections in the form of Conditional Access that result in client developers having to know how those things work in order to successfully use the libraries.

<b>Here's an example of the imperative steps that could be necessaring using MSAL to access Microsoft Graph for example</b>:

- Use acquireTokensilent to get a token for MS Graph (ex. mail.read)
- Get an access token back from MSAL via callback/promise/future/etc...
- Create an HTTP request for MS Graph mail resource injecting the access token received into the authorization header of the HTTP request
- If you get a error/exception back from MS Graph (Something other than 200) 
- parse out the challenge (claims request) from HTTP Response
- Submit that challenge as part of a second aquireTokenSilent for the same scope (mail.read)
- Get an error/exception back from MSAL via callback/promise/future/etc...
- Take the same challenge received and submit an interactive request (aquireToken)

Instead of this.... imagine....

You, the developer, declared:

- A list of mandatory and optional features in your application
- A list of APIs that your code would need to access
- A list of API/Scopes that each of your features required
- Which code modules / components belonged to which features
- Added provided indentity interceptors to your HTTP stack, OR used an HTTP stack provided by the libraries themselves...

<b>There is no get token method.  You, the developer, just make your HTTP request.  It either succeeds or fails.</b>

<b>If it fails and it can be fixed by the user, you just ask the library to fix it for your user.</b>

<b>You don't need to know how identity works in order to use it.</b>

The correct use of identity services is hard and authentication and authorization services continue to evolve, improving the security, reliability and performance of what they offer to the applications and services that they help protect. Having declarative libraries that encapsulate the interaction with the identity services is key to all apps being able to quickly and easily adopt the latest on offer.

## Contributing

This project welcomes contributions and suggestions.  Most contributions require you to agree to a
Contributor License Agreement (CLA) declaring that you have the right to, and actually do, grant us
the rights to use your contribution. For details, visit https://cla.opensource.microsoft.com.

When you submit a pull request, a CLA bot will automatically determine whether you need to provide
a CLA and decorate the PR appropriately (e.g., status check, comment). Simply follow the instructions
provided by the bot. You will only need to do this once across all repos using our CLA.

This project has adopted the [Microsoft Open Source Code of Conduct](https://opensource.microsoft.com/codeofconduct/).
For more information see the [Code of Conduct FAQ](https://opensource.microsoft.com/codeofconduct/faq/) or
contact [opencode@microsoft.com](mailto:opencode@microsoft.com) with any additional questions or comments.

## Trademarks

This project may contain trademarks or logos for projects, products, or services. Authorized use of Microsoft 
trademarks or logos is subject to and must follow 
[Microsoft's Trademark & Brand Guidelines](https://www.microsoft.com/en-us/legal/intellectualproperty/trademarks/usage/general).
Use of Microsoft trademarks or logos in modified versions of this project must not cause confusion or imply Microsoft sponsorship.
Any use of third-party trademarks or logos are subject to those third-party's policies.
