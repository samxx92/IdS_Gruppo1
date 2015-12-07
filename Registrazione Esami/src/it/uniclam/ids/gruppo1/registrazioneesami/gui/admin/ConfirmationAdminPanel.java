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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import it.uniclam.ids.gruppo1.registrazioneesami.ClientMainGUI;
import it.uniclam.ids.gruppo1.registrazioneesami.ServerMain;

public class ConfirmationAdminPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JButton salva = new JButton("Salva in S3");
	private JTextArea ta = new JTextArea(20, 50);

	private JButton back = new JButton("Indietro");

	public ConfirmationAdminPanel(ClientMainGUI clientGUI) {
		// JPanel pane = new JPanel(new GridBagLayout());

		// Container pane = getContentPane();
		// Definisci un oggetto gridbagconstraints per la specifica
		// dei vincoli dell'interfaccia
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(new GridBagLayout());

		// Campo salva
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 2; // 2 columns wide
		this.add(salva, c);

		// Campo esami (label)
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 4; // 2 columns wide
		this.add(new JLabel("Esami Verbalizzati in data odierna:"), c);

		// Campo esami
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 8; // 2 columns wide
		JScrollPane jp = new JScrollPane(ta);
		this.add(jp, c);

		// Campo indietro
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2; // 2 columns wide
		this.add(back, c);
		
		List<String> esami_verbalizzati = new ArrayList<String>();

		try {
			Socket s = new Socket(ServerMain.HOST, ServerMain.PORT);

			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);

			String req = ServerMain.QUERY_VISUALIZZA_VERBALIZZAZIONI_GIORNALIERE + "\n" + "\n";

			out.println(req);
			// System.out.println("Inviato: " + req);
			String line = in.readLine();
			if (line.isEmpty()) {
				JOptionPane.showMessageDialog(ConfirmationAdminPanel.this,
						"Non sono stati verbalizzati esami in giornata!", "Info", JOptionPane.INFORMATION_MESSAGE);
				s.close();
			} else {
				ta.append("Esame\tDocente\tMatricola\tVoto\tData Appello\tData Verbalizzazione\n");
				while (!line.isEmpty()) {
					esami_verbalizzati.add(line);
					String[] temp = line.split(";"); 
					int i = 0;
					while(i<temp.length){
						ta.append(temp[i] + "\t");
						i++;
					}
					ta.append("\n");
					line = in.readLine();
				}
				s.close();
			}

		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(ConfirmationAdminPanel.this, "Error in communication with server!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

		salva.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (ta.getText().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(ConfirmationAdminPanel.this, "Non ci sono elementi da salvare!", "Info",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					try {
						Socket s = new Socket(ServerMain.HOST, ServerMain.PORT);

						BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
						PrintWriter out = new PrintWriter(s.getOutputStream(), true);

						String req = ServerMain.QUERY_SALVA_ESAMI_IN_S3 + "\n";
						for (int i = 0;i<esami_verbalizzati.size(); i++){
							req+=esami_verbalizzati.get(i);
							req+="\n";
						}

						out.println(req);
						//System.out.println("Inviato: " + req);
						String line = in.readLine();
						if (line.equalsIgnoreCase(ServerMain.OK)){
							ta.setText("");
							JOptionPane.showMessageDialog(ConfirmationAdminPanel.this, "Gli esami sono stati salvati correttamente in S3", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						}

						s.close();


					} catch (IOException ioe) {
						JOptionPane.showMessageDialog(ConfirmationAdminPanel.this, "Error in communication with server!", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
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
