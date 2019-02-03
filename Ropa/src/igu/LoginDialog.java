package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Tienda;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class LoginDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtSurname;
	private JLabel lblSurname;
	private JTextField txtTotal;
	private JLabel lblTotal;
	double price;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			LoginDialog dialog = new LoginDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public LoginDialog(MainWindowRopa mw) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginDialog.class.getResource("/img/sh.png")));
		setBounds(100, 100, 515, 298);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		price = Tienda.getPrice(mw.getTicket());
		{
			JLabel lblName = new JLabel("Name:");
			lblName.setLabelFor(lblName);
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
			gbc_txtName.gridx = 5;
			gbc_txtName.gridy = 1;
			contentPanel.add(txtName, gbc_txtName);
			txtName.setColumns(10);
		}
		{
			lblSurname = new JLabel("Surname:");
			GridBagConstraints gbc_lblSurname = new GridBagConstraints();
			gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
			gbc_lblSurname.gridx = 2;
			gbc_lblSurname.gridy = 3;
			contentPanel.add(lblSurname, gbc_lblSurname);
		}
		{
			txtSurname = new JTextField();
			lblSurname.setLabelFor(txtSurname);
			GridBagConstraints gbc_txtSurname = new GridBagConstraints();
			gbc_txtSurname.insets = new Insets(0, 0, 5, 0);
			gbc_txtSurname.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtSurname.gridx = 5;
			gbc_txtSurname.gridy = 3;
			contentPanel.add(txtSurname, gbc_txtSurname);
			txtSurname.setColumns(10);
		}
		{
			lblTotal = new JLabel("Total:");
			GridBagConstraints gbc_lblTotal = new GridBagConstraints();
			gbc_lblTotal.insets = new Insets(0, 0, 0, 5);
			gbc_lblTotal.gridx = 2;
			gbc_lblTotal.gridy = 5;
			contentPanel.add(lblTotal, gbc_lblTotal);
			
		}
		{
			txtTotal = new JTextField();
			txtTotal.setEditable(false);
			lblTotal.setLabelFor(txtTotal);
			GridBagConstraints gbc_txtTotal = new GridBagConstraints();
			gbc_txtTotal.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtTotal.gridx = 5;
			gbc_txtTotal.gridy = 5;
			contentPanel.add(txtTotal, gbc_txtTotal);
			txtTotal.setColumns(10);
			txtTotal.setText(price+ "€");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txtName.getText().isEmpty()||txtSurname.getText().isEmpty())
						{
							JOptionPane.showMessageDialog(null, "enter name and surname");
						}
						else
						{
							try {
								Tienda.writeticket(txtName.getText().substring(0,3).concat(txtSurname.getText().substring(2,3)),price, txtName.getText(),txtSurname.getText());
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							System.exit(0);
							
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
