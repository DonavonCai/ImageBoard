import axios from 'axios'

export function Image(props) {
  console.log("props");
  console.log(props);
  return (
    <img src={props.image.url} alt="placeholder"loading="lazy" />
  );
}