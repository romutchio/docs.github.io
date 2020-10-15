import React from "react";
import './TextInput.css';

export default function TextInput({placeholder, className = '', hideLetters = false, inputRef = null, ...props}) {
    return (
        <input
            type={hideLetters ? 'password' : 'text'}
            className={`text-input ${className}`}
            placeholder={placeholder}
            ref={inputRef}
            {...props}
        />
    );
}