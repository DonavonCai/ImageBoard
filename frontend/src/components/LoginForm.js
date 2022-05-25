import axios from 'axios'
import { TextField, Typography, FormControl, Button } from '@mui/material'
import { useForm } from 'react-hook-form'

export function LoginForm() {
  const { register, handleSubmit, formState: { errors } } = useForm();

  const onSubmit = async (data) => {
    const {username, password} = data;
    try {
      const response = await axios.post("login", {
        username: username,
        password: password
      });

      console.log(response.data);
    } catch(error) {
      console.log(error);
    }
  };

  return (
    <div id="login-form-container">
      <Typography
          id="modal-title"
          variant="h5"
          component="h2"
          align="center">
        Login
      </Typography>

      <Typography
          id="modal-instructions"
          color="#616161"
          align="center">
        Please enter your username and password.
      </Typography>

      <FormControl align="center">
        <form onSubmit={handleSubmit(onSubmit)}>
          <TextField
              label="Username"
              size="small"
              variant="outlined"
              sx={{ marginTop: '8px' }}
              required
              {...register('username')}>
          </TextField>

          <TextField
              label="Password"
              size="small"
              variant="outlined"
              sx={{ marginTop: '8px' }}
              type="password"
              required
              {...register('password')}>
          </TextField>

          <Button
              type="submit"
              variant="contained"
              sx={{ display: 'block', marginTop: '8px' }}>
            Log in
          </Button>
        </form>
      </FormControl>
    </div>
  );
}