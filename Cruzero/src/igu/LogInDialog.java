package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogInDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JPasswordField pwdPass;
	private MainWincru mW;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LogInDialog dialog = new LogInDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LogInDialog() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LogInDialog.class.getResource("/img/agencia.jpg")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			contentPanel.add(panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblName = new JLabel("NAME:");
				GridBagConstraints gbc_lblName = new GridBagConstraints();
				gbc_lblName.insets = new Insets(0, 0, 5, 5);
				gbc_lblName.gridx = 1;
				gbc_lblName.gridy = 2;
				panel.add(lblName, gbc_lblName);
			}
			{
				txtName = new JTextField();
				GridBagConstraints gbc_txtName = new GridBagConstraints();
				gbc_txtName.insets = new Insets(0, 0, 5, 0);
				gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtName.gridx = 4;
				gbc_txtName.gridy = 2;
				panel.add(txtName, gbc_txtName);
				txtName.setColumns(10);
			}
			{
				JLabel lblPassword = new JLabel("PASSWORD: ");
				GridBagConstraints gbc_lblPassword = new GridBagConstraints();
				gbc_lblPassword.insets = new Insets(0, 0, 0, 5);
				gbc_lblPassword.gridx = 1;
				gbc_lblPassword.gridy = 4;
				panel.add(lblPassword, gbc_lblPassword);
			}
			{
				pwdPass = new JPasswordField();
				GridBagConstraints gbc_pwdPass = new GridBagConstraints();
				gbc_pwdPass.fill = GridBagConstraints.HORIZONTAL;
				gbc_pwdPass.gridx = 4;
				gbc_pwdPass.gridy = 4;
				panel.add(pwdPass, gbc_pwdPass);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txtName.getText().isEmpty())
						{
							JOptionPane.showMessageDialog(null, "insert user", "PASS", 0);
						}
						else{
							dispose();
						 
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
