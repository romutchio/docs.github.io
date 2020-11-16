import React from "react";
import './Button.css';

export default function Button({onClick, className = '', disabled = false, children, disabledTitle}) {
    return (
        <button
            className={`button ${className} ${disabled ? 'disabled' : ''}`}
            onClick={disabled ? null : onClick}
            title={disabled ? disabledTitle : null}
        >
            {children}
        </button>
    );
}
