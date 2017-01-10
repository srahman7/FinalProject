import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;//NEW STUFF!
import java.awt.event.*;

public class Scrabble {
    private String[][]grid;
    private String[][]oldGrid;
    private boolean start;
    private ArrayList<String> Words;
    private ArrayList<String> WordsAdded;
    private ArrayList<String> gridLetters;
    private Container pane;
    private JButton submit;
    private ArrayList<String> LetterBag;
    private ArrayList<String> player1Letters;
    private ArrayList<String> player2Letters;
    private ArrayList<String> oldLetters1;
    private ArrayList<String> oldLetters2;
    private int score1;
    private int score2;
    private int turnNumber;
    private int playerNumber;
    private Random randgen;
    private String lastWord;
   
    
    public Scrabble(){
	grid = new String[15][15];
	oldGrid = new String[15][15];
	clear();
	start = true;
	gridLetters= new ArrayList<String>();
	Words = new ArrayList<String>();
	WordsAdded= new ArrayList<String>();
	LetterBag= new ArrayList<String>();
	player1Letters= new ArrayList<String>();
	player2Letters= new ArrayList<String>();
	oldLetters1= new ArrayList<String>();
	oldLetters2= new ArrayList<String>();
	score1=0;
	score2=0;
	turnNumber = 1;
	playerNumber = 1;
	randgen = new Random();
	lastWord = " ";
	//submit.addActionListener(this);
	//submit.setActionCommand(subWord();
	
	
	
    }

    public void loadLetters(){
	try{
	    Scanner bag = new Scanner(new File("LetterBag.txt"));

	    while (bag.hasNext()) {
		LetterBag.add(bag.next());
	    }
	}
	catch(FileNotFoundException e){
	    System.out.println("LetterBag file not found.");
	    System.exit(1);
	}
    }

    public void distributeLetters1(){  
	while(player1Letters.size() < 10){
	    int letterIndex = randgen.nextInt(LetterBag.size());
	    player1Letters.add(LetterBag.get(letterIndex));
	    LetterBag.remove(letterIndex);
	}
	System.out.println(player1Letters.toString());    	    
    }

    public void distributeLetters2(){
	while(player2Letters.size() < 10){
	    int letterIndex = randgen.nextInt(LetterBag.size());
	    player2Letters.add(LetterBag.get(letterIndex));
	    LetterBag.remove(letterIndex);
	}
	System.out.println(player2Letters.toString());    
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
	if (playerNumber == 1){
	    int index = player1Letters.indexOf(letter);
	    if(index == -1){
		System.out.println("You do not have that letter.");
	    }
	    else{
		player1Letters.remove(player1Letters.indexOf(letter)); 
		grid[row -  1][col - 1] = letter;
		System.out.print("\033[2J");
		System.out.println(toString());
		System.out.println( "Current Player: " + playerNumber);
		System.out.println("");
		System.out.println("Player 1 Score: " + score1);
		System.out.println("Player 2 Score: " +score2);
		System.out.println("");
		System.out.println("Previous Word:");
		System.out.print(lastWord.substring(0, lastWord.length() - 1));
		System.out.println(" " + genScore(lastWord));
		System.out.println("");
		System.out.println(player1Letters.toString());
	    }
	}

	if(playerNumber == 2){
	    int index = player2Letters.indexOf(letter);
	    if(index == -1){
		System.out.println("You do not have that letter.");
	    }
	    else{
		player2Letters.remove(player2Letters.indexOf(letter)); 
		grid[row -  1][col - 1] = letter;
		System.out.print("\033[2J");
		System.out.println(toString());
		System.out.println( "Current Player: " + playerNumber);
		System.out.println("");
		System.out.println("Player 1 Score: " + score1);
		System.out.println("Player 2 Score: " +score2);
		System.out.println("");
		System.out.println("Previous Word:");
		System.out.print(lastWord.substring(0, lastWord.length() - 1));
		System.out.println(" " + genScore(lastWord));
		System.out.println("");
		System.out.println(player2Letters.toString());
	    }
	}
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
	return "word not added ";
    }

    public String wordAddedXtra(){
	return WordsAdded.get(WordsAdded.size()-1);
    }

    public boolean checkAllWordsAdded(){
	for(int i = 0; i < WordsAdded.size(); i++){
	    if(checkWord(WordsAdded.get(i)) == false){
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

     public int genScore(String word){
	 int score = 0;
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
	System.out.println("   1  2  3  4  5  6  7  8  9 10 11 12 13 14 15");
	String str = "1 [";
	
	for (int i = 0; i < grid.length; i++) {
	    
	    for (int j = 0; j < grid[i].length; j++) {
		str += grid[i][j];
		str += "| ";
	    }
	    
	    str += "\b" + "\b" + "]";
	    str += "\n";

	    if (i < grid.length - 1) {

		if(i < 8){
	      	str += (i + 2) + " [";
		}
		else{
		    str += (i + 2) + "[";
		}
	    }
	}

	return str;
    }


    public String toStringOld() {
	System.out.println("   1  2  3  4  5  6  7  8  9 10 11 12 13 14 15");
	String str = "1 [";
	
	for (int i = 0; i < oldGrid.length; i++) {
	    
	    for (int j = 0; j < oldGrid[i].length; j++) {
		str += oldGrid[i][j];
		str += "| ";
	    }
	    
	    str += "\b" + "\b" + "]";
	    str += "\n";

	    if (i < oldGrid.length - 1) {

		if(i < 8){
	      	str += (i + 2) + " [";
		}
		else{
		    str += (i + 2) + "[";
		}
	    }
	}

	return str;
    }

    public String getWords(){
	return (Words.toString());
    }

    public boolean branchedWord(String word){
	for (int x=0; x+1< word.length(); x++){
	    for (int i=0; i< gridLetters.size();i++){
		if (word.substring(x,x+1).equals(gridLetters.get(i))){
		    return true;
		}
	    }
	}
	return false;
    }
   
    public String getWordsAdded(){
	return (WordsAdded.toString());
    }

    public void endTurn(){
	horWordsAdded();
	verWordsAdded();
	if(checkAllWordsAdded() ){
	     
	    System.out.println(wordAdded());
	    System.out.println(gridLetters.toString());
	    System.out.println(branchedWord(wordAdded())==false && turnNumber!=1);

	    if (turnNumber!=1 && branchedWord(wordAdded())==false){
		
		WordsAdded.remove(WordsAdded.size() - 1);

		System.out.print("\033[2J");
		System.out.println(toStringOld());
		System.out.println( "Current Player: " + playerNumber);
		System.out.println("");
		System.out.println("Player 1 Score: " + score1);
		System.out.println("Player 2 Score: " +score2);
		System.out.println("");
		System.out.println("Previous Word:");
		System.out.print(wordAdded().substring(0, wordAdded().length() - 1));
		System.out.println(" " + genScore(wordAdded()));
		System.out.println("");

		if (playerNumber == 1){
		
		    for(int i = player1Letters.size() - 1; i > -1; i--){
			player1Letters.remove(i);
		    }
		
		    for(int i = 0; i < oldLetters1.size(); i++){
			player1Letters.add(oldLetters1.get(i));
		    }
		    System.out.println(player1Letters.toString());
		}
		else{
		
		    for(int i = player2Letters.size() - 1; i > -1; i--){
			player2Letters.remove(i);
		    }

		    for(int i = 0; i < oldLetters2.size(); i++){
			player2Letters.add(oldLetters2.get(i));
		    }
		    System.out.println(player2Letters.toString());
		}
     
		System.out.println("");
	    
		System.out.println("You did not build off of existing letters!");
		userInput();

		
	    }

	    else{

		if(playerNumber == 1){
		    addScore1(wordAdded());
		    lastWord = wordAdded();

		}
		else{addScore2(wordAdded());}
			    	    
		for (int x=0;x+1<wordAdded().length();x++){
		    gridLetters.add(wordAdded().substring(x,x+1));
		}

	    
		startNewTurn();
	    }
	}
	else{

	    WordsAdded.remove(WordsAdded.size() - 1);

	    System.out.print("\033[2J");
	    System.out.println(toStringOld());
	    System.out.println( "Current Player: " + playerNumber);
	    System.out.println("");
	    System.out.println("Player 1 Score: " + score1);
	    System.out.println("Player 2 Score: " +score2);
	    System.out.println("");
	    System.out.println("Previous Word:");
	    System.out.print(wordAdded().substring(0, wordAdded().length() - 1));
	    System.out.println(" " + genScore(wordAdded()));
	    System.out.println("");

	    if (playerNumber == 1){
		
		for(int i = player1Letters.size() - 1; i > -1; i--){
		    player1Letters.remove(i);
		}
		
		for(int i = 0; i < oldLetters1.size(); i++){
		    player1Letters.add(oldLetters1.get(i));
		}
		System.out.println(player1Letters.toString());
	    }
	    else{
		
		for(int i = player2Letters.size() - 1; i > -1; i--){
		    player2Letters.remove(i);
		}

		for(int i = 0; i < oldLetters2.size(); i++){
		    player2Letters.add(oldLetters2.get(i));
		}
		 System.out.println(player2Letters.toString());
	    }
     
	    System.out.println("");
	    System.out.println("The word you have submitted is not valid.");
	    userInput();
	}
    }
	     
    public void startNewTurn(){
	if(turnNumber % 2 == 1){
	    playerNumber = 2;}
	else{playerNumber = 1;}

	turnNumber++;
		
	System.out.print("\033[2J");
	System.out.println(toString());
	System.out.println( "Current Player: " + playerNumber);
	System.out.println("");
	System.out.println("Player 1 Score: " + score1);
	System.out.println("Player 2 Score: " +score2);
	System.out.println("");
	System.out.println("Previous Word:");
	System.out.print(wordAdded().substring(0, wordAdded().length() - 1));
	System.out.println(" " + genScore(wordAdded()));
	System.out.println("");


	for(int row = 0; row < oldGrid.length; row++){
	    for (int col = 0; col < oldGrid.length; col++){
		oldGrid[row][col] = grid[row][col];
	    }
	}
	
	if(playerNumber == 1){
	    
	    for(int i = oldLetters1.size() - 1; i > -1; i--){
		oldLetters1.remove(i);
	    }
	    
	    distributeLetters1();
	    
	    for(int i = 0; i < player1Letters.size(); i++){
		oldLetters1.add(player1Letters.get(i));
	    }
	    
	}
	else{
	    
	    for(int i = oldLetters2.size() - 1; i > -1; i--){
		oldLetters2.remove(i);
	    }
	    
	    distributeLetters2();
	    
	    for(int i = 0; i < player2Letters.size(); i++){
	        oldLetters2.add(player2Letters.get(i));
	    }
	    
	}
	
	Words.add(wordAdded());
	clearWordsAdded();
	clearWords();
	horWords();
	verWords();
	System.out.println("PLEASE ENTER A LETTER FOLLOWED BY THE X-COORDINATE AND Y-COORDINATE: FORMMATED LIKE THIS- A 1 1");
	userInput();
	
    }

    public void initializeGame(){
	System.out.print("\033[2J");
	System.out.println(toString());
	System.out.println( "Current Player: " + playerNumber);
	System.out.println("");
	System.out.println("Player 1 Score: " + score1);
	System.out.println("Player 2 Score: " + score2);
	System.out.println("");
	loadLetters();
	distributeLetters1();
	System.out.println("PLEASE ENTER A LETTER FOLLOWED BY THE X-COORDINATE AND Y-COORDINATE: FORMMATED LIKE THIS- A 1 1");
	userInput();
	
    }

    public void userInput(){

	while(scan.hasNext()){
	    String letter = scan.next();
	    if (letter.equals("submit")){ break;}
 	    int row = Integer.parseInt(scan.next());
	    int col = Integer.parseInt(scan.next());
	    addLetter(letter, col, row);
	}

	endTurn();
    }

    static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args){
	
	Scrabble a = new Scrabble();
	a.initializeGame();

    }

}
