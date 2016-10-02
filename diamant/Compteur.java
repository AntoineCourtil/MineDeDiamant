package diamant;

public class Compteur {
	protected int cpt;
	
	public Compteur(){
		this.cpt = 0;
	}
	
	public void add(){
		this.cpt+=1;
	}
	
	public void add(int n){
		this.cpt+=n;
	}
	
	public void remove(){
		this.cpt-=1;
	}
	
	public void remove(int n){
		this.cpt-=n;
	}
	
	public int getInt(){
		return this.cpt;
	}
	
	public String toString(){
		return Integer.toString(this.cpt);
	}

	public void set(int n) {
		this.cpt=n;
	}

	public void reset() {
		this.cpt=0;
	}
}