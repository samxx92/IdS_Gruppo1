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
import javax.swing.JTextField;

import it.uniclam.ids.gruppo1.registrazioneesami.ClientMainGUI;
import it.uniclam.ids.gruppo1.registrazioneesami.ServerMain;

public class RegistrationPanel extends JPanel{
	private JTextField id_Esame = new JTextField("", 15);
	private JTextField id_Studente = new JTextField("", 15);
	private JTextField data_Appello = new JTextField("", 15);
	private JTextField valutazione = new JTextField("", 15);
	
	private JButton verbalizza = new JButton("Verbalizza");
	private JButton clear = new JButton("Pulisci");
	private JButton back = new JButton("Indietro");
	
	private JTextArea ta = new JTextArea(12, 12);
	

	public RegistrationPanel(ClientMainGUI clientGUI){
		//JPanel pane = new JPanel(new GridBagLayout());

		//Container pane = getContentPane();
		// Definisci un oggetto gridbagconstraints per la specifica 
		// dei vincoli dell'interfaccia
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
			
		
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
		c.gridwidth = 5;   //2 columns wide
		this.add(verbalizza, c);
		
		// Campo back
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 2;   //2 columns wide
		this.add(back, c);
		
		// Campo risposta (label)
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 4;   //2 columns wide
		this.add(new JLabel("Risposta:"), c);
		
		// Campo risposta
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 8;   //2 columns wide
		JScrollPane jp = new JScrollPane(ta);
		this.add(jp, c);
		
		// Campo clear
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 4;
		c.gridwidth = 2;   //2 columns wide
		this.add(clear, c);
		
		
		clear.addActionListener(new ActionListener(){
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
				try{
					Socket s = new Socket(ServerMain.HOST, ServerMain.PORT);
					
					BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					PrintWriter out = new PrintWriter(s.getOutputStream(), true);
					
					String req = 
							ServerMain.QUERY_VERBALIZZA + "\n" + 
							"id_esame:" + id_Esame.getText() + "\n" + 
							"id_studente:" + id_Studente.getText() + "\n"+ 
							"valutazione:" + valutazione.getText() + "\n"+ 
							"data_appello:" + data_Appello.getText() + "\n"+ 
							"\n";
					
					out.println(req);
					
					
					
					//System.out.println("Inviato: " + req);
					/*String line = in.readLine();
					if (line.equalsIgnoreCase(ServerMain.OK)){
						line = in.readLine();
						if (line.equalsIgnoreCase("true")) {
							s.close();
							clientGUI.changePanel(ClientMainGUI.EXAMINATOR_PANEL);
						}
						else{
							JOptionPane.showMessageDialog(RegistrationPanel.this, "Utente non abilitato!", "Error", JOptionPane.ERROR_MESSAGE);
							s.close();
						}
					}*/
					
					
						
				} catch (IOException ioe){
					JOptionPane.showMessageDialog(RegistrationPanel.this, "Error in communication with server!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			}
		});
	
	}
	
}
