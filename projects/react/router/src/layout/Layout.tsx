import React, { PropsWithChildren } from 'react';

type LayoutProps = PropsWithChildren<{}>;

function Layout({children}:LayoutProps){
    return (
        <div className="flex dark:bg-[#282c34] bg-white align-middle flex-col min-h-screen">
            <div className="flex flex-col m-10 dark:bg-[#282c34] bg-white">
                {children}
            </div>
        </div>
    )
}

export default Layout;