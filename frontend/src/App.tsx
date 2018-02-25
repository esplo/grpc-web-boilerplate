import * as React from "react";
import "./App.css";

import { Code, grpc } from "grpc-web-client";
import { HelloReply, HelloRequest } from "./protobuf/hello_pb";
import { Greeter } from "./protobuf/hello_pb_service";

import * as logo from "./logo.svg";
import UnaryOutput = grpc.UnaryOutput;

const host = "http://localhost:8080";

interface IProps {}
interface IState {
  message: string;
}

class App extends React.Component<IProps, IState> {
  public constructor(props: IProps) {
    super(props);
    this.state = {
      message: "",
    };
  }

  public test() {
    const request = new HelloRequest();
    request.setName("from React");
    grpc.unary(Greeter.SayHello, {
      request,
      host,
      onEnd: (res: UnaryOutput<HelloReply>) => {
        const { status, statusMessage, headers, message, trailers } = res;
        if (status === Code.OK && message) {
          console.log("ok: ", message.toObject());
          this.setState({
            message: message.getMessage(),
          });
        }
        console.log(status, statusMessage, headers, message, trailers);
      },
    });
  }

  public componentDidMount() {
    this.test();
  }

  public render() {
    if (typeof logo !== "string") {
      return <div>invalid image</div>;
    }

    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
        </header>
        <p className="App-intro">
          To get started, edit <code>src/App.tsx</code> and save to reload.
        </p>
        <p>message from server: {this.state.message}</p>
      </div>
    );
  }
}

export default App;
