import React from "react";
import './Documents.css';

export default class Documents extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            documents: []
        }
    }


    render() {
        return (
            <ul className='documents'>
                {
                    this.state.documents.map(d => (
                        <li key={d.id} className='document'>{d.text}</li>
                    ))
                }
            </ul>
        );
    }

    componentDidMount() {
        const documents = []

        for (let i = 0; i < 20; i++) {
            documents.push({id: i, text: `документ #${i + 1}`});
        }

        this.setState({documents});
    }
}
