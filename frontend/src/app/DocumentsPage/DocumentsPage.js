import React from "react";
import './DocumentsPage.css';

import Search from "../Search/Search";
import Tags from "../Tags/Tags";
import Documents from "../Documents/Documents";
import Modal from "../Modal/Modal";
import DocumentModal from "../DocumentModal/DocumentModal";
import CreateModal from "../CreateModal/CreateModal";
import DeleteModal from "../DeleteModal/DeleteModal";

import * as FileSaver from 'file-saver'
import * as MimeTypes from 'mime-types'
import * as CryptoJS from 'crypto-js'

export default class DocumentsPage extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            documentModalShown: false,
            editModalShown: false,
            deleteModalShown: false,
            currentDocument: {},
            documents: []
        }
    }

    componentDidMount() {
        const documents = []

        for (let i = 0; i < 20; i++) {
            documents.push({id: i, name: `документ #${i + 1}`});
        }
        documents.push({id: 20, name: 'very long document name with spaces'})

        this.setState({documents});
    }

    render() {
        return (
            <>
                <Search/>
                <Tags/>
                <Documents documents={this.state.documents} onChose={this.choseDocument}/>
                {
                    this.state.documentModalShown &&
                    <Modal onClose={this.closeDocumentModal}>
                        <DocumentModal
                            document={this.state.currentDocument}
                            enableDownload={this.props.password}
                            onDownload={this.downloadDocument}
                            onEdit={this.editDocument}
                            onDelete={this.deleteDocument}
                        />
                    </Modal>
                }
                {
                    this.state.editModalShown &&
                    <Modal onClose={this.closeEditModal}>
                        <CreateModal
                            name={this.state.currentDocument.name}
                            tags={this.state.currentDocument.tags}
                            enableChangeFile={this.props.password}
                            edit={true}
                            onCreate={this.closeEditModal}
                        />
                    </Modal>
                }
                {
                    this.state.deleteModalShown &&
                    <Modal onClose={this.closeDeleteModal}>
                        <DeleteModal
                            document={this.state.currentDocument}
                            onDelete={this.closeDeleteModal}
                            onCancel={this.closeDeleteModal}
                        />
                    </Modal>
                }
            </>
        );
    }

    downloadDocument = () => {
        const name = this.state.currentDocument.name;
        const [, type, , encoded] = this.state.currentDocument.file.match(/data:(.*?);(.*?),(.*)/);

        const decoded = CryptoJS.AES.decrypt(encoded, this.props.password).toString(CryptoJS.enc.Utf8);
        const blob = new Blob([decoded], {type});

        FileSaver.saveAs(blob, `${name}.${MimeTypes.extension(type)}`)
        this.closeDocumentModal();
    }

    getDocument = async document => {
        return {
            id: document.id,
            name: document.name,
            tags: ["123", "456", "tag"],
            file: "data:text/plain;base64,U2FsdGVkX1/wadRcd3Ir/4ljZ0iPqo5aciFqySd+CYs="
        }
    }

    choseDocument = async document => {
        const currentDocument = await this.getDocument(document);

        this.setState({
            currentDocument,
            documentModalShown: true
        });
    }

    editDocument = () => {
        this.setState({
            documentModalShown: false,
            editModalShown: true
        });
    }

    deleteDocument = () => {
        this.setState({
            documentModalShown: false,
            deleteModalShown: true
        });
    }

    closeDocumentModal = () => {
        this.setState({documentModalShown: false});
    }

    closeEditModal = () => {
        this.setState({editModalShown: false});
    }

    closeDeleteModal = () => {
        this.setState({deleteModalShown: false});
    }
}
