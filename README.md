# 注册中心
## registerCenter 
* 需要第一个启动
* 可部署多个实例保证高可用，所有服务的eureka配置需要跟着修改

# 配置中心
## configCenter
* 首先需要初始化数据库，采用数据库作为配置中心，执行init.sql
* 修改application.yml的数据库配置
* 下面几个服务都集成了配置中心，可用于配置，具体配置信息可看init.sql内容

# 网关
## gateway
* 除了前两个，其他微服务需要在这里面配置路由，或者可修改配置为统一路由
* GlobalRequestFilter中可配置 WHITE_LIST 不需要鉴权的白名单，比如登陆获取token的接口
* GlobalRequestFilter中根据token获取用户信息逻辑需要配置，关系可维护在redis中
* 高可用及负载均衡可通过keeplived+haproxy来保证

# 用户服务
## userService
* 提供登陆接口，返回token,将用户信息和token进行维护，这里和网关相关的逻辑
* 提供鉴权获取逻辑

# 业务服务
## businessService
* 主要进行业务处理，提供了持久层mybatis接入
* 服务之间调用可参考业务服务调用用户服务的逻辑，可每一个服务都提供对应的API模块，对其他服务提供能力，特殊服务可不提供
* 集成了mybatis plus,代码生成可用 CodeGenerator 进行生成代码
# commons
* 对feign调用增加请求头保证用户信息不丢失，并且增加feign调用请求头，可忽略鉴权
* 增加requestId，在日志中打印，可以快速定位问题 todo 这里没有跟踪整个链路的requestId
* 提供鉴权注解，支持简单逻辑自定义鉴权
