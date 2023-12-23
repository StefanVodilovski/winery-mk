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

  return (
    <div id="winePageWidth">
        <div className='wine-description-container'>
            <div className='wine-description'>
                <div>
                  <img src={wineData.photoUrl} alt="" />
                </div>
                <div>
                  <h1>{wineData.name}</h1>
                  <p className='winePrice'>{wineData.price} mkd</p>
                  <p className='wineryName'>WINERY: <span>{wineData.winery.name}</span></p>
                  <form>
                    <div className='inputType'>
                      <button type="button" onClick={lowerValue} id='prev'><FontAwesomeIcon icon={faAngleLeft} /></button>
                      <input type="number" min='0' max={wineData.stock} value={number} onChange={changeValue}/>
                      <button type="button" onClick={higherValue} id='next'><FontAwesomeIcon icon={faAngleRight} /></button>
                    </div>
                    <button type='submit' id='addToCart'>ADD TO CART</button>
                  </form>
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





