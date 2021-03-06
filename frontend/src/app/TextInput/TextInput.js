import React from "react";
import './TextInput.css';

export default class TextInput extends React.Component {

    constructor(props) {
        super(props);

        this.ref = props.inputRef ?? React.createRef();
    }

    render() {
        let {placeholder, onEdit, className = '', inputRef, hideLetters = false, children, ...props} = this.props;
        return (
            <input
                type={hideLetters ? 'password' : 'text'}
                className={`text-input ${className}`}
                placeholder={placeholder}
                ref={this.ref}
                defaultValue={children}
                onKeyPress={this.onEnter}
                onChange={this.onChange}
                {...props}
            />
        );
    }

    onEnter = e => {
        if (e.key === 'Enter') {
            this.ref.current.blur()
        }
    }

    onChange = e => {
        this.props.onEdit && this.props.onEdit(e.target.value);
    }
}
