package ch7._13;

import io.netty.buffer.PooledByteBufAllocator;

/**
 * @author cheng
 *         2018/7/23 16:43
 */
public class Scratch {
    public static void main(String[] args) {

        PooledByteBufAllocator allocator = PooledByteBufAllocator.DEFAULT;

        allocator.directBuffer(16);
    }
}
