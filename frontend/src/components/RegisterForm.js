import axios from 'axios'
import { useState } from 'react'
import { Button, TextField, Typography, FormControl }  from '@mui/material'
import { useForm } from 'react-hook-form'
import { ErrorMessage } from '@hookform/error-message'
import { yupResolver } from '@hookform/resolvers/yup'
import * as Yup from 'yup'
// import { faEye } from '@fortawesome/free-solid-svg-icons';
// import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import './RegisterForm.css'

import { Visibility, VisibilityOff } from '@mui/icons-material'

export function RegisterForm() {
  // Constants:
  const textFieldProps = {
    size: "small",
    variant: "outlined",
    sx: { marginTop: '8px' },
  };

  // Form validation schema:
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
  const { register, handleSubmit, setError, formState: {errors} } = useForm(formOptions);
  const [showPassword, setShowPassword] = useState(false);
  const [showPasswordConfirm, setShowPasswordConfirm] = useState(false);

  // Helper functions:
  const togglePassword = () => {
    setShowPassword(!showPassword);
  }

  const togglePasswordConfirm = () => {
    setShowPasswordConfirm(!showPasswordConfirm);
  }

  const onSubmit = (data) => {
    const {username, email, password} = data;
    axios.post('register', {
      username: username,
      email: email,
      password: password,
    })
    .then((res) => {
      console.log(res.status);
    })
    .catch((error) => {
      if (error.response.status === 400) { // bad request
        const field = error.response.data.field;
        if (field === 'username') {
          setError('username', {type: "custom", message: "This username is already taken."});
        }
        if (field === 'email') {
          setError('email', {type: "custom", message: "This email is already taken."});
        }
      }
    });
  };

  return (
    <div id='register-form-container'>
      <Typography
          id='modal-title'
          variant='h5'
          component='h2'
          align='center'>
        Sign Up
      </Typography>

      <Typography
          id='modal-instructions'
          color='#616161'
          align='center'>
        Please create a username, email, and password.
      </Typography>

      <FormControl align='center'>
        <form onSubmit={handleSubmit(onSubmit)}>
          <TextField
              label='Username'
              {...register('username')}
              {...textFieldProps}>
          </TextField>
          <ErrorMessage
              errors={errors}
              name='username'
              as={<p className='error-message' />}>
          </ErrorMessage>

          <TextField
              label='Email'
              type='email'
              {...register('email')}
              {...textFieldProps}>
          </TextField>
          <ErrorMessage
              errors={errors}
              name='email'
              as={<p className='error-message' />}>
          </ErrorMessage>

          <TextField
              label='Password'
              type={showPassword? 'text' : 'password'}
              {...register('password')}
              {...textFieldProps}
              InputProps={{endAdornment:
                            <div class='eye' onClick={togglePassword}>
                              {showPassword? <Visibility /> : <VisibilityOff />}
                            </div>
              }}>
          </TextField>
          <ErrorMessage
              errors={errors}
              name='password'
              as={<p className='error-message' />}>
          </ErrorMessage>

          <TextField
              label='Password Confirmation'
              type={showPasswordConfirm? 'text' : 'password'}
              {...register('confirmPassword')}
              {...textFieldProps}
              InputProps={{endAdornment:
                            <div class='eye' onClick={togglePasswordConfirm}>
                              {showPasswordConfirm? <Visibility /> : <VisibilityOff />}
                            </div>
              }}>
          </TextField>
          <ErrorMessage
              errors={errors}
              name='confirmPassword'
              as={<p className='error-message' />}>
          </ErrorMessage>

          <Button
              type='submit'
              variant='contained'
              sx={{ display: 'block', marginTop: '8px' }}>
            Sign Up
          </Button>
        </form>
      </FormControl>
    </div>
  );
}