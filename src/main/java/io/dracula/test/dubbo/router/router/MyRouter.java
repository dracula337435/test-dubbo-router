package io.dracula.test.dubbo.router.router;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.Router;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author dk
 */
public class MyRouter implements Router {

    private URL url;

    private static Random random = new Random();

    private int count = 0;

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
        System.out.println(this);
        count ++;
        // 最初几次条件很宽地放行List<Invoker>，之后就可以随便筛选，详见README.md
        String methods = url.getParameter("methods");
        int expect = methods.split(",").length;
        if(count <= expect+1){
            System.out.println("在最初的几次中");
            return invokers;
        }else{
            System.out.println("已经过了最初的几次");
        }
        //
        System.out.println(count);
        if((count >= 4+1 && count <= 4+4)
                || count >= 4+4*5+1){
            List<Invoker<T>> list = new LinkedList<>();
            return list;
        }else{
            return invokers;
        }
//        double some = random.nextDouble();
//        if(some < 0.5){
//            throw new RpcException("随便打个错");
//        }
//        return invokers;
    }

    @Override
    public int compareTo(Router o) {
        return 0;
    }
}
