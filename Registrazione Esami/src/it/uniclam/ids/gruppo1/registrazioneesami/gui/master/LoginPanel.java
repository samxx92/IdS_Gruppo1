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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import it.uniclam.ids.gruppo1.registrazioneesami.ClientMainGUI;
import it.uniclam.ids.gruppo1.registrazioneesami.ServerMain;

public class LoginPanel extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextField username = new JTextField("", 15);
	private JPasswordField password = new JPasswordField("", 15);

	private JButton login = new JButton("Accedi");

	public LoginPanel(ClientMainGUI clientGUI) {
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
		c.gridwidth = 5; // 2 columns wide
		this.add(login, c);

		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Socket s = new Socket(ServerMain.HOST, ServerMain.PORT);

					BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					PrintWriter out = new PrintWriter(s.getOutputStream(), true);

					@SuppressWarnings("deprecation")
					String req = ServerMain.QUERY_LOGIN + "\n" + "telefono:" + username.getText() + "\n" + "password:"
							+ password.getText() + "\n" + "\n";

					out.println(req);
					String line = in.readLine();

					if (line.equalsIgnoreCase("true")) {
						s.close();
						clientGUI.changePanel(ClientMainGUI.EXAMINATOR_PANEL);
					} else {
						JOptionPane.showMessageDialog(LoginPanel.this, "Utente non abilitato!", "Error",
								JOptionPane.ERROR_MESSAGE);
						s.close();
					}


				} catch (IOException ioe) {
					JOptionPane.showMessageDialog(LoginPanel.this, "Error in communication with server!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		});

	}
}
