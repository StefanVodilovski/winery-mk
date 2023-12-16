import React from 'react'
import "./css/Register.css"


export const Register = () => {
    return (
        <div className='register-container'>
            <form method='POST' action='http://localhost:8080/register/save'>
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

