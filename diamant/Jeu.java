package diamant;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Jeu {
	private ArrayList<Vue> alv;
	private int[][] plateau;
	private Diamant d1;
	private Diamant d2;
	private Compteur nbEchanges;
	private Compteur score;
	private boolean is2Selected;
	private boolean isEmpty;
		
	final static public int SAPHIR = 1;
	final static public int RUBIS = 2;
	final static public int EMERAUDE = 3;
	final static public int DIAMANT = 4;
	final static public int VIDE = 9;
	
	
	public Jeu(int[][] tab){
		this.plateau = tab;
		this.alv = new ArrayList<Vue>();
		this.is2Selected=false;
		this.nbEchanges=new Compteur();
		this.score=new Compteur();
		this.isEmpty=false;;

		this.d1=null;
		this.d2=null;
	}

	//------------------------------ GENERAL -------------------------------------------------

	public void ajouterVue(Vue v){
		alv.add(v);
	}
	
	public void mettreAJour(){
		if(this.d1==null || this.d2==null) this.is2Selected=false;
		else this.is2Selected=true;
		
		for(Vue v:this.alv){
			v.mettreAJour();
		}
	}
	
	public boolean is2Selected(){
		return this.is2Selected;
	}
	
	public boolean isEmptyCase(){
		boolean res=false;
		
		int width=this.getLargeur();
		int height=this.getHauteur();
		int i=0;
		int j=0;
		
		while(i<width && !res){
			j=0;
			while(j<width && !res){
				if(this.getCase(i, j)==Jeu.VIDE) res=true;
				j++;
			}
			i++;
		}
		
		return res;
	}

	//------------------------------ PLATEAU -------------------------------------------------

	public void setCase(int x, int y, int val){
		this.plateau[x][y]=val;
	}
	public int getCase(int x, int y){
		return this.plateau[x][y];
	}

	public int getHauteur(){
		return this.plateau.length;
	}
	
	public int getLargeur(){
		return this.plateau[0].length;
	}
	
	public void nouveauPlateau(int taille){
		this.plateau=new int[taille][taille];
		for(int i=0;i<taille;i++){
			for(int j=0;j<taille;j++){
				Random rand = new Random();
				int nb = rand.nextInt(4)+1;
				this.setCase(i,j,nb);
			}
		}

		this.supprAlignement();
		
		while(this.isEmpty){
			this.descentePierres();
			this.nouvellesPierres();
			this.supprAlignement();			
		}
		
		this.mettreAJour();
	}

	//------------------------------ COMPTEUR -------------------------------------------------

	public int getNbEchanges(){
		return this.nbEchanges.getInt();
	}
	
	public String getTxtNbEchanges(){
		return this.nbEchanges.toString();
	}
	
	public int getScore(){
		return this.score.getInt();
	}
	
	public String getTxtScore(){
		return this.score.toString();
	}

	

	//------------------------------ DIAMANT -------------------------------------------------
	
	
	public Diamant getD1(){
		return this.d1;
	}
	
	public Diamant getD2(){
		return this.d2;
	}
	
	public boolean isD1(Diamant d){
		if(this.d1!=null) return this.d1.equals(d);
		return false;
	}
	
	public boolean isD2(Diamant d){
		if(this.d2!=null) return this.d2.equals(d);
		return false;
	}
	
	public void clic(Diamant d){
		//System.out.println("Coordonées : "+d.getTxtCoord());
		if(this.d1==null){
			if(this.d2==null || this.d2.isVoisin(d)) this.d1=d;
			if(this.d2!=null && (this.d2.equals(d) || !this.d2.isVoisin(d))) this.d2=null;
		}		
		else {
			if(this.d1.equals(d)) this.d1=null;
			
			else if(this.d2==null){
				if(this.d1==null || this.d1.isVoisin(d)){
					this.d2=d;
				}
				else {
					this.d1=null;
				}
			}
			else {
				if(this.d2.equals(d)) this.d2=null;
			}

		}
				
		this.mettreAJour();
	}
	
	
	//------------------ ACTIONS -----------------------------------------------------------------
	

	public void echange() {
		//System.out.println("ECHANGE");
		int swap = this.plateau[this.d1.getPosX()][this.d1.getPosY()];
		this.plateau[this.d1.getPosX()][this.d1.getPosY()]=this.plateau[this.d2.getPosX()][this.d2.getPosY()];
		this.plateau[this.d2.getPosX()][this.d2.getPosY()]=swap;

		this.is2Selected=false;
		this.d1=null;
		this.d2=null;
		this.nbEchanges.add();
		
		this.mettreAJour();		
	}
	
	public void supprAlignement() {
		//System.out.println("__SupprAlignement__");
		int width=this.getLargeur();
		int height=this.getHauteur();
		
		boolean[][] tab = new boolean[height][width];
		
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				tab[i][j]=false;
			}
		}
		
		//----- LIGNES
		
		for(int i=0;i<height;i++){
			int suite=0;
			int exCase=this.getCase(i, 0);
			for(int j=0;j<width;j++){
				if(exCase==this.getCase(i, j)){
					suite++;
					//System.out.println("Suite : "+suite);
				}
				else{
					if(suite>=3){
						//System.out.println("Suite OK");
						for(int k=0;k<suite;k++){
							//System.out.println("SupprCases ("+i+","+(j-k-1)+" = Jeu.VIDE)");
							//this.setCase(i,j-k-1,Jeu.VIDE);
							tab[i][j-k-1]=true;
						}
						//this.score.add(10*suite);
					}
					//System.out.println("NewCase");
					suite=1;
					exCase=this.getCase(i, j);
				}
			}
			if(suite>=3){
				for(int k=0;k<suite;k++){
					//System.out.println("SupprCases ("+i+","+(width-k-1)+" = Jeu.VIDE)");
					//this.setCase(i,width-k-1,Jeu.VIDE);
					tab[i][width-k-1]=true;
				}
				//this.score.add(10*suite);
			}
		}
		
		//----- COLONNES
		
		for(int i=0;i<width;i++){
			int suite=0;
			int exCase=this.getCase(i, 0);
			for(int j=0;j<height;j++){
				//System.out.println("("+j+","+i+") : "+this.getCase(j,i));
				if(exCase==this.getCase(j,i)){
					suite++;
					//System.out.println("Suite : "+suite);
				}
				else{
					if(suite>=3){
						//System.out.println("Suite OK");
						for(int k=0;k<suite;k++){
							//System.out.println("SupprCases ("+(j-k-1)+","+i+" = Jeu.VIDE)");
							//this.setCase((j-k-1),i,Jeu.VIDE);
							tab[j-k-1][i]=true;
						}
						//this.score.add(10*suite);
					}
					//System.out.println("NewCase");
					suite=1;
					exCase=this.getCase(j,i);
				}
			}
			if(suite>=3){
				for(int k=0;k<suite;k++){
					//System.out.println("SupprCases ("+(height-k-1)+","+i+" = Jeu.VIDE)");
					//this.setCase((height-k-1),i,Jeu.VIDE);
					tab[height-k-1][i]=true;
				}
				//this.score.add(10*suite);
			}
		}
		
		//---- SUPPRESSION
		
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if(tab[i][j]){
					if(this.getCase(i, j)!=Jeu.VIDE)this.score.add(10);
					this.setCase(i,j,Jeu.VIDE);
					this.isEmpty=true;
				}
			}
		}
		
		this.mettreAJour();		
	}
	
	public void descentePierres(){
		//System.out.println("descentePierres");
		int width=this.getLargeur();
		int height=this.getHauteur();
		
		for(int a=0;a<height;a++){
			for(int i=0;i<width;i++){
				for(int j=height-1;j>0;j--){
					if(this.getCase(j, i)==Jeu.VIDE) {
						//System.out.println("this.getCase("+(j-1)+", "+i+")");
						//this.setCase(j, i,this.getCase(j-1, i));
						for(int k=j;k>0;k--){
							//System.out.println("this.getCase("+(k-1)+", "+i+")");
							this.setCase(k, i,this.getCase(k-1, i));						
						}
						this.setCase(0, i,Jeu.VIDE);
					}
				}
			}
		}
		
		
		this.mettreAJour();
	}
	
	public void nouvellesPierres(){
		//System.out.println("nouvellesPierres");
		int width=this.getLargeur();
		int height=this.getHauteur();
		this.isEmpty=false;
		
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if(this.getCase(i, j)==Jeu.VIDE) {
					Random rand = new Random();
					int nb = rand.nextInt(4)+1;
					//System.out.println("nb : "+nb);
					this.setCase(i,j,nb);
				}
			}
		}
		
		this.mettreAJour();
	}
	
	public void modeRapide(){
		//System.out.println("modeRapide");
		this.is2Selected=false;
		int timeDelay=500;

		this.echange();
		//try { Thread.sleep(timeDelay); } catch (InterruptedException ie) {}
		
		this.supprAlignement();
		//try { Thread.sleep(timeDelay); } catch (InterruptedException ie) {}
		
		while(this.isEmpty){
			this.descentePierres();
			//try { Thread.sleep(timeDelay); } catch (InterruptedException ie) {}
			
			this.nouvellesPierres();
			//try { Thread.sleep(timeDelay); } catch (InterruptedException ie) {}
			
			this.supprAlignement();	
			//try { Thread.sleep(timeDelay); } catch (InterruptedException ie) {}	
			
		}

		this.mettreAJour();
	}
	
	
	//------------------ JEU -----------------------------------------------------------------
	
	public void rejouer(int taille){
		this.d1=null;
		this.d2=null;
		this.is2Selected=false;
		this.isEmpty=false;
		this.nbEchanges.reset();
		this.score.reset();
		
		this.nouveauPlateau(taille);
		
		
		this.mettreAJour();
	}
}
