public class Letter implements Comparable<Letter>{

    private String val;
    private int row;
    private int col;


    public Letter(String letter, int r, int c){
	val = letter;
	row = r;
	col = c;
    }

    public int compareTo(Letter x){
	if (
	    (this.row == x.row) &&
	    (this.col == x.col - 1)
	    )
	    {return 0;}
	
	if (
	    (this.row == x.row) &&
	    (this.col == x.col + 1)
	    )
	    {return 0;}

	if (
	    (this.row == x.row + 1) &&
	    (this.col == x.col)
	    )
	    {return 0;}
	
	if (
	    (this.row == x.row - 1) &&
	    (this.col == x.col + 1)
	    )
	    {return 0;}
	else{return -1;}
    }

    public String getVal(){
	return this.val;
    }

    public String getCoordinates(){
	return ("" + row + "," + col);
    }
}
