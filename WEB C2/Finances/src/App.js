import React from "react";
import Home from './pages/Home';
import Expenses from './pages/Expenses';

import { BrowserRouter, Link, Routes, Route } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <div>
        <nav>
          <Link to="/">Home</Link>
          <Link to="/expenses">Expenses</Link>
        </nav>

        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/expenses" element={<Expenses />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
