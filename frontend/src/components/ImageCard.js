import React from 'react'
import { ImageListItem } from '@mui/material';

export default function ImageCard(props) {
  return (
    <ImageListItem sx={{ minHeight: 300, background: 'gray' }}>
      <img src="placeholder.png" alt="placeholder"loading="lazy" />
    </ImageListItem>
  );
}