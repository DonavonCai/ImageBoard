import axios from 'axios'
import Button from '@mui/material/Button'
import TextField from '@mui/material/TextField'
import Typography from '@mui/material/Typography';
import { FormControl } from '@mui/material'
import { useForm } from 'react-hook-form'
import { ErrorMessage } from '@hookform/error-message'
import { yupResolver } from '@hookform/resolvers/yup'
import * as Yup from 'yup'
import './RegisterForm.css'

export function RegisterForm() {
  const validationSchema = Yup.object().shape({
    username: Yup.string()
      .required('Username is required.'),
    email: Yup.string()
      .required('Email is required.')
      .email('Email is invalid.'),
    password: Yup.string()
      .min(8, 'Password must be at least 8 characters.')
      .required('Password is required.'),
    confirmPassword: Yup.string()
      .oneOf([Yup.ref('password'), null], 'Passwords must match.')
      .required('Password confirmation is required.')
  });

  const formOptions = {resolver: yupResolver(validationSchema)};

  // Hooks:
  const { register, handleSubmit, formState: {errors} } = useForm(formOptions);

  // Props:
  const textFieldProps = {
    size: "small",
    variant: "outlined",
    sx: { marginTop: '8px' },
  };

  // Helper functions:
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
          <TextField label="Username" {...register("username")} {...textFieldProps} />
          <ErrorMessage errors={errors} name="username" as={<p className="error-message" />} />

          <TextField label="Email" {...register("email")} type="email" {...textFieldProps} />
          <ErrorMessage errors={errors} name="email" as={<p className="error-message" />} />

          <TextField label="Password" {...register("password")} type="password" {...textFieldProps} />
          <ErrorMessage errors={errors} name="password" as={<p className="error-message" />} />

          <TextField label="Password Confirmation" {...register("confirmPassword")} type="password" {...textFieldProps} />
          <ErrorMessage errors={errors} name="confirmPassword" as={<p className="error-message" />} />

          <Button type="submit" variant="contained" sx={{ display: 'block', marginTop: '8px' }}>Sign Up</Button>
        </form>
      </FormControl>
    </div>
  );
}