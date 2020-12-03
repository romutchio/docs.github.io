import React from "react";
import './Tags.css';

import Tag from "../Tag/Tag";

export default function Tags(props) {
    return (
        <div className='tags'>
            {
                props.tags.map(t => (
                    <Tag key={t.id} onClick={props.onTagClick}>
                        {t.name}
                    </Tag>
                ))
            }
        </div>
    );
}
