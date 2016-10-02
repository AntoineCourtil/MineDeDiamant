package diamant;

import java.awt.*;
import javax.swing.*;

public class LancerJeu extends JFrame{
	private Jeu jeu;
	private JPanel vuePlateau;
	private JPanel vueInfos;
	private JPanel vueBoutons;
	private JMenuBar barreMenu;
	private JToolBar toolBar;
	
	
	public LancerJeu(int[][] tab){
		super("Mine de Diamants");
		//this.setPreferredSize(new Dimension(800, 400)) ;
		//this.setLayout(new GridLayout(2,2));
		
		this.jeu = new Jeu(tab);
		this.vuePlateau = new VuePlateau(this.jeu);
		this.vueInfos = new VueInfos(this.jeu);
		this.vueBoutons = new VueBoutons(this.jeu);
		this.barreMenu = new BarreMenu(this.jeu);
		this.toolBar = new ToolBar(this.jeu);

		add(this.vuePlateau, BorderLayout.CENTER) ;	
		add(this.vueInfos, BorderLayout.EAST) ;	
		add(this.vueBoutons, BorderLayout.SOUTH) ;
        add(this.toolBar, BorderLayout.PAGE_START);
		this.setJMenuBar(this.barreMenu) ;	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		pack() ;
		setVisible(true) ;
	}
	
	public static void main(String[] a) {
		int[ ][ ]val = {{Jeu.RUBIS, Jeu.EMERAUDE, Jeu.RUBIS, Jeu.SAPHIR, Jeu.SAPHIR, Jeu.EMERAUDE},
						{Jeu.SAPHIR, Jeu.RUBIS, Jeu.EMERAUDE, Jeu.SAPHIR, Jeu.DIAMANT, Jeu.RUBIS},
						{Jeu.DIAMANT, Jeu.DIAMANT, Jeu.EMERAUDE, Jeu.EMERAUDE, Jeu.RUBIS, Jeu.SAPHIR},
						{Jeu.DIAMANT, Jeu.EMERAUDE, Jeu.DIAMANT ,Jeu.EMERAUDE, Jeu.SAPHIR, Jeu.EMERAUDE},
						{Jeu.EMERAUDE, Jeu.DIAMANT, Jeu.SAPHIR, Jeu.SAPHIR, Jeu.RUBIS, Jeu.SAPHIR},
						{Jeu.EMERAUDE, Jeu.SAPHIR, Jeu.EMERAUDE, Jeu.RUBIS, Jeu.RUBIS, Jeu.SAPHIR}};
		
		new LancerJeu(val);
		
	}
}
