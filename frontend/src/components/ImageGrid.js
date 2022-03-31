import axios from 'axios'
import { Component } from 'react'
import { ImageList, ImageListItem } from '@mui/material'
import InfiniteScroll from 'react-infinite-scroller'
import { Image } from './Image'
import { Buffer } from 'buffer';

export class ImageGrid extends Component {
  constructor(props) {
    super(props);
    this.state = {
      images: [],
      base64: ""
    };
  }

  componentDidMount() {
    this.getImages();
  }

  // todo: store multiple images in this.images[]
  getImages = () => {
    // this.setState({ images: [] });
    // Perform api call
    const path = "/img";
    const params = "?"
    const key = ""
    axios.get(path + params + key, {
      proxy: {
        host: 'localhost',
        port: 8080
      },
      responseType: 'arraybuffer'
    })
    .then(res => {
      this.setState({base64: Buffer.from(res.data, "binary").toString("base64")});
    })
    .catch(err => {
      console.error(err);
    });
  }

  // FIXME: this.state.images doesn't get the url
  render() {
    return (
        // <InfiniteScroll
        //   pageStart={0}
        //   loadMore={this.getImages}
        //   hasMore={this.state.images.length < 100}
        //   loader={<div className="loader" key={0}>Loading...</div>}
        //   useWindow={true}
        //   threshold={150}
        //   style={{ width: "80%", overflow: "auto" }}
        //   element={ImageList}
        //   variant="woven"
        //   cols={4}
        //   rowHeight={164}
        // >
        //   {this.state.images.map((i, index) => (
        //       <ImageListItem sx={{ minHeight: 300, background: "gray" }}>
                <Image base64={this.state.base64}/>
        //       </ImageListItem>
        //   ))}
        // </InfiniteScroll>
    );
  }
}