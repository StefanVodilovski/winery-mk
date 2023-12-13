import React from 'react'
import backgoundImg from '../../images/backgroundImage.jpg';
import "./css/Home.css"
import { Carousel } from '../Carousel';

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
        <div>
            <div>
                <img src={backgoundImg} alt="" className='bckImg' />
            </div>
            <Carousel />
            <MapContainer center={[41.950, 22]} zoom={8} >
                <TileLayer
                    attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                    url='https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
                />
                {markers.map(
                    marker => (
                        <Marker position={marker.geoCode} icon={customIcon}>
                            <Popup>
                                {marker.popUp}
                            </Popup>
                        </Marker>

                    )

                )}

            </MapContainer>



        </div>
    )
}

