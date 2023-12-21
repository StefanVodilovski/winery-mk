import React from 'react'
import { useLocation } from 'react-router-dom';
import "./css/Winery.css"

export const Winery = () => {
  const location = useLocation();
  const wineryData = location.state?.wineryData || {};

  return (
    <div>
        <div className='winery-description-container'>
            <div className='winery-description'>
                <div>
                  <img src={wineryData.photoUrl} alt="" />
                </div>
                <div>
                  <h1>{wineryData.name}</h1>
                  <p>{wineryData.description}</p>
                  <a href="">VISIT</a>
                </div>
            </div>

        </div>
        <div className='also-from-container'>
          <h1>ALSO FROM {wineryData.name.toUpperCase()}</h1>
          <div className='also-from-wineries-list'>
              
          </div>
        </div>
    </div>
  );
}