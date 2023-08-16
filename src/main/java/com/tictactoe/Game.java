package com.tictactoe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Game {
    private static final Logger logger = LoggerFactory.getLogger(Game.class);
    private final Box box = new Box();

    private static final Scanner scan = new Scanner(System.in);

    private byte input() {
        while (true) {
            final byte input = scan.nextByte();
            if (input > 0 && input < 10) {
                if (box.isBoxEmpty((byte) (input - 1)))
                    System.out.println("That one is already in use. Enter another.");
                else {
                    return input;
                }
            } else
                System.out.println("Invalid input. Enter again.");
        }
    }

    private boolean playHumanAndCheckWin() {
        byte input = input();
        box.fillBox((byte) (input - 1), 'X');

        return box.checkFinalCombination('X');
    }

    private boolean playPCAndCheckWin() {
        byte rnd = doPC();
        box.fillBox((byte) (rnd - 1), '0');

        return box.checkFinalCombination('0');
    }

    private byte doPC() {
        while (true) {
            final byte rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
            if (box.isBoxEmpty((byte) (rand - 1))) {
                return rand;
            }
        }
    }


    private boolean checkDraw() {
        for (byte i = 0; i < 9; i++) {
            if (box.isBoxEmpty(i)) {
                return true;
            }
        }

        return false;
    }

    enum WINRESULT {
        WIN, LOST, DRAW, GOING
    }

    private WINRESULT playStrategy() {
        if (playHumanAndCheckWin()) {
            return WINRESULT.WIN;
        } else if (checkDraw()) {
            return WINRESULT.DRAW;
        } else if (playPCAndCheckWin()) {
            return WINRESULT.LOST;
        } else return WINRESULT.GOING;
    }

    public void play() {

        while (true) {
            box.printBoxInfo();

            final WINRESULT result = playStrategy();
            if (result != WINRESULT.GOING) {
                writeWinningMessage(result);
                return;
            }
        }
    }

    private void writeWinningMessage(final WINRESULT variant) {
        if (WINRESULT.WIN.equals(variant)) {
            logger.info(MESAGE_ON_WIN);
        } else if (WINRESULT.LOST.equals(variant)) {
            logger.info(MESAGE_ON_LOST);
        } else if (WINRESULT.DRAW.equals(variant)) {
            logger.info(MESAGE_ON_DRAW);
        }
    }

    private static final String MESAGE_ON_WIN = "\nYou won the game!\nCreated by Shreyas Saha. Thanks for playing!";
    private static final String MESAGE_ON_LOST = "\nYou lost the game!\nCreated by Shreyas Saha. Thanks for playing!";
    private static final String MESAGE_ON_DRAW = "\nIt's a draw!\nCreated by Shreyas Saha. Thanks for playing!";

}