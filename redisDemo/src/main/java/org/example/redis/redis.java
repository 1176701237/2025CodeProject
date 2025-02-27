package org.example.redis;

import redis.clients.jedis.Jedis;

/**
 * @Discription:
 * @User: LiChenHao
 * @Date: 2024/3/8 11:28
 */
public class redis {
    private static Jedis jedis;
    public static void main(String[] args) {
        jedis =  JedisConnectionFactory.getJedis();
        jedis.set("JedisConnectionFactory","jedis");

        String jedisConnectionFactory = jedis.get("JedisConnectionFactory");
        System.out.println(jedisConnectionFactory);

    }
}
