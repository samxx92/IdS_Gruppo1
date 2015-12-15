package it.uniclam.ids.gruppo1.registrazioneesami;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import it.uniclam.ids.gruppo1.registrazioneesami.gui.NavigationPanelAdmin;
import it.uniclam.ids.gruppo1.registrazioneesami.gui.SettingsPanel;
import it.uniclam.ids.gruppo1.registrazioneesami.gui.admin.AdminPanel;
import it.uniclam.ids.gruppo1.registrazioneesami.gui.admin.ConfirmationAdminPanel;
import it.uniclam.ids.gruppo1.registrazioneesami.gui.admin.EnablePanel;

public class AdminMainGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	JPanel lastPanel = null;


	public static String ADMIN_PANEL = "Amministratore";
	public static String ENABLE_PANEL = "Abilitazione Docenti";
	public static String CONFIRMATION_ADMIN_PANEL = "Conferma Esami";
	public static String SETTINGS_PANEL = "Settings";


	// private AdminNavigationPanel adminNavigationPanel;

	private AdminPanel adminPanel;
	private EnablePanel enablePanel;
	private ConfirmationAdminPanel confirmationAdminPanel;
	private SettingsPanel settingsPanel;
	private NavigationPanelAdmin navigationPanelAdmin;


	public AdminMainGUI() {
		super("Client GUI for CellEx");

		adminPanel = new AdminPanel(this);

		changePanel(AdminMainGUI.ADMIN_PANEL);

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		pack();
		this.setSize(1000,600);
		setVisible(true);


	}

	public void changePanel(String panelName) {
		getContentPane().removeAll();
		if (panelName.equals(AdminMainGUI.SETTINGS_PANEL)) {
			settingsPanel = new SettingsPanel(this);
			lastPanel = settingsPanel;
		} else if (panelName.equals(AdminMainGUI.ADMIN_PANEL)) {
			adminPanel = new AdminPanel(this);
			lastPanel = adminPanel;
		} else if (panelName.equals(AdminMainGUI.ENABLE_PANEL)) {
			enablePanel = new EnablePanel(this);
			lastPanel = enablePanel;
		} else if (panelName.equals(AdminMainGUI.CONFIRMATION_ADMIN_PANEL)) {
			confirmationAdminPanel = new ConfirmationAdminPanel(this);
			lastPanel = confirmationAdminPanel;
		}



		getContentPane().add(lastPanel, BorderLayout.CENTER);
		navigationPanelAdmin = new NavigationPanelAdmin(this);
		getContentPane().add(navigationPanelAdmin, BorderLayout.SOUTH);

		getContentPane().revalidate();
	}

	public static void main(String[] args) {
		/* Use an appropriate Look and Feel */
		try {
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		/* Turn off metal's use of bold fonts */
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				@SuppressWarnings("unused")
				AdminMainGUI frame = new AdminMainGUI();

			}
		});
	}


}
