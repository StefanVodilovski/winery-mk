import React from "react";
import image from '../images/testImg.svg'

export const CarouselItem = ({ item }) => {
    return (
        <div className="carousel-item" >
            <div></div>
            <img className="carousel-img" src={image} />
            <div className="carousel-item-text">{item.description} dasdasda</div>
        </div>
    );
};