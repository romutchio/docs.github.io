import React from "react";
import './Page.css';

import Doggos from "../Doggos/Doggos";

export default function Page() {
    return (
        <article className='app-page'>
            <h1>Hello there!</h1>
            <h3>Work in progress...</h3>
            <Doggos/>
        </article>
    );
}
