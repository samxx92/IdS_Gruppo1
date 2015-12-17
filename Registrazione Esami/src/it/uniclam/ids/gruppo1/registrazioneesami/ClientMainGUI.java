package it.uniclam.ids.gruppo1.registrazioneesami;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import it.uniclam.ids.gruppo1.registrazioneesami.gui.NavigationPanel;
//import it.uniclam.ids.gruppo1.registrazioneesami.gui.SettingsPanel;
//import it.uniclam.ids.gruppo1.registrazioneesami.gui.admin.AdminPanel;
//import it.uniclam.ids.gruppo1.registrazioneesami.gui.admin.ConfirmationAdminPanel;
//import it.uniclam.ids.gruppo1.registrazioneesami.gui.admin.EnablePanel;
import it.uniclam.ids.gruppo1.registrazioneesami.gui.master.ConfirmPanel;
import it.uniclam.ids.gruppo1.registrazioneesami.gui.master.DailyExamPanel;
import it.uniclam.ids.gruppo1.registrazioneesami.gui.master.ExamReservationPanel;
import it.uniclam.ids.gruppo1.registrazioneesami.gui.master.ExaminatorPanel;
import it.uniclam.ids.gruppo1.registrazioneesami.gui.master.LoginPanel;
import it.uniclam.ids.gruppo1.registrazioneesami.gui.master.RegistrationPanel;

public class ClientMainGUI extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	JPanel lastPanel = null;

	public static String LOGIN_PANEL = "Login";
	public static String EXAMINATOR_PANEL = "Esaminatore";
	public static String REGISTRATION_PANEL = "Verbalizzazione";
	public static String EXAM_RESERVATION_PANEL = "Visualizza Prenotazioni Esami";
	public static String DAILY_EXAM_PANEL = "Verbalizzazioni Giornaliere";
	public static String ADMIN_PANEL = "Amministratore";
	public static String ENABLE_PANEL = "Abilitazione Docenti";
	public static String CONFIRMATION_ADMIN_PANEL = "Conferma Esami";
	public static String CONFIRM_PANEL = "Presidente";
	public static String SETTINGS_PANEL = "Settings";

	private LoginPanel loginPanel;
	private RegistrationPanel registrationPanel;
	private ConfirmPanel confirmPanel;
	private NavigationPanel navigationPanel;
	private ExaminatorPanel examinatorPanel;
	private ExamReservationPanel examReservationPanel;
	private DailyExamPanel dailyExamPanel;

	public ClientMainGUI() {
		super("Docente-Presidente");

		loginPanel = new LoginPanel(this);

		changePanel(ClientMainGUI.LOGIN_PANEL);

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		pack();
		this.setSize(1000, 600);
		setVisible(true);

	}

	public void changePanel(String panelName) {
		getContentPane().removeAll();
		if (panelName.equals(ClientMainGUI.LOGIN_PANEL)) {
			loginPanel = new LoginPanel(this);
			lastPanel = loginPanel;
		} else if (panelName.equals(ClientMainGUI.REGISTRATION_PANEL)) {
			registrationPanel = new RegistrationPanel(this);
			lastPanel = registrationPanel;
		} else if (panelName.equals(ClientMainGUI.CONFIRM_PANEL)) {
			confirmPanel = new ConfirmPanel(this);
			lastPanel = confirmPanel;
		} else if (panelName.equals(ClientMainGUI.EXAMINATOR_PANEL)) {
			examinatorPanel = new ExaminatorPanel(this);
			lastPanel = examinatorPanel;
		} else if (panelName.equals(ClientMainGUI.EXAM_RESERVATION_PANEL)) {
			examReservationPanel = new ExamReservationPanel(this);
			lastPanel = examReservationPanel;
		} else if (panelName.equals(ClientMainGUI.DAILY_EXAM_PANEL)) {
			dailyExamPanel = new DailyExamPanel(this);
			lastPanel = dailyExamPanel;
		}

		getContentPane().add(lastPanel, BorderLayout.CENTER);

		if (!panelName.equals(ClientMainGUI.EXAMINATOR_PANEL) && !panelName.equals(ClientMainGUI.EXAM_RESERVATION_PANEL)
				&& !panelName.equals(ClientMainGUI.REGISTRATION_PANEL)
				&& !panelName.equals(ClientMainGUI.DAILY_EXAM_PANEL)) {
			navigationPanel = new NavigationPanel(this);
			getContentPane().add(navigationPanel, BorderLayout.SOUTH);

		}

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
				ClientMainGUI frame = new ClientMainGUI();

			}
		});
	}

}
