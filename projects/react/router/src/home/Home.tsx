import React from 'react';
import logo from '../logo.svg';

function Home(){
    return (
        <div className="text-center">
        <header className="flex dark:bg-[#282c34] bg-white align-middle flex-col min-h-screen justify-center text-xl text-white ">
            <img src={logo} className="h-80 pointer-events-none animate-spinlogo" alt="logo" />
            <p>
            Edit <code>src/home/Home.tsx</code> and save to reload.
            </p>
            <a
            className="text-teal-500"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
            >
            Learn React
            </a>
        </header>
        </div>
    )
}

export default Home;