import React from "react";
import './CreateModal.css';

import TextInput from "../TextInput/TextInput";

import fileIcon from "../../images/attach_file-white-48dp.svg"

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
                <button className='create-modal-button' onClick={this.save}>
                    Сохранить
                </button>
            </div>
        );
    }

    getFile = e => {
        this.setState({filename: e.target.files[0]?.name});
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
}
