package io.dracula.test.dubbo.router.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import io.dracula.test.dubbo.router.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dk
 */
@RunWith(SpringRunner.class)
public class ConsumerTest {

    @Reference
    TestService testService;

    @Test
    public void test(){
        System.out.println(testService.sayHello("gxk"));
    }


    /**
     * @author dk
     */
    @Configuration
    @DubboComponentScan
    public static class Config{

        @Bean
        public ApplicationConfig applicationConfig(){
            return new ApplicationConfig("test-router-consumer");
        }

        @Bean
        public RegistryConfig registryConfig(){
            return new RegistryConfig("multicast://224.1.2.3:4567");
        }

    }

}
