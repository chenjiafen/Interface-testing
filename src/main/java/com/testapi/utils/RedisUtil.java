package com.testapi.utils;

import redis.clients.jedis.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class RedisUtil {


    //默认缓存时间
    private static final int EXPIRE = 60000;

    private static Properties properties;

    private static RedisUtil instance;

    private static JedisPool jedisPool;


    //集群
    private static JedisCluster jedisCluster;

    private static ReentrantLock lock = new ReentrantLock();

    private RedisUtil() {
    }

    public static RedisUtil getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new RedisUtil();
            }
            lock.unlock();
        }
        return instance;
    }


    /**
     * 初始化JedisPool
     */
    private void initJedisPool() {
        properties = new Properties();
        try {
            properties.load(new FileReader(new File("resources/redis.properties")));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(Integer.valueOf(properties.getProperty("redis.pool.maxIdle")));
        config.setTestOnBorrow(Boolean.getBoolean(properties.getProperty("redis.pool.testOnBorrow")));
        config.setTestOnReturn(Boolean.getBoolean(properties.getProperty("redis.pool.testOnReturn")));
        jedisPool = new JedisPool(
                config,
                properties.getProperty("redis.ip"), Integer.valueOf(properties.getProperty("redis.port"))
        );

    }

    /**
     * 初始化集群
     */
    public JedisCluster initJedisPools() {
        Properties properties = new Properties();
        Set<HostAndPort> haps = new LinkedHashSet<HostAndPort>();
        try {
            properties.load(new FileReader(new File("/Users/Work/crazyapi/src/main/resources/redis.properties")));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(Integer.valueOf(properties.getProperty("redis.pool.maxIdle")));
        config.setTestOnBorrow(Boolean.getBoolean(properties.getProperty("redis.pool.testOnBorrow")));
        config.setTestOnReturn(Boolean.getBoolean(properties.getProperty("redis.pool.testOnReturn")));
        String ipLsit = properties.getProperty("redis.ip");
        String[] ips = ipLsit.split(",");
        for (String ip : ips) {
            String[] i = ip.split(":");
            HostAndPort hap = new HostAndPort(i[0], Integer.parseInt(i[1]));
            haps.add(hap);

        }
        jedisCluster = new JedisCluster(haps, config);
        return jedisCluster;

    }

    /**
     * 通用方法：从JedisPool中获取Jedis
     *
     * @return
     */
    private Jedis getJedis() {
        if (jedisPool == null) {
            lock.lock();    //防止初始化时多线程竞争问题
            initJedisPool();
            lock.unlock();
        }
        return jedisPool.getResource();
    }


    /**
     * 通用方法：释放Jedis
     *
     * @param jedis
     */
    private void closeJedis(Jedis jedis) {
        jedis.close();
    }

    public static void main(String[] args) throws Exception {
//		Jedis redis=RedisUtil.getInstance().getJedis();
//		System.out.println("连接成功");
////		redis.auth("123456");
////		redis.setex("15210085687",4,"1234");
//		//redis.setex(key, seconds, value)
//		Thread.sleep(3000);
//		System.out.println(redis.get("15210085687"));
//		RedisUtil.getInstance().closeJedis(redis);


        //集群
        RedisUtil util = new RedisUtil();
        JedisCluster hs = util.initJedisPools();
        String name = hs.get("md:m:memberDomainByMemberId:[58207309582565409]");
        System.out.println(name);
        hs.close();


    }
}