import Home from "./pages/Home.js"
import Login from "./pages/Login.js"
import SignUp from "./pages/SignUp.js"
import User from "./pages/User.js"
import Account from "./pages/Account.js"
import Navigation from "./parts/Navigation.js"
import NotFound from "./pages/NotFound.js"

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
        <Route path="/account" element={<Account />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;