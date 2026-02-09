import Home from './pages/Home';
import Catalog from './pages/Catalog';
import Cart from './pages/Cart';
import Navigation from './parts/Navigation';
import {useState} from "react";

import { BrowserRouter, Routes, Route } from "react-router-dom"

function App() {
  const [items, setItems] = useState(
          [
              {   
                  id: 1,
                  name: "C4",
                  amount: "99",
                  img: "https://www.shutterstock.com/image-photo/bomb-timer-on-white-background-600nw-1999656191.jpg"
              },
              {   
                  id: 2,
                  name: "Defuse kit",
                  amount: "400",
                  img: "https://purepng.com/public/uploads/large/purepng.com-hammerhammerscoldstrike-agairon-hammer-1701527869920y9pcy.png"
              }
          ]   
  )

  const [cart, setCart] = useState([])

  const addToCart = (id) => {
    let item = { ...items.find(i => i.id === id) };

    let newId = Math.max(...cart.map(item => item.id)) + 1;

    if(newId == -Infinity){
        item.id = 1;
    }else{
        item.id = newId;
    }

    setCart([...cart, item]);
  }

  const removeFromCart = (id) => {
    setCart(cart.filter((item) => item.id !== id));
  }

  return (
    <BrowserRouter>
      <div>
        <Navigation />

        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/cart" element={<Cart list={cart} Remove={removeFromCart}/>} />
          <Route path="/catalog" element={<Catalog list={items} Add={addToCart}/>} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
