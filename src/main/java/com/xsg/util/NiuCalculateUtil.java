package com.xsg.util;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 检查是否有牛
 */
public class NiuCalculateUtil {

    /**
     * 查看是不是炸弹,返回炸弹
     *
     * @param pokers [K, 8, 5, 5, 7]
     * @return -1 代表不是炸弹
     */
    public static Integer isBoom(List<String> pokers) {
        Integer boom = -1;
        //按元素分类
        Map<Object, List<String>> result = pokers.stream().collect(Collectors.groupingBy(poker -> poker, Collectors.toList()));
        if (result.size() == 2) {
            for (Map.Entry<Object, List<String>> entry : result.entrySet()) {
                if (entry.getValue().size() == 4) {
                    boom = (Integer) entry.getKey();
                    break;
                }
            }
        }
        return boom;
    }

    /**
     * 判断是否未5小
     *
     * @param pokers
     * @return
     */
    public static boolean fiveLittle(List<String> pokers) {
        try {
            return pokers.stream().map(Integer::valueOf).reduce(0, (sum, b) -> sum + b, (a, b) -> a + b) <= 10;
        } catch (NumberFormatException e) {
            //出异常说明有非数字牌出现，总和必然大于10
        }
        return false;
    }

    /**
     * 随机3张牌组合成10的倍数，剩余两张牌相加取个位数的值
     *
     * @param pokers
     * @return -1 代表没牛
     */
    public static Integer hasNiu(List<String> pokers) {
        List<Integer> tmp = new ArrayList<>();
        for (String poker : pokers) {
            try {
                tmp.add(Integer.valueOf(poker));
            } catch (NumberFormatException e) {
                tmp.add(10);
            }
        }
        Integer[] newPokers = tmp.toArray(new Integer[5]);

        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++) {
                for (int k = j + 1; k < 5; k++) {
                    boolean isNiu = (newPokers[i] + newPokers[j] + newPokers[k]) % 10 == 0;
                    if (isNiu) {
                        return tmp.stream().reduce(0, (sum, b) -> sum + b) % 10;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        List<String> pokers = Arrays.asList(new String[]{"K", "5", "5", "5", "5"});
        System.out.println(isBoom(pokers));
        System.out.println(fiveLittle(Arrays.asList(new String[]{"1", "2", "5", "5", "5"})));
        System.out.println(fiveLittle(Arrays.asList(new String[]{"1", "2", "3", "3", "1"})));

        System.out.println(hasNiu(Arrays.asList(new String[]{"1", "2", "3", "3", "1"}))); //-1
        System.out.println(hasNiu(Arrays.asList(new String[]{"1", "3", "6", "3", "1"}))); //4
        System.out.println(hasNiu(Arrays.asList(new String[]{"K", "3", "10", "3", "Q"}))); //6
    }
}
