package checkersmodel;

public class Piece {
	boolean king = false;
	int owner;
	
	public Piece(int o){
		king = false;
		owner = o;
	}
	
	public void kingMe(){
		if(king == true){
			throw new IndexOutOfBoundsException();
		}else{
			king = true;
		}
	}
	
	public int getOwner(){
		return owner;
	}
	
	public boolean getKing(){
		return king;
	}
}
