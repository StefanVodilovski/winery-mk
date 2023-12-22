import React from 'react'
import { useLocation } from 'react-router-dom';
import "./css/Winery.css"

export const Winery = () => {
  const location = useLocation();
  const wineryData = location.state?.wineryData || {};

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
              
          </div>
        </div>
    </div>
  );
}