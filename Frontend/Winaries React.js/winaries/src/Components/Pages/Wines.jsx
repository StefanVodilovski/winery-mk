import React, { useState } from 'react'
import { Searchbar } from "../Searchbar"
import { SearchWinesList } from "../SearchWinesList"
import "./css/Wines.css"

export const Wines = () => {
    const [results, setResults] = useState([])

    return (
        <div className='wines-outer-container'>
            <div className='wines-inner-container'>
                <h1>WINES</h1>
                <div className='filter-section'>
                    <div className='search-bar-container'>
                        <Searchbar setResults={setResults}></Searchbar>
                    </div>
                    <div className='filter-options'>
                        <div className='filter-option'>
                            <label htmlFor="priceFilter">Price</label>
                            <input type="number" name='priceFilter' min={330} max={930}/>
                        </div>
                        <div className='filter-option'>
                            <label htmlFor="region">Region</label>
                            <select name='region' id='region'>
                                <option value={-1}></option>
                                <option value={0}>EASTERN</option>
                                <option value={1}>NORTHEASTERN</option>
                                <option value={2}>PELAGONIA</option>
                                <option value={3}>POLOG</option>
                                <option value={4}>SKOPJE</option>
                                <option value={5}>SOUTHEASTERN</option>
                                <option value={6}>SOUTHWESTERN</option>
                                <option value={7}>VARDAR</option>
                            </select>
                        </div>
                        <div className='filter-option'>
                            <label htmlFor="winery">Winery</label>
                            <select name='winery' id='winery'>

                            </select>
                        </div>
                        <div className='filter-option'>
                            <label htmlFor="litrage">Litrage</label>
                            <select name='litrage' id='litrage'>

                            </select>
                        </div>
                    </div>
                </div>

                <div className='wines-list'>
                    <SearchWinesList results={results} />
                </div>
            </div>
        </div>
    )
}


