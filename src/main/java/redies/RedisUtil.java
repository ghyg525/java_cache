package redies;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis操作工具类
 * @author YangJie
 */
public class RedisUtil {

	private static String host = "192.168.125.15";
	private static int port = 6379;
	private static int timeout = 10000; // 超时时间(ms)
	private static String password;
	private static int database = 1;
    private static JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), host, port, timeout, password, database);

    
    /**
     * 获取
     * @param key
     * @return
     */
    public static String get(String key){
    	Jedis jedis = null;
    	try {
    		jedis = jedisPool.getResource();
			return jedis.get(key);
		} finally {
			jedis.close();
		}
    }

	/**
     * 添加
     * @param key
     * @param value
     * @return
     */
    public static boolean set(String key, String value){
    	Jedis jedis = null;
    	try {
    		jedis = jedisPool.getResource();
    		return "OK".equals(jedis.set(key, value));
		} finally {
			jedis.close();
		}
    }
    
    /**
     * 删除
     * @param key
     * @param value
     * @return
     */
    public static boolean del(String key){
    	Jedis jedis = null;
    	try {
    		jedis = jedisPool.getResource();
    		return jedis.del(key) > 0;
		} finally {
			jedis.close();
		}
    }
    
}
