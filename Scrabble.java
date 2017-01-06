import java.util.*;
import java.io.*;

public class Scrabble {
    private String[][]grid;
    private int letterCount;
    private int wordCount;
    private boolean start;
    private ArrayList<String> Words;
   
    
    public Scrabble(){
	grid = new String[15][15];
	clear();
	letterCount = 0;
	wordCount = 0;
	start = true;
	Words = new ArrayList<String>();
    }

    public void clear(){
	for(int row = 0; row < 15; row++){
	    for(int col = 0; col < 15; col++){
		grid[row][col] = ".";
	    }
	}
    }
    
    public void addLetter(String letter, int row, int col){
	grid[row][col] = letter;
    }

    public void horWords(){
	String hor = "";
	
	for(int row = 0; row < 15; row++){
	    for(int col = 0; col < 15; col++){
		hor += grid[row][col];
	    }
	}
	
	for(String word: hor.split("\\.")){
	    if(word.length() > 1){
	    Words.add(word);
	    }
	}
    }
    
	
    public void verWords(){
	String ver = "";
	
	for(int col = 0; col < 15; col++){
	    for(int row = 0; row < 15; row++){
		ver += grid[row][col];
	    }
	}
	
	for(String word: ver.split("\\.")){
	    if(word.length() > 1){
		Words.add(word);
	    }
	}
    }
    

    public boolean checkAllWords(){
	for(int i = 0; i < Words.size(); i++){
	    if(checkWord(Words.get(i)) == false){
		return false;
	    }
	}
	return true;
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
		str += "| ";
	    }
	    
	    str += "]";
	    str += "\n";

	    if (i < grid.length - 1) {
	    str += "[";
	    }
	}

	return str;
    }

    public String getWords(){
	return (Words.toString());
    }

    public static void main(String[] args){
	
	Scrabble a = new Scrabble();
	a.addLetter("H", 10, 10);
	a.addLetter("E", 10, 11);
	a.addLetter("D", 11, 8);
	a.addLetter("O", 11, 9);
	a.addLetter("O", 12, 8);
	a.addLetter("I", 13, 8);
	System.out.println(a.toString());
	a.horWords();
	a.verWords();
        System.out.println(a.getWords());
	System.out.println(a.checkAllWords());
	//	System.out.println(checkWord("sghheu"));

    }

}
