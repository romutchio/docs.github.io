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

        this.searchQuery = '';
        this.chosenTags = [];
        this.state = {
            documentModalShown: false,
            editModalShown: false,
            deleteModalShown: false,
            currentDocument: {},
            documents: [],
            tags: []
        }
    }

    async componentDidMount() {
        await this.updateDocuments();
        await this.updateTags();
    }

    render() {
        return (
            <>
                <Search onEdit={this.editSearch}/>
                <Tags tags={this.state.tags} onTagClick={this.editChosenTags}/>
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
                            document={this.state.currentDocument}
                            enableChangeFile={this.props.password}
                            edit={true}
                            onCreate={this.createDocument}
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
                {
                    this.props.createModalShown &&
                    <Modal onClose={this.props.onCreateModalClose}>
                        <CreateModal password={this.props.password} onCreate={this.closeCreateModal}/>
                    </Modal>
                }
            </>
        );
    }

    downloadDocument = () => {
        const name = this.state.currentDocument.name;
        let [, type, , encoded] = this.state.currentDocument.file.match(/data:(.*?);(.*?),(.*)/);
        type += ';charset=utf-8';

        const decoded = CryptoJS.AES.decrypt(encoded, this.props.password).toString(CryptoJS.enc.Utf8);
        const file = new File([decoded], `${name}.${MimeTypes.extension(type)}`, {type});

        FileSaver.saveAs(file);
        this.closeDocumentModal();
    }

    getDocument = async document => {
        const response = await fetch(`/document/${document.id}`);

        if (response.status !== 200) {
            console.error(response.status, response.statusText);
            return;
        }

        const doc = await response.json();
        console.log('document', doc);

        return {...doc, file: doc.data};
    }

    updateDocuments = async () => {
        let uri = '/documents/search'
        const params = [];

        if (this.searchQuery) {
            const query = encodeURIComponent(this.searchQuery);
            params.push(`query=${query}`);
        }
        if (this.chosenTags?.length > 0) {
            const tags = this.chosenTags.map(t => encodeURIComponent(t)).join(",");
            params.push(`tags=${tags}`);
        }

        if (params.length > 0) {
            uri += `?${params.join('&')}`;
        }
        console.log('uri', uri);

        const response = await fetch(uri);

        if (response.status !== 200) {
            console.error(response.status, response.statusText);
            return;
        }

        const documents = await response.json();
        console.log('documents', documents);

        this.setState({documents});
    }

    updateTags = async () => {
        const response = await fetch('/tags');

        if (response.status !== 200) {
            console.error(response.status, response.statusText);
            return;
        }

        const tags = await response.json();
        console.log('tags', tags);

        this.setState({tags});
    }

    editSearch = async query => {
        this.searchQuery = query;
        await this.updateDocuments();
    }

    editChosenTags = async tag => {
        if (this.chosenTags.includes(tag)) {
            this.chosenTags = this.chosenTags.filter(t => t !== tag);
        } else {
            this.chosenTags.push(tag);
        }
        await this.updateDocuments();
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

    createDocument = async () => {
        this.closeEditModal();
        await this.updateDocuments();
        await this.updateTags();
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

    closeCreateModal = async () => {
        this.props.onCreateModalClose();

        await this.updateDocuments();
        await this.updateTags();
    }
}
