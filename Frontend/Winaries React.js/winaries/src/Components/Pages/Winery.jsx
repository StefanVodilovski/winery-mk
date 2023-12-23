import React, {useState, useEffect} from 'react'
import { useLocation } from 'react-router-dom';
import "./css/Winery.css"
import { request, setAuthHeader, getAuthToken } from '../../Helpers/axios_helper';
import {SliderBox} from "../SliderBox"


export const Winery = () => {
  const location = useLocation();
  const wineryData = location.state?.wineryData || {};
  const[results, setResults] = useState([])

  const initalData = () => {
    request(
        "GET",
        "/wineries/" + wineryData.id + "/wines",
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

  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 3,
    slidesToScroll: 3,
    responsive: [
      {
        breakpoint: 1450,
        settings: {
          slidesToShow: 2,
        },
      },
      {
        breakpoint: 900,
        settings: {
          slidesToShow: 1,
        },
      },
    ],
  };

  return (
    <div id='wineryPageWidth'>
        <div className='winery-description-container'>
                <div>
                  <img src={wineryData.photoUrl} alt="" />
                </div>
                <div id='wineryData'>
                  <h1>{wineryData.name}</h1>
                  <p>{wineryData.description.toUpperCase()}</p>
                  <a href="">VISIT</a>
                </div>

        </div>
        <hr/>
        <div className='also-from-container'>
          <h1>ALSO FROM {wineryData.name.toUpperCase()}</h1>
          <div className='also-from-wineries-list'>
              <SliderBox results={results} display="wine"/>
          </div>
        </div>
    </div>
  );
}