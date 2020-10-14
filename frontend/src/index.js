import React from 'react';
import ReactDOM from 'react-dom';
import * as serviceWorker from './serviceWorker';

import './index.css';

import Header from "./app/Header/Header";
import Page from "./app/Page/Page";

ReactDOM.render(
    <React.StrictMode>
        <div className="app">
            <Header/>
            <Page/>
        </div>
    </React.StrictMode>,
    document.getElementById('root')
);

serviceWorker.unregister();
