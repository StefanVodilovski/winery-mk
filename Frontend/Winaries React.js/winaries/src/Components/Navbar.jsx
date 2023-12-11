import React, { useState } from 'react'
import { Link, NavLink } from "react-router-dom"
import logoImage from '../images/logoImage.png';
import BagImage from '../images/BagImage.png';
import "./Navbar.css"

export const Navbar = () => {
    const [menuOpen, setMenuOpen] = useState(false)
    return (
        <nav>
            <Link to="/" className='title'>
                <img src={logoImage} />
            </Link>
            <div className='menu' onClick={() => {
                setMenuOpen(!menuOpen)
            }}>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
            </div>
            <ul className={menuOpen ? "open" : ""} >
                <li> <NavLink to="/"> Home </NavLink> </li>
                <li> <NavLink to="/wines"> Wines </NavLink> </li>
                <li> <NavLink to="/winaries"> Winaries </NavLink> </li>
                <li> <NavLink to="/events"> Events </NavLink> </li>
                <li> <NavLink to="/map"> Map </NavLink> </li>
            </ul>
            <ul className={menuOpen ? "open" : ""} name="right_end">
                <li><NavLink to="/cart"><img src={BagImage} alt='bagImage' /></NavLink></li>
                <li><NavLink to="/register">My profile</NavLink></li>
            </ul>
        </nav>
    )
}


