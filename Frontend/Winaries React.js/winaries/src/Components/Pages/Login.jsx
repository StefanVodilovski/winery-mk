import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';  // Ensure this line is correct
import axios from 'axios';
import "./css/Login.css"

export const Login = () => {
    const [loginStatus, setLoginStatus] = useState(null);
  const navigate = useNavigate();

  const handleLogin = async (event) => {
    event.preventDefault();

    try {
      const formData = new FormData(event.currentTarget);

      // Example: Serialize form data to JSON
      const data = {};
      formData.forEach((value, key) => {
        data[key] = value;
      });

      const response = await axios.post('http://localhost:8080/login', data, {
        headers: {
          'Content-Type': 'application/json',
        },
      });

      if (response.data.success) {
        setLoginStatus('success');
        navigate('/');
      } else {
        console.error('Login failed. Message:', response.data.message);
      }
    } catch (error) {
      console.error('Login failed', error);
    }
  };
    return (
        <div className='login-container'>
            <form onSubmit={handleLogin}>
                <h1>LOG IN</h1>
                <div className='input-outer-container'>
                    <div className='input-container'>
                        <label htmlFor="email">Email</label>
                        <input type="text" name="email" id="email" placeholder='Email...'/>
                    </div>
                    <div className='input-container'>
                        <label htmlFor="password">Password</label>
                        <input type="password" name="password" id="password" placeholder='Password...'/>
                    </div>
                    <div className='input-container'>
                        <label htmlFor="rememberMe">Password</label>
                        <input type="checkbox" name="remember-me" id="rememberMe"/>Remember Me
                    </div>
                </div>
                <button type='submit'>LOG IN</button>
            </form>
        </div>
    )
}

