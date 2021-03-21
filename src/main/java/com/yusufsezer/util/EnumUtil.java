package com.yusufsezer.util;

import java.util.concurrent.ThreadLocalRandom;

public class EnumUtil {

    public static <T extends Enum<T>> T getRandom(Class<T> clazz) {
        T[] enums = clazz.getEnumConstants();
        int rndNumber = ThreadLocalRandom.current().nextInt(enums.length);
        return enums[rndNumber];
    }

}
