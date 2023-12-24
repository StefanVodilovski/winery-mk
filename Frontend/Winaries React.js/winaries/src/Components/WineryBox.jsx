import React from 'react'
import "./WineryBox.css"
import { useNavigate } from 'react-router-dom';



export const WineryBox = ({ result }) => {
    const navigate = useNavigate(); 

    const handleCardClick = async () => {
        try {
          // Send a GET request to fetch data from the server
          const response = await fetch(`http://localhost:8080/wineries/${result.id}`);
          const data = await response.json();
    
          // Redirect to the "/wine" page with the fetched data
          navigate('/winery', { state: { wineryData: data } });
        } catch (error) {
          console.error('Error fetching wine data:', error);
        }
      };
    
    return (
        <div key={result.id} className='winery-card' onClick={handleCardClick}>
            <img src={result.photoUrl} alt="winery image" className='winery-card-image'  referrerPolicy="no-referrer"/>
            <div className='productCard__content'>
                <h3 className='productName'>{result.name.toUpperCase()}</h3>
            </div>
        </div>
    )
}
