import React from 'react'
import "./WineBox.css"


export const WineBox = ({ result }) => {
    return (
        <div key={result.id} className='wine-card'>
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


