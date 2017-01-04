import java.util.*;
import java.io.*;

public class Scrabble {
    private String[][]grid;
    private int letterCount;
    private int wordCount;
    private boolean start;
    
    public Scrabble(){
	grid = new String[15][15];
	letterCount = 0;
	wordCount = 0;
	start = true;
    }

    public void firstLetter(String letter){
	grid[8][8] = letter;
    }

    public void addLetter(String letter, int row, int col){
	grid[row][col] = letter;
    }


    public String toString() {
	String str = "[";
	
	for (int i = 0; i < grid.length; i++) {
	    
	    for (int j = 0; j < grid[i].length; j++) {
		str += grid[i][j];
		str += " ";
	    }
	    
	    str += "]";
	    str += "\n";

	    if (i < grid.length - 1) {
	    str += "[";
	    }
	}

	return str;
    }


    public static void main(String[] args){
	Scrabble a = new Scrabble();
	a.addLetter("H", 10, 10);
	System.out.println(a.toString());
    }

}
