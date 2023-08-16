package com.tictactoe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    enum WINRESULT {
        WIN,LOST, DRAW
    }

    private static void writeWinningMessage(final WINRESULT variant){
        if(WINRESULT.WIN.equals(variant)){
            logger.info("\nYou won the game!\nCreated by Shreyas Saha. Thanks for playing!");
        } else if(WINRESULT.LOST.equals(variant)){
            logger.info("\nYou lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
        } else if(WINRESULT.DRAW.equals(variant)){
            logger.info("\nIt's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
        }
    }
    private static WINRESULT winner (final int win){
        if (win == 1 ) return WINRESULT.WIN ;
        else if (win == 2 ) return WINRESULT.LOST;
        else return WINRESULT.DRAW;
    }
    private static boolean checkFinalCombination(final char[] box, final char symbol){
        if((box[0]==symbol && box[1]==symbol && box[2]==symbol) || (box[3]==symbol && box[4]==symbol && box[5]==symbol) || (box[6]==symbol && box[7]==symbol && box[8]==symbol) ||
                (box[0]==symbol && box[3]==symbol && box[6]==symbol) || (box[1]==symbol && box[4]==symbol && box[7]==symbol) || (box[2]==symbol && box[5]==symbol && box[8]==symbol) ||
                (box[0]==symbol && box[4]==symbol && box[8]==symbol) || (box[2]==symbol && box[4]==symbol && box[6]==symbol)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        byte input;
        byte rand;
        byte i;
        boolean boxAvailable = false;
        byte winner = 0;
        char box[] = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        System.out.println("Enter box number to select. Enjoy!\n");

        boolean boxEmpty = false;
        while (true) {
            System.out.println("\n\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
            System.out.println("-----------");
            System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
            System.out.println("-----------");
            System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
            if(!boxEmpty){
                for(i = 0; i < 9; i++)
                    box[i] = ' ';
                boxEmpty = true;
            }

            if(winner != 0){
                writeWinningMessage(
                        winner(winner));
            break;
            }

            while (true) {
                input = scan.nextByte();
                if (input > 0 && input < 10) {
                    if (box[input - 1] == 'X' || box[input - 1] == 'O')
                        System.out.println("That one is already in use. Enter another.");
                    else {
                        box[input - 1] = 'X';
                        break;
                    }
                }
                else
                    System.out.println("Invalid input. Enter again.");
            }

            if(checkFinalCombination(box,'X')){
                   winner = 1;
                   continue;
            }

            boxAvailable = false;
            for(i=0; i<9; i++){
                if(box[i] != 'X' && box[i] != 'O'){
                    boxAvailable = true;
                    break;
                }
            }

            if(!boxAvailable){
                winner = 3;
                continue;
            }

            while (true) {
                rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
                if (box[rand - 1] != 'X' && box[rand - 1] != 'O') {
                    box[rand - 1] = 'O';
                    break;
                }
            }

            if(checkFinalCombination(box, '0')){
                winner = 2;
            }
        }

    }
}