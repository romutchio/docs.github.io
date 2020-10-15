import React from "react";
import './Header.css';

import Account from "../Account/Account";
import Lock from "../Lock/Lock";

export default function Header() {
    return (
        <header className='app-header'>
            <div className='password-area'>
                <Lock/>
                <span>&#8592; Click me!</span>
            </div>
            <Account />
        </header>
    );
}
