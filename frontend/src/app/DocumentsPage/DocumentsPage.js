import React from "react";
import './DocumentsPage.css';

import Search from "../Search/Search";
import Tags from "../Tags/Tags";
import Documents from "../Documents/Documents";
import Modal from "../Modal/Modal";
import DocumentModal from "../DocumentModal/DocumentModal";

export default class DocumentsPage extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            modalShown: false,
            currentDocument: {},
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
                <Documents documents={this.state.documents} onChose={this.choseDocument}/>
                {
                    this.state.modalShown &&
                    <Modal onClose={this.closeModal}>
                        <DocumentModal
                            document={this.state.currentDocument}
                            enableDownload={this.props.password}
                        />
                    </Modal>
                }
            </>
        );
    }

    choseDocument = document => {
        this.setState({
            currentDocument: document,
            modalShown: true
        });
    }

    closeModal = () => {
        this.setState({modalShown: false});
    }
}
