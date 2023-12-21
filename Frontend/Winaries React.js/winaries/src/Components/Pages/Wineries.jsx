import React, { useState, useEffect } from 'react'
import { Searchbar } from "../Searchbar"
import { SearchWineriesList } from "../SearchWineriesList"
import "./css/Wineries.css"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faMagnifyingGlass} from '@fortawesome/free-solid-svg-icons'

export const Wineries = () => {
    const [results, setResults] = useState([])

    const [region, setRegion] = useState('-1');
    const [searchQuery, setSearchQuery] = useState('')

    const updateSearch = (value) => {
        setSearchQuery(value)
    }
    

    const handleButtonClick = () => {
        console.log('Region:', region);
        console.log('Search Query:', searchQuery)

        fetchData()
    };

    const fetchData = () => {
        // ova url e /wines/filter 
        // searchQuery=value.
        // priceFilter=
        // region=
        // winery=
        // literage= 
        let url = 'http://localhost:8080/wineries/filter?searchQuery=' + searchQuery + "&region=" + region;
        fetch(url)
            .then((response) => response.json())
            .then((json) => {
                setResults(json);
            })
            .catch((error) => {
                console.error('Error fetching data:', error);
            });
    };
    

    const initalData = () => {
        let url = 'http://localhost:8080/wineries/all'
        fetch(url)
            .then((response) => response.json())
            .then((json) => {
                setResults(json);
            })
            .catch((error) => {
                console.error('Error fetching data:', error);
            });
    };

    useEffect(() => {
        initalData();
    }, []);

    return (
        <div className='wineries-outer-container'>
            <div className='wineries-inner-container'>
                <h1>WINERIES</h1>
                <div className='filter-section'>
                    <div className='search-bar-container'>
                        <Searchbar setResults={setResults} updateSearch={updateSearch} ></Searchbar>
                        <button className='search-button' onClick={handleButtonClick}><FontAwesomeIcon icon={faMagnifyingGlass} /></button>
                    </div>
                    <div className='filter-options'>
                        <div className='filter-option'>
                            <label htmlFor="region">Region</label>
                            <select name='region' id='region' onChange={(e) => (setRegion(e.target.value))}>
                                <option value={-1}></option>
                                <option value={0}>Povardarie</option>
                                <option value={1}>Tikvesh</option>
                                <option value={2}>Veles</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div className='wineries-list'>
                    <SearchWineriesList results={results} />
                </div>
            </div>
        </div>
    )
}
