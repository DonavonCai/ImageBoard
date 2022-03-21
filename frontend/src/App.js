import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import FormModal from './components/FormModal'
import './App.css'

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <FormModal form="LoginForm"/>
        <FormModal form="RegisterForm"/>
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
