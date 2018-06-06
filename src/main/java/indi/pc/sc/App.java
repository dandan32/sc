package indi.pc.sc;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import indi.pc.sc.web.SCJettyServer;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = "indi.pc.sc.web.*")})
public class App {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(App.class);
        springApplication.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        springApplication.run(args);
    }
}

@Component
@Order(value = 1) // 启动Fetcher 线程
class FetcherRuner implements CommandLineRunner {
    @Override
    public void run(String... var1) {
        // do something
        System.out.println("Fetcher start");
    }
}

@Component
@Order(value = 1) // 启动Processor 线程
class ProcessorRuner implements CommandLineRunner {
    @Override
    public void run(String... var1) {
        // do something
        System.out.println("Processor start");
    }
}

@Component
@Order(value = 1) // 启动ResultWorker 线程
class ResultWorkerRuner implements CommandLineRunner {
    @Override
    public void run(String... var1) {
        // do something
        System.out.println("ResultWorker start");
    }
}

@Component
@Order(value = 1) // 启动Web监控
class WebRuner implements CommandLineRunner {
    @Override
    public void run(String... var1) {
        try {
            //do something
            SCJettyServer scJettyServer = new SCJettyServer();
            scJettyServer.run();
            System.out.println("Web start");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}