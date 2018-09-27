package com.xsg;

import com.xsg.service.Desk;
import com.xsg.service.Player;

/**
 * 牛牛
 */
public class App {
    public static void main(String[] args) {
        Player player1 = new Player("干志");
        Player player2 = new Player("老司机");
        Player player3 = new Player("圣国");
        Player player4 = new Player("阿黄");

        Desk desk = new Desk();
        desk.join(player1);
        desk.join(player2);
        desk.join(player3);
        desk.join(player4);

        desk.start();
    }
}
