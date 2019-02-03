package igu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logic.Ticket;


public class DialogPayment extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	
	private MainWincru mW;
	private DialogPayment dp;

	private JLabel lblCostumer;
	private JTextField txtCostumer;
	private JTextField txtTotall;
	private JLabel lblTotal;
	
	private static Ticket ticket;
	private JButton btnGenerateReceipt;
	private JButton btnCancel;
	private JLabel lblCruise;
	private JTextField txtCruisename;
	
	/**
	 * Create the dialog.
	 * @param ticket 
	 */
	public DialogPayment(MainWincru mW, Ticket ticket) {
		this.mW=mW;
		this.ticket = ticket;
		setResizable(false);
		
		setTitle("Ventana de registro");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLblCostumer());
		contentPanel.add(getTxtCostumer());
		contentPanel.add(getTxtTotall());
		contentPanel.add(getLblTotal());
		contentPanel.add(getBtnGenerateReceipt());
		contentPanel.add(getBtnCancel());
		contentPanel.add(getLblCruise());
		contentPanel.add(getTxtCruisename());
		
	}
	

	
	private JLabel getLblCostumer() {
		if (lblCostumer == null) {
			lblCostumer = new JLabel("Costumer: ");
			lblCostumer.setLabelFor(getTxtCostumer());
			lblCostumer.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblCostumer.setBounds(32, 51, 93, 14);
		}
		return lblCostumer;
	}
	private JTextField getTxtCostumer() {
		if (txtCostumer == null) {
			txtCostumer = new JTextField();
			txtCostumer.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					txtTotall.setText(ticket.getPriceP()+" € ");
					txtCruisename.setText(ticket.getCruises().get(0).getName());
				}
			});
			txtCostumer.setBorder(null);
			txtCostumer.setDisabledTextColor(Color.WHITE);
			txtCostumer.setSelectedTextColor(Color.WHITE);
			txtCostumer.setBounds(158, 50, 86, 20);
			txtCostumer.setColumns(10);
		}
		return txtCostumer;
	}
	private JTextField getTxtTotall() {
		if (txtTotall == null) {
			txtTotall = new JTextField();
			txtTotall.setEditable(false);
			txtTotall.setBorder(null);
			txtTotall.setBounds(158, 114, 86, 20);
			txtTotall.setColumns(10);
		}
		return txtTotall;
	}
	private JLabel getLblTotal() {
		if (lblTotal == null) {
			lblTotal = new JLabel("Total: ");
			lblTotal.setLabelFor(getTxtTotall());
			lblTotal.setBackground(Color.WHITE);
			lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblTotal.setBounds(37, 117, 46, 14);
		}
		return lblTotal;
	}
	private JButton getBtnGenerateReceipt() {
		if (btnGenerateReceipt == null) {
			btnGenerateReceipt = new JButton("Receipt");
			btnGenerateReceipt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String filename = txtCostumer.getText();
					parseReceipt(filename, ticket);
				}
			});
			btnGenerateReceipt.setBounds(331, 221, 103, 23);
		}
		return btnGenerateReceipt;
	}
	protected void parseReceipt(String filename, Ticket ticket)  {
		
		try ( 
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"))) {			
			writer.write(filename+ " ");
			writer.write(ticket.getPriceP()+ "e ");
			writer.write(ticket.ToString()+ " ");
			writer.close();
			System.exit(0);
			
		}
		catch (IOException ex) {
		    // Report
		}

		
	}



	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mW.reset();
					dispose();
					
				}
			});
			btnCancel.setBounds(213, 221, 89, 23);
		}
		return btnCancel;
	}
	private JLabel getLblCruise() {
		if (lblCruise == null) {
			lblCruise = new JLabel("cruise:");
			lblCruise.setBounds(37, 160, 46, 14);
		}
		return lblCruise;
	}
	private JTextField getTxtCruisename() {
		if (txtCruisename == null) {
			txtCruisename = new JTextField();
			txtCruisename.setEditable(false);
			txtCruisename.setBounds(158, 157, 86, 20);
			txtCruisename.setColumns(10);
		}
		return txtCruisename;
	}
}
