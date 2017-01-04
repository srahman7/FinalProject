import java.util.*;
import java.io.*;

public class WordSearch {
    private char[][]data;
    private ArrayList<String> wordsToAdd;
    private ArrayList<String> wordsAdded;
    private ArrayList<String> displayWords;
    private Random randgen;
    private long seed;

    public WordSearch(int rows, int cols) {
	data = new char[rows][cols];
	this.clear();
	wordsToAdd = new ArrayList<String>();
	wordsAdded = new ArrayList<String>();
        displayWords = new ArrayList<String>();
	seed = (long)(Math.random() * 10000);
	randgen = new Random(seed);
    }

    public WordSearch(int rows, int cols, int id) {
	data = new char[rows][cols];
	this.clear();
	wordsToAdd = new ArrayList<String>();
	wordsAdded = new ArrayList<String>();
	displayWords = new ArrayList<String>();
	seed = id;
	randgen = new Random(id);
    }

    public void printWordList() {
	System.out.println("Word Bank:");
        for (int i = 0; i < displayWords.size(); i++){
	    System.out.println(displayWords.get(i));
	}
	System.out.println(" ");
    }

    private void clear() {
	for (int i = 0; i < data.length; i++) {

	    for (int j = 0; j < data[i].length; j++) {
		data[i][j] = '_';
	    }
	}
    }

    public void loadWords(String fileName) {
	try{
	Scanner in = new Scanner(new File(fileName));

	while(in.hasNext()) {
	    wordsToAdd.add(in.next());
	}
	}
	catch(FileNotFoundException e){
	    System.out.println("File not found.");
	    System.exit(1);
	    }

	
    }


     public String toString() {
	String str = "[";

	for (int i = 0; i < data.length; i++) {

	    for (int j = 0; j < data[i].length; j++) {
		str += data[i][j];
		str += " ";
	    }

	    str += "]";
	    str += "\n";

	    if (i < data.length - 1) {
	    str += "[";
	    }
	}

	return str;
    }


    public void fillWithWords() {

	while (wordsToAdd.isEmpty() == false) {
	    int i = 0;

	    while (i < 100){

	    if(
	    addWord(
		    wordsToAdd.get(0),
		    randgen.nextInt(data.length),
		    randgen.nextInt(data[0].length),
		    randgen.nextInt(4))
	    == false)
		{i += 1;}
	    else{
		String original = wordsToAdd.get(0);
		wordsToAdd.remove(0);
		displayWords.add(original);
		wordsAdded.add(original);
		break;}

	    if(i == 100){	String original = wordsToAdd.get(0);
		wordsToAdd.remove(0);
		wordsAdded.add(original);}

	    }


	}
    }

    public boolean checkValid(String word, int row, int col, int dx, int dy){
	boolean end = true;

	for (int i = 0; i < word.length(); i++) {
	    if ((data[row + (i * dy)][col + (i * dx)] != word.charAt(i)) &&
		(data[row + (i * dy)][col + (i * dx)] != '\u0000') &&
		(data[row + (i * dy)][col + (i * dx)] != '_')) {

		end = false;
	    }
	}
	return end;
    }


    public boolean addWord(String word, int row, int col, int direction) {
	int dx = 0;
	int dy = 0;

	if (direction > 0){
	    dx = 1;
	}

	if (direction == 1){
	    dy = -1;
	}

	if (direction == 3 || direction == 0){
	    dy = 1;
	}

	try{
	if (checkValid(word, row, col, dx, dy) == false) {
	    return false;
	}
	}
	catch(ArrayIndexOutOfBoundsException e) {
	    return false;
	}

	for (int i = 0; i < word.length(); i++) {
	    data[row + (i * dy)][col + (i * dx)] = word.charAt(i);
		}

	return true;
    }
    
    public void fillWithTrash() {
	for (int i = 0; i < data.length; i++){

	    for (int j = 0; j < data[i].length; j++) {
		if (data[i][j] == '_'){
		    data[i][j] = (char)(randgen.nextInt(26)+ 65);
		}
	    }
	}
    }
	    



	////////////////////////////////////////////////////
			
    public static void main(String[] args) {
	
	if ((args.length < 3) ||
	    (args.length > 5)) {
	    System.out.println("Directions: java WordSearch rows cols filename.txt seed key");
	    System.out.println("Note: seed and key are optional.");
	    System.exit(1);
	}

	
	if (args.length == 3){
	    try{
		WordSearch x = new WordSearch(Integer.parseInt(args[0]),
					      Integer.parseInt(args[1]));
		
		x.loadWords(args[2]);
		x.fillWithWords();
		x.fillWithTrash();
		System.out.println(x.toString());
	        x.printWordList();
		System.out.println("Seed is: " + x.seed);
	    }
	    catch(NumberFormatException e){
		System.out.println("Directions: java WordSearch rows cols filename.txt seed key");
		System.out.println("Note: seed and key are optional.");
	    }
	}


	
	if (args.length == 4){
	    try{
		WordSearch x = new WordSearch(Integer.parseInt(args[0]),
					      Integer.parseInt(args[1]),
					      Integer.parseInt(args[3]));
		
		x.loadWords(args[2]);
		x.fillWithWords();
		x.fillWithTrash();
		System.out.println(x.toString());
		x.printWordList();
		System.out.println("Seed is: " + x.seed);
	    }
	    catch(NumberFormatException e){
		System.out.println("Directions: java WordSearch rows cols filename.txt seed key");
		System.out.println("Note: seed and key are optional.");
	    }
	}


	
	if (args.length == 5){

	    if(args[4].equals("key")){
	    try{
		WordSearch x = new WordSearch(Integer.parseInt(args[0]),
					      Integer.parseInt(args[1]),
					      Integer.parseInt(args[3]));
		
		x.loadWords(args[2]);
		x.fillWithWords();
		System.out.println(x.toString());
		x.printWordList();
		System.out.println("Seed is: " + x.seed);
	    }
	    catch(NumberFormatException e){
		System.out.println("Directions: java WordSearch rows cols filename.txt seed key");
		System.out.println("Note: seed and key are optional.");
	    }
	    }
	    else{
		System.out.println("Directions: java WordSearch rows cols filename.txt seed key");
		System.out.println("Note: seed and key are optional.");
	    }
	}


	
    }
    ////////////////////////////////////////////////////

}
