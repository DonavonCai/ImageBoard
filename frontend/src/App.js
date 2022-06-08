import React from 'react';
import { FormModal } from './components/FormModal'
import  { ImageGrid } from './components/ImageGrid'
import './App.css'
import { LogoutButton } from './components/LogoutButton';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <FormModal form="LoginForm"/>
        <FormModal form="RegisterForm"/>
        <LogoutButton></LogoutButton>
      </header>
      <div id="Page-content">
        <ImageGrid />
      </div>
    </div>
  );
}

export default App;
