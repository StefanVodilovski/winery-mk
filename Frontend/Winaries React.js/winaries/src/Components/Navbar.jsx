import React, { useState, useEffect } from 'react'
import { Link, NavLink } from "react-router-dom"
import logoImage from '../images/logoWhite.png';
import BagImage from '../images/BagImage.png';
import "./Navbar.css"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faBagShopping, faBars} from '@fortawesome/free-solid-svg-icons'

export const Navbar = () => {

    const [scrollVh, setScrollVh] = useState(0);

    useEffect(() => {
      const handleScroll = () => {
        const scrollPositionPixels = window.scrollY;
        const viewportHeight = window.innerHeight;
        const scrollPositionVh = (scrollPositionPixels / viewportHeight) * 100;
        setScrollVh(scrollPositionVh);
      };

      window.addEventListener('scroll', handleScroll);

      return () => {
        window.removeEventListener('scroll', handleScroll);
      };
    }, []);

    const navClass = scrollVh > 30 ? 'scrolled' : '';

    const [menuOpen, setMenuOpen] = useState(false)
    return (
        <nav className={navClass}>
            <Link to="/" className='title'>
                <div className='logo'></div>
            </Link>
            
            <div className='menu' onClick={() => {
                setMenuOpen(!menuOpen)
            }}>
                <FontAwesomeIcon icon={faBars} size="2xl"/>
            </div>
            <ul className={menuOpen ? "open" : ""} >
                <li> <NavLink to="/"> Home </NavLink> </li>
                <li> <NavLink to="/wines"> Wines </NavLink> </li>
                <li> <NavLink to="/winaries"> Winaries </NavLink> </li>
                <li> <NavLink to="/events"> Events </NavLink> </li>
                <li> <NavLink to="/map"> Map </NavLink> </li>
            </ul>
            <div className='right-end'>
                <ul className={menuOpen ? "open" : ""} name="right_end">
                    <li><NavLink to="/cart"><FontAwesomeIcon icon={faBagShopping} className='shopping-cart-icon'/></NavLink></li>
                    <li><NavLink to="/register">Login</NavLink></li>
                </ul>
            </div>
            
        </nav>
    )
}


