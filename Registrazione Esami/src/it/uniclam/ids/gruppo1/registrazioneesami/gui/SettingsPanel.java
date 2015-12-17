package it.uniclam.ids.gruppo1.registrazioneesami.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.uniclam.ids.gruppo1.registrazioneesami.AdminMainGUI;
import it.uniclam.ids.gruppo1.registrazioneesami.ServerMain;

public class SettingsPanel extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextField host = new JTextField("localhost");
	private JTextField port = new JTextField("5555", 20);

	private JButton salva = new JButton("Salva");

	public SettingsPanel(AdminMainGUI adminMainGUI) {
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new GridBagLayout());
		setBackground(Color.orange);

		// Campo host
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		this.add(new JLabel("Host:"), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		this.add(host, c);

		// Campo port
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 0;
		c.gridwidth = 2;
		this.add(new JLabel("Port:"), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 6;
		c.gridy = 0;
		this.add(port, c);

		// Campo salva
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 5; // 2 columns wide
		this.add(salva, c);

		salva.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ServerMain.HOST = host.getText();
				ServerMain.PORT = Integer.parseInt(port.getText());
			}
		});

	}

}
