import React from 'react'
import Button from '@mui/material/Button'


export function LogoutButton() {
  const handleOpen = () => {

  }

  return (
    <Button variant="contained"
    onClick={handleOpen}
    sx={{ marginRight: '1vh' }}>
    Sign Out
    </Button>
  )
}