package ch11._01;

/**
 * 单例模式：
 * 1. 一个类全局只有一个对象；
 * 2. 延迟创建：当使用对象的时候才创建对象；
 * 3. 避免线程安全问题：多线程环境时，多线程同时需要类对象同时创建，违背了一个类全局只有一个对象的原则；
 * <p>
 * 应用范例：
 * io.netty.handler.timeout.ReadTimeoutException
 * io.netty.handler.codec.mqtt.MqttEncoder
 * 推荐使用public static final <Class> INSTANCE = new <Class>();private <Class>() { }实现单例模式简单优雅
 *
 * @author cheng
 *         2018/7/27 14:14
 */
public class Singleton {

    private static Singleton singleton;

    public Singleton() {
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }

        return singleton;
    }
}
