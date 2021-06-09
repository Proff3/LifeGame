package ru.lifegame;

import ru.lifegame.templates.Glider;
import ru.lifegame.templates.SpaceShip;
import ru.lifegame.templates.Template;


import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    int UNIVERSE_SIZE;
    String[][] universe;
    Scanner scanner = new Scanner(System.in);
    Boolean[][] aliveCells;

    public Game(int universeSize){ //Initialization
        UNIVERSE_SIZE = universeSize;
        universe = new String[universeSize][universeSize];
        aliveCells = new Boolean[universeSize][universeSize];
        for(int i = 0; i < UNIVERSE_SIZE; i++){
            for(int j = 0; j < UNIVERSE_SIZE; j++){
                universe[i][j] = ".";
            }
        }
    }

    /**
     * Game processor. Starting with a new game by creating of the first generation.
     */
    public void Run() {
        startGame();
        while(true){
            System.out.println("if you want to exit the game, print 'exit', otherwise just type something");
            if (isGameEnded()) break;
            creteNextGeneration();
            printUniverse();
        }
    }

    void startGame(){ //Creating templates and first generation
        AbstractList<Template> elements = new ArrayList<>(); //Creation of the list of templates
        elements.add(new Glider(UNIVERSE_SIZE));
        elements.add(new Glider(UNIVERSE_SIZE));
        elements.add(new SpaceShip(UNIVERSE_SIZE));
        elements.forEach(temp -> temp.create(universe)); //Creation cells of templates
        printUniverse();

    }

    void printUniverse(){
        for(String[] stringArray: universe){
            StringBuilder row = new StringBuilder();
            for(String cell: stringArray){
                row.append(cell);
            }
            System.out.println(row);
        }
    }

    void creteNextGeneration(){
        countAliveCells();
        for(int x = 0; x < UNIVERSE_SIZE; x++){
            for(int y = 0; y < UNIVERSE_SIZE; y++){ //current Cell
                if(aliveCells[x][y]){
                    universe[x][y] = "@";
                }else{
                    universe[x][y] = ".";
                }
            }
        }
    }

    void countAliveCells(){
        for(int x = 0; x < UNIVERSE_SIZE; x++){
            for(int y = 0; y < UNIVERSE_SIZE; y++){ //current Cell
                int count = 0;
                for(int i = x - 1; i <= x + 1; i++){
                    for(int j = y - 1; j <= y + 1; j++){ //calculating alive cells around
                        int torI = (UNIVERSE_SIZE + i) % UNIVERSE_SIZE; //modeling the Tor surface
                        int torJ = (UNIVERSE_SIZE + j) % UNIVERSE_SIZE; //modeling the Tor surface
                        if(universe[torI][torJ] == "@" && (i != x || j != y)) {
                            count++;
                        }
                    }
                }
                if (count == 3) {
                    aliveCells[x][y] = true;
                }else if(count == 2 && universe[x][y] == "@"){
                    aliveCells[x][y] = true;
                }else{
                    aliveCells[x][y] = false;
                }
            }
        }
    }

    boolean isGameEnded(){
        String mes = scanner.next();
        if (mes.hashCode() == "exit".hashCode()) {
            return true;
        }
        return false;
    }
}
