import React from "react";
import './UpButton.css';

export default function UpButton({onClick}) {
    return (
        <button id='up-button' onClick={onClick}>
            &#8593;
        </button>
    );
}
