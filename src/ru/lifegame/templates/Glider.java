package ru.lifegame.templates;

public class Glider extends Template {

    public Glider(int universeSize){
        super(3, 3, universeSize);
    }

    public void create(String[][] universe){
        System.out.println("Universe const " + UNIVERSE_SIZE);
        int startCellX;
        int startCellY;
        do{ //randomize correct coordinates
            startCellX = (int)Math.round(Math.random() * UNIVERSE_SIZE);
            startCellY = (int)Math.round(Math.random() * UNIVERSE_SIZE);
        }while (!isEnoughSpace(startCellX, startCellY, universe));

        // creating cells of the template by modeling the Tor surface
        universe[(startCellX + 1) % UNIVERSE_SIZE][startCellY % UNIVERSE_SIZE] = "@";//first row

        universe[(startCellX + 2) % UNIVERSE_SIZE][(startCellY + 1) % UNIVERSE_SIZE] = "@";//second row

        universe[startCellX % UNIVERSE_SIZE][(startCellY + 2) % UNIVERSE_SIZE] = "@";//third row
        universe[(startCellX + 1) % UNIVERSE_SIZE][(startCellY + 2) % UNIVERSE_SIZE] = "@";
        universe[(startCellX + 2) % UNIVERSE_SIZE][(startCellY + 2) % UNIVERSE_SIZE] = "@";
    }
}
