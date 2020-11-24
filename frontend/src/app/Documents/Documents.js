import React from "react";
import './Documents.css';

export default function Documents({documents, onChose}) {
    return (
        <ul className='documents'>
            {
                documents.map(d => (
                    <li key={d.id} className='document' onClick={() => onChose(d)}>
                        {d.name}
                    </li>
                ))
            }
        </ul>
    );
}
