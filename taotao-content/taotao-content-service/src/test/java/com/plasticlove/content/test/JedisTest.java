package com.plasticlove.content.test;


import com.plasticlove.content.jedis.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisTest {
    @Test
    public void testJedis(){
        Jedis jedis = new Jedis("121.248.55.152",6379);
        jedis.set("Demo","Seu");
        System.out.println(jedis.get("Demo"));
        jedis.close();
    }


    @Test
    public void testJedisPool(){
        JedisPool jedisPool = new JedisPool("121.248.55.152",6379);
        Jedis jedis = jedisPool.getResource();
        jedis.set("江苏","南京");
        System.out.println(jedis.get("江苏"));
        jedis.close();
        jedisPool.close();
    }

    @Test
    public void testJedisClient(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        JedisClient jedisClient = context.getBean(JedisClient.class);
        jedisClient.set("无线谷","秣周东路");
        System.out.println(jedisClient.get("无线谷"));

    }
}
