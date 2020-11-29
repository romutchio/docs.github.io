import React from "react";
import './DeleteModal.css';

import Button from "../Button/Button";

export default class DeleteModal extends React.Component {
    render() {
        return (
            <div className='delete-modal'>
                <h2 className='delete-header'>Удалить документ "{this.props.document.name}"?</h2>
                <div className='delete-modal-buttons'>
                    <Button onClick={this.delete}>Да</Button>
                    <Button onClick={this.cancel}>Нет</Button>
                </div>
            </div>
        );
    }

    delete = async () => {
        await this.sendDeleteRequest();
        this.props.onDelete();
    }

    sendDeleteRequest = async () => {
        console.log(`deleted id ${this.props.document.id}`)
    }

    cancel = () => {
        this.props.onCancel();
    }
}
