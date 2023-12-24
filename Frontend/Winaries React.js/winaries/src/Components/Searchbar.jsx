import React, { useState, useEffect } from 'react'
import "./Searchbar.css"

export const Searchbar = ({ setResults, updateSearch }) => {
    const [input, setInput] = useState("")

    return (
        <div className='input-wrapper'>
            <input placeholder="Search..."
                name='searchQuery'
                onChange={(e) => updateSearch(e.target.value)}
            />
        </div>
    )
}


