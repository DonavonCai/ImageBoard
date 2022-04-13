import axios from 'axios'
import Button from '@mui/material/Button'
import TextField from '@mui/material/TextField'
import Typography from '@mui/material/Typography';
import { FormControl } from '@mui/material'
import { useForm } from 'react-hook-form'

export function RegisterForm() {
    const {
        handleSubmit,
        formState: { errors }
      } = useForm();

      const onSubmit = (data) => {
        console.log('You clicked sign up');
        const response = axios.post("register");
        response.then((res) => {
          console.log(res.data);
        });
      };

      return (
        <div id="register-form-container">
          <Typography id="modal-title" variant="h5" component="h2" align="center">
          Sign Up
          </Typography>
          <Typography id="modal-instructions" color="#616161" align="center">Please create a username, email, and password.</Typography>
          <FormControl align="center">
            <form onSubmit={handleSubmit(onSubmit)}>
              <TextField label="Username" size="small" variant="outlined" sx={{ marginTop: '8px' }} required />
              <TextField label="Email" size="small" variant="outlined" sx={{ marginTop: '8px' }} required />
              <TextField label="Password" size="small" variant="outlined" sx={{ marginTop: '8px' }} type="password" required />
              <TextField label="Password Confirmation" size="small" variant="outlined" sx={{ marginTop: '8px' }} type="password" required />
              <Button type="submit" variant="contained" sx={{ display: 'block', marginTop: '8px' }}>Sign Up</Button>
            </form>
          </FormControl>
        </div>
      );
}