package ch7._12;

import io.netty.buffer.PooledByteBufAllocator;

/**
 * @author cheng
 *         2018/7/23 12:00
 */
public class Scratch {
    public static void main(String[] args) {

        int page = 1024 * 8;
        PooledByteBufAllocator allocator = PooledByteBufAllocator.DEFAULT;

        allocator.directBuffer(2 * page);
    }
}
