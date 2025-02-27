package org.example.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import sun.awt.SunHints;

import java.security.Key;
import java.util.List;
import java.util.Map;

/**
 * @Discription:
 * @User: LiChenHao
 * @Date: 2024/3/8 14:39
 */
public class JedisConnectionFactory {
    private  static final JedisPool JEDIS_POOL;
    public Jedis jedis = new Jedis();
    static {
        //配置连接池
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig() ;
        jedisPoolConfig.setMaxTotal(8);//最大连接数
        jedisPoolConfig.setMaxIdle(8);//最大空闲数量
        jedisPoolConfig.setMinIdle(0);//最小空闲数量
        jedisPoolConfig.setMaxWaitMillis(1000);//最大等待时间ms
        String ip = "47.94.94.217";
        //创建链接池对象
        JEDIS_POOL = new JedisPool(jedisPoolConfig,ip,6379,1000,"123321");

    }
    public static  Jedis getJedis(){
        return JEDIS_POOL.getResource();
    }
}
