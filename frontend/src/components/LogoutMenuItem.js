import React from 'react'
import { MenuItem } from '@mui/material'


export function LogoutMenuItem(props) {
  const handleOpen = () => {
    props.onClick();
    console.log("logout");
  }

  return (
    <MenuItem variant="contained" onClick={handleOpen}>
      Sign Out
    </MenuItem>
  )
}