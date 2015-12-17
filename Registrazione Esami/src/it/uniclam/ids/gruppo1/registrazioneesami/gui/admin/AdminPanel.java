package it.uniclam.ids.gruppo1.registrazioneesami.gui.admin;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.uniclam.ids.gruppo1.registrazioneesami.AdminMainGUI;
import it.uniclam.ids.gruppo1.registrazioneesami.ClientMainGUI;

public class AdminPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JButton enable = new JButton(ClientMainGUI.ENABLE_PANEL);
	private JButton registration = new JButton(ClientMainGUI.CONFIRMATION_ADMIN_PANEL);
	private Icon cellEx = new ImageIcon("img/sa.png");

	public AdminPanel(AdminMainGUI adminMainGUI) {
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new GridBagLayout());
		setBackground(Color.orange);

		// campo icona
		JLabel cell_Ex = new JLabel(cellEx);
		// c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		this.add(cell_Ex, c);

		// Campo enable
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 5; // 2 columns wide
		this.add(enable, c);

		// Campo registration
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 5; // 2 columns wide
		this.add(registration, c);

		enable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				adminMainGUI.changePanel(AdminMainGUI.ENABLE_PANEL);

			}
		});

		registration.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				adminMainGUI.changePanel(AdminMainGUI.CONFIRMATION_ADMIN_PANEL);

			}
		});

	}
}
