import Home from "./pages/Home.js"
import Login from "./pages/Login.js"
import SignUp from "./pages/SignUp.js"
import User from "./pages/User.js"
import Navigation from "./parts/Navigation.js"
import NotFound from "./pages/NotFound.js"
import SendMoney from "./pages/SendMoney.js"
import './complete.css'

import { BrowserRouter, Routes, Route } from "react-router-dom"

function App() {
  return (
    <BrowserRouter>
      <Navigation />
  
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/user/:id" element={<User />} />
        <Route path="/:id/sendmoney" element={<SendMoney />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;