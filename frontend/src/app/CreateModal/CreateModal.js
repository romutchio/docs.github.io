import React from "react";
import './CreateModal.css';

import TextInput from "../TextInput/TextInput";
import Button from "../Button/Button";

import fileIcon from "../../images/attach_file-white-48dp.svg"
import AES from 'crypto-js/aes'

export default class CreateModal extends React.Component {

    constructor(props) {
        super(props);

        this.ref = React.createRef();
        this.state = {
            name: props.name,
            filename: props.name ? `Файл - ${props.name}` : null,
            tags: props.tags ?? []
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
        if (this.props.enableChangeFile) {
            return;
        }

        const file = e.target.files[0];
        this.setState({filename: file?.name});

        await this.processFile(file);
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

    save = () => {
        if (!this.state.name || (!this.props.edit && !this.state.file)) {
            return;
        }

        const document = {
            name: this.state.name,
            tags: this.state.tags,
            file: this.state.file
        };

        this.sendDocument(document);
        this.props.onCreate();
    }

    sendDocument = async document => {
        console.log(document)
    }

    processFile = async file => {
        const text = await file?.text();
        const encrypted = AES.encrypt(text, this.props.password);

        const result = `data:${file.type};base64,${encrypted.toString()}`;

        this.setState({file: result})
    }
}
