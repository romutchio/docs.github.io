import React from "react";
import './Page.css';

import Search from "../Search/Search";
import Tags from "../Tags/Tags";
import Documents from "../Documents/Documents";
import Footer from "../Footer/Footer";
import FixedButtons from "../FixedButtons/FixedButtons";

export default class Page extends React.Component {
    constructor(props) {
        super(props);
        this.ref = React.createRef();
    }


    render() {
        return (
            <article className='app-page' ref={this.ref}>
                <Search/>
                <Tags/>
                <Documents />
                <FixedButtons onUpClick={this.scrollUp} />
                <Footer/>
            </article>
        );
    }

    scrollUp = () => {
        console.log(123);
        this.ref.current.scrollTo(0, 0);
    }
}
