import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;//NEW STUFF!
import java.awt.event.*;

public class Scrabble {
    private Letter[][]grid;
    private Letter[][]oldGrid;
    private boolean start;
    private ArrayList<String> Words;
    private ArrayList<String> WordsAdded;
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
    private ArrayList<Letter> oldLetters;
    private ArrayList<Letter> newLetters;
    private ArrayList<String> data;
    private ArrayList<String> compWords;
    private ArrayList<String> helper;
    private boolean compMode;
    private String playerType;
    private ArrayList<Integer> rowsAllowed;
    private ArrayList<Integer> colsAllowed;
    
    
    public Scrabble(){
	grid = new Letter[15][15];
	oldGrid = new Letter[15][15];
	clear();
	clearOld();
	start = true;
	Words = new ArrayList<String>();
	WordsAdded= new ArrayList<String>();
	LetterBag= new ArrayList<String>();
	player1Letters= new ArrayList<String>();
	player2Letters= new ArrayList<String>();
	oldLetters1= new ArrayList<String>();
	oldLetters2= new ArrayList<String>();
	oldLetters= new ArrayList<Letter>();
	newLetters= new ArrayList<Letter>();
	Letter center = new Letter("_",8,8);
	oldLetters.add(center);
	score1=0;
	score2=0;
	turnNumber = 1;
	playerNumber = 1;
	randgen = new Random();
	lastWord = " ";
        data = new ArrayList<String>();
	compWords = new ArrayList<String>();
	helper= new ArrayList<String>();
	compMode=false;
	playerType= "";
	rowsAllowed= new ArrayList<Integer>();
	colsAllowed = new ArrayList<Integer>();
	
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


  
    public void clear(){
	for(int row = 0; row < 15; row++){
	    for(int col = 0; col < 15; col++){
		grid[row][col] = new Letter("_",(row+1),(col+1));
	    }
	}
    }

    public void clearOld(){
	for(int row = 0; row < 15; row++){
	    for(int col = 0; col < 15; col++){
		oldGrid[row][col] = new Letter("_",(row+1),(col+1));
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
		grid[row -  1][col - 1] = new Letter(letter,(row-1),(col-1));
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
		grid[row -  1][col - 1] = new Letter(letter,(row-1),(col-1));
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
	newLetters.add(new Letter(letter,(row-1),(col-1)));
	rowsAllowed.add(row-2);
	colsAllowed.add(col-2);
	rowsAllowed.add(row);
	colsAllowed.add(col);
     
	/*newLetters.add(new Letter(letter,(row+1),col));
	newLetters.add(new Letter(letter,row,(col-1)));
	newLetters.add(new Letter(letter,row,(col+1)));*/
	
    }



    public void horWords(){
	String hor = "";
	
	for(int row = 0; row < 15; row++){
	    for(int col = 0; col < 15; col++){
		hor += (grid[row][col]).getVal();
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
		ver += (grid[row][col]).getVal();
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
		hor += (grid[row][col]).getVal();
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
		ver += (grid[row][col]).getVal();
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
    public void distributeLetters2(){
	while(player2Letters.size() < 10){
	    int letterIndex = randgen.nextInt(LetterBag.size());
	    player2Letters.add(LetterBag.get(letterIndex));
	    data.add(LetterBag.get(letterIndex));
	    LetterBag.remove(letterIndex);
	}
	System.out.println(player2Letters.toString());
    }


    public void sort(){
	String big="";
	String smaller="";
	int index=0;
	for (int x=0; x< compWords.size()-1; x++){
	    big = compWords.get(x);
	    index=x;
	    for (int i=x+1; i<compWords.size();i++){
		if ( genScore(compWords.get(i)) > genScore(big) ){
		    big = compWords.get(i);
		    index=i;
		}
	    }
	    
	    compWords.set(index,compWords.get(x));
	    compWords.set(x,big);
	    
	}
	//System.out.println(compWords.toString());
    }
    public void findWords(){
	String word="";
	String w="";
	int count=0;
	try{
	    Scanner dict = new Scanner(new File("dictionary.txt"));
	    while(dict.hasNext()) {
		w=dict.next();
	        for (int x =0; x+1 <= w.length();x++){
		    if (data.contains(w.substring(x,x+1))){
			word+=w.substring(x,x+1);
			//System.out.println(word);
			//data.remove(data.indexOf(w.substring(x,x+1)));
		    }
		    else{
			word="";
			break;
		    }
		    /*if (checkWord(word)){
		      helper.add(word);
		      }*/
			
		}
		if (word!=""){
		    helper.add(word);
		}
	    }
	}
	    catch(FileNotFoundException e){
		System.out.println("File not found.");
		System.exit(1);
	    }
	//return helper.toString();
    }

    public void checkHelper(){
	int check=0;
	String word="";
	ArrayList<String> temp= new ArrayList<String>();
	for (int x=0; x< helper.size();x++){
	    check=0;
	    temp.clear();
	    temp.addAll(data);
	    //System.out.println(temp.toString());
	    word=helper.get(x);
	    for (int i=0;i+1<=word.length();i++){
		if (temp.contains(word.substring(i,i+1))){
		    temp.remove(word.substring(i,i+1));
		    check++;
		}
		
	    }
	    
	    //System.out.println(check);
	    if (check==word.length()){
		compWords.add(word);
	    }
		    
	}
	//System.out.println(compWords.toString());
    }


       public boolean addWords(String word, int row, int col){
	int determinant = randgen.nextInt(4);
	int deltaX=0;
	int deltaY=0;
	// add vertical
	if (determinant == 0){
	    deltaX=1;
	    deltaY=0;
	}
	// add reverse vertical
	if (determinant == 1){
	    deltaX=-1;
	    deltaY=0;
	}
	// add horizontal
	if (determinant == 2){
	    deltaX=0;
	    deltaY=1;
	}
	//add reverse horizontal 
	if (determinant == 3){
	    deltaX=0;
	    deltaY=-1;
	}

	boolean res = true;

	if (word.length()+row>=grid[0].length || row+word.length()*deltaX< 0 || word.length()*deltaY+col>=grid.length || col+word.length()*deltaY < 0){
	    return false;
	}
	for (int i=0; i+1<= word.length(); i++){
	    grid[row + i * deltaX][col + i * deltaY]=new Letter( word.substring(i,i+1),(row + i * deltaX),(col + i * deltaY) );
	}	
	return true;
			
	    
       }

	


  
    public boolean checkAdjacent(){
	boolean result = false;

	for (int j = 0; j < oldLetters.size(); j++){
	for (int i = 0; i < newLetters.size(); i++){
	    if ((newLetters.get(i)).compareTo(oldLetters.get(j)) != -1)
		{result = true;}
	}
	}
	return result;
    }	

    public int checkNumWords(){
	int num = 0;
	num = WordsAdded.size() - Words.size();
	return num;
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
		str += (grid[i][j]).getVal();
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
		str += (oldGrid[i][j]).getVal();
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

   
    public String getWordsAdded(){
	return (WordsAdded.toString());
    }

    public void compInput(){
	int count=0;
	boolean nothing=true;
	int tries=0;
	String word="";

	int indexR = randgen.nextInt(rowsAllowed.size());
	int indexC = randgen.nextInt(colsAllowed.size());
	int row= randgen.nextInt(15);
	int col= randgen.nextInt(15);

	findWords();
	checkHelper();
	sort();
	
	if (compWords.size()==0){
	    System.out.println("No words to add!");
	    tries=1000;
	}

	while (nothing && count<compWords.size()){
	    word=compWords.get(count);
	    while (nothing && tries<20){
		System.out.println(tries);
		if ( addWords( word, row, col) ){
		    nothing =false;
		    lastWord= word;
		    addScore2(word);
		    Words.add(word);
		    tries=1000;

		    for (int x =0; x+1 <= word.length();x++){
			player2Letters.remove(word.substring(x,x+1));
		    }
		}
		else {tries++;}
	    }
	    tries=0;
	}
	if (nothing){
	    System.out.println("The computer gave up!");
	}

    }

    
    public void endTurn(){
	horWordsAdded();
	verWordsAdded();

	if(checkAllWordsAdded() && checkAdjacent() && (checkNumWords() < 2)){

	    if (compMode){
		if (playerType.equals("1")){
		    addScore1(wordAdded());
		    lastWord = wordAdded();
		    startNewAITurn();
		}
		else{
		    compInput();
		    startNewAITurn();
		}
	    }
	    else{

		if(playerNumber == 1){
		    addScore1(wordAdded());
		    lastWord = wordAdded();
		}
		else{addScore2(wordAdded());}

	    
		startNewTurn();
	    }
	}

	    
	else{
	    
	    
	    clearWordsAdded();
	 
	    for(int i = newLetters.size() - 1; i > -1; i--){
		    newLetters.remove(i);
	    }

	    

	    System.out.print("\033[2J");

	    for(int row = 0; row < 15; row++){
		for(int col = 0; col < 15; col++){
		    grid[row][col] = oldGrid[row][col];
		}
	    }	    
	    System.out.println(toString());
	    System.out.println( "Current Player: " + playerNumber);
	    System.out.println("");
	    System.out.println("Player 1 Score: " + score1);
	    System.out.println("Player 2 Score: " +score2);
	    System.out.println("");
	    System.out.println("Previous Word:");
	    System.out.print(lastWord.substring(0, lastWord.length()));
	    System.out.println("");

	    if (playerNumber == 1 || playerType.equals("1")){
		
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
	
	for (int i = 0; i < newLetters.size(); i++){
	    oldLetters.add(newLetters.get(i));
	}
	
	for (int i = newLetters.size() - 1; i > -1; i--){
	    newLetters.remove(i);
	}
	
	    	
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
	System.out.println("PLEASE ENTER A LETTER FOLLOWED BY THE X-COORDINATE AND Y-COORDINATE: FORMMATTED LIKE THIS- A 1 1");
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
	for(int i = 0; i < player1Letters.size(); i++){
	    oldLetters1.add(player1Letters.get(i));
	}
	
	System.out.println("PLEASE ENTER A LETTER FOLLOWED BY THE X-COORDINATE AND Y-COORDINATE: FORMATTED LIKE THIS- A 1 1");
	userInput();
	
    }

    public void initializeAIGame(){
	compMode=true;
	playerType= "1";
	System.out.print("\033[2J");
	System.out.println(toString());
	System.out.println( "Current Player: " + playerType);
	System.out.println("");
	System.out.println("Player 1 Score: " + score1);
	System.out.println("Computer Score: " + score2);
	System.out.println("");
	loadLetters();
	distributeLetters1();
	System.out.println("PLEASE ENTER A LETTER FOLLOWED BY THE X-COORDINATE AND Y-COORDINATE: FORMMATED LIKE THIS- A 1 1");
	userInput();
	
    }


    public void startNewAITurn(){
	if(turnNumber % 2 == 1){
	    playerType = "Computer";}
	else{playerType = "1";}

	turnNumber++;
		
	System.out.print("\033[2J");
	System.out.println(toString());
	System.out.println( "Current Player: " + playerType);
	System.out.println("");
	System.out.println("Player 1 Score: " + score1);
	System.out.println("Computer Score: " +score2);
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
	
	if(playerType.equals("1")){
	    for(int i = oldLetters1.size() - 1; i > -1; i--){
		oldLetters1.remove(i);
	    }
	    
	    distributeLetters1();
	    
	    for(int i = 0; i < player1Letters.size(); i++){
		oldLetters1.add(player1Letters.get(i));
	    }
	    
	}


	else{ //playerNumber2 is the computer
	    

	    distributeLetters2();
	    
           	
	    Words.add(wordAdded());
	    clearWordsAdded();
	    clearWords();
	    horWords();
	    verWords();
	    endTurn();
	    

	}
	
    }
    

    public void modeInput(){
	System.out.print("\033[2J");
	System.out.println(toString());
	System.out.println("Mode? Select by typing \"2Player\" or \"Computer\"");
	while (scan.hasNext()){
	    String letter = scan.next();
	    if (letter.equals("Computer")){initializeAIGame();}
	    if (letter.equals("exit")){System.exit(1);}
	    else {initializeGame();}
	}
    }

    
    public void userInput(){

	while(scan.hasNext()){
	    String letter = scan.next();
	    if (letter.equals("submit")){ break;}
	    if (letter.equals("exit")){System.exit(1);}
 	    int row = Integer.parseInt(scan.next());
	    int col = Integer.parseInt(scan.next());
	    	    	    	
	    if (turnNumber==1 && start && (row!=8 || col!=8)){
		System.out.println("You must make the first word in the center!");
	    }
	    else{

		addLetter(letter, col, row);
		start=false;
	    }
	}


	//System.out.println("compmode is " + compMode);
	endTurn();
    }
    
    static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args){
	
	Scrabble a = new Scrabble();
	a.modeInput();

    }

}
