package diamant;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

public class VuePlateau extends JPanel implements Vue {
	private Jeu jeu;
	
	public VuePlateau(Jeu j){
		super();
		
		this.setLayout(new GridLayout(j.getHauteur(),j.getLargeur()));
		
		j.ajouterVue(this);
		this.jeu = j;
		
		this.mettreAJour();
	}
	
	
	public void mettreAJour() {
		
		this.removeAll();
		this.setLayout(new GridLayout(this.jeu.getHauteur(),this.jeu.getLargeur()));
		
		for(int i=0;i<this.jeu.getHauteur();i++){
			for(int j=0;j<this.jeu.getLargeur();j++){
				
				String nomImg="vide";

				if(this.jeu.getCase(i,j)==Jeu.DIAMANT) nomImg="diamant";
				if(this.jeu.getCase(i,j)==Jeu.RUBIS) nomImg="rubis";
				if(this.jeu.getCase(i,j)==Jeu.EMERAUDE) nomImg="emeraude";
				if(this.jeu.getCase(i,j)==Jeu.SAPHIR) nomImg="saphir";
				if(this.jeu.getCase(i,j)==Jeu.VIDE) nomImg="vide";
				
				JButton btn = new JButton(new ImageIcon(getClass().getResource("/img/"+nomImg+".png")));
				this.add(btn);
				Diamant d = new Diamant(this.jeu.getCase(i, j),i,j);
				if (this.jeu.isD1(d) || this.jeu.isD2(d)){
					Border brdSelected = new LineBorder(Color.RED, 3);
					btn.setBorder(brdSelected);
				}
				btn.addActionListener(new clic(this.jeu,d)) ;
			}
		}
		
		this.revalidate();
		this.repaint();
	}
	
	static class clic implements ActionListener
    {
		private Diamant d;
		private Jeu jeu;
        public clic(Jeu j, Diamant d){
        	this.jeu=j;
        	this.d=d;
        }
		
		public void actionPerformed(ActionEvent e)
        {
        	//System.out.println("Coordonées : ("+this.x+","+this.y+")");
        	jeu.clic(this.d);
        }
    }

}
