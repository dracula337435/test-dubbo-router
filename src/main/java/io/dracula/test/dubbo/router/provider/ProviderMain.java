package io.dracula.test.dubbo.router.provider;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dk
 */
@SpringBootApplication
@DubboComponentScan
public class ProviderMain {

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        SpringApplication.run(ProviderMain.class, args);
    }

}
