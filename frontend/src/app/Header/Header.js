import React from "react";
import './Header.css';

import Account from "../Account/Account";
import Password from "../Password/Password";
import AppName from "../AppName/AppName";

export default function Header() {
    return (
        <header className='app-header'>
            <div className='flex-box'>
                <AppName/>
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
