
import React, { useCallback, useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import "./css/Profile.css"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faPlus} from '@fortawesome/free-solid-svg-icons'
import { getAuthToken, request, setAuthHeader } from '../../Helpers/axios_helper';

export const Profile = () => {
    const navigate = useNavigate();

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


      const [userDetails, setUserDetails] = useState([])
      const [username, setUsername] = useState('')
      const [phoneNumber, setPhoneNumber] = useState('')
      const [address, setAddress] = useState('')
      const [changes, setChanges] = useState(false)

      const initalUserDetails = () => {
        request(
            "GET",
            "/auth/user/edit",
            {
              headers: {
                  Authorization: `Bearer ${getAuthToken()}`,
              },
            }
            ).then(
            (response) => {
                setUserDetails(response.data)
                setUsername(response.data.username)
                setPhoneNumber(response.data.phoneNumber)
                setAddress(response.data.address)
            }).catch(
            (error) => {
              console.error('Error fetching data:', error);
            }
        );
    };

    useEffect(() => {
        if(getAuthToken() !== "null" && getAuthToken() !== null){
            initalUserDetails()
        }
    }, []);

    const handleUsernameChange = (e) => {
        setUsername(e.target.value);
        setChanges(true)
    };

    const handlPhoneNumberChange = (e) => {
        setPhoneNumber(e.target.value);
        setChanges(true)
    };

    const handleAddressChange = (e) => {
        setAddress(e.target.value);
        setChanges(true)
    };


    const handleUserEdit = async (event) => {
        event.preventDefault();
            const formData = new FormData(event.currentTarget);
            request(
              "POST",
              "/auth/user/edit",
              {
                headers: {
                    Authorization: `Bearer ${getAuthToken()}`,
                },
                  username: formData.get('username'),
                  phoneNumber: formData.get('phoneNumber'),
                  address: formData.get('address'),
              }).then(
              (response) => {
                  navigate('/profile');
                  initalUserDetails()
                  setChanges(false)
              }).catch(
              (error) => {
                  navigate('/profile');
                  setChanges(false)
              }
          );
      };

    return (
        <div className='profile-container'>
            <form onSubmit={handleUserEdit}>
                <h1>USER PROFILE</h1>
                <div className='input-outer-container'>
                    <div className='input-container'>
                        <label htmlFor="email">Email</label>
                        <input type="text" name="email" id="email" placeholder='Email...' disabled readOnly value={userDetails.email}/>

                        <label htmlFor="username">Username</label>
                        <input type="text" name="username" id="username" placeholder='Username...' value={username} onChange={handleUsernameChange}/>

                        <label htmlFor="phoneNumber">Phone Number</label>
                        <input type="text" name="phoneNumber" id="phoneNumber" placeholder='Phone Number...' value={phoneNumber} onChange={handlPhoneNumberChange}/>

                        <label htmlFor="address">Address</label>
                        <input type="text" name="address" id="address" placeholder='Address...' value={address} onChange={handleAddressChange}/>
                    </div>
                    <div className='input-container'>
                        <p>Profile picture</p>
                        <div className="profile-pic">
                            <label className="-label" htmlFor="file">
                                <span className="glyphicon glyphicon-camera"></span>
                                <span><FontAwesomeIcon icon={faPlus}/></span>
                            </label>
                            <input id="file" type="file" name='profileImage' onChange={loadFile} accept="image/png, image/gif, image/jpeg"/>
                            <img src={require("../../images/defaultProfilePicture.jpg")} id="output" width="200" />
                        </div>
                    </div>
                </div>
                <button type='submit' disabled={!changes}>SUBMIT CHANGES</button>
            </form>
        </div>
    )
}
