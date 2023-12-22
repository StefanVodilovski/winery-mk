import React from 'react'
import "./EventBox.css"
import { useNavigate } from 'react-router-dom';



export const EventBox = ({ result }) => {
    const navigate = useNavigate(); 

    const handleCardClick = async () => {
        try {
          // Send a GET request to fetch data from the server
          const response = await fetch(`http://localhost:8080/events/${result.id}`);
          const data = await response.json();
    
          // Redirect to the "/wine" page with the fetched data
          navigate('/event', { state: { eventData: data } });
        } catch (error) {
          console.error('Error fetching wine data:', error);
        }
      };
    
    return (
        <div key={result.id} className='event-card' onClick={handleCardClick}>
            <img src={result.photoUrl} alt="event image" className='event-card-image'  referrerPolicy="no-referrer"/>
            <div className='productCard__content'>
                <h3 className='productName'>{result.name}</h3>
            </div>
        </div>
    )
}