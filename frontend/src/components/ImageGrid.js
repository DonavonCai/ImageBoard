import { Component } from 'react'
import { ImageList, ImageListItem } from '@mui/material'
import InfiniteScroll from 'react-infinite-scroller'
import { Image } from './Image'

export class ImageGrid extends Component {
  constructor(props) {
    super(props);
    this.state = {
      images: []
    };
  }

  getImages = () => {
      this.setState({images: [...this.state.images, "img" + this.state.images.length]});
  }

  render() {
    return (
      <InfiniteScroll
        pageStart={0}
        loadMore={this.getImages}
        hasMore={true}
        loader={<div key={"loader"}/>}
        useWindow={true}
        threshold={100}
        initialLoad={true}
        style={{ width: "80%", overflow: "auto" }}
        element={ImageList}
        variant="woven"
        cols={4}
        rowHeight={164}
      >
        {this.state.images.map((i, index) => (
          <ImageListItem key={i} sx={{ width: 300, height: 300, background: "gray" }}>
            <Image key={this.state.images[i]}/>
          </ImageListItem>
         ))}
       </InfiniteScroll>
    );
  }
}