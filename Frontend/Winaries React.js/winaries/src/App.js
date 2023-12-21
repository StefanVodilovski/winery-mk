
import { Route, Routes, useNavigate, useLocation  } from "react-router-dom"
import './App.css';
import { Navbar } from './Components/Navbar';
import { Footer } from './Components/Footer';
import { Home, Wines, Events, Map} from './Components/Pages';
import {Wineries} from './Components/Pages/Wineries'
import {Login} from './Components/Pages/Login';
import {Register} from './Components/Pages/Register';
import {Profile} from './Components/Pages/Profile';
import {CreateEvent} from './Components/Pages/CreateEvent'
import {Wine} from './Components/Pages/Wine'
import {Winery} from './Components/Pages/Winery'


import { useEffect } from 'react';



function App() {

  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    // Extract the route name (removing the leading slash)
    const routeName = location.pathname === '/' ? 'home' : location.pathname.slice(1);

    // Add a class to the body based on the route
    document.body.classList.add(`route-${routeName}`);

    // Remove the class when the component unmounts
    return () => {
      document.body.classList.remove(`route-${routeName}`);
    };
  }, [location.pathname]);

  return (
    <div className="App">
      <Navbar/>
      <Routes>
        <Route path="/" element={<Home />}> </Route>
        <Route path="/wines" element={<Wines />}> </Route>
        <Route path="/wineries" element={<Wineries />}> </Route>
        <Route path="/events" element={<Events />}> </Route>
        <Route path="/map" element={<Map />}> </Route>
        <Route path="/login" element={<Login />}> </Route>
        <Route path="/register" element={<Register />}> </Route>
        <Route path="/profile" element={<Profile />}> </Route>
        <Route path="/create-event" element={<CreateEvent />}> </Route>
        <Route path="/wine" element={<Wine />}> </Route>
        <Route path="/winery" element={<Winery />}> </Route>
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
