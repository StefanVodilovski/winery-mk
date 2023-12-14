
import { Route, Routes, useNavigate, useLocation  } from "react-router-dom"
import './App.css';
import { Navbar } from './Components/Navbar';
import { Footer } from './Components/Footer';
import { Home, Wines, Winaries, Events, Map, Login, Register} from './Components/Pages';
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
        <Route path="/winaries" element={<Winaries />}> </Route>
        <Route path="/events" element={<Events />}> </Route>
        <Route path="/map" element={<Map />}> </Route>
        <Route path="/login" element={<Login />}> </Route>
        <Route path="/register" element={<Register />}> </Route>
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
