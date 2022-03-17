import React from 'react'
import axios from 'axios'
import Button from '@mui/material/Button'
import TextField from '@mui/material/TextField'
import { FormControl } from '@mui/material'
import { useForm } from 'react-hook-form'

export default function LoginForm() {
  const {
    handleSubmit,
    formState: { errors }
  } = useForm();

  const onSubmit = (data) => {
    console.log('You clicked login');
    const response = axios.get("hello");
    response.then((res) => {
      console.log(res.data);
    });
  };

  return (
    <FormControl align="center">
      <form onSubmit={handleSubmit(onSubmit)}>
        <TextField label="Username" size="small" variant="outlined" sx={{ marginTop: '8px' }} required />
        <TextField label="Password" size="small" variant="outlined" sx={{ marginTop: '8px' }} type="password" required />
        <Button type="submit" variant="contained" sx={{ display: 'block', marginTop: '8px' }}>Log in</Button>
      </form>
    </FormControl>
  );
}