import React, { useState, useEffect } from 'react'
import { Searchbar } from "../Searchbar"
import { SearchEventsList } from "../SearchEventsList"
import "./css/Events.css"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faMagnifyingGlass} from '@fortawesome/free-solid-svg-icons'
import { request, setAuthHeader, getAuthToken } from '../../Helpers/axios_helper';

export const Events = () => {
    const [results, setResults] = useState([])
    const [resultsWineries, setResultsWineries] = useState([])

    const [searchQuery, setSearchQuery] = useState('')

    const updateSearch = (value) => {
        setSearchQuery(value)
    }

    const [winery, setWinery] = useState('-1');

    const handleButtonClick = () => {
        fetchData()
    };

    const fetchData = () => {
        let url = "/events/filter?";
        if(searchQuery != "")
            url = url + "&searchQuery=" + searchQuery;
        if(winery != -1) 
            request(
                "GET",
                url,
                ).then(
                (response) => {
                    setResults(response.data)
                }).catch(
                (error) => {
                  console.error('Error fetching data:', error);
                }
            );
    };    

    const initalData = () => {
        request(
            "GET",
            "/events/all",
            ).then(
            (response) => {
                setResults(response.data)
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
        initalData();
        initalDataWineries();
    }, []);

    return (
        <div className='events-outer-container'>
            <div className='events-inner-container'>
                <h1>EVENTS</h1>
                <div className='filter-section'>
                    <div className='search-bar-container'>
                        <Searchbar setResults={setResults} updateSearch={updateSearch} ></Searchbar>
                        <button className='search-button' onClick={handleButtonClick}><FontAwesomeIcon icon={faMagnifyingGlass} /></button>
                    </div>
                    <div className='filter-options'>
                        <div className='filter-option'>
                            <label htmlFor="winery">Winery</label>
                            <select name='winery' id='winery' onChange={(e) => (setWinery(e.target.value))}>
                                <option value={-1}></option>
                                {resultsWineries.map(winery => (
                                    <option value={winery.id}>{winery.name}</option>
                                ))}
                            </select>
                        </div>
                    </div>
                </div>

                <div className='events-list'>
                    <SearchEventsList results={results} />
                </div>
            </div>
        </div>
    )
}

