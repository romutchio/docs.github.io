import React from "react";
import './Documents.css';

export default function Documents({documents}) {
    return (
        <ul className='documents'>
            {
                documents.map(d => (
                    <li key={d.id} className='document'>{d.name}</li>
                ))
            }
        </ul>
    );
}
