import React from "react";
import './Doggos.css';

import corgi from "../../images/BigCorgi.gif";

// Test component to check high elements
export default function Doggos() {
    return (
        <div className='doggos'>
            <img src={corgi} alt='Doggo'/>
            <img src={corgi} alt='Doggo'/>
            <img src={corgi} alt='Doggo'/>
            <img src={corgi} alt='Doggo'/>
        </div>
    );
}
