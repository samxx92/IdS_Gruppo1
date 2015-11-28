package it.uniclam.ids.gruppo1.registrazioneesami.gui.master;

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
import it.uniclam.ids.gruppo1.registrazioneesami.ServerMain;
import it.uniclam.ids.gruppo1.registrazioneesami.gui.*;

public class LoginPanel extends JPanel {
	private JTextField username = new JTextField("",15);
	private JTextField password = new JTextField("",15);
	

	private JButton login = new JButton("Accedi");
	private JButton abilitazione = new JButton("Richiedi Abilitazione");
	

	public LoginPanel(ClientMainGUI clientGUI){
		//JPanel pane = new JPanel(new GridBagLayout());

		//Container pane = getContentPane();
		// Definisci un oggetto gridbagconstraints per la specifica 
		// dei vincoli dell'interfaccia
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		
		// Campo username
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		this.add(new JLabel("Telefono:"), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		this.add(username, c);
		
		// Campo password
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		this.add(new JLabel("Password:"), c);
	
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		this.add(password, c);
		
		
		// Campo accedi
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 5;   //2 columns wide
		this.add(login, c);
		
		// Campo richiedi abilitazione
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 5;   //2 columns wide
		//this.add(abilitazione, c);
		
		
		
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(ClientMainGUI.EXAMINATOR_PANEL);
				//TODO
				
			}
			
		});

		
		
	}
}