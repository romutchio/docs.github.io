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
            tags: [
                'tag', 'тэг', 'longer',
                'more', 'tags', 'to', 'god of tags'
            ]
        }
    }

    render() {
        return (
            <div className='create-modal'>
                <TextInput placeholder='Введите имя' className='create-modal-name'/>
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
                    <input type='file' className='create-modal-file' onChange={this.getFile}/>
                </label>
                <Button
                    className='create-modal-button'
                    disabled={!this.state.encrypted}
                    disabledTitle='Файл не выбран'
                    onClick={this.save}
                >
                    Сохранить
                </Button>
            </div>
        );
    }

    getFile = async e => {
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
        this.props.onCreate();
    }

    processFile = async file => {
        const text = await file?.text();
        const encrypted = AES.encrypt(text, this.props.password);
        console.log(encrypted.toString());

        this.setState({encrypted})
    }
}
