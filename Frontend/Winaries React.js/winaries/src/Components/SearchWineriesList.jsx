import React from 'react'
import { WineryBox } from "./WineryBox"
import "./SearchWinesList.css"

export const SearchWineriesList = ({ results }) => {
    return (
        <div className='results-list'>
            {results.map((result, id) => {
                return <WineryBox result={result} key={id} />;
                
            })}
        </div>
    )
}