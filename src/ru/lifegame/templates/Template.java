package ru.lifegame.templates;

public abstract class Template { //abstract class of templates
    int WIDTH_IN_CELLS;
    int HEIGHT_IN_CELLS;
    int UNIVERSE_SIZE;

    public Template(int width, int height, int universeSize){ //initialization constants
        WIDTH_IN_CELLS = width;
        HEIGHT_IN_CELLS = height;
        UNIVERSE_SIZE = universeSize;
    }

    /** Creating a new template */
    public abstract void create(String[][] universe);

    /** Returns boolean value, that indicates freedom of the required space for creation */
    boolean isEnoughSpace(int x, int y, String[][] universe){
        for(int i = 0; i < WIDTH_IN_CELLS; i++){
            for(int j = 0; j < HEIGHT_IN_CELLS ; j++){
                int torI = (x + i) % UNIVERSE_SIZE; //modeling the Tor surface
                int torJ = (y + j) % UNIVERSE_SIZE; //modeling the Tor surface
                if(universe[torI][torJ] == "@") return false;
            }
        }
        return true;
    }
}
