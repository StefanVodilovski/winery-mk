import React, { useState, useEffect } from 'react'
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
        let url = 'http://localhost:8080/wines/all';
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

    const filterData = (value) => {
        // ova url e /wines/filter 
        // searchQuery=value.
        // priceFilter=
        // region=
        // winery=
        // literage= 
        let url = 'http://localhost:8080/wines/filter?searchQuery=' + value;
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
        filterData(value)
    }

    useEffect(() => {
        fetchData("");
    }, []);

    return (
        <div className='input-wrapper'>
            <input placeholder="Search..."
                value={input}
                name='searchQuery'
                onChange={(e) => handleChange(e.target.value)}
            />
        </div>
    )
}


