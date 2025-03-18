package cn.stronger.we.drools;


import java.util.concurrent.atomic.LongAdder;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class LongAdd.class
 * @department Platform R&D
 * @date 2025/3/18
 * @desc do what?
 */
public class LongAdd {
    private static final LongAdder count = new LongAdder();

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            count.increment();
            count.add(2);
        }

        System.out.println(count);

    }
}
