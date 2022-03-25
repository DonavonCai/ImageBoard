import React from 'react';
// import { BrowserRouter, Routes, Route } from 'react-router-dom'
import FormModal from './components/FormModal'
import ImageGrid from './components/ImageGrid'
import './App.css'

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <FormModal form="LoginForm"/>
        <FormModal form="RegisterForm"/>
      </header>
      <div id="Page-content"
        style={{
          width: "100%",
          height: "100vh",
          display: "flex",
          justifyContent: "center",
        }}
      >
        <ImageGrid />
      </div>
    </div>
  );
}

export default App;
