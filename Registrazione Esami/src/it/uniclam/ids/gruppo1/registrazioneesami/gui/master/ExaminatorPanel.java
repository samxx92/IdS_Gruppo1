package it.uniclam.ids.gruppo1.registrazioneesami.gui.master;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.uniclam.ids.gruppo1.registrazioneesami.ClientMainGUI;

public class ExaminatorPanel extends JPanel{
	private JButton verbalizzazioni = new JButton(ClientMainGUI.REGISTRATION_PANEL);
	private JButton reservation = new JButton(ClientMainGUI.EXAM_RESERVATION_PANEL);
	private JButton registered = new JButton(ClientMainGUI.DAILY_EXAM_PANEL);
	private JButton back = new JButton("Logout");



	public ExaminatorPanel(ClientMainGUI clientGUI){
		//JPanel pane = new JPanel(new GridBagLayout());

		//Container pane = getContentPane();
		// Definisci un oggetto gridbagconstraints per la specifica 
		// dei vincoli dell'interfaccia
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(new GridBagLayout());


		// Campo esami scaduti
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 5;   //2 columns wide
		this.add(verbalizzazioni, c);


		// Campo esami confermati
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 5;   //2 columns wide
		this.add(reservation, c);

		// Campo esami verbalizzati in data odierna
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 5;   //2 columns wide
		this.add(registered, c);

		// Campo indietro
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;   //2 columns wide
		this.add(back, c);

		verbalizzazioni.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(ClientMainGUI.REGISTRATION_PANEL);
			}
		});

		reservation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(ClientMainGUI.EXAM_RESERVATION_PANEL);
			}
		});

		registered.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(ClientMainGUI.DAILY_EXAM_PANEL);

			}
		});

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(ClientMainGUI.LOGIN_PANEL);

			}
		});



	}

}
