package it.uniclam.ids.gruppo1.registrazioneesami.gui.admin;


import java.awt.Dimension;
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import it.uniclam.ids.gruppo1.registrazioneesami.ClientMainGUI;
import it.uniclam.ids.gruppo1.registrazioneesami.ServerMain;


public class ConfirmationAdminPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JButton salva = new JButton("Salva");

	private JTable table = new JTable(new DefaultTableModel(null, new Object[]{"Id Esame",
			"Id Docente", "Id Studente", "Voto", "Data Appello", "Data Verbalizzazione", "Confermato", 
	"Scaduto"}));

	private JButton back = new JButton("Indietro");

	private JButton save = new JButton("Salva Definitivamente");

	private JButton delete = new JButton("Cancella Esami Scaduti");

	public ConfirmationAdminPanel(ClientMainGUI clientGUI) {
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(new GridBagLayout());


		// Campo salva
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 2; // 2 columns wide
		this.add(salva, c);

		// Campo elimina
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 3;
		c.gridwidth = 1; // 2 columns wide
		this.add(delete, c);

		// Campo salva
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 3;
		c.gridwidth = 1; // 2 columns wide
		this.add(save, c);

		// Campo esami (label)
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 4; // 2 columns wide
		this.add(new JLabel("Esami Verbalizzati:"), c);

		//Campo esami
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 6; // 2 columns wide
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		Dimension d = new Dimension();
		d.setSize(800, 400);
		scrollPane.setPreferredSize(d);
		this.add(scrollPane, c);


		// Campo indietro
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2; // 2 columns wide
		this.add(back, c);

		try {
			Socket s = new Socket(ServerMain.HOST, ServerMain.PORT);

			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);

			String req = ServerMain.QUERY_VISUALIZZA_VERBALIZZAZIONI + "\n" + "\n";

			out.println(req);
			String line = in.readLine();
			if (line.isEmpty()) {
				JOptionPane.showMessageDialog(ConfirmationAdminPanel.this,
						"Non sono stati verbalizzati esami in giornata!", "Info", JOptionPane.INFORMATION_MESSAGE);
				s.close();
			} else {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				while (!line.isEmpty()) {
					String[] temp = line.split(";"); 
					model.addRow(new Object[]{temp[0],temp[1],temp[2],temp[3],temp[4],temp[5],temp[6],temp[7]});
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
				try {
					Socket s = new Socket(ServerMain.HOST, ServerMain.PORT);

					BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					PrintWriter out = new PrintWriter(s.getOutputStream(), true);

					String req = ServerMain.QUERY_CONFERMA_ESAMI + "\n";
					req+="admin\n";
					int [] index = table.getSelectedRows();
					String confermato1 = "admin";
					for (int i = 0;i<index.length;i++){
						if (!table.getValueAt(index[i], 6).equals(confermato1)){
							req+=table.getValueAt(index[i], 7)+"\n";
							req+= table.getValueAt(index[i], 0) + "" +
									table.getValueAt(index[i], 2 )+ "\n";
						}
					}
					out.println(req);
					String line = in.readLine();
					while(!line.isEmpty()){
						JOptionPane.showMessageDialog(ConfirmationAdminPanel.this, line, "info",
								JOptionPane.INFORMATION_MESSAGE);
						line=in.readLine();
					}

					s.close();


				} catch (IOException ioe) {
					JOptionPane.showMessageDialog(ConfirmationAdminPanel.this, "Error in communication with server!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				clientGUI.changePanel(ClientMainGUI.CONFIRMATION_ADMIN_PANEL);



			}
		});

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Socket s = new Socket(ServerMain.HOST, ServerMain.PORT);

					BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					PrintWriter out = new PrintWriter(s.getOutputStream(), true);

					String req = ServerMain.QUERY_CONFERMA_ESAMI + "\n";
					req+="true\n";
					int [] index = table.getSelectedRows();
					String confermato1 = "presidente";
					for (int i = 0;i<index.length;i++){
						if (table.getValueAt(index[i], 6).equals(confermato1)){
							req+= table.getValueAt(index[i], 0) + "" +
									table.getValueAt(index[i], 2 )+ "\n";
						}
					}
					out.println(req);
					String line = in.readLine();
					if (line.equalsIgnoreCase(ServerMain.OK)){
						JOptionPane.showMessageDialog(ConfirmationAdminPanel.this, "Gli esami sono stati salvati correttamente in S3", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					}

					s.close();


				} catch (IOException ioe) {
					JOptionPane.showMessageDialog(ConfirmationAdminPanel.this, "Error in communication with server!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

				clientGUI.changePanel(ClientMainGUI.CONFIRMATION_ADMIN_PANEL);

			}
		});

		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Socket s = new Socket(ServerMain.HOST, ServerMain.PORT);

					BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					PrintWriter out = new PrintWriter(s.getOutputStream(), true);

					String req = ServerMain.QUERY_CANCELLA_ESAMI_SCADUTI + "\n";
					out.println(req);
					String line = in.readLine();
					if (line.equalsIgnoreCase(ServerMain.OK)){
						JOptionPane.showMessageDialog(ConfirmationAdminPanel.this, "Gli esami scaduti sono stati cancellati", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					}

					s.close();


				} catch (IOException ioe) {
					JOptionPane.showMessageDialog(ConfirmationAdminPanel.this, "Error in communication with server!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}		
				clientGUI.changePanel(ClientMainGUI.CONFIRMATION_ADMIN_PANEL);

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
