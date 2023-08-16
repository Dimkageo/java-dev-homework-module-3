package com.tictactoe;

public class Box {

    private char[] box = new char[9];

    public Box() {
        initBox();
    }


    private void initBox() {
        for (int i = 0; i < 9; i++)
            box[i] = ' ';
    }
    void printBoxInfo() {
        System.out.println("\n\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
    }

    boolean checkFinalCombination(final char symbol) {
        if ((box[0] == symbol && box[1] == symbol && box[2] == symbol) ||
                (box[3] == symbol && box[4] == symbol && box[5] == symbol) ||
                (box[6] == symbol && box[7] == symbol && box[8] == symbol) ||
                (box[0] == symbol && box[3] == symbol && box[6] == symbol) ||
                (box[1] == symbol && box[4] == symbol && box[7] == symbol) ||
                (box[2] == symbol && box[5] == symbol && box[8] == symbol) ||
                (box[0] == symbol && box[4] == symbol && box[8] == symbol) ||
                (box[2] == symbol && box[4] == symbol && box[6] == symbol)) {
            return true;
        }
        return false;
    }

    boolean isBoxEmpty(final  byte i){
        return box[i] != 'X' && box[i] != 'O';
    }
    void fillBox(final  byte i,final char symbol){
        box[i] = symbol;
    }
}
