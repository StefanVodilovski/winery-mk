import React, { useCallback, useState, useEffect } from 'react'
import { MapContainer, TileLayer, Marker, Popup, useMapEvents } from 'react-leaflet';
import "./css/CreateEvent.css"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faPlus, faLocationDot } from '@fortawesome/free-solid-svg-icons'
import { Icon, divIcon } from "leaflet"
import MarkerClusterGroup from "react-leaflet-cluster"
import L from 'leaflet';
import ReactDOMServer from 'react-dom/server';
import { request, setAuthHeader, getAuthToken } from '../../Helpers/axios_helper';
import { SelectCheckBox } from '../SelectCheckBox';



export const CreateEvent = () => {
    const [results, setResults] = useState([])
    const [selectList, setSelectList] = useState([])
    const [selectedList, setSelectedList] = useState([])

    const [markerPosition, setMarkerPosition] = useState(null);

    const handleMapClick = (e) => {
        const { lat, lng } = e.latlng;
        setMarkerPosition({ lat, lng });
    };

    const loadFile = useCallback((event) => {
        const input = event.target;
        const image = document.getElementById("output");

        if (input.files && input.files[0]) {
            const reader = new FileReader();

            reader.onload = function (e) {
                image.src = e.target.result;
            };

            reader.readAsDataURL(input.files[0]);
        } else {
            // If no file is selected (e.g., canceled), reset the file input value
            input.value = null;
            // Optionally, you can clear the image source or provide a default image
            // image.src = ""; or image.src = "default-image.jpg";
        }
    }, []);

    const initalData = () => {
        request(
            "GET",
            "/wineries/all",
            ).then(
            (response) => {
                setResults(response.data)
                setSelectList(response.data)
            }).catch(
            (error) => {
              console.error('Error fetching data:', error);
            }
        );
    };

    useEffect(() => {
        initalData();
    }, []);


    const [isChecked, setIsChecked] = useState(false);

  const handleCheckboxChange = () => {
    setIsChecked(!isChecked);
  };

    return (
        <div className='create-event-outer-container'>
            <div className='create-event-inner-container'>
                <h1>CREATE EVENT</h1>
                <form>
                    <div className='event-details-section'>
                        <div className='left-seciton'>
                            <div>
                                <label htmlFor="name">Event Name</label>
                                <input type="text" name="name" id="name" placeholder='Name...' required/>
                            </div>

                            <div>
                                <label htmlFor="dateStart">Date Start</label>
                                <input type="datetime-local" name="dateStart" id="dateStart" required/>
                            </div>

                            <div>
                                <label htmlFor="dateEnd">Date End</label>
                                <input type="datetime-local" name="dateEnd" id="dateEnd" required/>
                            </div>

                            <div>
                                <label htmlFor="description">Description</label>
                                <textarea name="description" id="description" required></textarea>
                            </div>
                        </div>
                        <div className='right-seciton'>
                            <div>
                                <p>Profile Picture</p>
                                <div className="profile-pic">
                                    <label className="-label" htmlFor="file">
                                        <span className="glyphicon glyphicon-camera"></span>
                                        <span><FontAwesomeIcon icon={faPlus} /></span>
                                    </label>
                                    <input id="file" type="file" name="eventImage" onChange={loadFile} accept="image/png, image/gif, image/jpeg" />
                                    <img src={require("../../images/WineEventDefault.jpg")} id="output" width="200" />
                                </div>
                            </div>
                            <div>
                                <p>Event Location</p>
                                <div className='location-container'>
                                    <MapContainer center={[41.744556, 21.730710]} zoom={7} onClick={handleMapClick}>
                                        <TileLayer
                                            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                                            url='https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
                                        />
                                        <ClickHandler onMapClick={handleMapClick} />
                                        {markerPosition && (
                                            <Marker
                                                position={markerPosition}
                                                icon={L.icon({
                                                    iconUrl: 'https://static.vecteezy.com/system/resources/previews/010/149/056/non_2x/location-pin-icon-sign-symbol-design-free-png.png',
                                                    iconSize: [32],
                                                    iconAnchor: [18, 42],
                                                    className: 'non-clickable-icon',
                                                })}
                                            >

                                            </Marker>
                                        )}

                                    </MapContainer>
                                    {markerPosition && (
                                        <>
                                            <input type="hidden" name="lat" value={markerPosition.lat} required/>
                                            <input type="hidden" name="lng" value={markerPosition.lng} required/>
                                        </>
                                    )}
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className='wineries-selection-section'>
                        <hr/>
                        <h1>SELECT WINERIES</h1>
                        <div className='select-wineries-container'>

                            {selectList.map(selectWinery => (
                                <SelectCheckBox imageSrc={selectWinery.photoUrl} 
                                                altText={selectWinery.name} 
                                                initialValue={false} 
                                                id={selectWinery.id}
                                                results={results}
                                                setResults={setResults}
                                                selectList={selectList}
                                                setSelectList={setSelectList}
                                                selectedList={selectedList}
                                                setSelectedList={setSelectedList}
                                                text="SELECT"/> 
                            ))}
                                         
                        </div>
                        <hr/>
                        {selectedList.length != 0 ? (
                            <h1 id="selected-wineries-h1" >SELECTED WINERIES</h1>
                        ):(
                            <h1 id="selected-wineries-h1" ></h1>
                        )}
                        
                        <div className='selected-wineries-container'>
                            {selectedList.map(selectedWinery => (
                                <SelectCheckBox imageSrc={selectedWinery.photoUrl} 
                                                altText={selectedWinery.name} 
                                                initialValue={true} 
                                                id={selectedWinery.id}
                                                results={results}
                                                setResults={setResults}
                                                selectList={selectList}
                                                setSelectList={setSelectList}
                                                selectedList={selectedList}
                                                setSelectedList={setSelectedList}
                                                text="SELECTED"/> 
                            ))}
                        </div>
                    </div>
                    <button className="submit-button" type='submit' disabled={!markerPosition}>SUBMIT</button>
                </form>
            </div>
        </div>
    )
}


const ClickHandler = ({ onMapClick }) => {
    const map = useMapEvents({
        click: (e) => {
            onMapClick(e);
        },
    });

    return null; // This component doesn't render anything, it just handles the click event
};