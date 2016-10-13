package redies;

import java.util.Arrays;
import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Redis操作工具类
 * @author YangJie
 */
public class RedisUtil {

	static List<JedisShardInfo> shardInfoList = Arrays.asList(
			new JedisShardInfo("http://192.168.120.33:6379/1"),
			new JedisShardInfo("http://192.168.120.33:6379/1")
	);
	private static JedisPoolConfig poolConfig = new JedisPoolConfig();
    private static ShardedJedisPool shardedJedisPool = new ShardedJedisPool(poolConfig, shardInfoList);

    
    /**
     * 获取
     * @param key
     * @return
     */
    public static String get(String key){
    	return shardedJedisPool.getResource().get(key);
    }
    
    /**
     * 添加
     * @param key
     * @param value
     * @return
     */
    public static boolean set(String key, String value){
		return "OK".equals(shardedJedisPool.getResource().set(key, value));
    }
    
    /**
     * 删除
     * @param key
     * @param value
     * @return
     */
    public static boolean del(String key){
    	return shardedJedisPool.getResource().del(key) > 0;
    }
    
}
