package indi.pc.sc;

//import indi.pc.sc.web.SCJettyServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

@Component
@Order(value = 1) // 启动Fetcher 线程
class FetcherRuner implements CommandLineRunner {
    @Override
    public void run(String... var1) throws Exception{
        // do something
    }
}

@Component
@Order(value = 1) // 启动Processor 线程
class ProcessorRuner implements CommandLineRunner {
    @Override
    public void run(String... var1) throws Exception{
        // do something
    }
}

@Component
@Order(value = 1) // 启动ResultWorker 线程
class ResultWorkerRuner implements CommandLineRunner {
    @Override
    public void run(String... var1) throws Exception{
        // do something
    }
}

@Component
@Order(value = 1) // 启动Web监控
class WebRuner implements CommandLineRunner {
    @Override
    public void run(String... var1) throws Exception{
        // do something
//        SCJettyServer scJettyServer = new SCJettyServer();
//        scJettyServer.run();
    }
}