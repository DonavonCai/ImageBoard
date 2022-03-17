import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import LoginModal from './components/LoginModal'
import './App.css'

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <LoginModal />
      </header>
      <BrowserRouter>
        <Routes>
          {/* <Route path="/" element={<LoginButton />} /> */}
          {/* <Route path="/login" element={<LoginForm />} /> */}
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
