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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import it.uniclam.ids.gruppo1.registrazioneesami.ClientMainGUI;
import it.uniclam.ids.gruppo1.registrazioneesami.ServerMain;

public class ExamReservationPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private JButton back = new JButton("Indietro");

	private JTextArea ta = new JTextArea(20, 50);

	public ExamReservationPanel(ClientMainGUI clientGUI) {
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new GridBagLayout());
		setBackground(Color.yellow);

		// Campo back
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 5; // 2 columns wide
		this.add(back, c);

		// Campo esami prenotati (label)
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 4; // 2 columns wide
		this.add(new JLabel("Esami Prenotati:"), c);

		// Campo esami prenotati
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 8; // 2 columns wide
		JScrollPane jp = new JScrollPane(ta);
		this.add(jp, c);

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(ClientMainGUI.EXAMINATOR_PANEL);

			}
		});

		try {
			Socket s = new Socket(ServerMain.HOST, ServerMain.PORT);

			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);

			String req = ServerMain.QUERY_VISUALIZZA_PRENOTAZIONI + "\n" + "\n";

			out.println(req);

			String line = in.readLine();
			if (line.isEmpty()) {

				JOptionPane.showMessageDialog(ExamReservationPanel.this, "Non ci sono prenotazioni!", "Info",
						JOptionPane.INFORMATION_MESSAGE);

				s.close();
			} else {
				ta.append("Esame\tMatricola\tData Appello\tData Prenotazione\n");
				while (!line.isEmpty()) {
					String[] temp = line.split(";");
					int i = 0;
					while (i < temp.length) {
						ta.append(temp[i] + "\t");
						i++;
					}
					ta.append("\n");
					line = in.readLine();
				}
				s.close();
			}

		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(ExamReservationPanel.this, "Error in communication with server!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}
}
