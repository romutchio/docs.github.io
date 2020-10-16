import React from "react";
import './Tags.css';

export default class Tags extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            tags: [
                {id: 1, name: 'тэг'},
                {id: 2, name: 'паспорт'},
                {id: 3, name: 'документ'},
                {id: 4, name: 'два слова'},
                {id: 5, name: 'ну'},
                {id: 6, name: 'прям'},
                {id: 7, name: 'жесть'},
                {id: 8, name: 'как'},
                {id: 9, name: 'много'},
                {id: 10, name: 'тэгов'},
            ]
        }
    }


    render() {
        const {onTagClick} = this.props;

        return (
            <div className='tags'>
                {
                    this.state.tags.map(t => (
                        <button className='tag' key={t.id} onClick={() => onTagClick && onTagClick(t.id)}>
                            {t.name}
                        </button>
                    ))
                }
            </div>
        );
    }

    componentDidMount() {
        // get tags from backend
    }
}
