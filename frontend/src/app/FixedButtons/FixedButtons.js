import React from "react";
import './FixedButtons.css';

import Button from "../Button/Button";

export default function FixedButtons({onUpClick, onAddClick, showAddButton}) {
    return (
        <div className='fixed-buttons'>
            {
                showAddButton &&
                <Button className='fixed-button add-button' onClick={onAddClick}>
                    +
                </Button>
            }
            <Button className='fixed-button' onClick={onUpClick}>
                &#8593;
            </Button>
        </div>
    );
}
