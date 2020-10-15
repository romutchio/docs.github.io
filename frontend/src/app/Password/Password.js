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
                    onKeyPress={this.onEnter}
                />
            </div>
        );
    }

    onEnter = e => {
        if (e.key === 'Enter') {
            this.ref.current.blur()
        }
    }

    lock = password => {
        if (!password) {
            return;
        }

        localStorage.setItem(PASSWORD_KEY, password);
        this.ref.current.value = '';
        this.setState({isUnlocked: false});
    }

    unlock = () => {
        localStorage.removeItem(PASSWORD_KEY);
        this.setState({isUnlocked: true});
    }
}
