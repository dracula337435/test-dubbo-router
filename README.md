# 试验dubbo

router

group和version，不能一次来多个。
例如consumer端设置```group="g1,g2"```不是两个```group```，provider端设置``group=g1```，调不到的  
version一样  
consumer启动日志
```
INFO 22092 --- [           main] c.a.d.r.zookeeper.ZookeeperRegistry      :  [DUBBO] Register: dubbo://219.142.191.206:20880/io.dracula.test.dubbo.router.TestService?anyhost=true&application=test-router-provider&dubbo=2.6.2&generic=false&group=g1,g2&interface=io.dracula.test.dubbo.router.TestService&methods=sayHello&pid=22092&revision=v1,v2&side=provider&timestamp=1552361766254&version=v1,v2, dubbo version: 2.6.2, current host: 219.142.191.206
```
zkCli查看
```
[zk: localhost:2181(CONNECTED) 3] ls /dubbo/io.dracula.test.dubbo.router.TestService/providers
[dubbo%3A%2F%2F219.142.191.206%3A20880%2Fio.dracula.test.dubbo.router.TestService%3Fanyhost%3Dtrue%26application%3Dtest-router-provider%26dubbo%3D2.6.2%26generic%3Dfalse%26group%3Dg1%2Cg2%26interface%3Dio.dracula.test.dubbo.router.TestService%26methods%3DsayHello%26pid%3D22092%26revision%3Dv1%2Cv2%26side%3Dprovider%26timestamp%3D1552361766254%26version%3Dv1%2Cv2]
```

另外，Router，LoadBalance和Filter  
```com.alibaba.dubbo.rpc.cluster.Router```中的方法
```
URL getUrl();
<T> List<Invoker<T>> route(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException;
```
```com.alibaba.dubbo.rpc.cluster.LoadBalance```中的方法
```
<T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException;
```
```com.alibaba.dubbo.rpc.Filter```中的方法
```
Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException;
```
可见，3者的范围逐渐缩小：  
1. ```Router```得到```List<Invoker>```
1. ```LoadBalance```得到```Invoker```
1. ```Filter```得到```Result```

在真正发请求前，会有几次调用```Router```，如果这次失败，则后续没有Reference可用，一直```NullPointer```；如果这次没事，后续可随意成功失败
最初的几次结果会被缓存，如果将List<Invoker>范围缩小，之后将无法将其范围增大  
于是，应该前两次条件很宽地放行List<Invoker>，之后随便筛选  
经过对比，发现前两次的Invocation参数不太一样  
第一波，一次，methodName为null，parameterTypes和arguments均为空列表（非null）  
第二波，一个函数名一次，methodName有实际值，parameterTypes和arguments均为空列表（非null）  
父接口中的函数也会这样，如果有重载函数（同名，不同参数），算一个，在同一次中