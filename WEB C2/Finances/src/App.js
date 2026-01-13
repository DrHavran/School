import Home from './pages/Home';
import Expenses from './pages/Expenses';
import Navigation from './parts/Navigation';

import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <div>
        <Navigation />

        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/expenses" element={<Expenses />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
