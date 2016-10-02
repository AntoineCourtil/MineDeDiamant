package diamant;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class VueInfos extends JPanel implements Vue {
	private Jeu jeu;
	private JPanel panelCoord;
	private JPanel panelScore;
	private JLabel x1;
	private JLabel y1;
	private JLabel x2;
	private JLabel y2;
	private JLabel score;
	private JLabel nbEchanges;
	
	public VueInfos(Jeu j){
		super();

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		j.ajouterVue(this);
		this.jeu=j;
		
		//-------------------------------------------------------
		
		this.panelCoord=new JPanel();
		//this.panelCoord.setLayout(new BoxLayout(this.panelCoord, BoxLayout.PAGE_AXIS));
		this.panelCoord.setLayout(new GridLayout(4,2));
		
		JLabel txtX1 = new JLabel("x1 :");
		JLabel txtY1 = new JLabel("y1 :");
		JLabel txtX2 = new JLabel("x2 :");
		JLabel txtY2 = new JLabel("y2 :");

		this.x1 = new JLabel("");
		this.y1 = new JLabel("");
		this.x2 = new JLabel("");
		this.y2 = new JLabel("");

		this.panelCoord.add(txtX1);
		this.panelCoord.add(this.x1);
		this.panelCoord.add(txtY1);
		this.panelCoord.add(this.y1);
		this.panelCoord.add(txtX2);
		this.panelCoord.add(this.x2);
		this.panelCoord.add(txtY2);
		this.panelCoord.add(this.y2);
		
		Border blueline = BorderFactory.createLineBorder(Color.blue);
		
		TitledBorder title;
		title = BorderFactory.createTitledBorder(blueline, "Coordonnées des cases");
		this.panelCoord.setBorder(title);
		
		//-------------------------------------------------------
		
		this.panelScore=new JPanel();
		//this.panelScore.setLayout(new BoxLayout(this.panelScore, BoxLayout.PAGE_AXIS));
		this.panelScore.setLayout(new GridLayout(2,2));
		
		JLabel txtNbEchanges = new JLabel("nb échanges : ");
		JLabel txtScore = new JLabel("score : ");
		this.score = new JLabel("");
		this.nbEchanges = new JLabel("");

		this.panelScore.add(txtNbEchanges);
		this.panelScore.add(this.nbEchanges);
		this.panelScore.add(txtScore);
		this.panelScore.add(this.score);
		
		title = BorderFactory.createTitledBorder(blueline, "Score");
		this.panelScore.setBorder(title);
		
		//-------------------------------------------------------

		//this.panelCoord.setPreferredSize(new Dimension(250, 100)) ;
		//this.panelScore.setPreferredSize(new Dimension(250, 100)) ;
		
		this.add(this.panelCoord);
		this.add(this.panelScore);
		
		this.mettreAJour();
	}
	
	@Override
	public void mettreAJour() {
		if(this.jeu.getD1()!=null){
			this.x1.setText(this.jeu.getD1().getTxtPosX());
			this.y1.setText(this.jeu.getD1().getTxtPosY());
		}
		else{
			this.x1.setText("");
			this.y1.setText("");
		}
		if(this.jeu.getD2()!=null){
			this.x2.setText(this.jeu.getD2().getTxtPosX());
			this.y2.setText(this.jeu.getD2().getTxtPosY());
		}
		else{
			this.x2.setText("");
			this.y2.setText("");
		}

		this.nbEchanges.setText(this.jeu.getTxtNbEchanges());
		this.score.setText(this.jeu.getTxtScore());
	}

}
