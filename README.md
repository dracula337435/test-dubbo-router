# 试验dubbo

router

group和version，不能一次来多个。
例如consumer端设置```group="g1,g2"```不是两个```group```，provider端设置```group=g1```，调不到的
version一样
consumer启动日志
```
INFO 22092 --- [           main] c.a.d.r.zookeeper.ZookeeperRegistry      :  [DUBBO] Register: dubbo://219.142.191.206:20880/io.dracula.test.dubbo.router.TestService?anyhost=true&application=test-router-provider&dubbo=2.6.2&generic=false&group=g1,g2&interface=io.dracula.test.dubbo.router.TestService&methods=sayHello&pid=22092&revision=v1,v2&side=provider&timestamp=1552361766254&version=v1,v2, dubbo version: 2.6.2, current host: 219.142.191.206
```
zkcli查看
```
[zk: localhost:2181(CONNECTED) 3] ls /dubbo/io.dracula.test.dubbo.router.TestService/providers
[dubbo%3A%2F%2F219.142.191.206%3A20880%2Fio.dracula.test.dubbo.router.TestService%3Fanyhost%3Dtrue%26application%3Dtest-router-provider%26dubbo%3D2.6.2%26generic%3Dfalse%26group%3Dg1%2Cg2%26interface%3Dio.dracula.test.dubbo.router.TestService%26methods%3DsayHello%26pid%3D22092%26revision%3Dv1%2Cv2%26side%3Dprovider%26timestamp%3D1552361766254%26version%3Dv1%2Cv2]
```