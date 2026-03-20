
import React from "react";
import './App.css';
import Navbar from "../../../webbansach_frontend/src/layouts/header-footer/Navbar";
import Footer from "../../../webbansach_frontend/src/layouts/header-footer/Footer";
import HomePage from "../../../webbansach_frontend/src/layouts/homepage/HomePage";


function App() {
  return (
      <div className='App'>
          <Navbar/>
          <HomePage/>
          <Footer/>
      </div>
  );
}

export default App;
