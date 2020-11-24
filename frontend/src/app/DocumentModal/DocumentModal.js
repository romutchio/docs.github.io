import React from "react";
import './DocumentModal.css';
import Button from "../Button/Button";

export default function DocumentModal({document, enableDownload}) {
    console.log(document);

    return (
        <div className='document-modal'>
            <h2 className='document-modal-name'>{document.name}</h2>
            <Button
                className='document-modal-button'
                disabled={!enableDownload}
                disabledTitle='Введите пароль'
            >
                Скачать
            </Button>
            <Button className='document-modal-button'>Изменить</Button>
            <Button className='document-modal-button'>Удалить</Button>
        </div>
    );
}
