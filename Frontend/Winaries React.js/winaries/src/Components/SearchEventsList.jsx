import React from 'react'
import { EventBox } from "./EventBox"
import "./SearchWinesList.css"

export const SearchEventsList = ({ results }) => {
    return (
        <div className='results-list'>
            {results.map((result, id) => {
                return <EventBox result={result} key={id} />;
                
            })}
        </div>
    )
}