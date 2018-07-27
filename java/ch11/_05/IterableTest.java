package ch11._05;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

/**
 * 迭代器模式:实现内存零拷贝
 * 1.迭代器接口
 * 2.对容器里面各个对象进行访问
 * <p>
 * 应用范例:
 * io.netty.buffer.CompositeByteBuf
 * _getByte()获取索引index位置的组件Component,底层组件的buf调用getByte()获取ByteBuf
 *
 * @author cheng
 *         2018/7/27 15:31
 */
public class IterableTest {

    public static void main(String[] args) {

        ByteBuf header = Unpooled.wrappedBuffer(new byte[]{1, 2, 3});
        ByteBuf body = Unpooled.wrappedBuffer(new byte[]{4, 5, 6});

        ByteBuf merge = merge(header, body);

        merge.forEachByte(value -> {
            System.out.println(value);
            return true;
        });
    }

    public static ByteBuf merge(ByteBuf header, ByteBuf body) {

        // ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        // byteBuf.writeBytes(header);
        // byteBuf.writeBytes(body);

        // 实现零拷贝
        CompositeByteBuf byteBuf = ByteBufAllocator.DEFAULT.compositeBuffer(2);
        byteBuf.addComponent(true, header);
        byteBuf.addComponent(true, body);

        return byteBuf;
    }
}
