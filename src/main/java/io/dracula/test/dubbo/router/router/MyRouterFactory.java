package io.dracula.test.dubbo.router.router;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.cluster.Router;
import com.alibaba.dubbo.rpc.cluster.RouterFactory;

/**
 * @author dk
 */
public class MyRouterFactory implements RouterFactory {

    @Override
    public Router getRouter(URL url) {
        return new MyRouter(url);
    }

}
