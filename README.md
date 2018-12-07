# 微服务容器化案例

## 简介
使用docker将各个微服务组件容器化，使用docker-compose完成容器编排，实现微服务容器化部署。

**注意使用前使用gradle的bootJar构建每个应用的jar包，当然这步你也可以编写shell脚本完成，这里主要注重容器化，所以不做添加**


## 项目结构
| 目录 | 名称 | 访问地址 |
| --- | --- | --- |
| eureka | 注册中心 | http://127.0.0.1:8761 |
| gateway | 网关+路由 | http://127.0.0.1:8769/order/buy <br> http://127.0.0.1:8769/pay/pay |
| order | 订单服务 | http://127.0.0.1:8763/buy |
| pay | 支付服务 | http://127.0.0.1:8762/pay |

## 微服务容器化的步骤
1. 编写Dockerfile构建单个镜像  
2. 编写docker-compose.yml进行容器编排  
3. 修改各个应用中的application.yml的一些访问地址  

## 容器化相关命令
单独部署：  
进入某个应用的目录（目录下有Dockerfile）  
使用命令：docker build -t mic-eureka .  
  
整体编排部署：  
进入根目录（目录下有docker-compose.yml）  
后台启动：docker-compose up -d
停止：docker-compose down  

## 容器化说明
在所有module每个目录下面都存在一个Dockerfile，类似如下：

```Dockerfile
# 基础镜像
FROM java:openjdk-8u111

# 作者
MAINTAINER LinkinStar linkinstar@foxmail.com

# 将build/libs下的jar包拷贝到容器中的根目录，并命名为app.jar
COPY build/libs/*.jar /app.jar

# 声明开放端口
EXPOSE 8761

# 启动应用
ENTRYPOINT ["java", "-jar", "app.jar"]
````

## 容器编排
在根目录下有docker-compose.yml文件，用于进行容器编排：

````
# 声明版本
version: '3'

# 声明各个服务services
services:
  # 服务名称
  mic-eureka:
    # 构建当前服务的Dockerfile位置
    build:
      context: eureka
    # 镜像名称
    image: mic-eureka:latest
    # 映射的开放端口
    ports:
      - 8761:8761
  
  mic-gateway:
    build:
      context: gateway
    image: mic-gateway:latest
    # 需要关联的应用，这些关联的应用可以通过下面的名称来访问，类似域名的概念
    links:
      - mic-eureka
      - mic-pay
      - mic-order
    ports:
      - 8769:8769
      
  mic-pay:
    build:
      context: pay
    image: mic-pay:latest
  
  mic-order:
    build:
      context: order
    image: mic-order:latest
````






