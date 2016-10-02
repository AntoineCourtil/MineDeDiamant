package diamant;

public class Diamant {
	private int numero;
	private int posX;
	private int posY;
	
	public Diamant(int num, int x, int y){
		this.numero=num;
		this.posX=x;
		this.posY=y;
	}
	
	public int getNumero(){
		return this.numero;
	}
	
	public int getPosX(){
		return this.posX;
	}
	
	public int getPosY(){
		return this.posY;
	}
	
	public String getTxtPosX(){
		return Integer.toString(this.posX);
	}
	
	public String getTxtPosY(){
		return Integer.toString(this.posY);
	}
	
	public String getTxtCoord(){
		return "("+this.posX+","+this.posX+")";
	}
	
	public void setPosX(int x){
		this.posX=x;
	}
	
	public void setposY(int y){
		this.posY=y;
	}
	
	public boolean isVoisin(Diamant d){
		if(d.getPosX()==this.posX && d.getPosY()==this.posY+1) return true;
		if(d.getPosX()==this.posX && d.getPosY()==this.posY-1) return true;
		if(d.getPosX()==this.posX+1 && d.getPosY()==this.posY) return true;
		if(d.getPosX()==this.posX-1 && d.getPosY()==this.posY) return true;
		
		return false;
	}
	
	public boolean equals(Diamant d){
		return d.getNumero()==this.numero && d.getPosX()==this.posX && d.getPosY()==this.posY;
	}
}
