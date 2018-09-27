package com.xsg.service;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 牌局
 * 行为：
 *  加入牌局
 *  退出牌局
 *  牌局开始
 */
@Getter
public class Desk {
    private static final Integer MAX_PLAYER_COUNT = 10;

    private List<Player> players = new ArrayList<>();

    /**
     * 牌局开始
     */
    public void start() {
        if (players.size() <= 1) {
            System.out.println("用户人数不足2人，等待新用户加入");
            return;
        }

        //加入一个发牌人
        Publisher publisher = new Publisher(this);
        publisher.start();

        //展示结果
        showResult();
    }

    /**
     * 加入牌局
     */
    public boolean join(Player player) {
        if (players.size() >= MAX_PLAYER_COUNT) {
            return false;
        }

        players.add(player);
        return true;
    }

    /**
     * 退出牌局
     */
    public void exit(Player player) {
        players.remove(player);
    }

    /**
     * 本轮结果
     * @return
     */
    public void showResult() {
        players.stream().forEach(Player::show);
    }

    public List getPlayers() {
        return players;
    }
}
