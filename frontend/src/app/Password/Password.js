import React from "react";
import './Password.css';

import Lock from "../Lock/Lock";
import TextInput from "../TextInput/TextInput";

const PASSWORD_KEY = 'encryption_password';

export default class Password extends React.Component {
    constructor(props) {
        super(props);

        this.ref = React.createRef();

        const password = localStorage.getItem(PASSWORD_KEY);
        this.state = {
            isUnlocked: password == null
        }

        if (password) {
            props.onChange(password);
        }
    }

    render() {
        return (
            <div className='password-area'>
                <Lock onClick={this.unlock} unlocked={this.state.isUnlocked} />
                <TextInput
                    className={this.state.isUnlocked ? 'unlocked' : 'locked'}
                    hideLetters={true}
                    placeholder={this.state.isUnlocked ? 'Введите свое код-слово' : null}
                    onBlur={e => this.lock(e.target.value)}
                    inputRef={this.ref}
                />
            </div>
        );
    }

    lock = password => {
        if (!password) {
            return;
        }

        localStorage.setItem(PASSWORD_KEY, password);
        this.ref.current.value = '';
        this.setState({isUnlocked: false});

        this.props.onChange(password);
    }

    unlock = () => {
        localStorage.removeItem(PASSWORD_KEY);
        this.setState({isUnlocked: true});
        this.props.onChange(null);
    }
}
