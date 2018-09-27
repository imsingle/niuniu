package com.xsg.service;

import com.xsg.util.NiuCalculateUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 玩家
 */
public class Player {
    private String name;

    private boolean isHost = false;

    private List<String> pokers = new LinkedList<>();

    public Player(String name) {
        this.name = name;
    }

    /**
     * 接收牌
     */
    public void accept(String poker) {
        this.pokers.add(poker);
    }

    /**
     * 查看手上的牌，查看是否有牛
     */
    public void show() {
        StringBuilder message = new StringBuilder();
        message.append("玩家: ").append(name)
                .append(" 手上的牌：").append(pokers).append(" ").append(hasNiu());

        System.out.println(message);
    }

    /**
     * 4个头
     * 5小
     * 有牛
     * 无牛
     *
     * @return
     */
    public String hasNiu() {
        String result = "没有牛";
        List<String> newPokers = pokers.stream().map(poker -> poker.substring(2)).collect(Collectors.toList());
        Integer boom;
        if ((boom = NiuCalculateUtil.isBoom(newPokers)) > -1) {
            result = "4个" + boom;
        } else if (NiuCalculateUtil.fiveLittle(newPokers)) {
            result = "5小";
        } else if ((boom = NiuCalculateUtil.hasNiu(newPokers)) > -1) {
            result = "牛" + (boom == 0 ? "牛" : boom);
        }
        return result;
    }

    public void setHost(boolean host) {
        isHost = host;
    }
}
