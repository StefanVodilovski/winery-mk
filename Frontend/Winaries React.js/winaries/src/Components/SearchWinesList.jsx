import React from 'react'
import { WineBox } from "./WineBox"
import "./SearchWinesList.css"

export const SearchWinesList = ({ results }) => {
    return (
        <div className='results-list'>
            {results.map((result, id) => {
                return <WineBox result={result.name} key={id} />;
            })}
        </div>
    )
}

