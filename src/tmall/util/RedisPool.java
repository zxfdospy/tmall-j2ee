package tmall.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {
    private static JedisPool pool;

    private static  String redisIp=PropertiesUtil.getProperty("redis1.ip");
    private static  Integer redisPort=Integer.parseInt(PropertiesUtil.getProperty("redis1.port"));

    private static  Integer maxTotal=Integer.parseInt(PropertiesUtil.getProperty("redis.max.toal","20"));
    private static  Integer maxIdle=Integer.parseInt(PropertiesUtil.getProperty("redis.max.idel","20"));
    private static  Integer minIdle=Integer.parseInt(PropertiesUtil.getProperty("redis.min.toal","2"));
    private static  Boolean testOnBorrow=Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.borrow","true"));
    private static  Boolean testOnReturn=Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.return","true"));

    static{
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);

        config.setBlockWhenExhausted(true);//连接耗尽时，是否阻塞，false直接抛出异常，默认true
        pool=new JedisPool(config,redisIp,redisPort,1000*2);//单位是毫秒
    }

    public static Jedis getJedis(){
        return pool.getResource();
    }

    public static void returnResource(Jedis jedis){
        pool.returnResource(jedis);
    }

    public static void returnBrokenResource(Jedis jedis){
        pool.returnBrokenResource(jedis);
    }

}
