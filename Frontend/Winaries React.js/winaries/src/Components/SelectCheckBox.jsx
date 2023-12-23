import React, { useState } from 'react';
import './SelectCheckBox.css'


export const SelectCheckBox = ({results, setResults, selectList, setSelectList, selectedList, setSelectedList, imageSrc, altText, initialValue, id}) => {
  const [isChecked, setIsChecked] = useState(initialValue);

  const handleCheckboxChange = () => {
    let temp = !isChecked
    setIsChecked(temp);
    const winery = results.find((result) => result.id === id);
      if (temp) {
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
      <label className={`checkbox-button ${isChecked ? 'checked' : 'unchecked'}`}>
        <input type="checkbox" checked={isChecked} onClick={handleCheckboxChange} />
        {isChecked ? 'SELECTED' : 'SELECT'}
      </label>
    </div>
  );
};
