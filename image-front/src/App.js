import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Board from "./component/Board";
import Profile from "./component/Profile";
import Header from "./component/layout/Header";
import Main from "./component/Main";
import Footer from "./component/layout/Footer";

function App() {
  return (
    <Router>
      <Header />
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/board" element={<Board />} />
        <Route path="/profile" element={<Profile />} />
      </Routes>
      <Footer />
    </Router>
  );
}

export default App;
