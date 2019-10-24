###SpringCloud注解简单说明
	@EnableEurekaClient：声明为一个eureka客户端，注册中心使用eureka时，推荐使用，基于spring-cloud-netflix
	@EnableDiscoveryClient：基于spring-cloud-commons，声明为一个客户端，注册中心不是eureka时推荐使用
	@EnableHystrix：开启Hystrix，包括注解@EnableCircuitBreaker
	@EnableCircuitBreaker：开启熔断器，服务调用方开启后，ribbon客户端调用失败之后会走熔断
	@EnableHystrixDashboard：开启Hystrix监控页面
	@SpringCloudApplication：包含@EnableDiscoveryClient、@EnableCircuitBreaker、@SpringBootApplication三个注解
	
###ribbon配置
	ribbon的默认负载均衡方式是轮询的方式，此外还提供其他策略
	1. BestAvailabl：选择一个最小的并发请求的 Server，逐个考察 Server，如果 Server 被标记为错误，则跳过，然后再选择 ActiveRequestCount 中最小的 Server。
	2. AvailabilityFilteringRule：过滤掉那些一直连接失败的且被标记为 circuit tripped 的后端 Server，并过滤掉那些高并发的后端 Server 或者使用一个 AvailabilityPredicate 来包含过滤 Server 的逻辑。其实就是检查 Status 里记录的各个 Server 的运行状态。
	3. ZoneAvoidanceRule：使用 ZoneAvoidancePredicate 和 AvailabilityPredicate 来判断是否选择某个 Server，前一个判断判定一个 Zone 的运行性能是否可用，剔除不可用的 Zone（的所有 Server），AvailabilityPredicate 用于过滤掉连接数过多的 Server。
	4. RandomRule：随机选择一个 Server。
	5. RoundRobinRule：轮询选择，轮询 index，选择 index 对应位置的 Server。
	6. RetryRule：对选定的负载均衡策略机上重试机制，也就是说当选定了某个策略进行请求负载时在一个配置时间段内若选择 Server 不成功，则一直尝试使用 subRule 的方式选择一个可用的 Server。
	7. ResponseTimeWeightedRule：作用同 WeightedResponseTimeRule，ResponseTime-Weighted Rule 后来改名为 WeightedResponseTimeRule。
	8. WeightedResponseTimeRule：根据响应时间分配一个 Weight（权重），响应时间越长，Weight 越小，被选中的可能性越低。

| 配置名称                                              | 默认值  	     | 说明                                                                                                                                                                                         
|------------------------------------------------------|-----------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ribbon.eureka.enabled								   | true			 | 关闭和Eureka集成，当我们禁用了 Eureka 之后，就不能使用服务名称去调用接口了，必须指定服务地址。
| xxx.ribbon.listOfServers							   |  				 | 禁用 Eureka 后手动配置服务地址，针对具体服务，前缀是服务名，多个使用逗号间隔
| ribbon.ConnectTimeout								   | 1000			 | http建立socket超时时间,毫秒
| ribbon.ReadTimeout								   | 1000			 | http读取响应socket超时时间
| ribbon.maxAutoRetries								   | 				 | 同一台实例最大重试次数,不包括首次调用
| ribbon.maxAutoRetriesNextServer					   | 				 | 重试负载均衡其他的实例最大重试次数,不包括首次server
| ribbon.okToRetryOnAllOperations					   | false			 | 是否所有操作都重试，POST请求注意多次提交错误，默认false，设定为false的话，只有get请求会重试
| ribbon.retryableStatusCodes						   | 				 | 对Http响应码进行重试，多个使用逗号间隔
	重试的次数=MaxAutoRetries+MaxAutoRetriesNextServer+(MaxAutoRetries *MaxAutoRetriesNextServer) ，重试次数+1为实际调用次数
	Hystrix的超时时间=(Ribbon的重试次数+首次调用)*(ribbon.ReadTimeout+ribbon.ConnectTimeout)
###Hystrix配置属性详解
    参考链接：https://www.cnblogs.com/duan2/p/9302431.html
| 配置名称                                              | 默认值  	     | 说明                                                                                                                                                                                         
|------------------------------------------------------|-----------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| hystrix.command.default.execution.isolation.strategy | THREAD          | 表示HystrixCommand.run()的执行时的隔离策略：THREAD: 在单独的线程上执行，并发请求受线程池中的线程数限制，SEMAPHORE: 在调用线程上执行，并发请求量受信号量计数限制。只有在高并发(单个实例每秒达到几百个调用)的调用时，才需要修改HystrixCommands 的隔离策略为semaphore 。semaphore 隔离策略通常只用于非网络调用                                     
| execution.isolation.thread.timeoutInMilliseconds     | 1000            | 设置调用者执行的超时时间（单位毫秒）                                                                                                                          
| execution.isolation.thread.interruptOnTimeout        | true            | 表示设置是否在执行超时时，中断HystrixCommand.run() 的执行                                                                                                                        
| execution.isolation.thread.interruptOnCancel         | false           | 表示设置是否在取消任务执行时，中断HystrixCommand.run() 的执行                                                                                                       
| execution.isolation.semaphore.maxConcurrentRequests  | 10              | 当HystrixCommand.run()使用SEMAPHORE的隔离策略时，设置最大的并发量                                                                                                                                                                                                  
| fallback.isolation.semaphore.maxConcurrentRequests   | 10              | 此属性设置从调用线程允许HystrixCommand.getFallback（）方法允许的最大并发请求数，如果达到最大的并发量，则接下来的请求会被拒绝并且抛出异常                                                                                                                                                                                                
| fallback.enabled                                     | true            | 是否开启fallback功能                                                                                                                                                  
| circuitBreaker.enabled                               | true            | 是否开启断路器功能                                                                                                                                                                                  
| circuitBreaker.requestVolumeThreshold                | 20              | 该属性设置滚动窗口中将使断路器跳闸的最小请求数量，如果此属性值为20，则在窗口时间内（如10s内），如果只收到19个请求且都失败了，则断路器也不会开启。                                                                                                                                                                                           
| circuitBreaker.errorThresholdPercentage              | 50              | 设置失败百分比的阈值。如果失败比率超过这个值，则断路器跳闸并且进入fallback逻辑                                                                                                                                                      
| circuitBreaker.forceOpen                             | false           | 如果设置true，则强制使断路器跳闸，则会拒绝所有的请求.此值会覆盖circuitBreaker.forceClosed的值                                                                                                                                                                                          
| circuitBreaker.forceClosed                           | false           | 如果设置true，则强制使断路器进行关闭状态，此时会允许执行所有请求，无论是否失败的次数达到circuitBreaker.errorThresholdPercentage值                                                                                                                                                       
| metrics.rollingStats.timeInMilliseconds              | 10000           | 设置统计滚动窗口的时间长度                                                                                                                                                                                            
| metrics.rollingStats.numBuckets                      | 10              | 设置统计滚动窗口的桶数量，“metrics.rollingStats.timeInMilliseconds % metrics.rollingStats.numBuckets == 0”必须成立，在高并发的环境里，每个桶的时间长度建议大于100ms                                                                                                                 
| metrics.rollingPercentile.enabled                    | true            | 设置执行延迟是否被跟踪，并且被计算在失败百分比中。如果设置为false,则所有的统计数据返回-1
| metrics.rollingPercentile.timeInMilliseconds         | 60000           | 此属性设置统计滚动百分比窗口的持续时间                                                                                                                                                                                                     
| metrics.rollingPercentile.numBuckets                 | 6               | 设置统计滚动百分比窗口的桶数量，“metrics.rollingPercentile.timeInMilliseconds % metrics.rollingPercentile.numBuckets == 0”必须成立，在高并发的环境里，每个桶的时间长度建议大于1000ms                                                                                                                                                                               |
| metrics.rollingPercentile.bucketSize                 | 100             | 此属性设置每个桶保存的执行时间的最大值。如果桶数量是100，统计窗口为10s，如果这10s里有500次执行，只有最后100次执行会被统计到bucket里去                                                                                                                                                                                                    
| metrics.healthSnapshot.intervalInMilliseconds        | 500             | 采样时间间隔                                                                                                                                                                                
| requestCache.enabled                                 | true            | 是否开启请求缓存功能                                                                                                                                                                               
| requestLog.enabled                                   | true            | 表示是否开启日志，打印执行HystrixCommand的情况和事件                                                                                                                                                                                     
| maxRequestsInBatch                                   | Integer.MAX_VALUE| 设置同时批量执行的请求的最大数量                                                                                                                                                                                  
| timerDelayInMilliseconds                             | 10              | 批量执行创建多久之后，再触发真正的请求                                                                                                                                                                                
| requestCache.enabled                                 | true            | 是否对HystrixCollapser.execute() 和 HystrixCollapser.queue()开启请求缓存                                                                                                                                                                              
| coreSize                                             | 10              | 设置线程池的core的大小                                                                                                                      |
| maximumSize                                          | 10              | 设置最大的线程池的大小，只有设置allowMaximumSizeToDivergeFromCoreSize时，此值才起作用                                                                                                                                                                                      
| maxQueueSize                                         | -1              | 设置最大的BlockingQueue队列的值。如果设置-1，则使用SynchronousQueue队列，如果设置正数，则使用LinkedBlockingQueue队列                                                                                                                                                                                                 | queueSizeRejectionThreshold                          | 5               | 因为maxQueueSize值不能被动态修改，所有通过设置此值可以实现动态修改等待队列长度。即等待的队列的数量大于queueSizeRejectionThreshold时（但是没有达到maxQueueSize值），则开始拒绝后续的请求进入队列。
| keepAliveTimeMinutes 								   | 1				 | 设置线程多久没有服务后，需要释放（maximumSize-coreSize ）个线程
| allowMaximumSizeToDivergeFromCoreSize 			   | false			 | 设置allowMaximumSizeToDivergeFromCoreSize值为true时，maximumSize才有作用
| metrics.rollingStats.timeInMilliseconds			   | 10000           | 设置滚动窗口的时间
| metrics.rollingStats.numBuckets					   | 10 			 | 设置滚动静态窗口分成的桶的数量，“metrics.rollingStats.timeInMilliseconds % metrics.rollingStats.numBuckets == 0”必须成立，建议每个桶的时间长度大于100ms

###Actuator健康监控
	健康监控端点，默认只暴漏/actuator、/actuator/info、/actuator/health三个端点，可通过配置全部暴漏
| 端点名称		| 属性说明
| auditevents	| 公开当前应用程序的审计事件信息。
| beans			| 显示应用程序中所有Spring bean的完整列表。
| configprops	| 显示应用中配置的属性信息报告。
| env			| 显示应用中所有可用的环境属性报告，包括环境变量、JVM属性、应用的配置属性、命令行的参数。
| health		| 显示应用健康信息。
| httptrace		| 显示HTTP跟踪信息（默认情况下为最后100个HTTP请求 - 响应交换）。
| info			| 显示应用的自定义信息，默认是空。
| metrics		| 显示当前应用程序的“指标”信息，如内存信息、线程信息。
| mappings		| 显示所有url映射。
| scheduledtasks| 显示应用程序中的计划任务。
| shutdown		| 让应用程序正常关机。
| threaddump	| 程序运行中的线程信息。

###超时时间相关
    参考链接：https://blog.csdn.net/zzzgd_666/article/details/83314833
    
    1. 如果hystrix.command.default.execution.timeout.enabled为true,则会有两个执行方法超时的配置,一个就是ribbon的ReadTimeout,一个就是熔断器hystrix的timeoutInMilliseconds, 此时谁的值小谁生效
    2. 如果hystrix.command.default.execution.timeout.enabled为false,则熔断器不进行超时熔断,而是根据ribbon的ReadTimeout抛出的异常而熔断,也就是取决于ribbon
    3. ribbon的ConnectTimeout,配置的是请求服务的超时时间,除非服务找不到,或者网络原因,这个时间才会生效
    4. ribbon还有MaxAutoRetries对当前实例的重试次数,MaxAutoRetriesNextServer对切换实例的重试次数, 如果ribbon的ReadTimeout超时,或者ConnectTimeout连接超时,会进行重试操作
    5. 由于ribbon的重试机制,通常熔断的超时时间需要配置的比ReadTimeout长,ReadTimeout比ConnectTimeout长,否则还未重试,就熔断了
    6. 为了确保重试机制的正常运作,理论上（以实际情况为准）建议hystrix的超时时间为:(1 + MaxAutoRetries + MaxAutoRetriesNextServer) * ReadTimeout
