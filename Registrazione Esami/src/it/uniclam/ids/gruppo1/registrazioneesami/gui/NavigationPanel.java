package it.uniclam.ids.gruppo1.registrazioneesami.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.uniclam.ids.gruppo1.registrazioneesami.ClientMainGUI;

public class NavigationPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JButton login = new JButton(ClientMainGUI.EXAMINATOR_PANEL);
	private JButton administration = new JButton(ClientMainGUI.ADMIN_PANEL);
	private JButton confirm = new JButton(ClientMainGUI.CONFIRM_PANEL);
	private JButton settings = new JButton(ClientMainGUI.SETTINGS_PANEL);

	public NavigationPanel(ClientMainGUI clientGUI) {
		setLayout(new FlowLayout());
		add(login);
		add(administration);
		add(confirm);
		add(settings);
		// this.clientGUI = clientGUI;

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
