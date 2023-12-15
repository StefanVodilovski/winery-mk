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
        let url = 'https://jsonplaceholder.typicode.com'
        fetch(url)
            .then((response) => response.json())
            .then((json) => {
                const results = json.filter((item) => {
                    return (value && item && item.name && item.name.toLowerCase().includes(value))
                });
                setResults(results)
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


