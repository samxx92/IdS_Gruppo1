/*package it.uniclam.ids.gruppo1.registrazioneesami.gui.admin;
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
import it.uniclam.ids.gruppo1.registrazioneesami.gui.AdminPanel;

public class AdminNavigationPanel extends JPanel{
	private JButton enable = new JButton(AdminPanel.CONFIRMATION_ADMIN_PANEL);
	private JButton registration = new JButton(AdminPanel.STORE_EXAM_ADMIN_PANEL);
	private AdminPanel clientGUI = null;

	public AdminNavigationPanel(AdminPanel clientGUI){
		setLayout(new FlowLayout());
		add(cercaAmici);
		add(inserisciAmici);
		add(settings);
		add(enable);
		add(registration);
		this.clientGUI = clientGUI;



		enable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(AdminPanel.CONFIRMATION_ADMIN_PANEL);
			}
		});

		registration.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(AdminPanel.STORE_EXAM_ADMIN_PANEL);
			}
		});


}
}
 */