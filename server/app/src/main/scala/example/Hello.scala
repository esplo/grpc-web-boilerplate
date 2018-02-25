package example

import java.util.logging.Logger

import com.example.protos.hello.{GreeterGrpc, HelloReply, HelloRequest}
import io.grpc.{Server, ServerBuilder}

import scala.concurrent.{ExecutionContext, Future}

object Hello {
  private val logger = Logger.getLogger(classOf[Hello].getName)
  private val port = 50051

  def main(args: Array[String]): Unit = {
    val server = new Hello(ExecutionContext.global)
    server.start()
    server.blockUntilShutdown()
  }
}

class Hello(executionContext: ExecutionContext) {
  private[this] var server: Server = null

  private def start(): Unit = {
    server = ServerBuilder.forPort(Hello.port).addService(GreeterGrpc.bindService(new GreeterImpl, executionContext)).build.start
    Hello.logger.info("Server started, listening on " + Hello.port)
    sys.addShutdownHook {
      System.err.println("*** shutting down gRPC server since JVM is shutting down")
      this.stop()
      System.err.println("*** server shut down")
    }
  }

  private def stop(): Unit = {
    if (server != null) {
      server.shutdown()
    }
  }

  private def blockUntilShutdown(): Unit = {
    if (server != null) {
      server.awaitTermination()
    }
  }

  private class GreeterImpl extends GreeterGrpc.Greeter {
    override def sayHello(req: HelloRequest) = {
      val reply = HelloReply(message = "Hello " + req.name)
      Future.successful(reply)
    }
  }
}
