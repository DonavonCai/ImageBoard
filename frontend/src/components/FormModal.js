import React from 'react'
import Modal from '@mui/material/Modal';
import Fade from '@mui/material/Fade';
import Button from '@mui/material/Button'
import Backdrop from '@mui/material/Backdrop';
import Box from '@mui/material/Box';
import LoginForm from './LoginForm'
import RegisterForm from './RegisterForm';

export default function FormModal(props) {
    const [open, setOpen] = React.useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const style = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 400,
        bgcolor: 'background.paper',
        border: '2px solid #000',
        boxShadow: 24,
        p: 4,
        display: 'flex',
        flexDirection: 'column',
    };

    const formComponents = {
      'LoginForm': LoginForm,
      'RegisterForm': RegisterForm
    }
    const buttonTexts = {
      'LoginForm': "Login",
      'RegisterForm': "Sign up"
    }

    const EmbeddedForm = formComponents[props.form]

    return (
      <div class="form-modal">
        <Button variant="contained" onClick={handleOpen} sx={{ marginRight: '1vh' }}>{buttonTexts[props.form]}</Button>
        <Modal
          aria-labelledby="transition-modal-title"
          aria-describedby="transition-modal-description"
          open={open}
          onClose={handleClose}
          closeAfterTransition
          BackdropComponent={Backdrop}
          BackdropProps={{
            timeout: 500,
          }}
        >
          <Fade in={open}>
            <Box sx={style}>
              <EmbeddedForm />
            </Box>
          </Fade>
        </Modal>
      </div>
    );
  }