import React, { ReactElement } from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { useIsAuthenticated } from "@azure/msal-react";

export type ReadyCheckRouteProps = {
    signInRoute?: string;
    interactionRoute?: string;
}


function ReadyCheckRoute({signInRoute = '/signin'}): ReactElement | any {

    const isAuthenticated = useIsAuthenticated();

    if(isAuthenticated){
        return <Outlet />;
    }else{
        return <Navigate to={signInRoute} replace />
    }
   
}

export default ReadyCheckRoute;