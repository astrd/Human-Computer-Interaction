package igu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import logic.Ropa;
import logic.Tienda;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

public class MainWindowRopa extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnTitle;
	private JLabel lblShop;
	private JLabel lblIcon;
	private JPanel pnButtons;
	private JButton btnOk;
	private JButton btnCancel;
	private JSplitPane splitPane;
	private JTextArea tAdescription;
	private JPanel panel;
	private JLabel lblPrice;
	private JLabel lblTotal;
	private JTextField txtPrice;
	private JTextField txtTotal;
	private JSplitPane spList;
	private JPanel pnFilters;
	private JCheckBox chbx10;
	private JCheckBox chbx1050;
	private JCheckBox chbx50;
	
	private static List<Ropa> ropas;
	private List<Ropa> cart = new ArrayList<Ropa>();
	
	private JList<Ropa> list;
	private DefaultListModel<Ropa> model;
	private JButton btnAdd;
	private JSpinner spinner;
	private JMenuBar menuBar;
	private JMenu mnApplication;
	private JMenu mnHelp;
	private JMenuItem mntmReset;
	private JMenuItem mntmExit;
	private JSeparator separator;
	private JMenuItem mntmAbout;
	private JMenuItem mntmHelp;
	private JSeparator separator_1;
	MainWindowRopa mw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ropas = Tienda.parseRopa();
					MainWindowRopa frame = new MainWindowRopa();					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindowRopa() {
		setTitle("shop");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindowRopa.class.getResource("/img/cat mood.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 409);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnTitle(), BorderLayout.NORTH);
		contentPane.add(getPnButtons(), BorderLayout.SOUTH);
		contentPane.add(getSplitPane(), BorderLayout.CENTER);
		contentPane.add(getSpList(), BorderLayout.WEST);
		cargaAyuda();
		mw= this;
		
	}

	private JPanel getPnTitle() {
		if (pnTitle == null) {
			pnTitle = new JPanel();
			pnTitle.setLayout(new GridLayout(1, 0, 0, 0));
			pnTitle.add(getLblIcon());
			pnTitle.add(getLblShop());
		}
		return pnTitle;
	}
	private JLabel getLblShop() {
		if (lblShop == null) {
			lblShop = new JLabel("Shop");
			lblShop.setFont(new Font("Tahoma", Font.BOLD, 65));
			lblShop.setBackground(Color.WHITE);
		}
		return lblShop;
	}
	private JLabel getLblIcon() {
		if (lblIcon == null) {
			lblIcon = new JLabel("");
			lblIcon.setForeground(Color.WHITE);
			lblIcon.setBackground(Color.WHITE);
			lblIcon.setIcon(new ImageIcon(MainWindowRopa.class.getResource("/img/sh.png")));
		}
		return lblIcon;
	}
	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			pnButtons.setBackground(Color.WHITE);
			pnButtons.setAlignmentX(Component.RIGHT_ALIGNMENT);
			pnButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnButtons.add(getBtnOk());
			pnButtons.add(getBtnCancel());
		}
		return pnButtons;
	}
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("Ok");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(cart.size()==0)
					{
						JOptionPane.showMessageDialog(null, "choose price");
					}
					else
					{
					LoginDialog dialog = new LoginDialog(mw);
					dialog.setVisible(true);
					dialog.setEnabled(true);
					dialog.setModal(true);
					}
					
				}
			});
			btnOk.setAlignmentX(Component.RIGHT_ALIGNMENT);
			btnOk.setHorizontalAlignment(SwingConstants.RIGHT);
			btnOk.setMnemonic('o');
		}
		return btnOk;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Reset");
			btnCancel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			btnCancel.setVerticalAlignment(SwingConstants.BOTTOM);
			btnCancel.setHorizontalAlignment(SwingConstants.RIGHT);
			btnCancel.setMnemonic('r');
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reset();
				}
			});
		}
		return btnCancel;
	}
	private void reset()
	{
		txtPrice.setText(" ");
		txtTotal.setText(" ");
		list.clearSelection();
		btnAdd.setEnabled(false);
		chbx10.setSelected(false);
		chbx1050.setSelected(false);
		chbx50.setSelected(false);
		spinner.setValue(1);
		cart = new ArrayList<Ropa>();
		
	}
	private JSplitPane getSplitPane() {
		if (splitPane == null) {
			splitPane = new JSplitPane();
			splitPane.setLeftComponent(getTAdescription());
			splitPane.setRightComponent(getPanel());
		}
		return splitPane;
	}
	private JTextArea getTAdescription() {
		if (tAdescription == null) {
			tAdescription = new JTextArea();
			tAdescription.setPreferredSize(new Dimension(100, 88));
			tAdescription.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			tAdescription.setWrapStyleWord(true);
			tAdescription.setText("Description:");
			tAdescription.setLineWrap(true);
		}
		return tAdescription;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			GridBagConstraints gbc_btnAdd = new GridBagConstraints();
			gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
			gbc_btnAdd.gridx = 1;
			gbc_btnAdd.gridy = 1;
			panel.add(getBtnAdd(), gbc_btnAdd);
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.insets = new Insets(0, 0, 5, 5);
			gbc_spinner.gridx = 3;
			gbc_spinner.gridy = 1;
			panel.add(getSpinner(), gbc_spinner);
			GridBagConstraints gbc_lblPrice = new GridBagConstraints();
			gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
			gbc_lblPrice.gridx = 4;
			gbc_lblPrice.gridy = 1;
			panel.add(getLblPrice(), gbc_lblPrice);
			GridBagConstraints gbc_txtPrice = new GridBagConstraints();
			gbc_txtPrice.insets = new Insets(0, 0, 5, 0);
			gbc_txtPrice.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPrice.gridx = 6;
			gbc_txtPrice.gridy = 1;
			panel.add(getTxtPrice(), gbc_txtPrice);
			GridBagConstraints gbc_lblTotal = new GridBagConstraints();
			gbc_lblTotal.insets = new Insets(0, 0, 0, 5);
			gbc_lblTotal.gridx = 4;
			gbc_lblTotal.gridy = 3;
			panel.add(getLblTotal(), gbc_lblTotal);
			GridBagConstraints gbc_txtTotal = new GridBagConstraints();
			gbc_txtTotal.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtTotal.gridx = 6;
			gbc_txtTotal.gridy = 3;
			panel.add(getTxtTotal(), gbc_txtTotal);
		}
		return panel;
	}
	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel("price:");
			lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblPrice;
	}
	private JLabel getLblTotal() {
		if (lblTotal == null) {
			lblTotal = new JLabel("total:");
			lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblTotal;
	}
	private JTextField getTxtPrice() {
		if (txtPrice == null) {
			txtPrice = new JTextField();
			txtPrice.setEditable(false);
			txtPrice.setColumns(10);
		}
		return txtPrice;
	}
	private JTextField getTxtTotal() {
		if (txtTotal == null) {
			txtTotal = new JTextField();
			txtTotal.setEditable(false);
			txtTotal.setColumns(10);
		}
		return txtTotal;
	}
	private JSplitPane getSpList() {
		if (spList == null) {
			spList = new JSplitPane();
			spList.setOrientation(JSplitPane.VERTICAL_SPLIT);
			spList.setLeftComponent(getPnFilters());
			spList.setRightComponent(getList());
		}
		return spList;
	}
	private JPanel getPnFilters() {
		if (pnFilters == null) {
			pnFilters = new JPanel();
			pnFilters.setLayout(new GridLayout(1, 0, 0, 0));
			pnFilters.add(getChbx10());
			pnFilters.add(getChbx1050());
			pnFilters.add(getChbx50());
		}
		return pnFilters;
	}
	private JCheckBox getChbx10() {
		if (chbx10 == null) {
			chbx10 = new JCheckBox("<10");
			chbx10.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					manageFilters();
				}
			});
		}
		return chbx10;
	}
	private JCheckBox getChbx1050() {
		if (chbx1050 == null) {
			chbx1050 = new JCheckBox("10-50");
			chbx1050.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					manageFilters();
				}
			});
		}
		return chbx1050;
	}
	private JCheckBox getChbx50() {
		if (chbx50 == null) {
			chbx50 = new JCheckBox(">50");
			chbx50.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					manageFilters();
				}
			});
			
		}
		return chbx50;
	}
	private void manageFilters()
	{
		model.clear();
		if(chbx10.isSelected()||chbx1050.isSelected()||chbx50.isSelected())
		{
		if(chbx10.isSelected())
		{
			for(Ropa r:ropas)
			{
				if(r.getPrice()<10)
				{
					model.addElement(r);
				}
			}
		}
		if(chbx1050.isSelected())
		{
			for(Ropa r:ropas)
			{
				if(r.getPrice()>=10 && r.getPrice()<=50)
				{
					model.addElement(r);
				}
			}
		}
		if(chbx50.isSelected())
		{
			for(Ropa r:ropas)
			{
				if(r.getPrice()>50)
				{
					model.addElement(r);
				}
			}
		}
		}
		else
		{
			for(Ropa r:ropas)
			{
				model.addElement(r);
			}
		}
	}
	private JList<Ropa> getList() {
		if (list == null) {
			model = new DefaultListModel<Ropa>();
			list = new JList<Ropa>(model);
			for(Ropa r: ropas)
			{
				model.addElement(r);
			}
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list.addListSelectionListener(new ListSelectionListener() {
				@SuppressWarnings("unchecked")
				public void valueChanged(ListSelectionEvent e) {
					JList<Ropa> r = (JList<Ropa>) e.getSource();
					if(r.isSelectionEmpty())
					{
						tAdescription.setText(" ");
						
					}
					else
					{					
					btnAdd.setEnabled(true);
					txtPrice.setText(r.getSelectedValue().getPrice()* (int) spinner.getValue()+"€");
					tAdescription.setText(r.getSelectedValue().getDescription());
					}
									
				}
			});
			
		}
		return list;
	}
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Add");
			btnAdd.setMnemonic('d');
			btnAdd.setEnabled(false);
			
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					Ropa r = (Ropa) list.getSelectedValue();
					int amount = (int) spinner.getValue();
					
					while(0<amount)
					{
						cart.add(r);
						txtTotal.setText(Tienda.getPrice(cart)+"€");
						amount--;
					}
				}
			});
		}
		return btnAdd;
	}
	private JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(1, null, 10, 1));
		}
		return spinner;
	}
	
	

void cargaAyuda(){

   URL hsURL;
   HelpSet hs;

    try {
	    	File fichero = new File("help/ayuda.hs");
	    	hsURL = fichero.toURI().toURL();
	        hs = new HelpSet(null, hsURL);
	      }

    catch(Exception e){
      System.out.println("Ayuda no encontrada");
      return;
   }

   HelpBroker hb = hs.createHelpBroker();

   hb.enableHelpKey(getRootPane(),"inicio", hs);
   hb.enableHelpOnButton(mntmHelp,"inicio",hs);
   hb.enableHelp(btnAdd, "add", hs);
   
 }

	
	
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnApplication());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}
	private JMenu getMnApplication() {
		if (mnApplication == null) {
			mnApplication = new JMenu("Application");
			mnApplication.setMnemonic('A');
			mnApplication.add(getMntmReset());
			mnApplication.add(getSeparator());
			mnApplication.add(getMntmExit());
		}
		return mnApplication;
	}
	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.setMnemonic('H');
			mnHelp.add(getMntmAbout());
			mnHelp.add(getSeparator_1());
			mnHelp.add(getMntmHelp());
		}
		return mnHelp;
	}
	private JMenuItem getMntmReset() {
		if (mntmReset == null) {
			mntmReset = new JMenuItem("Reset");
			mntmReset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					reset();
				}
			});
			mntmReset.setMnemonic('s');
		}
		return mntmReset;
	}
	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			mntmExit.setMnemonic('E');
		}
		return mntmExit;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "by Astrid Gamoneda sponsored by Emilio");
				}
			});
			mntmAbout.setMnemonic('b');
		}
		return mntmAbout;
	}
	private JMenuItem getMntmHelp() {
		if (mntmHelp == null) {
			mntmHelp = new JMenuItem("Help");
			mntmHelp.setMnemonic('l');
		}
		return mntmHelp;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	public List<Ropa> getTicket()
	{
		return cart;
	}
}
