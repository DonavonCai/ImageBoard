import React from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom'
import { FormModal } from './components/FormModal'
import  { ImageGrid } from './components/ImageGrid'
import './App.css'
import { Button } from '@mui/material';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route
            path="/"
            element={
              <div className="App">
                <header className="App-header">
                  <Link to="/login">
                    <Button type="button">
                      Login
                    </Button>
                  </Link>
                  <Link to="/register">
                    <Button type="button">
                      Sign Up
                    </Button>
                  </Link>
                </header>
              </div>
            }>
        </Route>
        <Route
            path="login"
            element={<FormModal form="LoginForm"/>}>
        </Route>
        <Route
            path="register"
            element={<FormModal form="RegisterForm"/>}>
        </Route>
      </Routes>
      <div id="Page-content">
        <ImageGrid />
      </div>
    </BrowserRouter>

    // <div className="App">
    //   <header className="App-header">
    //     <FormModal form="LoginForm"/>
    //     <FormModal form="RegisterForm"/>
    //   </header>
    //   <div id="Page-content">
    //     <ImageGrid />
    //   </div>
    // </div>
  );
}

export default App;
