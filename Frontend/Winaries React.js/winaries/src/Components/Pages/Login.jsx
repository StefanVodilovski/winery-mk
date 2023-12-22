import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';  // Ensure this line is correct
import axios from 'axios';
import "./css/Login.css"
import { request, setAuthHeader } from '../../Helpers/axios_helper';

export const Login = () => {
  const navigate = useNavigate();

  const handleLogin = async (event) => {
    event.preventDefault();
        const formData = new FormData(event.currentTarget);
        request(
          "POST",
          "/auth/login",
          {
              username: formData.get('username'),
              password: formData.get('password'),
          }).then(
          (response) => {
              setAuthHeader(response.data.token);
              navigate('/');
          }).catch(
          (error) => {
              setAuthHeader(null);
              navigate('/login');
          }
      );
  };
    return (
        <div className='login-container'>
            <form onSubmit={handleLogin}>
                <h1>LOG IN</h1>
                <div className='input-outer-container'>
                    <div className='input-container'>
                        <label htmlFor="username">Email</label>
                        <input type="text" name="username" id="username" placeholder='Username...'/>
                    </div>
                    <div className='input-container'>
                        <label htmlFor="password">Password</label>
                        <input type="password" name="password" id="password" placeholder='Password...'/>
                    </div>
                </div>
                <button type='submit'>LOG IN</button>
            </form>
        </div>
    )
}

