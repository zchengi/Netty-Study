package ch10._02;

import io.netty.util.concurrent.FastThreadLocal;

/**
 * @author cheng
 *         2018/7/26 19:08
 */
public class FastThreadLocalTest {

    private static FastThreadLocal<Object> threadLocal1 = new FastThreadLocal<Object>() {
        @Override
        protected Object initialValue() {
            return new Object();
        }

        @Override
        protected void onRemoval(Object value) {
            System.out.println("onRemoval");
        }
    };

    private static FastThreadLocal<Object> threadLocal0 = new FastThreadLocal<Object>() {
        @Override
        protected Object initialValue() {
            return new Object();
        }
    };

    public static void main(String[] args) {

        new Thread(() -> {
            Object object = threadLocal1.get();
            // .. do with object
            System.out.println(object);

            while (true) {
                threadLocal1.set(new Object());
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            Object object = threadLocal1.get();
            // ... do with object
            System.out.println(object);
            while (true) {
                System.out.println(threadLocal1.get() == object);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
