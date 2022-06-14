# Introduction

This document contains a list of functional requirements/user stories/use cases for a developer to integrate with Microsoft Identity and other identity systems without writing imperative code.  

## Assumptions

### Developers

The following is a list of assumptions about developers looking to make use of the Microsoft Identity platform or other Identity Services.  

- A developer may or may not be familiar with identity protocols and libraries available from different identity providers.
- A developer of an application is not typically building it to perform a function to which identity services are the raison d'etre (reason for being)
- A developer does not have the time to read about all potential edge cases around the use of identity
- A developer does not have the time to understand all of the different policies that an organization administrator may be able to extert over their use of an organization data via APIs
- A developer does not have the ability to update all of the applications that they've built at the whim of Microsoft or any other software vendor
- A developer may or may not understand the 

### Organizations

The following is a list of assumptions about organizations who may be managing a portfolio of applications that already make use of the Microsoft Identity platform or other Identity services.

- An organization does not always have developers available to update an application in their portfolio
- An organization does not always have the source code for an application in their portfolio
- An organization does not always know how policy in configuration chagnes in their organization or within the identity provider that they use will impact their users access to applications
- An organization wants to keep help desk costs down.  As a result errors in applications should not simply direct users to helpdesk.  Supporting organizational efforts around user self-service is important.
- An organization may want to know when one of their applications is not working as expected or as it normally would


## Glossary

- Application: A software agent.  This document will use this term to refer to software intended to be used by human agents.
- Application services: A software agent.  These are the backend services that support the operation of the application.  An application may or may not have its own application services, but many do.
- User: A human or software agent.  This document will use this term to refer to human agents only.
- Model: The software representation of the data model
- View: A UI surface via which the a user can interact with or simply consume data from the data model.
- Controller: A coordinating class that connects the model and the view
- Module: A module in this case is one or more correlated views, models and controllers.  An application can have 1 or more modules.  A module can contain other modules.
- Desired state: A declaration of the desired state of the system.  For example: A developer can declare which APIs that a particular module needs the ability to call at run time.
- Navigation: With an application users can move from one view to another view.  All applications have the ability to navigate between views within an application.  Navigation can be as simple as open the 1 and only screen... and as complex as navigating between screens in nested modules.  
- Navigation Path/Route: All applications have the ability to declare a unique path identifier to enable navigation within the application.  These paths/routes/idenfiers can optionally contain parameters.
- Account: The representation of a user in software.  An application's services may require a user to create an account within itself, with it's own credentials, backup authentication methods etc....  Alternatively an application and it's services may rely on an external identity provider to perform authentication related to an account and simply store additional information about the user in it's own services.
- Identity Provider: These are services that may provide accounts that have already been created and secured by the user to the application and application services.  Relying on an external identity provider is an alternative to handling account management and authentication within an application itself.
- Authenticated Session: 
- Sign In: The business process via which an authenticated session for the user is created.  This business process can span the application an external identity provider and the applications services.  At the end of this process the applications should be "ready" for the user to use the intial module in the application.


## Configuration

