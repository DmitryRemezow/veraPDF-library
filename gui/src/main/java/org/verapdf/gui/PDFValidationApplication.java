package org.verapdf.gui;

import org.apache.log4j.Logger;
import org.verapdf.gui.tools.GUIConstants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * Main frame of the PDFA Conformance Checker
 *
 * @author Maksim Bezrukov
 */
public class PDFValidationApplication extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = -5569669411392145783L;

	private static final Logger LOGGER = Logger.getLogger(PDFValidationApplication.class);

	private AboutPanel aboutPanel;
	private Properties settings;
	private SettingsPanel settingsPanel;
	private CheckerPanel checkerPanel;

	/**
	 * Creates the frame.
	 */
	public PDFValidationApplication() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(GUIConstants.FRAME_COORD_X, GUIConstants.FRAME_COORD_Y, GUIConstants.FRAME_WIDTH, GUIConstants.FRAME_HEIGHT);
		setResizable(false);

		setTitle(GUIConstants.TITLE);

		loadSettings();

		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		setJMenuBar(menuBar);

		aboutPanel = null;
		try {
			aboutPanel = new AboutPanel();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error in reading logo image.", GUIConstants.ERROR, JOptionPane.ERROR_MESSAGE);
			LOGGER.error("Exception in reading logo image", e);
		}

		try {
			settingsPanel = new SettingsPanel();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(PDFValidationApplication.this, "Error initialising settings panel.", GUIConstants.ERROR, JOptionPane.ERROR_MESSAGE);
			LOGGER.error("Exception in initialising settings panel", e);
		}

		final JMenuItem sett = new JMenuItem("Settings");
		sett.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (settingsPanel != null && settingsPanel.showDialog(PDFValidationApplication.this, "Settings", settings)) {
					settings.setProperty(GUIConstants.PROPERTY_PROCESSING_TYPE, String.valueOf(settingsPanel.getProcessingType()));
					settings.setProperty(GUIConstants.PROPERTY_HIDE_PASSED_RULES, String.valueOf(settingsPanel.isDispPassedRules()));
					settings.setProperty(GUIConstants.PROPERTY_MAX_NUMBER_FAILED_CHECKS, String.valueOf(settingsPanel.getFailedChecksNumber()));
					settings.setProperty(GUIConstants.PROPERTY_MAX_NUMBER_DISPLAYED_FAILED_CHECKS, String.valueOf(settingsPanel.getFailedChecksDisplayNumber()));
					settings.setProperty(GUIConstants.PROPERTY_FEATURES_CONFIG_FILE, settingsPanel.getFeaturesPluginConfigPath());
					saveSettings();
					if (checkerPanel != null) {
						checkerPanel.setSettings(settings);
					}
				}
			}
		});

		menuBar.add(sett);

		JMenuItem about = new JMenuItem("About");
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (aboutPanel != null) {
					aboutPanel.showDialog(PDFValidationApplication.this, "About veraPDF");
				}
			}
		});

		menuBar.add(about);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(GUIConstants.EMPTYBORDER_INSETS, GUIConstants.EMPTYBORDER_INSETS, GUIConstants.EMPTYBORDER_INSETS, GUIConstants.EMPTYBORDER_INSETS));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		setContentPane(contentPane);

		MiniLogoPanel logoPanel = null;
		try {
			logoPanel = new MiniLogoPanel(GUIConstants.LOGO_NAME);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(PDFValidationApplication.this, "Error in creating mini logo.", GUIConstants.ERROR, JOptionPane.ERROR_MESSAGE);
			LOGGER.error("Exception in creating mini logo", e);
		}

		contentPane.add(logoPanel);

		checkerPanel = null;
		try {
			checkerPanel = new CheckerPanel(settings);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(PDFValidationApplication.this, "Error in loading xml or html image.", GUIConstants.ERROR, JOptionPane.ERROR_MESSAGE);
			LOGGER.error("Exception in loading xml or html image", e);
		}
		contentPane.add(checkerPanel);
	}

	private void loadSettings() {
		File configFile = new File("./temp/config.properties");
		settings = new Properties(GUIConstants.DEFAULT_PROPERTIES);
		if (configFile.exists()) {
			try {
				FileReader reader = new FileReader(configFile);
				settings.load(reader);
				reader.close();
			} catch (IOException e) {
				LOGGER.error("Couldn't load config file", e);
			}
		}
	}

	private void saveSettings() {
		try {
			File dir = new File("./temp/");
			if (!dir.exists() && !dir.mkdir()) {
				throw new IOException("Can not create temporary directory.");
			}
			File configFile = new File("./temp/config.properties");
			FileWriter writer = new FileWriter(configFile);
			settings.store(writer, "settings");
			writer.close();
		} catch (IOException e) {
			LOGGER.error("Couldn't save config into file", e);
		}
	}

	/**
	 * Starting point of the gui
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(
							UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
					LOGGER.error("Exception in configuring UI manager", e);
				}
				try {
					PDFValidationApplication frame = new PDFValidationApplication();
					frame.setVisible(true);
				} catch (Exception e) {
					LOGGER.error("Exception", e);
				}
			}
		});
	}

}
