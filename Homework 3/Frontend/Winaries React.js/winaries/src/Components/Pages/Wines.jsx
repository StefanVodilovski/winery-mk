import React, { useState, useEffect } from 'react'
import { Searchbar } from "../Searchbar"
import { SearchWinesList } from "../SearchWinesList"
import "./css/Wines.css"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faMagnifyingGlass} from '@fortawesome/free-solid-svg-icons'
import { request, setAuthHeader, getAuthToken } from '../../Helpers/axios_helper';

export const Wines = () => {
    const [resultsWines, setResultsWines] = useState([])
    const [resultsWineries, setResultsWineries] = useState([])

    const [searchQuery, setSearchQuery] = useState('')

    const updateSearch = (value) => {
        setSearchQuery(value)
    }

    const [priceFilter, setPriceFilter] = useState('-1');
    const [region, setRegion] = useState('-1');
    const [winery, setWinery] = useState('-1');
    const [litrage, setLitrage] = useState('-1');

    const handleButtonClick = () => {
        fetchData()
    };

    const fetchData = () => {
        let url = "/wines/filter?";
        if(searchQuery != "")
            url = url + "&searchQuery=" + searchQuery;
        if(priceFilter != -1)
            url = url + "&priceFilter=" + priceFilter;
        if(region != -1)
            url = url + "&region=" + region;
        if(winery != -1)
            url = url + "&winery=" + winery;
        if(litrage != -1)
            url = url + "&litrage=" + litrage;
        
            request(
                "GET",
                url,
                ).then(
                (response) => {
                    setResultsWines(response.data)
                }).catch(
                (error) => {
                  console.error('Error fetching data:', error);
                }
            );
    };    

    const initalDataWines = () => {
        request(
            "GET",
            "/wines/all",
            ).then(
            (response) => {
                setResultsWines(response.data)
            }).catch(
            (error) => {
              console.error('Error fetching data:', error);
            }
        );
    };

    const initalDataWineries = () => {
        request(
            "GET",
            "/wineries/all",
            ).then(
            (response) => {
                setResultsWineries(response.data)
            }).catch(
            (error) => {
              console.error('Error fetching data:', error);
            }
        );
    };

    useEffect(() => {
        initalDataWines();
        initalDataWineries();
    }, []);

    return (
        <div className='wines-outer-container'>
            <div className='wines-inner-container'>
                <h1>WINES</h1>
                <div className='filter-section'>
                    <div className='search-bar-container'>
                        <Searchbar setResults={setResultsWines} updateSearch={updateSearch} ></Searchbar>
                        <button className='search-button' onClick={handleButtonClick}><FontAwesomeIcon icon={faMagnifyingGlass} /></button>
                    </div>
                    <div className='filter-options'>
                        <div className='filter-option'>
                            <label htmlFor="priceFilter">Price</label>
                            <select id='priceFilter' name='priceFilter' onChange={(e) => (setPriceFilter(e.target.value))}>
                                <option value={-1}></option>
                                <option value={500}>Up to 500 den</option>
                                <option value={1500}>Up to 1500 den</option>
                                <option value={2500}>Up to 2500 den</option>
                            </select>
                        </div>
                        <div className='filter-option'>
                            <label htmlFor="region">Region</label>
                            <select name='region' id='region' onChange={(e) => (setRegion(e.target.value))}>
                                <option value={-1}></option>
                                <option value={"Povardarie"}>Povardarie</option>
                                <option value={"Tikvesh"}>Tikvesh</option>
                                <option value={"Veles"}>Veles</option>
                            </select>
                        </div>
                        <div className='filter-option'>
                            <label htmlFor="winery">Winery</label>
                            <select name='winery' id='winery' onChange={(e) => (setWinery(e.target.value))}>
                                <option value={-1}></option>
                                {resultsWineries.map(winery => (
                                    <option value={winery.id}>{winery.name}</option>
                                ))}
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
                    <SearchWinesList results={resultsWines} />
                </div>
            </div>
        </div>
    )
}


