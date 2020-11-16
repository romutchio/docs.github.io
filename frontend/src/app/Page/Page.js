import React from "react";
import './Page.css';

import Search from "../Search/Search";
import Tags from "../Tags/Tags";
import Documents from "../Documents/Documents";
import Footer from "../Footer/Footer";
import FixedButtons from "../FixedButtons/FixedButtons";
import Modal from "../Modal/Modal";
import CreateModal from "../CreateModal/CreateModal";

export default class Page extends React.Component {
    constructor(props) {
        super(props);
        this.ref = React.createRef();

        this.state = {
            modalShown: false
        }
    }


    render() {
        return (
            <article className='app-page' ref={this.ref}>
                <Search/>
                <Tags/>
                <Documents/>
                <FixedButtons
                    showAddButton={this.props.password}
                    onUpClick={this.scrollUp}
                    onAddClick={this.createDocument}
                />
                <Footer/>
                {
                    this.state.modalShown &&
                    <Modal onClose={this.closeModal}>
                        <CreateModal password={this.props.password} onCreate={this.closeModal}/>
                    </Modal>
                }
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
