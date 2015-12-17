package it.uniclam.ids.gruppo1.registrazioneesami.gui.master;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import it.uniclam.ids.gruppo1.registrazioneesami.ClientMainGUI;
import it.uniclam.ids.gruppo1.registrazioneesami.ServerMain;

public class RegistrationPanel extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextField id_Esame = new JTextField("", 15);
	private JTextField id_Studente = new JTextField("", 15);
	private JTextField data_Appello = new JTextField("", 15);

	private JButton verbalizza = new JButton("Verbalizza");
	private JButton clear = new JButton("Pulisci");
	private JButton back = new JButton("Indietro");

	private JTextArea ta = new JTextArea(12, 12);

	public RegistrationPanel(ClientMainGUI clientGUI) {
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new GridBagLayout());
		setBackground(Color.yellow);

		NumberFormat longFormat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numberFormatter.setValueClass(Integer.class); // optional, ensures you
														// will always get a
														// long value
		numberFormatter.setAllowsInvalid(false); // this is the key!!
		// numberFormatter.setMinimum(18); //Optional
		// numberFormatter.setMaximum(31);
		JFormattedTextField valutazione = new JFormattedTextField(numberFormatter);

		// Campo id_esame
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		this.add(new JLabel("ID Esame:"), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		this.add(id_Esame, c);

		// Campo id_studente
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		this.add(new JLabel("ID Studente:"), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		this.add(id_Studente, c);

		// Campo data_appello
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		this.add(new JLabel("Data Appello:"), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		this.add(data_Appello, c);

		// Campo valutazione
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		this.add(new JLabel("Voto:"), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		this.add(valutazione, c);

		// Campo verbalizza
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 5; // 2 columns wide
		this.add(verbalizza, c);

		// Campo back
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 2; // 2 columns wide
		this.add(back, c);

		// Campo risposta (label)
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 4; // 2 columns wide
		this.add(new JLabel("Risposta:"), c);

		// Campo risposta
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 8; // 2 columns wide
		JScrollPane jp = new JScrollPane(ta);
		this.add(jp, c);

		// Campo clear
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 4;
		c.gridwidth = 2; // 2 columns wide
		this.add(clear, c);

		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ta.setText("");
			}
		});

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(ClientMainGUI.EXAMINATOR_PANEL);

			}
		});

		verbalizza.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					String voto = valutazione.getText();
					if (voto.equalsIgnoreCase("") || id_Esame.getText().equalsIgnoreCase("")
							|| id_Studente.getText().equalsIgnoreCase("")
							|| data_Appello.getText().equalsIgnoreCase("")) {
						JOptionPane.showMessageDialog(RegistrationPanel.this, "I campi non possono essere nulli!",
								"Error", JOptionPane.ERROR_MESSAGE);
					} else {
						int voto_int = Integer.parseInt(voto);

						if (voto_int < 18) {
							JOptionPane.showMessageDialog(RegistrationPanel.this,
									"Non si può inserire un voto inferiore a 18/30", "Error",
									JOptionPane.ERROR_MESSAGE);
						} else if (voto_int > 31) {
							JOptionPane.showMessageDialog(RegistrationPanel.this,
									"Non si può inserire un voto superiore a 30/30 e lode", "Error",
									JOptionPane.ERROR_MESSAGE);
						} else {
							Socket s = new Socket(ServerMain.HOST, ServerMain.PORT);

							BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
							PrintWriter out = new PrintWriter(s.getOutputStream(), true);

							String req = ServerMain.QUERY_VERBALIZZA + "\n" + "id_esame:" + id_Esame.getText() + "\n"
									+ "id_studente:" + id_Studente.getText() + "\n" + "valutazione:"
									+ valutazione.getText() + "\n" + "data_appello:" + data_Appello.getText() + "\n"
									+ "\n";

							out.println(req);

							String line = in.readLine();

							if (line.equals("true")) {

								line = in.readLine();
								if (line.equals("true")) {
									ta.append("L'esame è stato trovato nelle prenotazioni\n");
									line = in.readLine();
									if (line.equals("true")) {
										ta.append("L'esame è stato verbalizzato!");
									} else {
										JOptionPane.showMessageDialog(RegistrationPanel.this,
												"L'esame è già presente nel database!", "Info",
												JOptionPane.INFORMATION_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(RegistrationPanel.this,
											"L'esame non è stato trovato!", "Error", JOptionPane.ERROR_MESSAGE);
								}

								s.close();

							}

							else {
								JOptionPane.showMessageDialog(RegistrationPanel.this,
										"I dati della verbalizzazione non sono corretti!", "Error",
										JOptionPane.ERROR_MESSAGE);
								s.close();
							}

						}
					}

				} catch (IOException ioe) {
					JOptionPane.showMessageDialog(RegistrationPanel.this, "Error in communication with server!",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}

}
