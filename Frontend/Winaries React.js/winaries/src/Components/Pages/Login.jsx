import React from 'react'
import "./css/Login.css"

export const Login = () => {
    return (
        <div className='login-container'>
            <form method='POST' action='http://localhost:8080/login'>
                <h1>LOG IN</h1>
                <div className='input-outer-container'>
                    <div className='input-container'>
                        <label htmlFor="username">Email</label>
                        <input type="text" name="username" id="username" placeholder='Email...'/>
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

