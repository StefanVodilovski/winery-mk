import React, { useCallback, useState } from 'react'
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import "./css/CreateEvent.css"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faPlus, faLocationDot} from '@fortawesome/free-solid-svg-icons'
import { Icon, divIcon } from "leaflet"
import MarkerClusterGroup from "react-leaflet-cluster"
import L from 'leaflet';
import ReactDOMServer from 'react-dom/server';



export const CreateEvent = () => {

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



    return (
        <div className='create-event-outer-container'>
            <div className='create-event-inner-container'>
                <h1>CREATE EVENT</h1>
                <form>
                    <div className='event-details-section'>
                        <div className='left-seciton'>
                            <div>
                                <label htmlFor="name">Event Name</label>
                                <input type="text" name="name" id="name" placeholder='Name...'/>
                            </div>

                            <div>
                                <label htmlFor="date">Date</label>
                                <input type="date" name="date" id="date"/>
                            </div>

                            <div className='input-time'>
                                <div>
                                    <label htmlFor="start">Start</label>
                                    <input type="time" name="start" id="start" placeholder=''/>
                                </div>
                                <div>
                                    <label htmlFor="end">End</label>
                                    <input type="time" name="end" id="end" placeholder=''/>
                                </div>
                            </div>

                            <div>
                                <label htmlFor="description">Description</label>
                                <textarea name="description" id="description"></textarea>
                            </div>
                        </div>
                        <div className='right-seciton'>
                            <div>
                                <p>Profile Picture</p>
                                <div class="profile-pic">
                                    <label class="-label" for="file">
                                        <span class="glyphicon glyphicon-camera"></span>
                                        <span><FontAwesomeIcon icon={faPlus}/></span>
                                    </label>
                                    <input id="file" type="file" onChange={loadFile} accept="image/png, image/gif, image/jpeg"/>
                                    <img src={require("../../images/WineEventDefault.jpg")} id="output" width="200" />
                                </div>
                            </div>
                            <div>
                                <p>Event Location</p>
                                <div className='location-container'>
                                    <MapContainer center={[41.744556, 21.730710]} zoom={7}>
                                        <TileLayer
                                            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                                            url='https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
                                        />
                                    </MapContainer>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className='winaries-selection-section'>
                        <div className='search-container'>
                                <label htmlFor="search">Search Wineries</label>
                                <input type="text" name="search" id="search" placeholder='Search...'/>
                        </div>
                        <div className='filter'>
                            <p>REGION</p>
                            <select name="region"></select>
                        </div>
                        <div className='select-winaries-container'>

                        </div>
                        <div className='selected-winaries-container'>

                        </div>
                    </div>
                    <button className="submit-button" type='submit'>SUBMIT</button>
                </form>
            </div>
        </div>
    )
}
