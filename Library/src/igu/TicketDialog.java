package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Library;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TicketDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtPrice;
	static MainWindowLib lib;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TicketDialog dialog = new TicketDialog(lib);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param lib 
	 */
	public TicketDialog(MainWindowLib lib) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TicketDialog.class.getResource("/img/libreria.jpg")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Id:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 4;
			gbc_lblNewLabel.gridy = 1;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			txtName = new JTextField();
			txtName.setEditable(false);
			GridBagConstraints gbc_txtName = new GridBagConstraints();
			gbc_txtName.insets = new Insets(0, 0, 5, 0);
			gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtName.gridx = 6;
			gbc_txtName.gridy = 1;
			contentPanel.add(txtName, gbc_txtName);
			txtName.setColumns(10);
		}
		{
			JLabel lblTotal = new JLabel("Total:");
			lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
			GridBagConstraints gbc_lblTotal = new GridBagConstraints();
			gbc_lblTotal.insets = new Insets(0, 0, 0, 5);
			gbc_lblTotal.gridx = 4;
			gbc_lblTotal.gridy = 3;
			contentPanel.add(lblTotal, gbc_lblTotal);
		}
		{
			txtPrice = new JTextField();
			txtPrice.setEditable(false);
			GridBagConstraints gbc_txtPrice = new GridBagConstraints();
			gbc_txtPrice.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPrice.gridx = 6;
			gbc_txtPrice.gridy = 3;
			contentPanel.add(txtPrice, gbc_txtPrice);
			txtPrice.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Library.generateTicket(lib.getUser(),lib.getTotal());
						System.exit(0);
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
			this.lib = lib;
			txtName.setText(lib.getUser().getName());
			txtPrice.setText(lib.getTotal()+"€");
		}
	}

}
