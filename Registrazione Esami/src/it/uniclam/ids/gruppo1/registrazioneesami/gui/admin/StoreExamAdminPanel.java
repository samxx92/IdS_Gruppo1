package it.uniclam.ids.gruppo1.registrazioneesami.gui.admin;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.uniclam.ids.gruppo1.registrazioneesami.ClientMainGUI;;

public class StoreExamAdminPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JButton esamiScaduti = new JButton("Esami Scaduti");
	private JButton esamiConfermati = new JButton("Esami Confermati");
	private JButton back = new JButton("Indietro");

	public StoreExamAdminPanel(ClientMainGUI clientGUI) {
		// JPanel pane = new JPanel(new GridBagLayout());

		// Container pane = getContentPane();
		// Definisci un oggetto gridbagconstraints per la specifica
		// dei vincoli dell'interfaccia
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(new GridBagLayout());

		// Campo esami scaduti
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 5; // 2 columns wide
		this.add(esamiScaduti, c);

		// Campo esami confermati
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 5; // 2 columns wide
		this.add(esamiConfermati, c);

		// Campo indietro
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2; // 2 columns wide
		this.add(back, c);

		esamiScaduti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(ClientMainGUI.ADMIN_EXPIRED_EXAM);
			}
		});

		esamiConfermati.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(ClientMainGUI.ADMIN_CONFIRMED_EXAM);
			}
		});

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(ClientMainGUI.ADMIN_PANEL);

			}
		});

	}

}
