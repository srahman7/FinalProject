import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;//NEW STUFF!
import java.awt.event.*;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class work{
    private Random randgen;
    private ArrayList<String> LetterBag;
    private ArrayList<String> player2Letters;
    private ArrayList<String> helper;
    private String cool;
    private ArrayList<String> data;
    private ArrayList<String> compWords;
    private String[][]grid;
  

    public work(){
	grid = new String[15][15];
	clear();
	randgen = new Random();
	player2Letters= new ArrayList<String>();
	LetterBag= new ArrayList<String>();
	helper= new ArrayList<String>();
	cool="hdjskslatjdkmclcllc";
	data = new ArrayList<String>();
	compWords = new ArrayList<String>();
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

    public int key(String let){
	if ( let.equals("A") || let.equals("E") || let.equals("I") || let.equals("O") || let.equals("U") || let.equals("L") || let.equals("N") || let.equals("R") || let.equals("S") || let.equals("T") ){
	    return 1;
	}
	if ( let.equals("D") || let.equals("G") ){
	    return 2;
	}
	if ( let.equals("B") || let.equals("C") || let.equals("M") || let.equals("P") ){
	    return 3;
	}

	if ( let.equals("F") || let.equals("H") || let.equals("W") || let.equals("Y") || let.equals("V") ){
	    return 4;
	}
	if ( let.equals("K") ){
	    return 5;
	}
	if ( let.equals("J") || let.equals("X") ){
	    return 8;
	}
	else {
	    return 10;
	}
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

    
    public static boolean branchedWord(String word, String[] gridLetters){
	for (int x=0; x+1< word.length(); x++){
	    for (int i=0; i< gridLetters.length;i++){
		if (word.substring(x,x+1).equals(gridLetters[i])){
		    return true;
		}
	    }
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

    public boolean checkWord(String word){
	//word = word.substring(0,(word.length() - 1));
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
	System.out.println(compWords.toString());
    }
    public String findWords(){
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
	return helper.toString();
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
	System.out.println(compWords.toString());
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

    public void clear(){
	for(int row = 0; row < 15; row++){
	    for(int col = 0; col < 15; col++){
		grid[row][col] = "_";
	    }
	}
    }

    /* public void check(){
	for (int x =0; x < compWords.size();x++){
	    if (checkWord(compWords.get(x))==false){
		compWords.remove(compWords.get(x));
	    }
	}
	System.out.println(compWords.toString());
	}*/
    
    /* public String compInput(){
	int score=0;
	int score1=0;
	String word="";
	if (helper.size()>1){
	    for (int i=0; i<helper.size();i++){
		score=genScore(helper.get(i));
		for (int x = i+1; x < helper.size(); x++){
		    score1=genScore(helper.get(x));
		    if (score<score1){
			word=helper.get(x);
		    }
		    else{word=helper.get(i);}
		}
	    }
	}
	else{ word=helper.get(0);}
	return word;
	}*/

   

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
	/*System.out.println("determinant: " + determinant);
	  System.out.println("Y: " +deltaY);
	  System.out.println("X: " + deltaX);
	  System.out.println("can I add ? " + (row + word.length() * deltaX));
	  System.out.println("can I add this? " + (col + word.length()*deltaY));*/
	if ((word.length()+row>=grid[0].length || row+word.length()*deltaX< 0 || word.length()*deltaY+col>=grid.length || col+word.length()*deltaY < 0) || grid[row][col]!="_"&& grid[row][col]!=word.substring(0,1)){
	    return false;
	}
	else {
	    for (int x =0; x+1 <= word.length();x++){
		if (grid[row + x * deltaX][col + x * deltaY]!="_" && grid[row + x * deltaX][col + x * deltaY] != word.substring(x,x+1)){
		    return false;
		}
	    }
	    for (int i=0; i+1<= word.length(); i++){
		grid[row + i * deltaX][col + i * deltaY]=word.substring(i,i+1);
	    }	
	    return true;
	}
	
	    
		    
		
	    
    }
	

	    
	    
	    
    
    public static void main(String[] args) {
	work a = new work();

	ArrayList<String> c= new ArrayList<String>();
	c.add("A");
	c.add("G");
	c.add("E");
	c.add("Z");
	System.out.println((c.clone()).toString());
	
	a.loadLetters();
	a.distributeLetters2();
	a.sort();
	a.findWords();
	a.checkHelper();
	a.sort();
	a.addWords("hello",6,6);
	System.out.println(a.toString());
	//System.out.println(a.key("X"));
	//System.out.println(a.compInput());
	
    }
}
