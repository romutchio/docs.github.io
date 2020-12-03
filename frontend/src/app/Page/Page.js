import React from "react";
import './Page.css';

import Footer from "../Footer/Footer";
import FixedButtons from "../FixedButtons/FixedButtons";
import DocumentsPage from "../DocumentsPage/DocumentsPage";

export default class Page extends React.Component {
    constructor(props) {
        super(props);
        this.ref = React.createRef();

        this.state = {
            modalShown: false,
        }
    }

    render() {
        return (
            <article className='app-page' ref={this.ref}>
                <DocumentsPage
                    password={this.props.password}
                    createModalShown={this.state.modalShown}
                    onCreateModalClose={this.closeModal}
                />
                <FixedButtons
                    enableAddButton={this.props.password}
                    onUpClick={this.scrollUp}
                    onAddClick={this.createDocument}
                />
                <Footer/>
            </article>
        );
    }

    scrollUp = () => {
        this.ref.current.scrollTo(0, 0);
    }

    createDocument = () => {
        this.setState({modalShown: true});
    }

    closeModal = () => {
        this.setState({modalShown: false});
    }
}
