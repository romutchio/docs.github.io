import React from 'react';
import ReactDOM from 'react-dom';

import corgi from './images/BigCorgi.gif';

import './index.css';

ReactDOM.render(
    <React.StrictMode>
        <div className="App">
            <header>
                <h1>Hello there!</h1>
                <h3>Work in progress...</h3>
                <img src={corgi} alt='Doggo'/>
            </header>
        </div>
    </React.StrictMode>,
    document.getElementById('root')
);
