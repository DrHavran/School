import Home from './pages/Home';
import Catalog from './pages/Catalog';
import Cart from './pages/Cart';
import Navigation from './parts/Navigation';

import { BrowserRouter, Routes, Route } from "react-router-dom"

function App() {
  return (
    <BrowserRouter>
      <div>
        <Navigation />

        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/cart" element={<Cart/>} />
          <Route path="/catalog" element={<Catalog/>} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
