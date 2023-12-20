import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import "./css/Register.css"


export const Register = () => {

    const [registrationStatus, setRegistrationStatus] = useState(null);
    const navigate = useNavigate();

    const handleRegistration = async (event) => {
        event.preventDefault();

        console.log(event.currentTarget);
      
        try {
          const formData = new FormData(event.currentTarget);
      
          const response = await axios.post('http://localhost:8080/auth/register/save', formData);
      
          if (response.status === 200) {
            setRegistrationStatus('success');
            navigate('/login');
          } else {
            console.error('Registration failed. Status:', response.status);
          }
        } catch (error) {
          console.error('Registration failed', error);
        }
      };

    return (
        <div className='register-container'>
            <form onSubmit={handleRegistration}>
                <h1>REGISTER</h1>
                <div className='input-outer-container'>
                    <div className='input-container'>
                        <label htmlFor="email">Email</label>
                        <input type="text" name="email" id="email" placeholder='Email...'/>

                        <label htmlFor="password">Password</label>
                        <input type="password" name="password" id="password" placeholder='Password...'/>

                        <label htmlFor="confirmPassword">Confirm Password</label>
                        <input type="password" name="confirmPassword" id="confirmPassword" placeholder='Confirm Password...'/>
                    </div>
                    <div className='input-container'>
                        <label htmlFor="username">Username</label>
                        <input type="text" name="username" id="username" placeholder='Username...'/>

                        <label htmlFor="phoneNumber">Phone Number</label>
                        <input type="text" name="phoneNumber" id="phoneNumber" placeholder='Phone Number...'/>

                        <label htmlFor="address">Address</label>
                        <input type="text" name="address" id="address" placeholder='Address...'/>
                    </div>
                </div>
                <button type='submit'>REGISTER</button>
            </form>
        </div>
    )
}

