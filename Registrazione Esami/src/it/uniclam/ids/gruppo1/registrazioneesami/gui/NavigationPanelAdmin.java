package it.uniclam.ids.gruppo1.registrazioneesami.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.uniclam.ids.gruppo1.registrazioneesami.AdminMainGUI;
import it.uniclam.ids.gruppo1.registrazioneesami.ClientMainGUI;

public class NavigationPanelAdmin extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JButton administration = new JButton(ClientMainGUI.ADMIN_PANEL);
	private JButton settings = new JButton(ClientMainGUI.SETTINGS_PANEL);

	public NavigationPanelAdmin(AdminMainGUI adminGUI) {
		setLayout(new FlowLayout());
		add(administration);
		add(settings);

		administration.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adminGUI.changePanel(AdminMainGUI.ADMIN_PANEL);

			}
		});

		settings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adminGUI.changePanel(AdminMainGUI.SETTINGS_PANEL);
			}
		});

	}
}
