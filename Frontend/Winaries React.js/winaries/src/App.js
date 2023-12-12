
import { Route, Routes } from "react-router-dom"
import './App.css';
import { Navbar } from './Components/Navbar';
import { Footer } from './Components/Footer';
import { Home, Wines, Winaries, Events, Map } from './Components/Pages';



function App() {
  return (
    <div className="App">
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />}> </Route>
        <Route path="/wines" element={<Wines />}> </Route>
        <Route path="/winaries" element={<Winaries />}> </Route>
        <Route path="/events" element={<Events />}> </Route>
        <Route path="/map" element={<Map />}> </Route>
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
