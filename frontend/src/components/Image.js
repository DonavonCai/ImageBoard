import axios from 'axios'

export function Image(props) {
  return (
    <img src={"data:image/jpeg;charset=utf-8;base64," + props.base64} alt="placeholder"loading="lazy" />
  );
}