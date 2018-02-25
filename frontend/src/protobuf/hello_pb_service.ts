// package: com.example.protos
// file: hello.proto

import * as hello_pb from "./hello_pb";
export class Greeter {
  public static serviceName = "com.example.protos.Greeter";
}
export namespace Greeter {
  export class SayHello {
    public static readonly methodName = "SayHello";
    public static readonly service = Greeter;
    public static readonly requestStream = false;
    public static readonly responseStream = false;
    public static readonly requestType = hello_pb.HelloRequest;
    public static readonly responseType = hello_pb.HelloReply;
  }
}
