import React, { useState, useEffect } from 'react'
import backgoundImg from '../../images/backgroundImage.jpg';
import "./css/Home.css"
import { Carousel } from '../Carousel';
import { Link, NavLink } from "react-router-dom"
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet"
import { Icon, divIcon } from "leaflet"
import { request, setAuthHeader, getAuthToken } from '../../Helpers/axios_helper';


export const Home = () => {
    const [results, setResults] = useState([])

    const initalData = () => {
        request(
            "GET",
            "/wineries/all",
            ).then(
            (response) => {
                setResults(response.data)
            }).catch(
            (error) => {
              console.error('Error fetching data:', error);
            }
        );
    };

    useEffect(() => {
        initalData();
    }, []);

    const customIcon = new Icon({
        iconUrl: require("../../images/pin.png"),
        iconSize: [38, 38]
    })

    return (
        <div className='home'>
            <div className="background-container">
                <div className='title-container'>
                    <h1 className='welcome-title'>
                        WELCOME
                    </h1>
                    <h2 className='slogan'>
                        Sip elegance, taste excellence
                    </h2>
                </div>
            </div>
            <Carousel />
            <div className='map-container'>
                <MapContainer center={[41.671658, 21.762017]} zoom={9} dragging={false} scrollWheelZoom={false}>
                    <TileLayer
                        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                        url='https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
                    />
                    <div className='open-map-button-container'>
                        <NavLink className='open-map-button' to="/map" > OPEN MAP </NavLink>
                    </div>
                    {results.map(
                        winery => (
                            <Marker position={[winery.xcordinate, winery.ycordinate]} icon={customIcon}>
                                <Popup>
                                    {winery.name}
                                </Popup>
                            </Marker>

                        )

                    )}

                </MapContainer>
            </div>


        </div>
    )
}

