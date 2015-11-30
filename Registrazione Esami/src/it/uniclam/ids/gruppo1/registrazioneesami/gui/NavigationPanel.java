package it.uniclam.ids.gruppo1.registrazioneesami.gui;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.uniclam.ids.gruppo1.registrazioneesami.ClientMainGUI;
import it.uniclam.ids.gruppo1.registrazioneesami.ServerMain;

public class NavigationPanel extends JPanel{

	private JButton login = new JButton(ClientMainGUI.EXAMINATOR_PANEL);
	private JButton administration = new JButton(ClientMainGUI.ADMIN_PANEL);
	private JButton confirm = new JButton(ClientMainGUI.CONFIRM_PANEL);
	private JButton settings = new JButton(ClientMainGUI.SETTINGS_PANEL);
	private ClientMainGUI clientGUI = null;

	public NavigationPanel(ClientMainGUI clientGUI){
		setLayout(new FlowLayout());
		add(login);
		add(administration);
		add(confirm);
		add(settings);
		this.clientGUI = clientGUI;

		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(ClientMainGUI.LOGIN_PANEL);
			}
		});
		administration.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(ClientMainGUI.ADMIN_PANEL);

			}
		});

		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(ClientMainGUI.CONFIRM_PANEL);

			}
		});

		settings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(ClientMainGUI.SETTINGS_PANEL);
			}
		});

	}
}
