import React from 'react';
import ReactDOM from 'react-dom';
import * as serviceWorker from './serviceWorker';

import './index.css';

import Header from "./app/Header/Header";
import Page from "./app/Page/Page";

class App extends React.Component {
    constructor(props) {
        super(props);

        this.state = {}
    }


    render() {
        return (
            <div className="app">
                <Header onPasswordChange={this.onPasswordChange}/>
                <Page password={this.state.password}/>
            </div>
        );
    }

    onPasswordChange = password => this.setState({password});
}

ReactDOM.render(
    <React.StrictMode>
        <App/>
    </React.StrictMode>,
    document.getElementById('root')
);

serviceWorker.unregister();
