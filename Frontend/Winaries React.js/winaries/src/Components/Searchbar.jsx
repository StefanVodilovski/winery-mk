import React, { useState } from 'react'
import { FaSearch } from "react-icons/fa"
import "./Searchbar.css"

export const Searchbar = ({ setResults }) => {
    const [input, setInput] = useState("")

    const fetchData = (value) => {
        // ova url e /wines/filter 
        // searchQuery=value.
        // priceFilter=
        // region=
        // winery=
        // literage= 
        let url = 'http://localhost:8080/wines/1';
        fetch(url)
        .then((response) => response.json())
        .then((json) => {
            console.log(json);
            setResults(json);
        })
        .catch((error) => {
            console.error('Error fetching data:', error);
        });
    };

    const handleChange = (value) => {
        setInput(value)
        fetchData(value)
    }

    return (
        <div className='input-wrapper'>
            <FaSearch id='search-icon' />
            <input placeholder="searc wines"
                value={input}
                name='search'
                onChange={(e) => handleChange(e.target.value)}
            />
        </div>
    )
}


