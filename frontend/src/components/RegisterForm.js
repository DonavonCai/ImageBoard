import axios from 'axios'
import Button from '@mui/material/Button'
import TextField from '@mui/material/TextField'
import Typography from '@mui/material/Typography';
import { FormControl } from '@mui/material'
import { useForm } from 'react-hook-form'
import { ErrorMessage } from '@hookform/error-message'
import './RegisterForm.css'

export function RegisterForm() {
  // State:
  const { register, handleSubmit, formState: {errors} } = useForm();

  // Props:
  const textFieldProps = {
    size: "small",
    variant: "outlined",
    sx: { marginTop: '8px' },
  };

  // const errorMessageStyle = {
  //   color: "bf1650",
  // }

  const onSubmit = (data) => {
    console.log('You clicked sign up');
    // const response = axios.post("register");
    // response.then((res) => {
    //   console.log(res.data);
    // });
  };

  return (
    <div id="register-form-container">
      <Typography id="modal-title" variant="h5" component="h2" align="center">
      Sign Up
      </Typography>
      <Typography id="modal-instructions" color="#616161" align="center">Please create a username, email, and password.</Typography>
      <FormControl align="center">
        <form onSubmit={handleSubmit(onSubmit)}>
          <TextField label="Username" {...register("username", {required: "Username is required.", maxLength: 20})} {...textFieldProps} />
          <ErrorMessage errors={errors} name="username" as={<p className="error-message" />} />
          <TextField label="Email" {...register("email", {required: "Email is required."})} type="email" {...textFieldProps} />
          <ErrorMessage errors={errors} name="email" as={<p className="error-message" />} />
          <TextField label="Password" {...register("password", {required: "Password is required."})} type="password" {...textFieldProps} />
          <ErrorMessage errors={errors} name="password" as={<p className="error-message" />} />
          <TextField label="Password Confirmation" {...register("passwordConfirm", {required: "Password confirmation required."})} type="password" {...textFieldProps} />
          {/* Todo: add password confirmation (yup, hookform/resolver) */}
          <ErrorMessage errors={errors} name="passwordConfirm" as={<p className="error-message" />} />
          <Button type="submit" variant="contained" sx={{ display: 'block', marginTop: '8px' }}>Sign Up</Button>
        </form>
      </FormControl>
    </div>
  );
}