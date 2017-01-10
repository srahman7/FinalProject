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
  

    public work(){
	randgen = new Random();
	player2Letters= new ArrayList<String>();
	LetterBag= new ArrayList<String>();
	helper= new ArrayList<String>();
	cool="hdjskslatjdkmclcllc";
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

    public String findWords(){
	int tries=0;
	String word= "";
	int count=10;
	int letterIndex=randgen.nextInt(count);


	while (tries < 50){
	    if (checkWord(word)){
		helper.add(word);
	    }
	    if (word.length()>4){
		word="";
	    }
	    word+=player2Letters.get(letterIndex);
	    letterIndex=randgen.nextInt(count);
	    tries++;

	}
	    
	return helper.toString();
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

    
    public String compInput(){
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
    }

	

	    
	    
	    
    
    public static void main(String[] args) {
	work a = new work();
	String b = "jjsskskkkskcolsjsokkksksk";
	System.out.println(b.contains("coole"));
	
	a.loadLetters();
	a.distributeLetters2();
	System.out.println(a.findWords());
	System.out.println(a.compInput());
	
    }
}
