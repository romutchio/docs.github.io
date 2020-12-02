import React from "react";
import './Search.css';

import TextInput from "../TextInput/TextInput";

export default function Search({onEdit}) {
    return (
        <TextInput placeholder={'Поиск'} className='search' onChange={onEdit}/>
    );
}
