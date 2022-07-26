import React from 'react';
import { FormModal } from './components/FormModal'
import  { ImageGrid } from './components/ImageGrid'
import './App.css'
import { ProfileMenu } from './components/ProfileMenu';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <FormModal form="LoginForm"/>
        <FormModal form="RegisterForm"/>
        <ProfileMenu></ProfileMenu>
      </header>
      <div id="Page-content">
        {/* <ImageGrid /> */}
      </div>
    </div>
  );
}

export default App;
