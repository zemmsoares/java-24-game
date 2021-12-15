package Game;

 public class Puzzle{  
	int game_id;
	int n1;  
	int n2;  
	int n3;  
	int n4;
	Boolean pro_name;  
	int difficulty;
	//Product class constructor  
	public Puzzle(int game_id,int n1,int n2,int n3,int n4, boolean n, int d) 
		{  
			this.game_id = game_id;
			this.n1 = n1;
			this.n2 = n2;
			this.n3 = n3;
			this.n4 = n4;
			pro_name = n;
			this.difficulty = d;
		}  	
}  