import axios from 'axios'
import { useState } from 'react'
import { Visibility, VisibilityOff } from '@mui/icons-material'
import { ErrorMessage } from '@hookform/error-message'
import { TextField }  from '@mui/material'

export function RegisterTextField({name, displayName, register, getValues, errors, setError}) {
  // Constants:
  const textFieldProps = {
    size: "small",
    variant: "outlined",
    sx: { marginTop: '8px' },
  };

  // Helper functions:
  const checkAvailability = (fieldName) => {
    const fieldData = getValues(fieldName);

    if (!fieldData) return;

    axios.get('register', {params: {field: fieldName, data: fieldData}})
    .then((res) => {
      console.log(res);
      let fieldExists = res.data;
      if (fieldExists)
        setError(fieldName, {type: "custom", message: "This " + fieldName + " is already taken."});
    })
    .catch((error) => {
      console.error(error.response.data);
    })
  }

  return (
    <div>
    <TextField
        label={displayName}
        {...register(name, {
          onBlur: (e) => {checkAvailability(name)}
        })}
        {...textFieldProps}>
    </TextField>
    <ErrorMessage
        errors={errors}
        name={name}
        as={<p className='error-message' />}>
    </ErrorMessage>
    </div>
  );
}

export function RegisterPasswordField({name, displayName, register, errors}) {
  const textFieldProps = {
    size: "small",
    variant: "outlined",
    sx: { marginTop: '8px' },
  };

  const [showPassword, setShowPassword] = useState(false);
  const togglePassword = () => {
    setShowPassword(!showPassword);
  }

  return (
    <div>
    <TextField
    label={displayName}
    type={showPassword? 'text' : 'password'}
    {...register(name)}
    {...textFieldProps}
    InputProps={{endAdornment:
                  <div class='eye' onClick={togglePassword}>
                    {showPassword? <Visibility /> : <VisibilityOff />}
                  </div>
    }}>
    </TextField>
    <ErrorMessage
        errors={errors}
        name={name}
        as={<p className='error-message' />}>
    </ErrorMessage>
    </div>
  );
}