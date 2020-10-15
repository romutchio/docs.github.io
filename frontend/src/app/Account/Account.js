import React from "react";
import './Account.css';

import defaultAvatar from '../../images/default_avatar.svg';

export default class Account extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: 'Name Surname',
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

    componentDidMount() {
        // get account info from backend
    }
}

