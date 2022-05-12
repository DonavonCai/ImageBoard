import axios from 'axios'
import { useState } from 'react'
import { Button, Typography, FormControl }  from '@mui/material'
import { useForm } from 'react-hook-form'
import { yupResolver } from '@hookform/resolvers/yup'
import { StatusCodes } from 'http-status-codes'
import { RegisterTextField, RegisterPasswordField } from './RegisterInputFields'
import * as Yup from 'yup'
import './RegisterForm.css'

export function RegisterForm() {
  // Form validation schema:
  const validationSchema = Yup.object().shape({
    username: Yup.string()
      .required('Username is required.'),
    email: Yup.string()
      .required('Email is required.')
      .email('Must be a valid email.'),
    password: Yup.string()
      .min(8, 'Password must be at least 8 characters.')
      .required('Password is required.'),
    confirmPassword: Yup.string()
      .oneOf([Yup.ref('password'), null], 'Passwords must match.')
      .required('Password confirmation is required.')
  });

  // Hooks:
  const { register, getValues, handleSubmit, setError, formState: {errors} } = useForm({
    mode: "onBlur",
    resolver: yupResolver(validationSchema)
  });
  const [enableButton, setEnableButton] = useState(true);

  // Helper functions:
  const onSubmit = (data) => {
    const {username, email, password} = data;
    axios.post('register', {
      username: username,
      email: email,
      password: password,
    })
    .then((res) => {
      if (res.status === StatusCodes.OK) {
        setEnableButton(false);
        // TODO: log user in?
      }
    })
    .catch((error) => {
      if (error.response.status === StatusCodes.CONFLICT) {
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
          <RegisterTextField
              name='username'
              displayName='Username'
              register={register}
              getValues={getValues}
              errors={errors}
              setError={setError}>
          </RegisterTextField>

          <RegisterTextField
              name='email'
              displayName='Email'
              register={register}
              getValues={getValues}
              errors={errors}
              setError={setError}>
          </RegisterTextField>

          <RegisterPasswordField
              name='password'
              displayName='Password'
              register={register}
              errors={errors}>
          </RegisterPasswordField>

          <RegisterPasswordField
              name='confirmPassword'
              displayName='Password Confirmation'
              register={register}
              errors={errors}>
          </RegisterPasswordField>

          <Button
              type='submit'
              disabled={!enableButton}
              variant='contained'
              sx={{ display: 'block', marginTop: '8px' }}>
            Sign Up
          </Button>
        </form>
      </FormControl>
    </div>
  );
}