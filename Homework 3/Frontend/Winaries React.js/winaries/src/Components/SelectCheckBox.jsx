import React, { useState } from 'react';
import './SelectCheckBox.css'


export const SelectCheckBox = ({results, setResults, selectList, setSelectList, selectedList, setSelectedList, imageSrc, altText, initialValue, id, text}) => {
  const [isChecked, setIsChecked] = useState(initialValue);

  const handleCheckboxChange = () => {
    const winery = results.find((result) => result.id === id);
      if (text === "SELECT") {
        setSelectList(selectList.filter(w => w.id !== id));
        setSelectedList(selectedList.concat(winery));
      } else {
        setSelectList(selectList.concat(winery));
        setSelectedList(selectedList.filter(w => w.id !== id));
      }    
  };

  return (
      <div className="image-container">
        <img
          src={imageSrc}
          alt={altText}
          className="image"
        />
        <button type="button" onClick={handleCheckboxChange}>{text}</button>
      </div>
  );
};
