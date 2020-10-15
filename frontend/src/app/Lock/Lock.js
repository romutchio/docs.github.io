import React from "react";
import './Lock.css';

export default class Lock extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            unlocked: false
        }
    }

    render() {
        return (
            <div className='lock-container' onClick={this.toggleLock}>
                <span className={`lock${this.state.unlocked ? ' unlocked' : ''}`}/>
            </div>
        );
    }

    toggleLock = () => {
        this.setState({unlocked: !this.state.unlocked});
    }
}
