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
        // 最初的几次结果会被缓存，如果将List<Invoker>范围缩小，之后将无法将其范围增大
        // 于是，应该前两次条件很宽地放行List<Invoker>，之后随便筛选
        // 经过对比，发现前两次的Invocation参数不太一样
        // 第一波，一次，methodName为null，parameterTypes和arguments均为空列表（非null）
        // 第二波，一个函数名一次，methodName有实际值，parameterTypes和arguments均为空列表（非null）
        // 父接口中的函数也会这样，如果有重载函数（同名，不同参数），算一个，在同一次中
//        boolean isFirst2Call = invocation.getMethodName() == null;
//        if(isFirst2Call){
//            return invokers;
//        }
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
