package io.dracula.test.dubbo.router.provider;

import com.alibaba.dubbo.config.annotation.Service;
import io.dracula.test.dubbo.router.TestService;

/**
 * @author dk
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
