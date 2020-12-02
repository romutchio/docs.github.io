import React from "react";
import './Tags.css';

import Tag from "../Tag/Tag";

export default class Tags extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            tags: []
        }
    }


    render() {
        return (
            <div className='tags'>
                {
                    this.state.tags.map(t => (
                        <Tag key={t.id} onClick={this.onTagClick}>
                            {t.name}
                        </Tag>
                    ))
                }
            </div>
        );
    }

    onTagClick = tag => {
        this.props.onTagClick(tag);
    }

    async componentDidMount() {
        const response = await fetch('/tags');

        if (response.status !== 200) {
            console.error(response.status, response.statusText);
            return;
        }

        const tags = await response.json();
        console.log('tags', tags);

        this.setState({tags});
    }
}
