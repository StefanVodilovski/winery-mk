import React, { useState } from 'react'
// import { CarouselItem } from './CarouselItem'
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

import "./Carousel.css"

export const Carousel = () => {


    const items = [
        {
            id: 0,
            title: "BEST OFFER",
            description: "CHATEAU KAMNIK, TEMJANIKA 2022",
            price: "680 den",
            icon: require('../images/wine1.png')
        },
        {
            id: 1,
            title: "NEWEST",
            description: "CHATEAU KAMNIK, GOLDEN TEMJANIKA",
            price: "1070 den",
            icon: require('../images/wine3.png')
        },
        {
            id: 2,
            title: "CHEAPEST",
            description: "CHATEAU KAMNIK, SELECTION 2021",
            price: "370 den",
            icon: require('../images/wine2.png')
        }

    ]

    const settings = {
        dots: true,
        infinite: true,
        speed: 250,
        slidesToShow: 1,
        slidesToScroll: 1,
        initialSlide: 1,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1,
                    initialSlide: 2,
                    infinite: true,
                    dots: true
                }
            },
            {
                breakpoint: 600,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1,
                    initialSlide: 2,
                    infinite: true,
                    dots: true
                }
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1,
                    initialSlide: 2,
                    infinite: true,
                    dots: true
                }
            }
        ]
    };




    return (
        <div className='Carousel'>
            <Slider {...settings}>
                {items.map((item) => {
                    return <div className='card'>
                        <div className='card-top'>
                            {console.log(item.title)}
                            <h1>{item.title}</h1>
                            <img src={item.icon} alt="" />
                        </div>
                        <div className='card-bottom'>
                            <h3>{item.description}</h3>
                            <h3>{item.price}</h3>
                        </div>
                    </div>
                })}
            </Slider>

        </div>
    )
}


