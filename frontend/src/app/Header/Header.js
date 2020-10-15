import React from "react";
import './Header.css';

import Account from "../Account/Account";
import Password from "../Password/Password";

export default function Header() {
    return (
        <header className='app-header'>
            <div className='flex-box'>
                <h1>Docs.io</h1>
            </div>
            <div className='flex-box'>
                <Password/>
            </div>
            <div className='flex-box'>
                <Account/>
            </div>
        </header>
    );
}
