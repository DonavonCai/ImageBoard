import axios from 'axios'
import { Component } from 'react'
import { ImageList, ImageListItem } from '@mui/material'
import InfiniteScroll from 'react-infinite-scroller'
import { Image } from './Image'
import { Buffer } from 'buffer'
import { RingLoader } from 'react-spinners'

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
      responseType: 'arraybuffer'
    })
    .then(res => {
      this.setState({base64: Buffer.from(res.data, "binary").toString("base64")});
      this.setState({images: [...this.state.images, "newImage"]});
    })
    .catch(err => {
      console.error(err);
    });
  }

  render() {
    return (
      <InfiniteScroll
        pageStart={0}
        loadMore={this.getImages}
        hasMore={true}
        loader={<RingLoader loading={true} />}
        useWindow={true}
        threshold={80}
        initialLoad={true}
        style={{ width: "80%", overflow: "auto" }}
        element={ImageList}
        variant="woven"
        cols={4}
        rowHeight={164}
      >
        {this.state.images.map((i, index) => (
          <ImageListItem sx={{ width: 300, background: "gray" }}>
            <Image base64={this.state.base64}/>
          </ImageListItem>
         ))}
       </InfiniteScroll>
    );
  }
}