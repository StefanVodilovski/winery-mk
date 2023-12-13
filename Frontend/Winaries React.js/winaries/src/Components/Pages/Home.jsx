import React from 'react'
import backgoundImg from '../../images/backgroundImage.jpg';
import "./css/Home.css"
import { Carousel } from '../Carousel';


export const Home = () => {
    return (
        <div className='home'>
            <div className="background-container">
                <div className='title-container'>
                    <h1 className='welcome-title'>
                        WELCOME
                    </h1>
                    <h2 className='slogan'>
                        Sip elegance, taste excellence
                    </h2>
                </div>
            </div>
            <Carousel />
        </div>
    )
}

