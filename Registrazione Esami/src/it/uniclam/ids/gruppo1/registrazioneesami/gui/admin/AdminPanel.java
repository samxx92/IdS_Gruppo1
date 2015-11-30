package it.uniclam.ids.gruppo1.registrazioneesami.gui.admin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.uniclam.ids.gruppo1.registrazioneesami.ClientMainGUI;
import it.uniclam.ids.gruppo1.registrazioneesami.ServerMain;
import it.uniclam.ids.gruppo1.registrazioneesami.gui.*;

@SuppressWarnings("serial")
public class AdminPanel extends JPanel{

	private JButton enable = new JButton(ClientMainGUI.ENABLE_PANEL);
	private JButton registration = new JButton(ClientMainGUI.CONFIRMATION_ADMIN_PANEL);
	private JButton store = new JButton(ClientMainGUI.STORE_EXAM_ADMIN_PANEL);


	public AdminPanel(ClientMainGUI clientGUI){
		//JPanel pane = new JPanel(new GridBagLayout());

		//Container pane = getContentPane();
		// Definisci un oggetto gridbagconstraints per la specifica 
		// dei vincoli dell'interfaccia
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(new GridBagLayout());


		// Campo enable
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 5;   //2 columns wide
		this.add(enable, c);

		// Campo registration
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 5;   //2 columns wide
		this.add(registration, c);

		// Campo store
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 5;   //2 columns wide
		this.add(store, c);



		enable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(ClientMainGUI.ENABLE_PANEL);

			}
		});

		registration.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(ClientMainGUI.CONFIRMATION_ADMIN_PANEL);

			}
		});

		store.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(ClientMainGUI.STORE_EXAM_ADMIN_PANEL);

			}
		});




	}
}
