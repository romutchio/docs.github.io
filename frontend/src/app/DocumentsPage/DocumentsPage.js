import React from "react";
import './DocumentsPage.css';

import Search from "../Search/Search";
import Tags from "../Tags/Tags";
import Documents from "../Documents/Documents";

export default class DocumentsPage extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            documents: []
        }
    }

    componentDidMount() {
        const documents = []

        for (let i = 0; i < 20; i++) {
            documents.push({id: i, name: `документ #${i + 1}`});
        }

        this.setState({documents});
    }

    render() {
        return (
            <>
                <Search/>
                <Tags/>
                <Documents documents={this.state.documents}/>
            </>
        );
    }
}
