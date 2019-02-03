package igu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logic.User;
import logic.UsersDataBase;

public class LoginDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtSurname;
	private JPasswordField pwdPass;
	static MainWindowLib lib ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginDialog dialog = new LoginDialog(lib);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginDialog(MainWindowLib lib) {
		this.lib = lib;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginDialog.class.getResource("/img/libreria.jpg")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblName = new JLabel("Name:");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 2;
			gbc_lblName.gridy = 1;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			txtName = new JTextField();
			GridBagConstraints gbc_txtName = new GridBagConstraints();
			gbc_txtName.insets = new Insets(0, 0, 5, 0);
			gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtName.gridx = 4;
			gbc_txtName.gridy = 1;
			contentPanel.add(txtName, gbc_txtName);
			txtName.setColumns(10);
		}
		{
			JLabel lblSurname = new JLabel("Surname:");
			GridBagConstraints gbc_lblSurname = new GridBagConstraints();
			gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
			gbc_lblSurname.gridx = 2;
			gbc_lblSurname.gridy = 3;
			contentPanel.add(lblSurname, gbc_lblSurname);
		}
		{
			txtSurname = new JTextField();
			GridBagConstraints gbc_txtSurname = new GridBagConstraints();
			gbc_txtSurname.insets = new Insets(0, 0, 5, 0);
			gbc_txtSurname.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtSurname.gridx = 4;
			gbc_txtSurname.gridy = 3;
			contentPanel.add(txtSurname, gbc_txtSurname);
			txtSurname.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password: ");
			GridBagConstraints gbc_lblPassword = new GridBagConstraints();
			gbc_lblPassword.insets = new Insets(0, 0, 0, 5);
			gbc_lblPassword.gridx = 2;
			gbc_lblPassword.gridy = 5;
			contentPanel.add(lblPassword, gbc_lblPassword);
		}
		{
			pwdPass = new JPasswordField();
			pwdPass.setEchoChar('*');
			pwdPass.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!txtName.getText().isEmpty()&& !txtSurname.getText().isEmpty())
					{
						pwdPass.setEnabled(true);
					}
				}
			});
			GridBagConstraints gbc_pwdPass = new GridBagConstraints();
			gbc_pwdPass.fill = GridBagConstraints.HORIZONTAL;
			gbc_pwdPass.gridx = 4;
			gbc_pwdPass.gridy = 5;
			contentPanel.add(pwdPass, gbc_pwdPass);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String passText = new String(pwdPass.getPassword());
						User u = UsersDataBase.isValid(txtName.getText(),txtSurname.getText(),passText);

						if(u != null)
						{
							lib.setUser(u);
							dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "worng input", "login", 0);
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
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
