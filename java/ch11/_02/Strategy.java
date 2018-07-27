package ch11._02;


/**
 * 策略模式:
 * 1.封装一系列可相互替换的算法家族
 * 2.动态选择某一个策略
 * 应用范例:
 * io.netty.util.concurrent.DefaultEventExecutorChooserFactory#newChooser(io.netty.util.concurrent.EventExecutor[])
 *
 * 算法家族:EventExecutorChooser,算法实现:next(),
 * 策略实现:PowerOfTowEventExecutorChooser/GenericEventExecutorChooser,选择策略:newChooser()
 *
 * @author cheng
 *         2018/7/27 14:23
 */
public class Strategy {

    private Cache cacheMemory = new CacheMemoryImpl();
    private Cache cacheRedis = new CacheRedisImpl();

    public interface Cache {
        boolean add(String key, Object object);
    }

    public class CacheMemoryImpl implements Cache {
        @Override
        public boolean add(String key, Object object) {
            // 保存到 map
            return false;
        }
    }

    public class CacheRedisImpl implements Cache {
        @Override
        public boolean add(String key, Object object) {
            // 保存到 redis
            return false;
        }
    }

    /**
     * 路由策略动态选择策略
     *
     * @param key
     * @return
     */
    public Cache getCache(String key) {
        if (key.length() < 10) {
            return cacheRedis;
        }
        return cacheMemory;
    }
}
