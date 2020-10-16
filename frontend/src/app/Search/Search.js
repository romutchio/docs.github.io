import React from "react";
import './Search.css';

import TextInput from "../TextInput/TextInput";

export default function Search() {
    return (
        <TextInput placeholder={'Поиск'} className='search'/>
    );
}
