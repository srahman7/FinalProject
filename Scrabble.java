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
    private  ArrayList<String> Words;
    private  ArrayList<String> WordsAdded;
    private Container pane;
    private JButton submit;
    private String[] letterBag;
    private int score1;
    private int score2;
    
   
    
    public Scrabble(){
	grid = new String[15][15];
	clear();
	letterCount = 0;
	wordCount = 0;
	start = true;
	Words = new ArrayList<String>();
	WordsAdded= new ArrayList<String>();
	score1=0;
	score2=0;
	//submit.addActionListener(this);
	//submit.setActionCommand(subWord();
	
	
	
    }

    public void clear(){
	for(int row = 0; row < 15; row++){
	    for(int col = 0; col < 15; col++){
		grid[row][col] = "_";
	    }
	}
    }

    public void clearWords(){
	Words.clear();
    }

    public void clearWordsAdded(){
	WordsAdded.clear();
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

	int id = 0;
	
	for(String word: hor.split("\\_")){
	    if(word.length() > 1){
	    Words.add(word + id);
	    id++;
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

	char id = 'a';
	
	for(String word: ver.split("\\_")){
	    if(word.length() > 1){
		Words.add(word + id);
		id++;
	    }
	}
    }
    public void horWordsAdded(){

	String hor = "";
	
	for(int row = 0; row < 15; row++){
	    for(int col = 0; col < 15; col++){
		hor += grid[row][col];
	    }
	}

	int id = 0;
	
	for(String word: hor.split("\\_")){
	    if(word.length() > 1){
	    WordsAdded.add(word+id);
	    id++;
	    }
	}
    }
    
 
    public void verWordsAdded(){
	String ver = "";
	
	for(int col = 0; col < 15; col++){
	    for(int row = 0; row < 15; row++){
		ver += grid[row][col];
	    }
	}

	char id = 'a';
	
	for(String word: ver.split("\\_")){
	    if(word.length() > 1){
		WordsAdded.add(word + id);
		id++;
	    }
	}
    }

    public String wordAdded(){
	boolean check = false;
	for (int x = 0; x < WordsAdded.size(); x++){
	    int lastCheck = 0;
	    for (int i=0; i <Words.size(); i++){
		if( !(WordsAdded.get(x)).equals(Words.get(i)) ){
		    check = false;
		    lastCheck++;
		}
		else{
		    check=true;
		    lastCheck=0;
		}
	    }
	    if (check == false && lastCheck==Words.size()){
		return WordsAdded.get(x);
	    }
	}
	return "Word not added";
    }

    public String wordAddedXtra(){
	return WordsAdded.get(WordsAdded.size()-1);
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
	word = word.substring(0,(word.length() - 1));
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

    public int addScore1(String word){
	for (int x =0; x+1 <= word.length();x++){
	    if ( (word.substring(x,x+1)).equals("A") || (word.substring(x,x+1)).equals("E") || (word.substring(x,x+1)).equals("I") || (word.substring(x,x+1)).equals("O") || (word.substring(x,x+1)).equals("U") || (word.substring(x,x+1)).equals("L") || (word.substring(x,x+1)).equals("N") || (word.substring(x,x+1)).equals("R") || (word.substring(x,x+1)).equals("S") || (word.substring(x,x+1)).equals("T") ){
		score1++;
	    }
	    if ( (word.substring(x,x+1)).equals("D") || (word.substring(x,x+1)).equals("G") ){
		score1+=2;
	    }
	    if ( (word.substring(x,x+1)).equals("B") || (word.substring(x,x+1)).equals("C") || (word.substring(x,x+1)).equals("M") || (word.substring(x,x+1)).equals("P")  ){
		score1+=3;
	    }
	    if ( (word.substring(x,x+1)).equals("F") || (word.substring(x,x+1)).equals("H") || (word.substring(x,x+1)).equals("W") || (word.substring(x,x+1)).equals("Y") || (word.substring(x,x+1)).equals("V") ){
		score1+=4;
	    }
	    if ( (word.substring(x,x+1)).equals("K") ){
		score1+=5;
	    }
	    if ( (word.substring(x,x+1)).equals("J") || (word.substring(x,x+1)).equals("X") ){
		score1+=8;
	    }
	    if ( (word.substring(x,x+1)).equals("Q") || (word.substring(x,x+1)).equals("Z") ){
		score1+=10;
	    }
	    else {
		score1+=0;
	    }
	}
	return score1;
	
		
	
    }
        public int addScore2(String word){
	for (int x =0; x+1 <= word.length();x++){
	    if ( (word.substring(x,x+1)).equals("A") || (word.substring(x,x+1)).equals("E") || (word.substring(x,x+1)).equals("I") || (word.substring(x,x+1)).equals("O") || (word.substring(x,x+1)).equals("U") || (word.substring(x,x+1)).equals("L") || (word.substring(x,x+1)).equals("N") || (word.substring(x,x+1)).equals("R") || (word.substring(x,x+1)).equals("S") || (word.substring(x,x+1)).equals("T") ){
		score2++;
	    }
	    if ( (word.substring(x,x+1)).equals("D") || (word.substring(x,x+1)).equals("G") ){
		score2+=2;
	    }
	    if ( (word.substring(x,x+1)).equals("B") || (word.substring(x,x+1)).equals("C") || (word.substring(x,x+1)).equals("M") || (word.substring(x,x+1)).equals("P")  ){
		score2+=3;
	    }
	    if ( (word.substring(x,x+1)).equals("F") || (word.substring(x,x+1)).equals("H") || (word.substring(x,x+1)).equals("W") || (word.substring(x,x+1)).equals("Y") || (word.substring(x,x+1)).equals("V") ){
		score2+=4;
	    }
	    if ( (word.substring(x,x+1)).equals("K") ){
		score2+=5;
	    }
	    if ( (word.substring(x,x+1)).equals("J") || (word.substring(x,x+1)).equals("X") ){
		score2+=8;
	    }
	    if ( (word.substring(x,x+1)).equals("Q") || (word.substring(x,x+1)).equals("Z") ){
		score2+=10;
	    }
	    else {
		score2+=0;
	    }
	}
	return score2;
	
		
	
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

   
    public String getWordsAdded(){
	return (WordsAdded.toString());
    }

    public void endTurn(){
	if(checkAllWords()){
	    startNewTurn(); 
	}
	else{System.out.println("error " + Words);}
    }
	     
    public void startNewTurn(){
	horWordsAdded();
	verWordsAdded();
	System.out.println(getWordsAdded());
	System.out.println(wordAdded());
	Words.add(wordAdded());
	clearWordsAdded();
	clearWords();
	horWords();
	verWords();
    }

    
    public static void main(String[] args){
	
	Scrabble a = new Scrabble();
	a.addLetter("D", 8, 8);
	a.addLetter("O", 8, 9);
	a.addLetter("G", 8, 10);
	System.out.println(a.toString());
	a.endTurn();
	a.addLetter("E", 9, 8);
	a.addLetter("A", 10, 8);
	a.addLetter("R", 11, 8);
	System.out.println(a.toString());
	a.endTurn();
	a.addLetter("E", 11, 9);
	a.addLetter("A", 11, 10);
	a.addLetter("D", 11, 11);
	System.out.println(a.toString());
	a.endTurn();
    }

}
