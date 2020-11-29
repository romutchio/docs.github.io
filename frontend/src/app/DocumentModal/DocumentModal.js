import React from "react";
import './DocumentModal.css';

import Button from "../Button/Button";

export default function DocumentModal({document, enableDownload, onDownload, onEdit, onDelete}) {
    return (
        <div className='document-modal'>
            <h2 className='document-modal-name'>{document.name}</h2>
            <Button
                className='document-modal-button'
                disabled={!enableDownload}
                disabledTitle='Введите пароль'
                onClick={onDownload}
            >
                Скачать
            </Button>
            <Button className='document-modal-button' onClick={onEdit}>
                Изменить
            </Button>
            <Button className='document-modal-button' onClick={onDelete}>
                Удалить
            </Button>
        </div>
    );
}
