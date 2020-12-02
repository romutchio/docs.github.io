import React from "react";
import './Account.css';

import defaultAvatar from '../../images/default_avatar.svg';

export default class Account extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: 'Имя пользователя',
            avatar: defaultAvatar
        }
    }


    render() {
        const {name, avatar} = this.state;

        return (
            <div className='account'>
                <img src={avatar} alt='👤' id='account-avatar'/>
                <span id='account-name'>{name}</span>
            </div>
        );
    }

    async componentDidMount() {
        const response = await fetch('/user');

        if (response.status !== 200) {
            console.error(response.status, response.statusText);
            return;
        }

        const user = await response.json();
        this.setState({name: user.name, avatar: user.imageUrl});
    }
}

