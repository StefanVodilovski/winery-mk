import React from 'react'
import backgoundImg from '../../images/backgroundImage.jpg';
import "./css/Home.css"
import { Carousel } from '../Carousel';
import { Link, NavLink } from "react-router-dom"
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet"
import { Icon, divIcon } from "leaflet"


export const Home = () => {
    const markers = [
        {
            geoCode: [41.43570265650141, 22.003397140766968],
            popUp: "Tikves winery"
        },
        {
            geoCode: [41.581161, 21.936234],
            popUp: "Stobi winery"
        },
        {
            geoCode: [41.99534976816323, 21.427186099999997],
            popUp: "Wines kamnik"
        },
        {
            geoCode: [42.007989862779425, 21.490283400000003],
            popUp: "Kamnik winery"
        }
    ]

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
                    {markers.map(
                        marker => (
                            <Marker position={marker.geoCode} icon={customIcon} interactive={false}>
                                <Popup>
                                    {marker.popUp}
                                </Popup>
                            </Marker>

                        )

                    )}

                </MapContainer>
            </div>


        </div>
    )
}

