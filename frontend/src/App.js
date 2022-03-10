import React from "react";

// import About from "./pages/about";

import logo from './logo.svg';

function App() {

  return (
    <div className="App">
    <header className="App-header">
      <img src={logo} className="App-logo" alt="logo" />
      <button>Login</button>
    </header>
  </div>
    // Todo: set up multiple pages
        // <BrowserRouter>
        //   <Routes>
        //     <Route exact path="/" render={props => <About/> } />
        //   </Routes>
        // </BrowserRouter>
  );
}

export default App;
