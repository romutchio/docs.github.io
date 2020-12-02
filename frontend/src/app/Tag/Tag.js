import React from "react";
import './Tag.css';

import Button from "../Button/Button";

export default class Tag extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            chosen: false
        }
    }

    render() {
        return (
            <Button className={`tag ${this.state.chosen ? 'chosen' : ''}`} onClick={this.onClick}>
                {this.props.children}
            </Button>
        );
    }

    onClick = () => {
        this.props.onClick(this.props.children);
        this.setState({chosen: !this.state.chosen});
    }
}
