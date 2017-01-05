import java.util.*;
import java.io.*;

public class Scrabble {
    private String[][]grid;
    private int letterCount;
    private int wordCount;
    private boolean start;
    private String[]origWords;
   
    
    public Scrabble(){
	grid = new String[15][15];
	letterCount = 0;
	wordCount = 0;
	start = true;
    }

    public void addLetter(String letter, int row, int col){
	grid[row][col] = letter;
    }

    


    public static boolean checkWord(String word){
	try{
	    Scanner dict = new Scanner(new File("dictionary.txt"));
	    while(dict.hasNext()) {
		if((dict.next()).equals(word)){
		    return true;
		}
	    }
	}
        catch(FileNotFoundException e){
	    System.out.println("File not found.");
	    System.exit(1);
	}

	return false;
	
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
	System.out.println(checkWord("sghheu"));
    }

}
