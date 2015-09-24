package org.verapdf.gui;

import org.verapdf.gui.tools.GUIConstants;
import org.verapdf.gui.tools.SettingsHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Settings Panel
 *
 * @author Maksim Bezrukov
 */
class SettingsPanel extends JPanel {

	private JButton okButton;
	private boolean ok;
	JDialog dialog;
	private JTextField numberOfFailed;
	private JTextField numberOfFailedDisplay;
	private JRadioButton valAndFeat;
	private JRadioButton val;
	private JRadioButton feat;
	private JCheckBox hidePassedRules;
	private JTextField thirdPartyProfilePathField;
	private JFileChooser chooser;

	/**
	 * Settings panel
	 */
	public SettingsPanel() throws IOException {
		setBorder(new EmptyBorder(GUIConstants.EMPTYBORDER_INSETS, GUIConstants.EMPTYBORDER_INSETS, GUIConstants.EMPTYBORDER_INSETS, GUIConstants.EMPTYBORDER_INSETS));
		setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		ButtonGroup bGroup = new ButtonGroup();
		panel.setLayout(new GridLayout(8, 2));

		panel.add(new JPanel());
		valAndFeat = new JRadioButton(GUIConstants.VALIDATING_AND_FEATURES);
		bGroup.add(valAndFeat);
		panel.add(valAndFeat);
		panel.add(new JLabel(GUIConstants.PROCESSING_TYPE));
		val = new JRadioButton(GUIConstants.VALIDATING);
		bGroup.add(val);
		panel.add(val);
		panel.add(new JPanel());
		feat = new JRadioButton(GUIConstants.FEATURES);
		bGroup.add(feat);
		panel.add(feat);

		panel.add(new JLabel(GUIConstants.DISPLAY_PASSED_RULES));
		hidePassedRules = new JCheckBox();
		panel.add(hidePassedRules);
		panel.add(new JLabel(GUIConstants.MAX_NUMBER_FAILED_CHECKS));

		numberOfFailed = new JTextField();
		numberOfFailed.addKeyListener(getKeyAdapter(numberOfFailed, false));

		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel1.add(numberOfFailed);
		panel1.add(new JLabel(GUIConstants.MAX_FAILED_CHECKS_SETTING_TIP));
		panel.add(panel1);

		panel.add(new JLabel(GUIConstants.MAX_NUMBER_FAILED_DISPLAYED_CHECKS));

		numberOfFailedDisplay = new JTextField();
		numberOfFailedDisplay.addKeyListener(getKeyAdapter(numberOfFailedDisplay, true));

		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.add(numberOfFailedDisplay);
		panel2.add(new JLabel(GUIConstants.MAX_FAILED_CHECKS_DISP_SETTING_TIP));
		panel.add(panel2);

		panel.add(new JLabel(GUIConstants.THIRDPARTY_CONFIG_LABEL_TEXT));

		thirdPartyProfilePathField = new JTextField();
		panel.add(thirdPartyProfilePathField);

		panel.add(new JLabel());
		JButton choose = new JButton(GUIConstants.THIRDPARTY_CONFIG_CHOOSE_BUTTON);

		chooser = new JFileChooser();
		File currentDir = new File(
				new File(GUIConstants.DOT).getCanonicalPath());
		chooser.setCurrentDirectory(currentDir);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(new FileNameExtensionFilter(GUIConstants.XML, GUIConstants.XML));

		choose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int resultChoose = chooser.showOpenDialog(SettingsPanel.this);
				if (resultChoose == JFileChooser.APPROVE_OPTION) {
					if (!chooser.getSelectedFile().exists()) {
						JOptionPane.showMessageDialog(SettingsPanel.this,
								"Error. Selected file doesn't exist.",
								GUIConstants.ERROR, JOptionPane.ERROR_MESSAGE);
					} else if (!chooser.getSelectedFile().getName().toLowerCase()
							.endsWith(GUIConstants.DOT + GUIConstants.XML.toLowerCase())) {
						JOptionPane.showMessageDialog(
								SettingsPanel.this,
								"Error. Selected file is not in "
										+ GUIConstants.XML.toUpperCase() + " format.",
								GUIConstants.ERROR, JOptionPane.ERROR_MESSAGE);
					} else {
						thirdPartyProfilePathField.setText(chooser.getSelectedFile().getAbsolutePath());
					}
				}

			}
		});

		JButton clear = new JButton(GUIConstants.THIRDPARTY_CONFIG_CLEAR_BUTTON);
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				thirdPartyProfilePathField.setText("");
			}
		});

		JPanel panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel3.add(choose);
		panel3.add(clear);
		panel.add(panel3);

		add(panel, BorderLayout.CENTER);

		okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ok = true;
				dialog.setVisible(false);
			}
		});

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dialog.setVisible(false);
			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Shows about dialog
	 *
	 * @param parent parent component of the dialog
	 * @param title  title of the dialog
	 */
	public boolean showDialog(Component parent, String title, Properties prop) {

		ok = false;

		boolean dispPassedRulesBool = SettingsHelper.isDispPassedRules(prop);
		hidePassedRules.setSelected(dispPassedRulesBool);

		int numbOfFail = SettingsHelper.getNumbOfFail(prop);
		if (numbOfFail == -1) {
			numberOfFailed.setText("");
		} else {
			numberOfFailed.setText(String.valueOf(numbOfFail));
		}

		int numbOfFailDisp = SettingsHelper.getNumbOfFailDisp(prop);
		if (numbOfFailDisp == -1) {
			numberOfFailedDisplay.setText("");
		} else {
			numberOfFailedDisplay.setText(String.valueOf(numbOfFailDisp));
		}

		int type = SettingsHelper.getProcessingType(prop);
		switch (type) {
			case 3:
				valAndFeat.setSelected(true);
				break;
			case 1:
				val.setSelected(true);
				break;
			case 2:
				feat.setSelected(true);
				break;
		}
		thirdPartyProfilePathField.setText(SettingsHelper.getFeaturesPluginConfigFilePath(prop));

		Frame owner;
		if (parent instanceof Frame) {
			owner = (Frame) parent;
		} else {
			owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);
		}

		if (dialog == null || dialog.getOwner() != owner) {
			dialog = new JDialog(owner, true);
			dialog.setResizable(false);
			dialog.add(this);
			dialog.getRootPane().setDefaultButton(okButton);
			dialog.pack();
			dialog.setTitle(title);
		}

		dialog.setLocation(GUIConstants.SETTINGSDIALOG_COORD_X, GUIConstants.SETTINGSDIALOG_COORD_Y);
		dialog.setSize(690, 281);
		dialog.setVisible(true);

		return ok;
	}

	private static KeyAdapter getKeyAdapter(final JTextField field, final boolean fromZero) {
		return new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((field.getText().length() == 6) && field.getSelectedText().length() == 0 &&
						(c != KeyEvent.VK_BACK_SPACE) &&
						(c != KeyEvent.VK_DELETE)) {
					e.consume();
				} else if (c == '0' && ((!fromZero && field.getText().length() == 0) || field.getText().startsWith("0"))) {
					e.consume();
				} else if (!(((c >= '0') && (c <= '9')) ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE))) {
					e.consume();
				} else {
					super.keyTyped(e);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (field.getText().startsWith("0")) {
					field.setText(field.getText().replaceFirst("0*", ""));
					if (field.getText().length() == 0) {
						if (fromZero) {
							field.setText("0");
						} else {
							field.setText("");
						}
					}
				}
				super.keyReleased(e);
			}
		};
	}

	/**
	 * @return integer that indicates selected processing type.
	 */
	public int getProcessingType() {
		if (valAndFeat.isSelected()) {
			return GUIConstants.VALIDATING_AND_FEATURES_FLAG;
		} else if (val.isSelected()) {
			return GUIConstants.VALIDATING_FLAG;
		} else {
			return GUIConstants.FEATURES_FLAG;
		}
	}

	/**
	 * @return true if desplay passed pules option selected
	 */
	public boolean isDispPassedRules() {
		return hidePassedRules.isSelected();
	}

	/**
	 * @return selected number for maximum fail checks for a rule. If not selected returns -1
	 */
	public int getFailedChecksNumber() {
		String str = numberOfFailed.getText();
		return str.length() > 0 ? Integer.parseInt(str) : -1;
	}

	/**
	 * @return selected number for maximum displayed fail checks for a rule. If not selected returns -1
	 */
	public int getFailedChecksDisplayNumber() {
		String str = numberOfFailedDisplay.getText();
		return str.length() > 0 ? Integer.parseInt(str) : -1;
	}

	/**
	 * @return path to the config file for features plugins
	 */
	public String getFeaturesPluginConfigPath() {
		return GUIConstants.THIRDPARTY_CONFIG_NOT_CHOSEN_TEXT.equals(thirdPartyProfilePathField.getText()) ?
				null : thirdPartyProfilePathField.getText();
	}
}
