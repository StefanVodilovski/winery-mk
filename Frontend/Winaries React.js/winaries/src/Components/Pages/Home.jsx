import React from 'react'
import backgoundImg from '../../images/backgroundImage.jpg';
import "./css/Home.css"
import { Carousel } from '../Carousel';




export const Home = () => {
    return (
        <div>
            <div>
                <img src={backgoundImg} alt="" className='bckImg' />
            </div>
            <Carousel />
        </div>
    )
}

