import React from "react";
import './FixedButtons.css';

export default function FixedButtons(onUpClick, onAddClick) {
    return (
        <div className='fixed-buttons'>
            <button className='fixed-button' id='add-button' onClick={onAddClick}>
                +
            </button>
            <button className='fixed-button' onClick={onUpClick}>
                &#8593;
            </button>
        </div>
    );
}
