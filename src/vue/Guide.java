package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class Guide extends JPanel {

	private JLabel labelTitre;
	private JLabel labelIndications;
	private JLabel labelIndications2;
	private JLabel labelIndications3;
	private JLabel labelImageInterlo;
	private JButton boutonValider;
	private JButton boutonAnnuler;
	private boolean guideActive;
	private JLabel labelIcon;
	private boolean[] competencesAcquises;

	public Guide(int yBounds, int widthBounds) {
		this.setLayout(null);
		this.setVisible(true);
		this.setBounds(0, yBounds, widthBounds, 500);
		this.setOpaque(false);	

		this.guideActive = false;
		
		competencesAcquises = new boolean[6];

		this.labelTitre = new JLabel();
		this.labelIndications = new JLabel();
		this.labelIndications2 = new JLabel();
		this.labelIndications3 = new JLabel();
		this.boutonValider = new JButton();
		this.boutonAnnuler = new JButton();

		ImageIcon imageIcon = new ImageIcon(new ImageIcon("images/fleches/fleche_haut.gif").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		labelIcon = new JLabel(imageIcon);
		labelIcon.setBounds(880, 30, 200, 200);

		// Image du personnage expliquant les règles	
		ImageIcon imageInterlo = new ImageIcon(new ImageIcon("images/profile/Garen.png").getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
		labelImageInterlo = new JLabel(imageInterlo);
		labelImageInterlo.setBounds(0, 0, 300, 250);

		this.add(labelTitre);
		this.add(labelIndications);
		this.add(labelIndications2);
		this.add(labelIndications3);
		this.add(labelImageInterlo);
		this.add(boutonValider);
		this.add(boutonAnnuler);
		this.add(labelIcon);
	}

	public void afficherIndicationsDeplacement() {
		if (aValideCompetence(0)) {
			labelIcon.setBounds(810, 30, 200, 200);

			labelTitre.setText("Indications");

			labelIndications2.setVisible(false);
			labelIndications.setVisible(true);
			labelIndications.setText("<html>Pour pouvoir déplacer un soldat, vous devez cliquer "
					+ "sur l'hexagone sur lequel vous voulez qu'il se rende.</html>");

			boutonValider.setVisible(false);
			validerCompetence(1);
		}
	}

	public void afficherIndicationsDeplacement2() {
		if (aValideCompetence(1)) {
			labelIcon.setVisible(false);
			labelIndications.setText("<html>ATTENTION : un soldat ne peut se déplacer que si ses points de déplacements "
					+ "additioné au bonus de déplacement du terrain sur lequel il se trouve le permettent.</html>");
			labelIndications.setBounds(300, 130, this.getWidth()-464, 50);	

			boutonValider.setVisible(true);
			for (ActionListener actionL : boutonValider.getActionListeners()) {
				boutonValider.removeActionListener(actionL);
			}

			labelIndications2.setText("<html>De plus, en fonction de la vision du soldat, il pourra voir plus ou moins loin. Les champs de vision qui lui sont inaccessibles seront camouflés par un brouillard de guerre.</html>");

			boutonValider.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					labelIndications2.setVisible(true);
					labelIndications.setVisible(false);
					boutonValider.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							labelIcon.setVisible(true);
							labelIcon.setBounds(840, 30, 200, 200);
							labelIndications2.setText("<html>Pour avoir des informations sur un terrain, il suffit de passer la souris quelque temps sur l'un de ses hexagones.</html>");
							boutonValider.setVisible(false);
							validerCompetence(2);
						}
					});
				}
			});
		}
	}

	public void afficherIndicationsDeplacement3() {
		if (aValideCompetence(2)) {
			labelIcon.setBounds(630, 50, 200, 200);

			labelIndications2.setText("<html>Vous trouverez toutes les informations du soldat sélectionné à droite.</html>");
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("images/fleches/fleche_droite.gif").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			labelIcon.setIcon(imageIcon);
			for (ActionListener actionL : boutonValider.getActionListeners()) {
				boutonValider.removeActionListener(actionL);
			}
			boutonValider.setVisible(true);
			ImageIcon imageFond = new ImageIcon("images/button_small_copper_H22-active.png");
			boutonValider.setIcon(imageFond);
			boutonValider.setBounds(this.getWidth()-94,165, 50, 20);
			boutonValider.setFont(new Font("Times New Roman", Font.PLAIN, 11));
			boutonValider.setText("OK");
			boutonValider.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					labelIndications.setVisible(false);
					labelIndications2.setText("<html>Cherchez dans les alentours, des soldats arborant un hexagone de couleur rouge autour d'eux. Passez votre tour à l'aide du bouton 'finir tour' si vous souhaitez passez la main à un autre joueur. </html>");
					labelIcon.setVisible(false);
					validerCompetence(3);
					boutonValider.setVisible(false);
				}
			});
		}
	}

	public void afficherIndicationsCombat() {
		if (aValideCompetence(3)) {
			labelIcon.setBounds(this.getWidth()-474, 50, 200, 200);

			labelIndications2.setText("<html>Vous vous trouvez à proximité d'un ennemi !</html>");

			labelIndications.setText("<html>Pour pouvoir attaquer un soldat, il ne doit y avoir aucun hexagone vous séparant l'un de l'autre. Une fois ce critère rempli, il vous suffit de cliquer sur l'ennemi pour l'attaquer. </html>");
			labelIndications.setBounds(300, 130, this.getWidth()-434, 50);	

			for (ActionListener actionL : boutonValider.getActionListeners()) {
				boutonValider.removeActionListener(actionL);
			}
			boutonValider.setVisible(true);
			boutonValider.setIcon(new ImageIcon("images/large-button-active.png"));
			boutonValider.setBounds(this.getWidth()-144,165, 100, 22);
			boutonValider.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			boutonValider.setText("Suivant");
			boutonValider.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					labelIndications.setVisible(true);
					labelIndications2.setVisible(false);

					boutonValider.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							labelIndications.setText("<html>En fonction des points d'attaque du soldat avec lequel vous êtes actuellement "
									+ "en train de jouer, en fonction des points de défense de la victime et en fonction du bonus de défense du terrain sur lequel se trouve le soldat que vous comptez attaquer, la victime perdra ou non des points de vies.</html>");

							for (ActionListener actionL : boutonValider.getActionListeners()) {
								boutonValider.removeActionListener(actionL);
							}
							boutonValider.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									labelIndications.setText("<html>Lorsqu'un soldat perd tous ses points de vies. Il est KO et le nombre de soldats du joueur auquel il est rattaché diminue. Le joueur ne peut par conséquent plus utiliser ce soldat pour la suite de la partie.</html>");
									labelIndications2.setText("<html>Vous pouvez retrouver toutes les informations du joueur à droite.</html>");

									boutonValider.addActionListener(new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent e) {
											labelIndications.setVisible(false);
											labelIndications2.setVisible(true);
											labelIcon.setVisible(true);

											for (ActionListener actionL : boutonValider.getActionListeners()) {
												boutonValider.removeActionListener(actionL);
											}
											boutonValider.addActionListener(new ActionListener() {
												@Override
												public void actionPerformed(ActionEvent e) {
													labelIndications2.setText("<html>Attaquez un soldat adverse.</html>");
													boutonValider.setVisible(false);
													labelIcon.setVisible(false);
													validerCompetence(4);
												}
											});
										}
									});
								}
							});	
						}
					});
				}
			});
		}
	}

	public void afficherFinTuto() {
		if (aValideCompetence(4)) {
			labelIcon.setVisible(true);
			labelIcon.setBounds(this.getWidth()-224, 30, 200, 200);

			labelIndications2.setText("<html>Information supplémentaire : la petite carte en haut à droite vous permet d'avoir une vue d'ensemble sur le plateau et sur les différents terrains. Vous pouvez explorer le plateau en bougeant son cadre.</html>");

			labelIndications.setText("<html>Vous êtes arrivé à la fin de ce tutoriel.</html>");

			for (ActionListener actionL : boutonValider.getActionListeners()) {
				boutonValider.removeActionListener(actionL);
			}

			boutonValider.setVisible(true);
			boutonValider.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					labelIcon.setVisible(false);
					labelIndications2.setVisible(false);
					labelIndications.setVisible(true);
					boutonValider.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							labelIndications.setText("<html>En cas de besoin, vous pourrez le relancer à tout moment à l'aide du bouton '?'.</html>");
							boutonValider.setText("Fin");
							for (ActionListener actionL : boutonValider.getActionListeners()) {
								boutonValider.removeActionListener(actionL);
							}
							boutonValider.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									validerCompetence(5);
									for (ActionListener actionL : boutonValider.getActionListeners()) {
										boutonValider.removeActionListener(actionL);
									}
									afficherQuestion();
								}
							});
						}
					});
				}
			});
		}
	}

	public void afficherIndicationsSelection() {
		boutonAnnuler.setVisible(false);
		labelIndications.setText("<html>Le jeu est composé de cinq terrains distincts et des soldats des différents joueurs."
				+ " Tous les joueurs disposent de 10 soldats au lancement du jeu. </html>");

		labelIndications2.setVisible(false);
		labelIndications2.setText("<html>Pour gagner la partie, il suffit de répondre aux critères du scénario que vous venez de choisir, avant les autres joueurs.</html>");
		labelIndications2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		labelIndications2.setForeground(Color.white);
		labelIndications2.setBounds(300, 130, 640, 50);
		
		boutonValider.setText("Suivant");
		for (ActionListener actionL : boutonValider.getActionListeners()) {
			boutonValider.removeActionListener(actionL);
		}
		boutonValider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				labelIndications.setVisible(false);
				labelIndications2.setVisible(true);
				boutonValider.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						labelIndications2.setText("<html>Pour pouvoir réaliser une action avec l'un de vos soldats, vous devez tout "
								+ "d'abord le sélectionner en cliquant dessus.</html>");
						boutonValider.setVisible(false);
						ImageIcon imageIcon = new ImageIcon(new ImageIcon("images/fleches/fleche_haut.gif").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
						labelIcon.setIcon(imageIcon);
						labelIcon.setVisible(true);
						validerCompetence(0);
					}
				});
			}
		});

	}

	public void afficherQuestion() {
		labelImageInterlo.setVisible(true);
		
		labelIcon.setVisible(false);
		
		labelTitre.setVisible(true);
		labelTitre.setText("Bienvenue sur WarGame !");
		labelTitre.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		labelTitre.setForeground(new Color(200, 173, 10));
		labelTitre.setBounds(300, 100, 700, 50);

		labelIndications2.setText("");
		
		labelIndications.setVisible(true);
		labelIndications.setText("<html>Voulez-vous lancer le tutoriel ?</html>");
		labelIndications.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		labelIndications.setForeground(Color.white);
		labelIndications.setBounds(300, 130, this.getWidth()-434, 50);	

		for (ActionListener actionL : boutonValider.getActionListeners()) {
			boutonValider.removeActionListener(actionL);
		}
		for (ActionListener actionL : boutonAnnuler.getActionListeners()) {
			boutonAnnuler.removeActionListener(actionL);
		}
		
		boutonValider.setVisible(true);
		boutonValider.setText("Oui");
		boutonValider.setBorder(UIManager.getBorder("Button.border"));
		boutonValider.setIcon(new ImageIcon("images/large-button-active.png"));
		boutonValider.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boutonValider.setForeground(Color.white);
		boutonValider.setBounds(this.getWidth()-144,165, 100, 22);
		boutonValider.setHorizontalTextPosition(JButton.CENTER);
		boutonValider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i=0; i<6; i++) {
					competencesAcquises[i] = false;
				}
				guideActive = true;
				labelIndications.setVisible(true);
				labelTitre.setVisible(true);
				labelImageInterlo.setVisible(true);
				afficherIndicationsSelection();
			}
		});

		boutonAnnuler.setVisible(true);
		boutonAnnuler.setText("Non");
		boutonAnnuler.setBorder(UIManager.getBorder("Button.border"));
		boutonAnnuler.setIcon(new ImageIcon("images/large-button-active.png"));
		boutonAnnuler.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boutonAnnuler.setForeground(Color.white);
		boutonAnnuler.setBounds(this.getWidth()-254,165, 100, 22);
		boutonAnnuler.setHorizontalTextPosition(JButton.CENTER);
		boutonAnnuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guideActive = false;
				boutonAnnuler.setVisible(false);
				labelIndications.setVisible(false);
				labelTitre.setVisible(false);
				labelImageInterlo.setVisible(false);
				boutonValider.setVisible(false);
			}
		});

	}

	public void validerCompetence(int numCompetence) {
		competencesAcquises[numCompetence] = true;
	}

	public boolean aValideCompetence(int numCompetence) {
		return competencesAcquises[numCompetence];
	}

	public boolean isGuideActive() {
		return guideActive;
	}

	public void setGuideActive(boolean guideActive) {
		this.guideActive = guideActive;
	}

}
