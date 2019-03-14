package io.dracula.test.dubbo.router;

/**
 * @author dk
 */
public interface TestService {

    /**
     *
     * @param name
     * @return
     */
    String sayHello(String name);

    /**
     *
     * @return
     */
    String sayHello();

    /**
     *
     * @param name
     * @return
     */
    String sayHello2(String name);

}
