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
    private ArrayList<Integer>pointsAllowed;
    private ArrayList<String> gridLetters;
    private ArrayList<String> checkWords;
    private ArrayList<Integer> colsAllowed;
    private ArrayList<Integer> rowsAllowed;
    private long startTime;
    private boolean timedMode;
    private boolean noWords;
    
    
    public Scrabble(){
	noWords= false;
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
	startTime = System.currentTimeMillis();
	score1=0;
	score2=0;
	turnNumber = 1;
	playerNumber = 1;
	randgen = new Random();
	lastWord = " ";
        data = new ArrayList<String>();
	timedMode = false;
	compWords = new ArrayList<String>();
	helper= new ArrayList<String>();
	compMode=false;
	playerType= "";
	pointsAllowed= new ArrayList<Integer>();
	gridLetters=new ArrayList<String>();
	checkWords= new ArrayList<String>();
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
	if (playerNumber == 1 || playerType.equals("1")){

	    int index = player1Letters.indexOf(letter);
	    if(index == -1){
		System.out.println("You do not have that letter.");
	    }

	    else{
		player1Letters.remove(player1Letters.indexOf(letter)); 
		grid[row -  1][col - 1] = new Letter(letter,(row-1),(col-1));
		System.out.print("\033[2J");
		System.out.println(toString());

		if (playerNumber==1){
		    System.out.println( "Current Player: " + playerNumber);
		    System.out.println("");
		    System.out.println("Player 1 Score: " + score1);
		    System.out.println("Player 2 Score: " +score2);
		}
		if (compMode){
		    System.out.println( "Current Player: " + playerType);
		    System.out.println("");
		    System.out.println("Player 1 Score: " + score1);
		    System.out.println("Computer Score: " +score2);
		}
		System.out.println("");
		System.out.println("Previous Word:");
		System.out.print(lastWord.substring(0, lastWord.length() - 1));
		System.out.println(" " + genScore(lastWord));
		System.out.println("");
		System.out.println(player1Letters.toString());
	    }
	}

	if(playerNumber == 2 || playerType.equals("Computer")){
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
		if (playerNumber==2){
		System.out.println("Player 2 Score: " +score2);
		}
		else {
		    System.out.println("Computer Score: " +score2);
		}
		System.out.println("");
		System.out.println("Previous Word:");
		System.out.print(lastWord.substring(0, lastWord.length() - 1));
		System.out.println(" " + genScore(lastWord));
		System.out.println("");
		System.out.println(player2Letters.toString());
	    }
	}
	newLetters.add(new Letter(letter,(row-1),(col-1)));

     
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
	for (int x =0; x < gridLetters.size();x++){
	    data.add(gridLetters.get(x));
	}
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

    public void getOldLetters(){
	    for (int x =0; x < newLetters.size();x++){
		gridLetters.add((newLetters.get(x)).getVal());
	    }
	
	    
	    //System.out.println(gridLetters.toString());
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

    public void checkLast(){
	String word="";
	boolean checked =false;
	int count=0;
	getOldLetters();
	for (int i =0; i < checkWords.size();i++){
	    word=checkWords.get(i);
	    for (int x =0; x+1<= word.length();x++){
		if (gridLetters.contains(word.substring(x,x+1))){
		    compWords.add(word);
		    break;
		}

		
	    }
	}
    }


       public boolean addWords(String word, int row, int col){
 	int determinant = randgen.nextInt(2);
 	int deltaX=0;
 	int deltaY=0;
 	// add vertical
 	if (determinant == 0){
 	    deltaX=1;
 	    deltaY=0;
 	}
 	// add horizontal
 	if (determinant == 1){
 	    deltaX=0;
 	    deltaY=1;
 	}

	

	boolean res = true;

	if (word.length()+row>=grid[0].length || row+word.length()*deltaX< 0 || word.length()*deltaY+col>=grid.length || col+word.length()*deltaY < 0 && !(word.substring(0,1).equals((grid[row][col]).getVal())) ){
	    return false;
	}

	else{
	    for (int x =0; x+1 <= word.length();x++){
		if ( !((grid[row + x * deltaX][col + x * deltaY]).getVal()).equals("_") && !((grid[row + x * deltaX][col + x * deltaY]).getVal()).equals(word.substring(x,x+1)) ){
		    return false;
		}
	    }
    
	    for (int i=0; i+1<= word.length(); i++){
		grid[row + i * deltaX][col + i * deltaY]=new Letter( word.substring(i,i+1),(row + i * deltaX),(col + i * deltaY) );
		newLetters.add(new Letter(word.substring(i,i+1),(row+i*deltaX),(col+ i * deltaY)));
	    }	
	    return true;
	}
			
	    
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
	int row =8;
	int col=8;
 	getOldLetters();
	findWords();
	checkHelper();
	sort();

	//System.out.println(helper.toString());
	//System.out.println(compWords.toString());
	//System.out.println("hello");
	//System.out.println("data " + data.toString());
	

	//int indexR = randgen.nextInt(rowsAllowed.size());
	//int indexC = randgen.nextInt(colsAllowed.size());
	int index = 0;

	
	if (compWords.size()==0){
	    System.out.println("No words to add!");

	    noWords=true;
	    tries=5000;
	}
	
	
	while (nothing && count < compWords.size()){
	
	

	//row=10;
	//col=10;

	/*System.out.println("row"+row);
	System.out.println("col"+col);
	System.out.println(player2Letters.toString());*/


	//System.out.println(checkWords.toString());


	word=compWords.get(count);
	while (nothing && tries<5){
	    System.out.println(tries);
	    index= randgen.nextInt(rowsAllowed.size());
	    row = rowsAllowed.get(index);
	    col = colsAllowed.get(index);
	    if ( addWords( word, row, col) ){
		    nothing =false;
		    //lastWord= word;
		    addScore2(word);
		    WordsAdded.add(word);
		    tries=5000;
		    //newLetters.clear();
		    gridLetters.clear();
		    for (int i = 0; i < newLetters.size(); i++){
			rowsAllowed.add( (newLetters.get(i)).getRow());
			colsAllowed.add ( (newLetters.get(i)).getCol());
			oldLetters.add(newLetters.get(i));
		    }
	
		    for (int i = newLetters.size() - 1; i > -1; i--){
			newLetters.remove(i);
		    }
		    rowsAllowed.remove(index);
		    colsAllowed.remove(index);

		    for (int x =0; x+1 <= word.length();x++){
			player2Letters.remove(word.substring(x,x+1));
			data.remove(word.substring(x,x+1));
		    }
	    }
	    else {tries++;
		index= randgen.nextInt(rowsAllowed.size());}
	}
	tries=0;
	count++;
	}
	if (nothing && count>= compWords.size()){
	    noWords=true;
	    System.out.println("The computer gave up!");
	}
	//System.out.println("rows allowed " + rowsAllowed.toString());
	//System.out.println(" cols allowed "+ colsAllowed.toString());
    }

    
    public void endTurn(){
	horWordsAdded();
	verWordsAdded();

	if(timedMode){

	long endTime = System.currentTimeMillis();

	if(endTime - startTime > 90){
	    System.out.println("You have exceeded the time limit.");
	    System.exit(1);
	}
	}
	
	//System.out.println(oldLetters.toString());
	//System.out.println(newLetters.toString());
	//System.out.println(checkAdjacent());
	 if (compMode){
	     if ( playerType.equals("1") && checkAllWordsAdded() && (checkNumWords() < 2) && checkAdjacent() ){
		    addScore1(wordAdded());
		    lastWord = wordAdded();
		    for (int x =0; x < newLetters.size();x++){
			int r = (newLetters.get(x)).getRow();
			int c = (newLetters.get(x)).getCol();
			/*if ( ((grid[r+1][c]).getVal()).equals("_") ){
			    pointsAllowed.add(r+1);
			    pointsAllowed.add(c);
			}
			if ( ((grid[r-1][c]).getVal()).equals("_") ){
			    pointsAllowed.add(r-1);
			    pointsAllowed.add(c);
			}
			if ( ((grid[r][c+1]).getVal()).equals("_") ){
			    pointsAllowed.add(r);
			    pointsAllowed.add(c+1);
			}
			if ( ((grid[r][c-1]).getVal()).equals("_") ){
			    pointsAllowed.add(r);
			    pointsAllowed.add(c-1);
			    }*/
			rowsAllowed.add(r);
			colsAllowed.add(c);
		    }
		    if (score1>=50){
			System.out.println("Player 1 has won!");
			System.exit(0);
		    }
		    startNewAITurn();
		    
		}

	     else{
		    compInput();
		    lastWord=wordAdded();
		    if (score2>=50){
			System.out.println("Computer has won");
			System.exit(0);
		    }
		    
		    startNewAITurn();
		}
	     
	 }
	if( checkAllWordsAdded() && checkAdjacent() && (checkNumWords() < 2) ){
	    // System.out.println("im here");
	   
	  

		if(playerNumber == 1){
		    addScore1(wordAdded());
		    lastWord = wordAdded();
		    if (score1>=50){
			System.out.println("Player 1 has won!");
			System.exit(0);
		    }
		}
		else{
		    addScore2(wordAdded());
		    lastWord= wordAdded();
		    if (score2>=50){
			System.out.println("Player 2 has won");
			System.exit(0);
		    }
		}

	    
		startNewTurn();
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

	
	if(timedMode){

	    startTime = System.currentTimeMillis();
	    }
	
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
	if (noWords){
	    System.out.println("The computer could not add any words!");
	}
	else{
	    System.out.print(wordAdded().substring(0, wordAdded().length()));
	}
	System.out.println(" " + genScore(wordAdded()));
	System.out.println("");
	


	for(int row = 0; row < oldGrid.length; row++){
	    for (int col = 0; col < oldGrid.length; col++){
		oldGrid[row][col] = grid[row][col];
	    }
	}
		           	
	Words.add(wordAdded());
	clearWordsAdded();
	clearWords();
	horWords();
	verWords();
	
	if(playerType.equals("1")){
	    for(int i = oldLetters1.size() - 1; i > -1; i--){
		oldLetters1.remove(i);
	    }
	    
	    distributeLetters1();
	    
	    for(int i = 0; i < player1Letters.size(); i++){
		oldLetters1.add(player1Letters.get(i));
	    }
	    System.out.println("PLEASE ENTER A LETTER FOLLOWED BY THE X-COORDINATE AND Y-COORDINATE: FORMMATED LIKE THIS- A 1 1");	    
	    userInput();
	    //System.out.println("hi");
	    
	}




	else{ //playerNumber2 is the computer
	    

	    distributeLetters2();
	    
	    endTurn();
	    

	}
	
    }
    

    public void modeInput(){
	System.out.print("\033[2J");
	System.out.println(toString());
	System.out.println("Mode? Select by typing \"2Player\" or \"Computer\"");
	while (scan.hasNext()){
	    String letter = scan.next();
	    if (letter.equals("2Player")){
		System.out.println("\"Normal\" or \"Timed\" ?");
		String mode2 = scan.next();
		if (mode2.equals("Timed")){
			timedMode = true;
		    }
	    }
	    if (letter.equals("Computer")){initializeAIGame();}
	    if (letter.equals("exit")){System.exit(1);}
	    else {initializeGame();}
	}
    }

    
    public void userInput(){
	//System.out.println("user input");
	/*System.out.println(checkAllWordsAdded());
	System.out.println(checkAdjacent());
	System.out.println(checkNumWords() < 2);
	System.out.println( "verdict " +  (checkAllWordsAdded() && checkAdjacent() && (checkNumWords() < 2)) );
	System.out.println(newLetters.toString());
	System.out.println(oldLetters.toString());*/

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
		System.out.println("hello");
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
