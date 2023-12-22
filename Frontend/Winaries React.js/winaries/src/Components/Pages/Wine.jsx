import React from 'react'
import { useLocation } from 'react-router-dom';
import "./css/Wine.css"

export const Wine = () => {
  const location = useLocation();
  const wineData = location.state?.wineData || {};

  return (
    <div>
        <div className='wine-description-container'>
            <div className='wine-description'>
                <div>
                  <img src={wineData.photoUrl} alt="" />
                </div>
                <div>
                  <h1>{wineData.name}</h1>
                  <p>{wineData.price} mkd</p>
                  <p>WINERY: <span>{wineData.winery.name}</span></p>
                  <form>
                    <input type="number" min='0' max={wineData.stock}/>
                    <button>ADD TO CART</button>
                  </form>
                </div>
            </div>

        </div>
        <div className='also-from-container'>
          <h1>ALSO FROM {wineData.winery.name.toUpperCase()}</h1>
          <div className='also-from-wines-list'>
              
          </div>
        </div>
    </div>
  );
}





