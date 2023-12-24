import React, {useState, useEffect} from 'react'
import { useLocation } from 'react-router-dom';
import "./css/Wine.css"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faAngleRight, faAngleLeft} from '@fortawesome/free-solid-svg-icons'
import { request, setAuthHeader, getAuthToken } from '../../Helpers/axios_helper';
import {SliderBox} from "../SliderBox"


export const Wine = () => {
  const location = useLocation();
  const wineData = location.state?.wineData || {};

  const[results, setResults] = useState([])

  const [number, setNumber] = useState(1);

  const lowerValue = () => {
    if (number > 0){
        setNumber(number-1);
    }
  }

  const higherValue = () => {
    if (number < wineData.stock){
        setNumber(number+1);
    }
  }

  const changeValue = (e) =>{
    setNumber(parseInt(e.target.value))
  }

  const handleAddToCart = () => {
    request(
      "GET",
      "/wines/add/cart/item/" + wineData.id +"?quantity=" + number,
      {
        headers: {
            authorizationHeader: `Bearer ${getAuthToken()}`, // Include the authentication token in the Authorization header
        },
      }
      ).catch(
      (error) => {
        console.error('Error fetching data:', error);
      }
    );
  }

  const initalData = () => {
    request(
        "GET",
        "/wineries/" + wineData.winery.id + "/wines",
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

  useEffect(() => {
    setNumber(1);
  }, [wineData]);

  return (
    <div id="winePageWidth">
        <div className='wine-description-container'>
            <div className='wine-description'>
                <div>
                  <img src={wineData.photoUrl} alt="" />
                </div>
                <div>
                  <h1>{wineData.name.toUpperCase()}</h1>
                  <p className='winePrice'>{wineData.price} mkd</p>
                  <p className='wineryName'>WINERY: <span>{wineData.winery.name}</span></p>
                    <div className='add-to-cart-form'>
                    <div className='inputType'>
                      <button type="button" onClick={lowerValue} id='prev'><FontAwesomeIcon icon={faAngleLeft} /></button>
                      <input name="quantity" type="number" min='0' max={wineData.stock} value={number} onChange={changeValue}/>
                      <button type="button" onClick={higherValue} id='next'><FontAwesomeIcon icon={faAngleRight} /></button>
                    </div>
                    <button type='submit' id='addToCart' onClick={handleAddToCart}>ADD TO CART</button>
                    </div>
                </div>
            </div>

        </div>
        <hr/>
        <div className='also-from-container'>
          <h1>ALSO FROM {wineData.winery.name.toUpperCase()}</h1>
          <div className='also-from-wines-list'>
              <SliderBox results={results} display="wine"/>
          </div>
        </div>
    </div>
  );
}





