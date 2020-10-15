import React from "react";
import './Lock.css';

export default function Lock({onClick, unlocked, ...props}) {
    return (
        <div className='lock-container' onClick={onClick} {...props}>
            <span className={`lock ${unlocked ? 'unlocked' : 'locked'}`}/>
        </div>
    );
}
