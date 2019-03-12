package io.dracula.test.dubbo.router.router;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.Router;

import java.util.List;

/**
 * @author dk
 */
public class MyRouter implements Router {

    private URL url;

    public MyRouter(URL url){
        System.out.println("初始化了一个");
        this.url = url;
    }

    @Override
    public URL getUrl() {
        return this.url;
    }

    @Override
    public <T> List<Invoker<T>> route(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        System.out.println("invokers = [" + invokers + "], url = [" + url + "], invocation = [" + invocation + "]");
        return invokers;
    }

    @Override
    public int compareTo(Router o) {
        return 0;
    }
}
