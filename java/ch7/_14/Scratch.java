package ch7._14;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

/**
 * @author cheng
 *         2018/7/23 17:06
 */
public class Scratch {
    public static void main(String[] args) {
        PooledByteBufAllocator allocator = PooledByteBufAllocator.DEFAULT;

        ByteBuf byteBuf = allocator.directBuffer(16);
        byteBuf.release();
    }
}
