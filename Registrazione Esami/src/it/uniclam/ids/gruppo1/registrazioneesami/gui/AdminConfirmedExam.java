package it.uniclam.ids.gruppo1.registrazioneesami.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.ChangedCharSetException;

import it.uniclam.ids.gruppo1.registrazioneesami.ClientMainGUI;

public class AdminConfirmedExam extends JPanel{
private JTextArea ta = new JTextArea(20, 50);
	

	private JButton confirm = new JButton("Salva Definitivamente");
	

	
	public AdminConfirmedExam(ClientMainGUI clientGUI){
		//JPanel pane = new JPanel(new GridBagLayout());

		//Container pane = getContentPane();
		// Definisci un oggetto gridbagconstraints per la specifica 
		// dei vincoli dell'interfaccia
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		
		// Campo salva
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 3;
			c.gridwidth = 5;   //2 columns wide
			this.add(confirm, c);
			
				
			// Campo esami (label)
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 4;   //2 columns wide
			this.add(new JLabel("Esami Confermati:"), c);
						
			// Campo esami
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 8;   //2 columns wide
			JScrollPane jp = new JScrollPane(ta);
			this.add(jp, c);
		
		
		
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clientGUI.changePanel(ClientMainGUI.ADMIN_CONFIRMED_EXAM);
				
			}
		});

		
		
	}


}