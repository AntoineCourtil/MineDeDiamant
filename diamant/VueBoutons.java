package diamant;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class VueBoutons extends JPanel implements Vue {
	private Jeu jeu;
	private JButton btnModeRapide;
	private JButton btnEchange;
	private JButton btnSupprAlign;
	private JButton btnDescentePierres;
	private JButton btnNouvellesPierres;
	
	public VueBoutons(Jeu j){
		super();
		
		this.setLayout(new GridLayout(1,5));
		
		j.ajouterVue(this);
		this.jeu=j;

		this.btnModeRapide = new JButton("<html>Mode <br/> Rapide</html>");
		this.btnEchange = new JButton("Echange");
		this.btnSupprAlign = new JButton("<html>Suppression <br/> des <br/> Alignements</html>");
		this.btnDescentePierres = new JButton("<html>Descente <br/> des <br/> Pierres</html>");
		this.btnNouvellesPierres = new JButton("<html>Nouvelles <br/> Pierres</html>");

		this.add(this.btnModeRapide);
		this.add(this.btnEchange);
		this.add(this.btnSupprAlign);
		this.add(this.btnDescentePierres);
		this.add(this.btnNouvellesPierres);

		
		
		//---- ACTION LISTENER
		
		this.btnEchange.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jeu.echange();
			}
		});
		
		this.btnSupprAlign.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jeu.supprAlignement();
			}
		});
		
		this.btnDescentePierres.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jeu.descentePierres();
			}
		});
		
		this.btnNouvellesPierres.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jeu.nouvellesPierres();
			}
		});
		
		this.btnModeRapide.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jeu.modeRapide();
			}
		});
		
		
		
		
		this.mettreAJour();
	}
	
	
	@Override
	public void mettreAJour() {
		this.btnEchange.setEnabled(this.jeu.is2Selected());
		this.btnDescentePierres.setEnabled(this.jeu.isEmptyCase());
		this.btnModeRapide.setEnabled(this.jeu.is2Selected());
	}

}
