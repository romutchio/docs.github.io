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
            modalShown: false,
            documents: []
        }
    }

    componentDidMount() {
        const documents = []

        for (let i = 0; i < 20; i++) {
            documents.push({id: i, name: `документ #${i + 1}`});
        }

        this.setState({documents});
    }

    render() {
        return (
            <article className='app-page' ref={this.ref}>
                <Search/>
                <Tags/>
                <Documents documents={this.state.documents}/>
                <FixedButtons
                    showAddButton={this.props.password}
                    onUpClick={this.scrollUp}
                    onAddClick={this.createDocument}
                />
                <Footer/>
                {
                    this.state.modalShown &&
                    <Modal onClose={this.closeModal}>
                        <CreateModal password={this.props.password} onCreate={this.addDocument}/>
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

    addDocument = async document => {
        const id = await this.sendDocument(document);

        this.setState({
            modalShown: false,
            documents: [...this.state.documents, {id, name: document.name}]
        });
    }

    sendDocument = async document => {
        console.log(document);

        return document.name
    }
}
