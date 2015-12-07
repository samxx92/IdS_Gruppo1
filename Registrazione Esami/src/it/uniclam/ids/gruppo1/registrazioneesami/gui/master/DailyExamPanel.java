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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import it.uniclam.ids.gruppo1.registrazioneesami.ClientMainGUI;
import it.uniclam.ids.gruppo1.registrazioneesami.ServerMain;

public class DailyExamPanel extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private JButton back = new JButton("Indietro");

	private JTextArea ta = new JTextArea(20, 50);

	public DailyExamPanel(ClientMainGUI clientGUI) {
		// JPanel pane = new JPanel(new GridBagLayout());

		// Container pane = getContentPane();
		// Definisci un oggetto gridbagconstraints per la specifica
		// dei vincoli dell'interfaccia
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(new GridBagLayout());

		// Campo back
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 5; // 2 columns wide
		this.add(back, c);

		// Campo esami verbalizzati in data odierna(label)
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 4; // 2 columns wide
		this.add(new JLabel("Esami Verbalizzati in data odierna:"), c);

		// Campo esami verbalizzati in data odierna
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 8; // 2 columns wide
		JScrollPane jp = new JScrollPane(ta);
		this.add(jp, c);

		try {
			Socket s = new Socket(ServerMain.HOST, ServerMain.PORT);

			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);

			String req = ServerMain.QUERY_VISUALIZZA_VERBALIZZAZIONI_GIORNALIERE_DOCENTE + "\n" + "\n";

			out.println(req);
			// System.out.println("Inviato: " + req);
			String line = in.readLine();
			if (line.isEmpty()) {
				JOptionPane.showMessageDialog(DailyExamPanel.this,
						"Non sono stati verbalizzati esami in giornata!", "Info", JOptionPane.INFORMATION_MESSAGE);
				
				s.close();
			} else {
				while (!line.isEmpty()) {

					ta.append(line + "\n");
					line = in.readLine();
				}
				s.close();
			}

		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(DailyExamPanel.this, "Error in communication with server!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}



		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(ClientMainGUI.EXAMINATOR_PANEL);

			}
		});

	}

}
