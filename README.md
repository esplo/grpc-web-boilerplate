# gRPC web boilerplate

Get started with gRPC, Scala, React, and Typescript.


## Usage

```sh
$ docker-compose up
```

wait until the server is ready, then access to `http://localhost:3000`


## Features

- easy to start (hit only 1 command and wait...)
- hot reload (including auto-generation from .proto files)


## Core components

- [gRPC](https://grpc.io/)
- [gRPC Web Proxy](https://github.com/improbable-eng/grpc-web/tree/master/go/grpcwebproxy)
- [protobuf](https://github.com/google/protobuf)
- [ts-protoc-gen](https://github.com/improbable-eng/ts-protoc-gen)

### sub components

- Scala (backend)
    - [ScalaPB](https://github.com/scalapb/ScalaPB)
- React/Typescript (frontend)
    - [wmonk/create-react-app-typescript](https://github.com/wmonk/create-react-app-typescript)

## Project Structure

```
.
|-- server  # ref. https://scalapb.github.io/grpc.html
|   └── app/src/main
|       └── protobuf  # store your .proto
|       └── scala/example
|           └── Hello.scala
|-- frontend  # ref. https://github.com/wmonk/create-react-app-typescript
    └── src
        └── protobuf  # store auto-generated codes
        └── App.tsx
```

