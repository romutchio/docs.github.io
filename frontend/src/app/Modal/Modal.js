import React from "react";
import './Modal.css';

export default function Modal({children, vertical = false, onClose}) {
    return (
        <div id='blackout' onClick={onClose}>
            <div className='modal' onClick={e => e.stopPropagation()}>
                <button id='modal-close-button' onClick={onClose}>
                    X
                </button>
                {children}
            </div>
        </div>
    );
}
