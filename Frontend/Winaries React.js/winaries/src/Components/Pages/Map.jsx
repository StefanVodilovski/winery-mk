import React, { useState, useEffect } from 'react'
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet"
import { Icon, divIcon } from "leaflet"
import MarkerClusterGroup from "react-leaflet-cluster"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faMagnifyingGlass} from '@fortawesome/free-solid-svg-icons'
import { request, setAuthHeader, getAuthToken } from '../../Helpers/axios_helper';

import "leaflet/dist/leaflet.css";
import "./css/Map.css"



export const Map = () => {
    const [results, setResults] = useState([])
    const [filter, setFilter] = useState([])
    const [input, setInput] = useState('')

    const customIcon = new Icon({
        iconUrl: require("../../images/pin.png"),
        iconSize: [38, 38]
    })

    const initalData = () => {
        request(
            "GET",
            "/wineries/all",
            ).then(
            (response) => {
                setResults(response.data)
                setFilter(response.data)
            }).catch(
            (error) => {
              console.error('Error fetching data:', error);
            }
        );
    };

    useEffect(() => {
        initalData();
    }, []);

    const changeInput = (e) =>{
        setInput(e.target.value)
        filterWineries(e.target.value);
    }

    const filterWineries = (value) => {
        if (value.trim() === '') {
            setFilter(results);
        } else {
            const filteredResults = results.filter(result => result.name.toLowerCase().includes(value.toLowerCase()));
            setFilter(filteredResults);
        }
    };


    return (
        <div className='map'>

            <div className='search-container'>
                <input type="text" name="search" onChange={changeInput} value={input}/>
                <div className='search-icon-container'>
                    <FontAwesomeIcon icon={faMagnifyingGlass} className='search-icon'/>
                </div>
            </div>
            <MapContainer center={[41.744556, 21.730710]} zoom={9}>
                <TileLayer
                    attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                    url='https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
                />

                <MarkerClusterGroup
                    chunkedLoading
                // iconCreateFunction={createClusterCustomIcon}
                >
                    {filter.map(
                        winery => (
                            <Marker key={winery.id} position={[winery.xcordinate, winery.ycordinate]} icon={customIcon}>
                                <Popup>
                                    {winery.name}
                                </Popup>
                            </Marker>

                        )
                    )}
                </MarkerClusterGroup>
            </MapContainer>

        </div>
    )
}


