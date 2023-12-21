import React from 'react'
import "./WineBox.css"
import { useNavigate } from 'react-router-dom';



export const WineBox = ({ result }) => {
    const navigate = useNavigate(); 

    const handleCardClick = async () => {
        try {
          // Send a GET request to fetch data from the server
          const response = await fetch(`http://localhost:8080/wines/${result.id}`);
          const data = await response.json();
    
          // Redirect to the "/wine" page with the fetched data
          navigate('/wine', { state: { wineData: data } });
        } catch (error) {
          console.error('Error fetching wine data:', error);
        }
      };
    
    return (
        <div key={result.id} className='wine-card' onClick={handleCardClick}>
            <img src={result.photoUrl} alt="wine image" className='wine-card-image'  referrerPolicy="no-referrer"/>
            <div className='productCard__content'>
                <h3 className='productName'>{result.name}</h3>
                <div className='displayStack__1'>
                    <div className='productPrice'>{result.price}mkd</div>
                </div>
            </div>
        </div>
    )
}


