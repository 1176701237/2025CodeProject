# Redis
redis是基于内存的键值型NoSQL数据库
## 特征
1. 键值型，k-v,value 支持多种不同数据结构，功能丰富
2. 单线程，每个命令具备原子性  （6.0的多线程仅仅对于网络数据处理，核心命令处理仍旧是单线程）
3. 低延迟、速度快（基于内存（核心）、IO多路复用、良好的编码）
4. 支持数据持久化，定期将数据从内存持久化到磁盘 从而确保数据的安全性
5. 支持主从集群、分片集群
6. 支持多语言客户端
  
## redis数据结构介绍
基本类型
1.String 
2.Hash:{name:"name",age:21}
3.List:有序集合 可重复
4.Set：无序集合 不可重复
5.SortedSet：有序可排序集合不能重复
特殊类型
6.GEO：地理坐标  {A：（10，10）}
7.BitMap：位
8.HyperLog：

## 通用命令
MSET k1 k2 k3 
1.KEYS :跟据模板查询key   keys *, keys *?*,keys ?    模糊查询可能会阻塞服务
2.DEL：删除指定得key  DEL name, DEL k1 k2 k3  (返回删除的数量)
3.exists：判断key是否存在, exists name, exists age;
4.EXPIRE：给key设置有效期，有效期到期时会自动删除  
5.TTL：查看key的剩余有效期

## String类型
### String类型命令
value是字符串，（String，int，float），最大不能超过512M
常见命令：
set,get：存取   
MSet,MGet:批量存取
INCR：整型key自增1 INCR age 
INCRBY：key自增指定大小 INCRBY age 2
INCRBYFLOAT:必须指定大小
SetNx：当key不存在再插入  SetNx name lichenhao =  set name lichenhao nx
SetEx：添加key时候设置有效期  SetEx name Lichenhao = set name lichenhao ex 10
 

### key的结构
Redis允许多层级结构，
    lch:java {"redis":"redis","MQ":"MQ"} 
    
## Hash类型
Hash类型 又叫散列，其Value是一个无序字典，
Hash结构可以将对象中的每个字段独立存储，可以针对单个字段做CRUD
常见命令
HSet key filed value: 添加或者修改Hash类型key的field的value
HGet key filed ：获取一个Hash类型的key的file的value
HMSet ：批量添加
HMGet：批量获取
HGetAll：获取一个Hash类型的key中的file和value
HKeys:获取一个Hash类型中的key的所有的filed
HVals：获取一个Hash类型中的key的所有的value
HInCrBy：让一个Hash类型Key字段自增 并指定长度
HSetNx：添加一个Hash类型的field value，且判断是否存在 不存在则添加

## List类型
双向链表，有序且可重复
L/R push key ele 左/右插入一个元素  
L/R Pop key  移除左/右第一个元素
LRange key star end   返回范围内的元素
BLPOP key tim  阻塞获取

## set类型
value为null的HashMap  无序 元素不可重复 查找快 支持交集并集差集
SADD key member set中加一个或多个元素
Srem key member 删除set中指定元素
Scard key ：返回set元素的个数
sismember key member : 判断一个元素是否存在set中
Smembers ： 获取set所有元素

## SortedSet
可排序set，SortSet每一个元素都带有score属性，可以基于score属性对元素排序。底层试先的是一个跳表（skipList）加hash表
可排序，不可重复 查询速度快 （实现排行榜）
ZAdd key score member 添加一个或多个元素到sorted set ，如果存在则更新 
ZRem key member 删除指定元素
ZScore key member 获取指定元素的score
ZRank key member 获取指定元素的排名
ZCard key  获取元素总个数
ZCount key min max 统计在给定score范围内的元素个数
ZIncrBy key increment member 让指定元素自增，步长为increment
ZRange（ByScore） key min max 按照score排序后 获取指定（Score）范围内的元素
Zdiff zinter zunion 差 交 并集
 



## 什么是缓存
缓存更新三种策略
    1、内存淘汰
    2、过期淘汰
    3、主动更新
    
缓存主动更新策略
    更新数据库的同时更新缓存
    用删除缓存还是更新缓存：
        更新缓存：每次更新数据库都更新缓存。无效写操作比较错
        删除缓存。更新数据库时让缓存失效。查询时再更新缓存
    如何保证缓存与数据库的操作同时失败成功
        单体系统：将缓存与数据库放在一个事务
        分布式：利用tcc分布式事务方案
     先操作数据库再操作缓存
     
      
## 缓存穿透
缓存穿透是指客户端请求的数据在缓存和数据库中都不存在，这样缓存永远不会生效，请求会到数据库
    1、缓存空对象
        1、额外的内存消耗
        2、短期不一致
    2、布隆过滤
        优点：内存占用少 没有多余key
        缺点：复杂、存在误判
    3、增强id复杂度
    4、做好数据基础格式校验
## 缓存雪崩
缓存雪崩是指在同一时间段大量的key同时时效或者redis宕机，导致大量请求到数据库
1、给不同的key设置有效期的随机值
2、利用redis集群

## 缓存击穿
缓存击穿也叫热点key问题，高并发访问斌且缓存重建业务比较复杂的key突然失效 ，访问就会到数据库
1、互斥锁 没有额外内存消耗，保证一致性，实现简单，。线程需要等待，性能受影响，可能死锁
2、逻辑过期 线程无序等待，性能较好，  不保证一致性，有额外内存消耗，实现复杂

 this::方法
 
 函数式编程
 
## 全局id
    唯一性
    高可用
    高性能
    递增性
    安全性
    可以跟据redis的自增icrment实现
    
 
## 锁
 悲观锁，Synchronized  lock
 
 乐观锁 判断数据是否被更改
    版本号、cas法
  
 
## 分布式锁
    满足分布式或者集群下多进程可见且互斥的锁
    高可用、高性能、安全性
获取锁：
    互斥：setnx lock thread 添加过期时间 expire lock 10
          set key value ex nx time 
    非阻塞：尝试一次、成功返回true,失败返回false
    
释放锁：
    手动释放：del key
    超时释放

### lua脚本
eval "return redis.call('set','name','jack')" 0;

eval "return redis.call('set',keys[1],argv[1])" 1 name Rose;


     if (redis.call('get' ,KEYS[1]) == ARGV[1] ) then
       return redis.call('del',KEYS[1])
     end
     return 0;


### 基于setnx实现分布式锁
    1、不可重入 同一个线程无法多次获取同一把锁
    2、不可重试 获取锁值会获取一次
    3、超时释放 业务耗时久则释放锁
    4、主从一致性 

## Redisson
   8.1. 可重入锁（Reentrant Lock）
   8.2. 公平锁（Fair Lock）
   8.3. 联锁（MultiLock）
   8.4. 红锁（RedLock）
   8.5. 读写锁（ReadWriteLock）
   8.6. 信号量（Semaphore）
   8.7. 可过期性信号量（PermitExpirableSemaphore）
   8.8. 闭锁（CountDownLatch）  
   redisson分布式锁原理
   可重入：利用hash结构记录线程id和重入次数
   可重试：利用信号量和pubSub功能实现等待、唤醒，获取锁失败的重试机制
   超时续约：利用watchDog，每隔一段时间（releaseTime/3）,重置超时时间
   
   
不可重入的分布式锁：
    原理： 利用setnx的互斥性，利用ex避免死锁，释放锁时判断线程标识
    缺陷：不可重入，无法重试、锁超时失效
可重入的redis分布式锁：
    原理：利用hash结构，记录线程标识和重入次数，利用watchDog延续锁时间，利用信号量控制锁重试等待
    缺陷：redis宕机引起锁失效问题
redisson的multiLock:
    原理：多个独立的redis节点，必须在所有节点都获取重入锁，才算获取锁成功
    缺陷：成本高 实现复杂