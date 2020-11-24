import React from "react";
import './FixedButtons.css';

import Button from "../Button/Button";

export default function FixedButtons({onUpClick, onAddClick, enableAddButton}) {
    return (
        <div className='fixed-buttons'>
            <Button
                className='fixed-button add-button'
                onClick={onAddClick}
                disabled={!enableAddButton}
                disabledTitle='Введите пароль'
            >
                +
            </Button>
            <Button className='fixed-button' onClick={onUpClick}>
                &#8593;
            </Button>
        </div>
    );
}
