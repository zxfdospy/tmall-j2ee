package tmall.util;

import redis.clients.jedis.Jedis;

public class RedisPoolUtil {

    public static String set(String key,String value){
        Jedis jedis=null;
        String result=null;

        try {
            jedis=RedisPool.getJedis();
            result=jedis.set(key,value);
        } catch (Exception e) {
            RedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static String setEx(String key, String value, int exTime) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.setex(key, exTime, value);
        } catch (Exception e) {
            RedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static String get(String key) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            RedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static Long expire(String key, int exTime) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.expire(key, exTime);
        } catch (Exception e) {

            RedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }


    public static Long del(String key) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.del(key);
        } catch (Exception e) {
            RedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static void main(String[] args) {
        set("1","2");
    }

}
