import React from "react";
import axios from "axios";

// import About from "./pages/about";

function App() {
  function requestLogin(e) {
    e.preventDefault();
    console.log('You clicked login');
    const response = axios.get("hello");
    response.then((res) => {
      console.log(res.data);
    });
  }
  return (
    <div className="App">
    <header className="App-header">
      <button onClick={requestLogin}>Login</button>
    </header>
  </div>
  );
}

export default App;
