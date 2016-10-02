package diamant;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BarreMenu extends JMenuBar {
	private Jeu jeu;
	
	public BarreMenu(Jeu j){
		this.jeu=j;
		
		JMenu menuPrincipal = new JMenu("Mine de Diamants");
		
		JMenuItem jmiQuitter = new JMenuItem("Quitter"/*, image*/) ;
		menuPrincipal.add(jmiQuitter) ;
		
		this.add(menuPrincipal) ;
		
		
		JMenu menuJeu = new JMenu("Jeu");
		
		JMenuItem jmiRejouer = new JMenuItem("Rejouer"/*, image*/) ;
		JMenuItem jmiNewPlateau = new JMenuItem("Générer un nouveau plateau"/*, image*/) ;
		JMenu menuChangeTaille = new JMenu("Changer de Taille de Plateau") ;
			JMenuItem jmiX6 = new JMenuItem("6x6"/*, image*/) ;
			JMenuItem jmiX10 = new JMenuItem("10x10"/*, image*/) ;
			JMenuItem jmiX14 = new JMenuItem("14x14"/*, image*/) ;

			menuChangeTaille.add(jmiX6);
			menuChangeTaille.add(jmiX10);
			menuChangeTaille.add(jmiX14);
		
		menuJeu.add(jmiRejouer) ;
		menuJeu.add(jmiNewPlateau) ;
		menuJeu.add(menuChangeTaille) ;
		
		
		//----------- ACTION LISTENER ------------------------------------------
		
		
		jmiQuitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		jmiRejouer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jeu.rejouer(jeu.getHauteur());
			}
		});
		
		jmiNewPlateau.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jeu.nouveauPlateau(jeu.getHauteur());
			}
		});
		
		jmiX6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jeu.rejouer(6);
			}
		});
		
		jmiX10.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jeu.rejouer(10);
			}
		});
		
		jmiX14.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jeu.rejouer(14);
			}
		});
		

		this.add(menuJeu) ;
	}
}
