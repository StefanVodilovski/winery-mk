import React, { useState } from 'react'
import { Searchbar } from "../Searchbar"
import { SearchWinesList } from "../SearchWinesList"
import "./css/Wines.css"

export const Wines = () => {
    const [results, setResults] = useState([])

    return (
        <div >
            <div className='search-bar-container'>
                <Searchbar setResults={setResults}></Searchbar>
                <SearchWinesList results={results} />
            </div>
        </div>
    )
}


