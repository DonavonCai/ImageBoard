import axios from 'axios'
import { Component } from 'react'
import { Buffer } from 'buffer'

export class Image extends Component {
  constructor(props) {
    super(props)
    this.state = {
      base64: ""
    }
  }

  componentDidMount() {
    this.getImage();
  }

  getImage() {
    // Perform api call
    const path = "/img";
    const params = "?"
    const key = ""
    axios.get(path + params + key, {
      responseType: 'arraybuffer'
    })
    .then(res => {
      this.setState({base64: Buffer.from(res.data, "binary").toString("base64")});
    })
    .catch(err => {
      console.error(err);
    });
  }

  render() {
    return (
      <img src={"data:image/jpeg;charset=utf-8;base64," + this.state.base64} alt="" loading="lazy" />
    );
  }
}