package diamant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ToolBar extends JToolBar {
	private Jeu jeu;
	
	public ToolBar(Jeu j){
		this.jeu=j;

		JButton btnQuitter = new JButton(new ImageIcon(getClass().getResource("/img/exit.png")));
		JButton btnRejouer = new JButton(new ImageIcon(getClass().getResource("/img/replay.png")));
		JButton btnNewPlateau = new JButton(new ImageIcon(getClass().getResource("/img/new.png")));

		JButton btnX6 = new JButton(new ImageIcon(getClass().getResource("/img/6x6.png")));
		JButton btnX10 = new JButton(new ImageIcon(getClass().getResource("/img/10x10.png")));
		JButton btnX14 = new JButton(new ImageIcon(getClass().getResource("/img/14x14.png")));

		this.add(btnQuitter);
		this.add(btnRejouer);
		this.add(btnNewPlateau);
		this.add(btnX6);
		this.add(btnX10);
		this.add(btnX14);
		
		
		//----------- ACTION LISTENER ------------------------------------------
		
		
		btnQuitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		btnRejouer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jeu.rejouer(jeu.getHauteur());
			}
		});
		
		btnNewPlateau.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jeu.nouveauPlateau(jeu.getHauteur());
			}
		});
		
		btnX6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jeu.rejouer(6);
			}
		});
		
		btnX10.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jeu.rejouer(10);
			}
		});
		
		btnX14.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jeu.rejouer(14);
			}
		});
	}
}
