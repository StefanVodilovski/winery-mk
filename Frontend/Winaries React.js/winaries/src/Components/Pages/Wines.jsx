import React, { useState, useEffect } from 'react'
import { Searchbar } from "../Searchbar"
import { SearchWinesList } from "../SearchWinesList"
import "./css/Wines.css"

export const Wines = () => {
    const [results, setResults] = useState([])

    const [searchQuery, setSearchQuery] = useState('')

    const updateSearch = (value) => {
        setSearchQuery(value)
    }

    const [priceFilter, setPriceFilter] = useState('-1');
    const [region, setRegion] = useState('-1');
    const [winery, setWinery] = useState('-1');
    const [litrage, setLitrage] = useState('-1');

    // Function to handle the click event of the button
    const handleButtonClick = () => {
        // Do something with the selected values, e.g., pass them to another function
        console.log('Price Filter:', priceFilter);
        console.log('Region:', region);
        console.log('Winery:', winery);
        console.log('Litrage:', litrage);
        console.log('Search Query:', searchQuery)

        let query_data = {
            "searchQuery": results,
            "priceFilter": priceFilter,
            "region": region,
            "winery": winery,
            "litrage": litrage
        }
        fetchData(query_data)
    };

    const fetchData = (query_data) => {
        // ova url e /wines/filter 
        // searchQuery=value.
        // priceFilter=
        // region=
        // winery=
        // literage= 
        let url = 'http://localhost:8080/wines/filter?searchQuery=' + query_data.searchQuery.value 
        + "&priceFilter=" + query_data.priceFilter.value + "&region=" + query_data.region.value 
        + "&winery=" + query_data.winery.value + "&litrage=" + query_data.litrage.value;
        console.log(url)
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

    const initalData = () => {
        // ova url e /wines/filter 
        // searchQuery=value.
        // priceFilter=
        // region=
        // winery=
        // literage= 
        let url = 'http://localhost:8080/wines/all'
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

    useEffect(() => {
        initalData();
    }, []);

    return (
        <div className='wines-outer-container'>
            <div className='wines-inner-container'>
                <h1>WINES</h1>
                <div className='filter-section'>
                    <div className='search-bar-container'>
                        <Searchbar setResults={setResults} updateSearch={updateSearch} ></Searchbar>
                        <button onClick={handleButtonClick}></button>
                    </div>
                    <div className='filter-options'>
                        <div className='filter-option'>
                            <label htmlFor="priceFilter">Price</label>
                            <select id='priceFilter' name='priceFilter' onChange={(e) => (setPriceFilter(e.target.value))}>
                                <option value={-1}></option>
                                <option value={500}>to 500 den</option>
                                <option value={1500}>to 1500 den</option>
                                <option value={2500}>to 2500 den</option>
                            </select>
                        </div>
                        <div className='filter-option'>
                            <label htmlFor="region">Region</label>
                            <select name='region' id='region' onChange={(e) => (setRegion(e.target.value))}>
                                <option value={-1}></option>
                                <option value={0}>Povardarie</option>
                                <option value={1}>Tikvesh</option>
                                <option value={2}>Veles</option>
                            </select>
                        </div>
                        <div className='filter-option'>
                            <label htmlFor="winery">Winery</label>
                            <select name='winery' id='winery' onChange={(e) => (setWinery(e.target.value))}>
                                <option value={-1}></option>
                                <option value={1}>Tikveš Châteaux & Domaines</option>
                                <option value={2}>Kartal Winery</option>
                                <option value={3}>Iliev Winery</option>
                                <option value={4}>Old School Winery</option>
                                <option value={5}>Kamnik Winary</option>
                                <option value={6}>Paragon Winery</option>
                            </select>
                        </div>
                        <div className='filter-option'>
                            <label htmlFor="litrage">Litrage</label>
                            <select name='litrage' id='litrage' onChange={(e) => (setLitrage(e.target.value))}>
                                <option value={-1}></option>
                                <option value={0.375}>0.375L</option>
                                <option value={0.75}>0.75L</option>

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


