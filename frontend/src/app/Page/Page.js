import React from "react";
import './Page.css';

import Doggos from "../Doggos/Doggos";
import UpButton from "../UpButton/UpButton";

export default class Page extends React.Component {
    constructor(props) {
        super(props);
        this.ref = React.createRef();
    }


    render() {
        return (
            <article className='app-page' ref={this.ref}>
                <h1>Hello there!</h1>
                <h3>Work in progress...</h3>
                <Doggos/>
                <UpButton onClick={this.scrollUp} />
            </article>
        );
    }

    scrollUp = () => {
        console.log(123);
        this.ref.current.scrollTo(0, 0);
    }
}
