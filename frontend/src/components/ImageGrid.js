import axios from 'axios'
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

  componentDidMount() {
    this.getImages();
  }

  // todo: store multiple images in this.images[]
  getImages = () => {
    // this.setState({ images: [] });
    // Perform api call
    const url = "https://api.thedogapi.com/v1/images/search";
    const params = "?"
    const key = "api_key=1f729da2-ff96-4f37-9baf-f34da3543fd0"
    axios.get(url + params + key)
    .then(res => {
      // console.log(res);
      return res.data;
    })
    .then(data => {
      console.log("data:")
      console.log(data[0].url);
      this.setState({images: data[0]});
    })
    .catch(err => {
      console.error(err.message);
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
                <Image image={this.state.images}/>
        //       </ImageListItem>
        //   ))}
        // </InfiniteScroll>
    );
  }
}