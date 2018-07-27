package ch11._03;

/**
 * 装饰者模式:
 * 1.装饰者和被装饰者继承同一个接口
 * 2.装饰者给被装饰者动态修改行为
 * <p>
 * 应用范例:
 * io.netty.buffer.WrappedByteBuf
 * io.netty.buffer.UnreleasableByteBuf
 * io.netty.buffer.SimpleLeakAwareByteBuf
 * <p>
 * 构造函数参数为被装饰者,装饰者代理被装饰者方法,覆盖被装饰者方法实现动态修改被装饰者行为
 *
 * @author cheng
 *         2018/7/27 14:46
 */
public class Decorate {

    /**
     * 优惠方案
     */
    public interface OnSalePlan {
        float getPrice(float oldPrice);
    }

    /**
     * 无优惠
     */
    public static class NonePlan implements OnSalePlan {

        static final OnSalePlan INSTANCE = new NonePlan();

        public NonePlan() {
        }

        @Override
        public float getPrice(float oldPrice) {
            return oldPrice;
        }
    }

    /**
     * 立减优惠
     */
    public static class KnockPlan implements OnSalePlan {

        // 立减金额
        private float amount;

        public KnockPlan(float amount) {
            this.amount = amount;
        }

        @Override
        public float getPrice(float oldPrice) {
            return oldPrice - amount;
        }
    }

    /**
     * 打折优惠
     */
    public static class DiscountPlan implements OnSalePlan {

        // 折扣
        private int discount;
        private OnSalePlan previousPlan;

        public DiscountPlan(int discount) {
            this(discount, NonePlan.INSTANCE);
        }

        public DiscountPlan(int discount, OnSalePlan previousPlan) {
            this.discount = discount;
            this.previousPlan = previousPlan;
        }

        @Override
        public float getPrice(float oldPrice) {
            return previousPlan.getPrice(oldPrice) * discount / 10;
        }
    }

    public static void main(String[] args) {

        DiscountPlan simpleDiscountPlan = new DiscountPlan(5);
        System.out.println(simpleDiscountPlan.getPrice(100));

        KnockPlan previousPlan = new KnockPlan(50);
        DiscountPlan complexDiscountPlan = new DiscountPlan(5, previousPlan);
        System.out.println(complexDiscountPlan.getPrice(100));
    }
}
