package it.uniclam.ids.gruppo1.registrazioneesami.gui.admin;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.uniclam.ids.gruppo1.registrazioneesami.ClientMainGUI;
import it.uniclam.ids.gruppo1.registrazioneesami.ServerMain;;

public class ConfirmationAdminPanel extends JPanel{


	private JButton salva = new JButton("Salva");	
	private JTextArea ta = new JTextArea(20, 50);

	private JButton back = new JButton("Indietro");


	public ConfirmationAdminPanel(ClientMainGUI clientGUI){
		//JPanel pane = new JPanel(new GridBagLayout());

		//Container pane = getContentPane();
		// Definisci un oggetto gridbagconstraints per la specifica 
		// dei vincoli dell'interfaccia
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(new GridBagLayout());

		// Campo salva
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 2;   //2 columns wide
		this.add(salva, c);


		// Campo esami (label)
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 4;   //2 columns wide
		this.add(new JLabel("Esami Verbalizzati in data odierna:"), c);

		// Campo esami
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 8;   //2 columns wide
		JScrollPane jp = new JScrollPane(ta);
		this.add(jp, c);

		// Campo indietro
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;   //2 columns wide
		this.add(back, c);


		salva.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO

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
