import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;//NEW STUFF!
import java.awt.event.*;

public class Scrabble {
    private String[][]grid;
    private int letterCount;
    private int wordCount;
    private boolean start;
    private ArrayList<String> Words;
    private Container pane;
    private JButton submit;
    private String[] letterRack;
    private int score;
    
   
    
    public Scrabble(){
	grid = new String[15][15];
	clear();
	letterCount = 0;
	wordCount = 0;
	start = true;
	Words = new ArrayList<String>();
	score=0;
	//submit.addActionListener(this);
	//submit.setActionCommand(subWord();
	
	
	
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

    public int addScore(String word){
	for (int x =0; x+1 <= word.length();x++){
	    if ( (word.substring(x,x+1)).equals("A") || (word.substring(x,x+1)).equals("E") || (word.substring(x,x+1)).equals("I") || (word.substring(x,x+1)).equals("O") || (word.substring(x,x+1)).equals("U") || (word.substring(x,x+1)).equals("L") || (word.substring(x,x+1)).equals("N") || (word.substring(x,x+1)).equals("R") || (word.substring(x,x+1)).equals("S") || (word.substring(x,x+1)).equals("T") ){
		score++;
	    }
	    if ( (word.substring(x,x+1)).equals("D") || (word.substring(x,x+1)).equals("G") ){
		score+=2;
	    }
	    if ( (word.substring(x,x+1)).equals("B") || (word.substring(x,x+1)).equals("C") || (word.substring(x,x+1)).equals("M") || (word.substring(x,x+1)).equals("P")  ){
		score+=3;
	    }
	    if ( (word.substring(x,x+1)).equals("F") || (word.substring(x,x+1)).equals("H") || (word.substring(x,x+1)).equals("W") || (word.substring(x,x+1)).equals("Y") || (word.substring(x,x+1)).equals("V") ){
		score+=4;
	    }
	    if ( (word.substring(x,x+1)).equals("K") ){
		score+=5;
	    }
	    if ( (word.substring(x,x+1)).equals("J") || (word.substring(x,x+1)).equals("X") ){
		score+=8;
	    }
	    if ( (word.substring(x,x+1)).equals("Q") || (word.substring(x,x+1)).equals("Z") ){
		score+=10;
	    }
	    else {
		score+=0;
	    }
	}
	return score;
	
		
	
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
	System.out.println(a.addScore("DOG"));
        System.out.println(a.getWords());
	System.out.println(a.checkAllWords());
	//	System.out.println(checkWord("sghheu"));

    }

}
