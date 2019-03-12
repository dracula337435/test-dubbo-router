package io.dracula.test.dubbo.router.consumer;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;
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
    public void setRouter(){
        RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
        Registry registry = registryFactory.getRegistry(URL.valueOf("zookeeper://127.0.0.1:2181"));
//        registry.register(URL.valueOf("condition://0.0.0.0/io.dracula.test.dubbo.router.TestService?category=routers&dynamic=false&rule=" + URL.encode("host = 10.20.153.10 => host = 10.20.153.11")));
        registry.register(URL.valueOf("my://0.0.0.0/io.dracula.test.dubbo.router.TestService?category=routers&msg=something&dynamic=false"));
    }

    @Test
    public void test(){
        for(int i=0; i<10; i++){
            System.out.println(testService.sayHello("gxk"));
        }
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
            return new RegistryConfig("zookeeper://127.0.0.1:2181");
        }

    }

}
