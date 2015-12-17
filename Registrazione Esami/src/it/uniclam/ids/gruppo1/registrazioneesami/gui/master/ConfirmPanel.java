package it.uniclam.ids.gruppo1.registrazioneesami.gui.master;

import java.awt.Color;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import it.uniclam.ids.gruppo1.registrazioneesami.ClientMainGUI;
import it.uniclam.ids.gruppo1.registrazioneesami.ServerMain;

public class ConfirmPanel extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private JButton confirm = new JButton("Conferma");

	private JTable table = new JTable(new DefaultTableModel(null,
			new Object[] { "Id Esame", "Id Docente", "Id Studente", "Voto", "Data Appello", "Data Verbalizzazione" }));

	private JTextField presidente = new JTextField();

	private JButton view = new JButton("Visualizza");

	public ConfirmPanel(ClientMainGUI clientGUI) {

		GridBagConstraints c = new GridBagConstraints();
		setLayout(new GridBagLayout());
		setBackground(Color.yellow);

		// Campo conferma
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 5; // 2 columns wide*/
		this.add(confirm, c);

		// Campo esami verbalizzati(label)
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 4; // 2 columns wide
		this.add(new JLabel("Esami Verbalizzati:"), c);

		// Campo esami verbalizzati
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 8; // 2 columns wide
		// table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		Dimension d = new Dimension();
		d.setSize(800, 400);
		scrollPane.setPreferredSize(d);
		this.add(scrollPane, c);

		// Campo id presidente (label)
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2; // 2 columns wide
		this.add(new JLabel("Id Presidente:"), c);

		// Campo id presidente
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 2; // 2 columns wide
		this.add(presidente, c);

		// Campo pulsante visualizza
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 0;
		c.gridwidth = 2; // 2 columns wide
		this.add(view, c);

		view.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					while (model.getRowCount() > 0) {
						// System.out.println(model.getRowCount());
						model.removeRow(0);
					}
					Socket s = new Socket(ServerMain.HOST, ServerMain.PORT);

					BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					PrintWriter out = new PrintWriter(s.getOutputStream(), true);

					String req = ServerMain.QUERY_VISUALIZZA_ESAMI_PRESIDENTE + "\n";
					req += presidente.getText() + "\n";
					out.println(req);
					String line = in.readLine();
					if (line.isEmpty()) {
						JOptionPane.showMessageDialog(ConfirmPanel.this, "Non sono presenti esami da confermare",
								"Info", JOptionPane.INFORMATION_MESSAGE);

						s.close();
					} else {
						while (!line.isEmpty()) {
							String[] temp = line.split(";");
							model.addRow(new Object[] { temp[0], temp[1], temp[2], temp[3], temp[4], temp[5] });
							line = in.readLine();
						}
						s.close();
					}

				} catch (IOException ioe) {
					JOptionPane.showMessageDialog(ConfirmPanel.this, "Error in communication with server!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Socket s = new Socket(ServerMain.HOST, ServerMain.PORT);

					BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					PrintWriter out = new PrintWriter(s.getOutputStream(), true);

					String req = ServerMain.QUERY_CONFERMA_ESAMI + "\n";
					req += "presidente\n";
					int[] index = table.getSelectedRows();
					for (int i = 0; i < index.length; i++) {
						req += table.getValueAt(index[i], 0) + "" + table.getValueAt(index[i], 2) + "\n";
					}
					out.println(req);
					String line = in.readLine();
					if (line.equalsIgnoreCase(ServerMain.OK)) {
						JOptionPane.showMessageDialog(ConfirmPanel.this, "Conferma eseguita con successo!", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					}

					s.close();

				} catch (IOException ioe) {
					JOptionPane.showMessageDialog(ConfirmPanel.this, "Error in communication with server!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int[] index = table.getSelectedRows();
				int i = 0;
				while (i < index.length) {
					if (i != 0) {
						index[i] = index[i] - 1;
					}
					model.removeRow(index[i]);
					i++;

				}

			}

		});

	}

}
