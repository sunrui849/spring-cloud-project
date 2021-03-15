# 注册中心
## registerCenter 
* 需要第一个启动

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


# 用户服务
## userService
* 提供登陆接口，返回token,将用户信息和token进行维护，这里和网关相关的逻辑
* 提供鉴权获取逻辑

# 业务服务
## businessService
* 主要进行业务处理，提供了持久层接入
* 服务之间调用可参考业务服务调用用户服务的逻辑，可每一个服务都提供对应的API模块，对其他服务提供能力，特殊服务可不提供

