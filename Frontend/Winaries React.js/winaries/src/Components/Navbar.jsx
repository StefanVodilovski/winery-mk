import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom';
import { Link, NavLink } from "react-router-dom"
import logoImage from '../images/logoWhite.png';
import "./Navbar.css"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faBagShopping, faBars} from '@fortawesome/free-solid-svg-icons'
import { getAuthToken, request, setAuthHeader } from '../Helpers/axios_helper';

export const Navbar = () => {

    const [scrollVh, setScrollVh] = useState(0);
    const navigate = useNavigate();

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

    const [activeProfile, setActiveProfile] = useState(false);
    const [activeHamburger, setActiveHamburger] = useState(false);

  // Define a function to handle the click event
    const handleClickProfile = () => {
    // Update the state using the setCount function
        setActiveProfile(!activeProfile);
    };

    const handleClickHamburger = () => {
        // Update the state using the setCount function
            setActiveHamburger(!activeHamburger);
        };

    const closeProfileMenu = () => {
        // Update the state using the setCount function
        setActiveProfile(false);
    };
    const closeHamburgerMenu = () => {
            // Update the state using the setCount function
        setActiveHamburger(false);
    };

    useEffect(() => {
        const handleResize = () => {
          if (window.innerWidth < 1000) {
            closeProfileMenu();
          }else if (window.innerWidth > 1000){
            closeHamburgerMenu();
          }
        };
    
        // Add event listener for the resize event
        window.addEventListener('resize', handleResize);
    
        // Remove the event listener when the component is unmounted
        return () => {
          window.removeEventListener('resize', handleResize);
        };
      }, []);



      const handleLogout = async () => {
            request(
              "GET",
              "/auth/logout",
              {

              }).then(
              () => {
                  setAuthHeader(null);
                  navigate('/login');
              }).catch(
              (error) => {
                  setAuthHeader(null);
                  navigate('/');
              }
          );
      };
    return (
        <nav className={navClass}>
            <Link to="/" className='title'>
                <div className='logo'></div>
            </Link>
            
            <div className='menu' onClick={handleClickHamburger}>
                <FontAwesomeIcon icon={faBars} size="2xl" className='hamburger'/>
            </div>

            <div className={`hamburger-menu ${activeHamburger ? 'hamburger-menu-opened' : ''}`}>
            <ul >
                <li> <NavLink to="/" onClick={() => {closeProfileMenu(); closeHamburgerMenu();}}> Home </NavLink> </li>
                <li> <NavLink to="/wines" onClick={() => {closeProfileMenu(); closeHamburgerMenu();}}> Wines </NavLink> </li>
                <li> <NavLink to="/wineries" onClick={() => {closeProfileMenu(); closeHamburgerMenu();}}> Wineries </NavLink> </li>
                <li> <NavLink to="/events" onClick={() => {closeProfileMenu(); closeHamburgerMenu();}}> Events </NavLink> </li>
                <li> <NavLink to="/map" onClick={() => {closeProfileMenu(); closeHamburgerMenu();}}> Map </NavLink> </li>
                <li><NavLink to="/cart" onClick={() => {closeProfileMenu(); closeHamburgerMenu();}}><FontAwesomeIcon icon={faBagShopping} className='shopping-cart-icon'/></NavLink></li>
                {getAuthToken() !== "null" ? (
                      <li><button onClick={handleLogout}>LOG OUT</button></li>
                    ) : (
                      <li>
                        <NavLink to="/login" onClick={() => { closeProfileMenu(); closeHamburgerMenu(); }}>LOG IN</NavLink>
                      </li>
                    )}
            </ul>
            </div>

            <ul >
                <li> <NavLink to="/" onClick={() => {closeProfileMenu(); closeHamburgerMenu();}}> Home </NavLink> </li>
                <li> <NavLink to="/wines" onClick={() => {closeProfileMenu(); closeHamburgerMenu();}}> Wines </NavLink> </li>
                <li> <NavLink to="/wineries" onClick={() => {closeProfileMenu(); closeHamburgerMenu();}}> Wineries </NavLink> </li>
                <li> <NavLink to="/events" onClick={() => {closeProfileMenu(); closeHamburgerMenu();}}> Events </NavLink> </li>
                <li> <NavLink to="/map" onClick={() => {closeProfileMenu(); closeHamburgerMenu();}}> Map </NavLink> </li>

            </ul>
            <div className='right-end'>
                <ul name="right_end">
                    <li><NavLink to="/cart" onClick={() => {closeProfileMenu(); closeHamburgerMenu();}}><FontAwesomeIcon icon={faBagShopping} className='shopping-cart-icon'/></NavLink></li>
                    {getAuthToken() !== "null" ? (
                      <li>
                        <img
                          className={activeProfile ? 'active' : ''}
                          onClick={handleClickProfile}
                          src={require("../images/defaultProfilePicture.jpg")}
                        />
                      </li>
                    ) : (
                      <li>
                        <NavLink to="/login" onClick={() => { closeProfileMenu(); closeHamburgerMenu(); }}>
                          LOG IN
                        </NavLink>
                      </li>
                    )}
                    
                </ul>
            </div>

            <div className={`profile-menu ${activeProfile ? 'profile-menu-opened' : ''}`}>
                <div className='profile-username'>
                    <img src={require("../images/defaultProfilePicture.jpg")} />
                    <p>Name</p>
                </div>
                
                <div className='profile-links'>
                    <NavLink to="/profile" onClick={() => {closeProfileMenu(); closeHamburgerMenu();}}>Edit your profiles</NavLink>
                </div>
                <div className='profile-links'>
                    <NavLink to="#" onClick={() => {closeProfileMenu(); closeHamburgerMenu();}}>View your orders</NavLink>
                </div>
                <div className='profile-links'>
                    <NavLink to="/create-event" onClick={() => {closeProfileMenu(); closeHamburgerMenu();}}>Create event</NavLink>
                </div>
                <div className='logout'>
                    <button onClick={handleLogout}>LOG OUT</button>
                </div>
            </div>     
        </nav>
    )
}


