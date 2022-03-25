import React from 'react'
import { ImageList } from '@mui/material'
import ImageCard from './ImageCard'

export default function ImageGrid(props) {
  return (
    <ImageList
      sx={{
        width: '80%',
        height: '100%'
      }}
      variant="woven" cols={4} rowHeight={164}
    >
      <ImageCard />
      <ImageCard />
      <ImageCard />
      <ImageCard />
      <ImageCard />
    </ImageList>
  );
}