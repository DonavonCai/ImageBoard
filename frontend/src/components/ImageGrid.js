import React, { Component } from 'react'
import { ImageList } from '@mui/material'
import { ImageListItem } from '@mui/material';
import InfiniteScroll from 'react-infinite-scroller'

export class ImageGrid extends Component {
  constructor(props) {
    super(props);
    this.state = {
      images: []
    };
  }

  componentDidMount() {
  }

  getImages = () => {
    console.log("get images")
    this.setState({ images: [] });
    // Todo: perform api call

    // generate dummies for testing. remove later.
    var newImages = []
    for (var i = this.state.images.length; i < this.state.images.length + 4; i++) {
      newImages.push("image" + String(i));
    }
    this.setState({ images: [...this.state.images, ...newImages] });
  }

  render() {
    return (
        <InfiniteScroll
          pageStart={0}
          loadMore={this.getImages}
          hasMore={this.state.images.length < 1000}
          loader={<div className="loader" key={0}>Loading...</div>}
          useWindow={true}
          threshold={150}
          style={{ width: "80%", overflow: "auto" }}
          element={ImageList}
          variant="woven"
          cols={4}
          rowHeight={164}
        >
          {this.state.images.map((i, index) => (
              <ImageListItem sx={{ minHeight: 300, background: "gray" }}>
                <img src="" alt="placeholder"loading="lazy" />
              </ImageListItem>
          ))}
        </InfiniteScroll>
    );
  }
}