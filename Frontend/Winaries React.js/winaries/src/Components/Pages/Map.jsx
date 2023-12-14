import React from 'react'
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet"
import { Icon, divIcon } from "leaflet"
import MarkerClusterGroup from "react-leaflet-cluster"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faMagnifyingGlass} from '@fortawesome/free-solid-svg-icons'

import "leaflet/dist/leaflet.css";
import "./css/Map.css"



export const Map = () => {
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


    // const createClusterCustomIcon = function (cluster) {
    //     return new divIcon({
    //         html: `<span class="cluster-icon">${cluster.getChildCount()}</span>`,
    //         className: "custom-marker-cluster",
    //         iconSize: point(33, 33, true)
    //     });
    // };

    return (
        <div className='map'>

            <div className='search-container'>
                <input type="text" name="search"/>
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
                    {markers.map(
                        marker => (
                            <Marker position={marker.geoCode} icon={customIcon}>
                                <Popup>
                                    {marker.popUp}
                                </Popup>
                            </Marker>

                        )

                    )}
                </MarkerClusterGroup>
            </MapContainer>

        </div>
    )
}


