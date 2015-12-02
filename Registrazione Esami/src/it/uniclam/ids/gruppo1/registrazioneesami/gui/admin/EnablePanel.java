package it.uniclam.ids.gruppo1.registrazioneesami.gui.admin;

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

public class EnablePanel extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextField telefono = new JTextField("", 15);
	private JTextField password = new JTextField("", 15);

	private JButton enable = new JButton("Abilita");
	private JButton passwordRecovery = new JButton("Recupera Password");
	private JButton back = new JButton("Indietro");

	private JTextArea ta = new JTextArea(12, 12);

	public EnablePanel(ClientMainGUI clientGUI) {
		// JPanel pane = new JPanel(new GridBagLayout());

		// Container pane = getContentPane();
		// Definisci un oggetto gridbagconstraints per la specifica
		// dei vincoli dell'interfaccia
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(new GridBagLayout());

		// Campo telefono
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		this.add(new JLabel("Telefono:"), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		this.add(telefono, c);

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

		// Campo abilita
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 11;
		c.gridwidth = 2; // 2 columns wide
		this.add(enable, c);

		// Campo recupera password
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 5; // 2 columns wide
		this.add(passwordRecovery, c);

		// Campo docenti abilitati (label)
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 5; // 2 columns wide
		this.add(new JLabel("Docenti Abilitati:"), c);

		// Campo docenti abilitati
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 8; // 2 columns wide
		JScrollPane jp = new JScrollPane(ta);
		this.add(jp, c);

		// Campo indietro
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 11;
		c.gridwidth = 2; // 2 columns wide
		this.add(back, c);

		try {
			Socket s = new Socket(ServerMain.HOST, ServerMain.PORT);

			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);

			String req = ServerMain.QUERY_VISUALIZZA_DOCENTI_ABILITATI + "\n" + "\n";

			out.println(req);
			// System.out.println("Inviato: " + req);
			String line = in.readLine();
			if (line.equalsIgnoreCase(ServerMain.OK)) {
				line = in.readLine();
				if (line.isEmpty()) {
					ta.append("Non ci sono Docenti Abilitati");
					s.close();
				} else {
					while (!line.isEmpty()) {

						ta.append(line + "\n");
						line = in.readLine();
					}
					s.close();
				}
			}

		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(EnablePanel.this, "Error in communication with server!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

		enable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					if (telefono.getText().equalsIgnoreCase("") || password.getText().equalsIgnoreCase("")) {
						JOptionPane.showMessageDialog(EnablePanel.this, "Nessun campo può essere nullo!", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Socket s = new Socket(ServerMain.HOST, ServerMain.PORT);

						BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
						PrintWriter out = new PrintWriter(s.getOutputStream(), true);

						String req = ServerMain.QUERY_ABILITA_DOCENTE + "\n" + "telefono:" + telefono.getText() + "\n"
								+ "password:" + password.getText() + "\n" + "\n";

						out.println(req);

						String line = in.readLine();
						if (line.equalsIgnoreCase(ServerMain.OK)) {
							line = in.readLine();
							if (line.equalsIgnoreCase("gia_abilitato")) {
								JOptionPane.showMessageDialog(EnablePanel.this,
										"Il Docente è già abilitato o non fa parte dell'Ateneo!", "Error",
										JOptionPane.ERROR_MESSAGE);
							} else {
								ta.append(line + "\n");
							}

						}
						s.close();
					}

				} catch (IOException ioe) {
					JOptionPane.showMessageDialog(EnablePanel.this, "Error in communication with server!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		passwordRecovery.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Socket s = new Socket(ServerMain.HOST, ServerMain.PORT);

					BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					PrintWriter out = new PrintWriter(s.getOutputStream(), true);

					String req = ServerMain.QUERY_RECUPERA_PASSWORD + "\n" + telefono.getText() + "\n" + "\n";

					out.println(req);

					// System.out.println("Inviato: " + req);
					String line = in.readLine();
					if (line.equalsIgnoreCase(ServerMain.OK)) {
						line = in.readLine();
						if (line.isEmpty()) {
							JOptionPane.showMessageDialog(EnablePanel.this, "Il Docente non è abilitato!", "Error",
									JOptionPane.ERROR_MESSAGE);
							;
							s.close();
						} else {
							// password.setText(line);
							JOptionPane.showMessageDialog(EnablePanel.this, "La password è: " + line, "Password",
									JOptionPane.INFORMATION_MESSAGE);
							;
							s.close();
						}
					}
				} catch (IOException ioe) {
					JOptionPane.showMessageDialog(EnablePanel.this, "Error in communication with server!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(ClientMainGUI.ADMIN_PANEL);

			}
		});
	}
}
