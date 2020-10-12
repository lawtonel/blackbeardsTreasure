package com.blackbeardstreasure.gamemaster;

import com.blackbeardstreasure.player.Player;

public interface GameMaster {
    String randomAction(Player currentPlayer);
    String getName();
}
