import React from "react";
import './Documents.css';

export default class Documents extends React.Component {
    constructor(props) {
        super(props);
    }


    render() {
        return (
            <ul className='documents'>
                {
                    this.props.documents.map(d => (
                        <li key={d.id} className='document'>{d.name}</li>
                    ))
                }
            </ul>
        );
    }
}
