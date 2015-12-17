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
	private JButton confirm = new JButton(ClientMainGUI.CONFIRM_PANEL);

	public NavigationPanel(ClientMainGUI clientGUI) {
		setLayout(new FlowLayout());
		add(login);
		add(confirm);

		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(ClientMainGUI.LOGIN_PANEL);
			}
		});

		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(ClientMainGUI.CONFIRM_PANEL);

			}
		});
	}
}
