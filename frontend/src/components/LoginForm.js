import React from 'react';
import axios from 'axios';
import Button from '@mui/material/Button'

export default function LoginForm() {
  function requestLogin(e) {
    e.preventDefault();
    console.log('You clicked login');
    const response = axios.get("hello");
    response.then((res) => {
      console.log(res.data);
    });
  }
  return (
    <form onSubmit={requestLogin}>
      <label>
        <input type="text" name="username" placeholder="Username"/>
      </label>
      <br />
      <label>
        <input type="text" name="password" placeholder="Password"/>
      </label>
      <br />
      <Button type="submit" variant="contained">Log in</Button>
    </form>
  );
}