import React, { useState } from 'react';
import './SelectCheckBox.css'


export const SelectCheckBox = ({results, setResults, selectList, setSelectList, selectedList, setSelectedList, imageSrc, altText, initialValue, id}) => {
  const [isChecked, setIsChecked] = useState(initialValue);

  const handleCheckboxChange = () => {
    setSelectedList((prevSelectedList) => {
      const winery = results.find((result) => result.id === id);
  
      if (isChecked) {
        // Remove from selectedList and add to selectList
        setSelectList((prevSelectList) => [...prevSelectList, winery]);
        return prevSelectedList.filter((item) => item.id !== id);
      } else {
        // Remove from selectList and add to selectedList
        setSelectList((prevSelectList) => prevSelectList.filter((item) => item.id !== id));
        return [...prevSelectedList, winery];
      }
    });
  
    setIsChecked(!isChecked);
  };

  return (
    <div className="image-container">
      <img
        src={imageSrc}
        alt={altText}
        className="image"
      />
      <label className={`checkbox-button ${isChecked ? 'checked' : 'unchecked'}`}>
        <input type="checkbox" checked={isChecked} onChange={handleCheckboxChange} />
        {isChecked ? 'SELECTED' : 'SELECT'}
      </label>
    </div>
  );
};
