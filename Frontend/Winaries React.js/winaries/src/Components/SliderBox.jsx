import React, {useState, useEffect} from 'react'
import Slider from "react-slick";
import { WineBox } from "./WineBox"
import { WineryBox } from "./WineryBox"
import "./SliderBox.css"

export const SliderBox = ({results, display}) => {
    const settings = {
        dots: true,
        infinite: true,
        speed: 500,
        slidesToShow: 3,
        slidesToScroll: 3,
        responsive: [
          {
            breakpoint: 1400,
            settings: {
              slidesToShow: 2,
              slidesToScroll: 2,
            },
          },
          {
            breakpoint: 950,
            settings: {
              slidesToShow: 1,
              slidesToScroll: 1,
            },
          },
        ],
      };

      return(
        <Slider {...settings}>
            {results.map((result, id) => {
                if(display === "wine")
                    return <WineBox result={result} key={id}/>;
                if(display === "winery")
                    return <WineryBox result={result} key={id}/>;
                
            })}
        </Slider>
      )

}