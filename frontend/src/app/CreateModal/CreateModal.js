import React from "react";
import './CreateModal.css';

import TextInput from "../TextInput/TextInput";
import Button from "../Button/Button";

import fileIcon from "../../images/attach_file-white-48dp.svg"
import * as CryptoJS from 'crypto-js'

export default class CreateModal extends React.Component {

    constructor(props) {
        super(props);

        this.ref = React.createRef();
        this.state = {
            name: props.document?.name,
            filename: props.document?.name ? `Файл - ${props.name}` : null,
            tags: props.document?.tags ?? []
        }
    }

    render() {
        return (
            <div className='create-modal'>
                <TextInput
                    placeholder='Введите имя'
                    className='create-modal-name'
                    onBlur={this.setName}
                >
                    {this.state.name}
                </TextInput>
                <div className='create-modal-tags'>
                    {
                        this.state.tags.map(tag => (
                            <span key={tag} className='create-modal-tag' onClick={this.deleteFlag(tag)}>
                                {tag}
                            </span>
                        ))
                    }
                    <TextInput
                        placeholder='Добавить тэг'
                        className='create-modal-tag-input'
                        onBlur={this.addTag}
                        inputRef={this.ref}
                    />
                </div>
                <label className='create-modal-file-label'>
                <span className='create-modal-file-button'>
                    <img src={fileIcon} alt='file'/>
                </span>
                    {this.state.filename || 'Выберите файл'}
                    <input
                        type='file'
                        className={`create-modal-file ${this.props.enableChangeFile ? '' : 'disabled'}`}
                        onChange={this.getFile}
                        title={this.props.enableChangeFile ? null : 'Введите пароль'}
                    />
                </label>
                <Button
                    className='create-modal-button'
                    disabled={!this.state.name || (!this.props.edit && !this.state.file)}
                    disabledTitle={this.state.name ? 'Выберите файл' : 'Введите имя документа'}
                    onClick={this.save}
                >
                    Сохранить
                </Button>
            </div>
        );
    }

    setName = e => {
        this.setState({name: e.target.value});
    }

    getFile = async e => {
        if (!this.props.enableChangeFile) {
            return;
        }

        const file = e.target?.files?.[0];

        if (file) {
            this.setState({filename: file.name});
            await this.processFile(file);
        }
    }

    addTag = e => {
        const tag = e.target.value;
        this.ref.current.value = '';

        if (!tag || this.state.tags.includes(tag)) {
            return;
        }

        this.setState({tags: [...this.state.tags, tag]});
    }

    deleteFlag = tag => () => {
        const tags = this.state.tags.filter(i => i !== tag);

        this.setState({tags});
    }

    save = async () => {
        if (!this.state.name || (!this.props.edit && !this.state.file)) {
            return;
        }

        const document = {
            name: this.state.name,
            tags: this.state.tags,
            data: this.state.file
        };

        if (this.props.edit) {
            document.id = this.props.document.id;
            await this.updateDocument(document);
        } else {
            await this.sendDocument(document);
        }

        this.props.onCreate();
    }

    sendDocument = async document => {
        const response = await fetch('/document', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(document)
        });

        if (response.status !== 200) {
            console.error(response.status, response.statusText);
        }
    }

    updateDocument = async document => {
        const response = await fetch('/document', {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(document)
        });

        if (response.status !== 200) {
            console.error(response.status, response.statusText);
        }
    }

    processFile = async file => {
        const content = await file?.arrayBuffer();
        const bytes = CryptoJS.lib.WordArray.create(content);
        const encrypted = CryptoJS.AES.encrypt(bytes, this.props.password);

        const result = `data:${file.type};base64,${encrypted.toString()}`;

        this.setState({file: result})
    }
}
