
import React, { useCallback } from 'react';
import "./css/Profile.css"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faPlus} from '@fortawesome/free-solid-svg-icons'

export const Profile = () => {
    const loadFile = useCallback((event) => {
        const input = event.target;
        const image = document.getElementById("output");
    
        if (input.files && input.files[0]) {
          const reader = new FileReader();
    
          reader.onload = function (e) {
            image.src = e.target.result;
          };
    
          reader.readAsDataURL(input.files[0]);
        } else {
          // If no file is selected (e.g., canceled), reset the file input value
          input.value = null;
          // Optionally, you can clear the image source or provide a default image
          // image.src = ""; or image.src = "default-image.jpg";
        }
      }, []);

      

    return (
        <div className='profile-container'>
            <form>
                <h1>USER PROFILE</h1>
                <div className='input-outer-container'>
                    <div className='input-container'>
                        <label htmlFor="email">Email</label>
                        <input type="text" name="email" id="email" placeholder='Email...' disabled/>

                        <label htmlFor="username">Username</label>
                        <input type="text" name="username" id="username" placeholder='Username...'/>

                        <label htmlFor="phoneNumber">Phone Number</label>
                        <input type="text" name="phoneNumber" id="phoneNumber" placeholder='Phone Number...'/>

                        <label htmlFor="address">Address</label>
                        <input type="text" name="address" id="address" placeholder='Address...'/>
                    </div>
                    <div className='input-container'>
                        <p>Profile picture</p>
                        <div className="profile-pic">
                            <label className="-label" for="file">
                                <span className="glyphicon glyphicon-camera"></span>
                                <span><FontAwesomeIcon icon={faPlus}/></span>
                            </label>
                            <input id="file" type="file" name='profileImage' onChange={loadFile} accept="image/png, image/gif, image/jpeg"/>
                            <img src={require("../../images/defaultProfilePicture.jpg")} id="output" width="200" />
                        </div>
                    </div>
                </div>
                <button type='submit' disabled>SUBMIT CHANGES</button>
            </form>
        </div>
    )
}
