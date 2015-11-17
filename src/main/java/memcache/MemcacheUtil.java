package memcache;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;


/**
 * Memcache操作工具类
 * @author YangJie
 */
public class MemcacheUtil {

	private static MemcachedClient memcachedClient = initMemcachedClient();
	private static MemcachedClient initMemcachedClient() {
		try {
			return new XMemcachedClientBuilder(AddrUtil.getAddresses("localhost:11211")).build();
		} catch (IOException e) {
			e.printStackTrace();
		};
		return null;
	}
	
	/**
	 * 获取
	 * @param key
	 * @return
	 */
	public static <T> T get(Object key) {
		try {
			return key==null ? null : memcachedClient.get(key.toString());
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 保存
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean set(Object key, Object value) {
		return set(key, value, 0);
	}
	
	/**
	 * 保存 (如果保存实体需要实体实现Serializable接口)
	 * @param key
	 * @param value
	 * @param exp 有效期(秒)
	 * @return
	 */
	public static boolean set(Object key, Object value, int exp) {
		try {
			return key==null ? false : memcachedClient.set(key.toString(), exp, value);
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
}
