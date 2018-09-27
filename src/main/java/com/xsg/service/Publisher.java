package com.xsg.service;

import java.util.*;

/**
 * 发牌者
 */
public class Publisher {

    //黑桃（Spade）、红桃（Heart）、梅花（Club）、方块（Diamond）
    public static final String[] POKER_LIST = {
            "黑桃1", "黑桃2", "黑桃3", "黑桃4", "黑桃5", "黑桃6", "黑桃7", "黑桃8", "黑桃9", "黑桃10", "黑桃J", "黑桃Q", "黑桃K",
            "红桃1", "红桃2", "红桃3", "红桃4", "红桃5", "红桃6", "红桃7", "红桃8", "红桃9", "红桃10", "红桃J", "红桃Q", "红桃K",
            "梅花1", "梅花2", "梅花3", "梅花4", "梅花5", "梅花6", "梅花7", "梅花8", "梅花9", "梅花10", "梅花J", "梅花Q", "梅花K",
            "方块1", "方块2", "方块3", "方块4", "方块5", "方块6", "方块7", "方块8", "方块9", "方块10", "方块J", "方块Q", "方块K"
    };
//    public static final String[] POKER_LIST = {
//            "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SJ", "SQ", "SK", "SA",
//            "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HJ", "HQ", "HK", "HA",
//            "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CJ", "CQ", "CK", "CA",
//            "方块2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "DJ", "DQ", "DK", "DA"
//    };

    public static final int POKER_COUNT_PER_PLAYER = 5;

    private Desk desk;

    /**
     * 洗完后的牌
     */
    private LinkedList<String> pokers = new LinkedList<>();

    /**
     * 接收一个牌局
     *
     * @param desk
     */
    public Publisher(Desk desk) {
        this.desk = desk;
    }

    /**
     * 开始发牌
     */
    public void start() {
        reClear();
        publish();
    }

    /**
     * 洗牌
     */
    public void reClear() {
        pokers = new LinkedList(Arrays.asList(POKER_LIST));
        Collections.shuffle(pokers);
    }

    /**
     * 发牌
     */
    public void publish() {
        List<Player> players = desk.getPlayers();
        for (int i = 0; i < POKER_COUNT_PER_PLAYER; i++) {
            players.stream().forEach(player -> {
                String poker = pokers.pop();
                player.accept(poker);
            });
        }
    }
}
