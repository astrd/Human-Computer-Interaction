package igu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import logic.Book;
import logic.Library;
import logic.User;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

public class MainWindowLib extends JFrame {

	private JPanel contentPane;
	private JPanel pnTitle;
	private JLabel lblTitle;
	private JSplitPane splitPane;
	private JSplitPane splitPane_1;
	private JPanel panel;
	private JCheckBox ch20;
	private JScrollPane scrollPane;
	private JList listbooks;
	private JSplitPane splitPane_2;
	private JScrollPane scrollPane_1;
	private JPanel panel_1;
	private JLabel lblprice;
	private JTextArea tADescrption;
	private JPanel panel_2;
	private JButton btnOk;
	private JButton btnCancel;
	
	private LoginDialog lD;
	private DefaultListModel<Book> model;
	
	
	
	private static List<Book> cat;
	private static List<Book> purchase;
	private User u;
	
	private JTextField txtPrice;
	private JLabel lblTotal;
	private JTextField txtTotal;
	private JButton btnAdd;
	private JSpinner spinner;
	private JCheckBox ch10;
	private JCheckBox ch30;
	
	TicketDialog td;
	MainWindowLib lib;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnHelp;
	private JMenuItem mntmReset;
	private JMenuItem mntmInfo;
	private JMenuItem mntmHelp;
	private JSeparator separator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cat= Library.parseBooks();
					purchase = new ArrayList<Book>();
					MainWindowLib frame = new MainWindowLib();
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
	public MainWindowLib() {
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindowLib.class.getResource("/img/libreria.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 351);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnTitle(), BorderLayout.NORTH);
		contentPane.add(getSplitPane(), BorderLayout.CENTER);
		contentPane.add(getPanel_2(), BorderLayout.SOUTH);
		
		lib = this;
		lD = new LoginDialog(lib);
		lD.setModal(true);
		lD.setVisible(true);

		lD.setLocationRelativeTo(this);
		cargaAyuda();
	}

	private JPanel getPnTitle() {
		if (pnTitle == null) {
			pnTitle = new JPanel();
			pnTitle.setBackground(Color.WHITE);
			pnTitle.setLayout(new GridLayout(0, 1, 0, 0));
			pnTitle.add(getLblTitle());
		}
		return pnTitle;
	}
	private JLabel getLblTitle() {
		if (lblTitle == null) {
			lblTitle = new JLabel("LIBRARY EII OVIEDO");
			lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 28));
		}
		return lblTitle;
	}
	private JSplitPane getSplitPane() {
		if (splitPane == null) {
			splitPane = new JSplitPane();
			splitPane.setLeftComponent(getSplitPane_1());
			splitPane.setRightComponent(getSplitPane_2());
		}
		return splitPane;
	}
	private JSplitPane getSplitPane_1() {
		if (splitPane_1 == null) {
			splitPane_1 = new JSplitPane();
			splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane_1.setLeftComponent(getPanel());
			splitPane_1.setRightComponent(getScrollPane());
		}
		return splitPane_1;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setToolTipText("checkin filters");
			panel.setLayout(new GridLayout(1, 0, 0, 0));
			panel.add(getCh10());
			panel.add(getCh20());
			panel.add(getCh30());
		}
		return panel;
	}
	private JCheckBox getCh20() {
		if (ch20 == null) {
			ch20 = new JCheckBox("10-30");
			ch20.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					manageCheckBoxes();
				}
			});
		}
		return ch20;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}
	private JList getList() {
		if (listbooks == null) {
			model = new  DefaultListModel<Book>();
			listbooks = new JList<Book>(model);
			listbooks.setToolTipText("the books");
			
			for(Book b : cat)
			{
				model.addElement(b);
			}
			listbooks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			listbooks.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					JList<Book> bl = (JList<Book>) e.getSource();
					if(bl.isSelectionEmpty())
					{
						tADescrption.setText(" ");
						
					}
					else
					{
						tADescrption.setText(bl.getSelectedValue().getTagline());
						txtPrice.setText(bl.getSelectedValue().getPrice()+ " €");
						
					}
					
				}
			});
			
		
		}
		return listbooks;
	}
	private JSplitPane getSplitPane_2() {
		if (splitPane_2 == null) {
			splitPane_2 = new JSplitPane();
			splitPane_2.setLeftComponent(getScrollPane_1());
			splitPane_2.setRightComponent(getPanel_1());
		}
		return splitPane_2;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getTADescrption());
		}
		return scrollPane_1;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
			GridBagLayout gbl_panel_1 = new GridBagLayout();
			gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel_1.setLayout(gbl_panel_1);
			GridBagConstraints gbc_lblprice = new GridBagConstraints();
			gbc_lblprice.insets = new Insets(0, 0, 5, 5);
			gbc_lblprice.gridx = 2;
			gbc_lblprice.gridy = 1;
			panel_1.add(getLblprice(), gbc_lblprice);
			GridBagConstraints gbc_txtPrice = new GridBagConstraints();
			gbc_txtPrice.insets = new Insets(0, 0, 5, 5);
			gbc_txtPrice.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPrice.gridx = 4;
			gbc_txtPrice.gridy = 1;
			panel_1.add(getTxtPrice(), gbc_txtPrice);
			GridBagConstraints gbc_btnAdd = new GridBagConstraints();
			gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
			gbc_btnAdd.gridx = 5;
			gbc_btnAdd.gridy = 1;
			panel_1.add(getBtnAdd(), gbc_btnAdd);
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.insets = new Insets(0, 0, 5, 5);
			gbc_spinner.gridx = 6;
			gbc_spinner.gridy = 1;
			panel_1.add(getSpinner(), gbc_spinner);
			GridBagConstraints gbc_lblTotal = new GridBagConstraints();
			gbc_lblTotal.insets = new Insets(0, 0, 5, 5);
			gbc_lblTotal.gridx = 2;
			gbc_lblTotal.gridy = 4;
			panel_1.add(getLblTotal(), gbc_lblTotal);
			GridBagConstraints gbc_txtTotal = new GridBagConstraints();
			gbc_txtTotal.insets = new Insets(0, 0, 5, 5);
			gbc_txtTotal.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtTotal.gridx = 4;
			gbc_txtTotal.gridy = 4;
			panel_1.add(getTxtTotal(), gbc_txtTotal);
		}
		return panel_1;
	}
	private JLabel getLblprice() {
		if (lblprice == null) {
			lblprice = new JLabel("Price:");
		}
		return lblprice;
	}
	private JTextArea getTADescrption() {
		if (tADescrption == null) {
			tADescrption = new JTextArea();
			tADescrption.setEditable(false);
			tADescrption.setWrapStyleWord(true);
			tADescrption.setLineWrap(true);
			tADescrption.setText("DESCRIPTION: ");
		}
		return tADescrption;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panel_2.add(getBtnOk());
			panel_2.add(getBtnCancel());
		}
		return panel_2;
	}
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("ok");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!txtTotal.getText().isEmpty())
					{
					td = new TicketDialog(lib);
					td.setVisible(true);
					td.setLocationRelativeTo(lib);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please select something");
					}
				}
			});
			btnOk.setMnemonic('O');
			btnOk.setToolTipText("ok");
			btnOk.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return btnOk;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("cancel");
			btnCancel.setMnemonic('C');
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					reset();
				}
			});
			btnCancel.setToolTipText("cancel");
			btnCancel.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return btnCancel;
	}
	private void reset()
	{
		txtPrice.setText(" ");
		txtTotal.setText(" ");
		listbooks.clearSelection();
		ch10.setSelected(false);
		ch20.setSelected(false);
		ch30.setSelected(false);
		manageCheckBoxes();
		spinner.setValue(1);
		purchase = new ArrayList<Book>();
		
	}
	private JTextField getTxtPrice() {
		if (txtPrice == null) {
			txtPrice = new JTextField();
			txtPrice.setEditable(false);
			txtPrice.setColumns(10);
		}
		return txtPrice;
	}
	private JLabel getLblTotal() {
		if (lblTotal == null) {
			lblTotal = new JLabel("Total:");
		}
		return lblTotal;
	}
	private JTextField getTxtTotal() {
		if (txtTotal == null) {
			txtTotal = new JTextField();
			txtTotal.setEditable(false);
			txtTotal.setColumns(10);
		}
		return txtTotal;
	}
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("add");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int amount = (int) spinner.getValue();
					while(amount > 0)
					{
					Book b =(Book) listbooks.getSelectedValue();
					purchase.add(b);
					amount--;
					}
					txtTotal.setText(Library.getTotalPrice(purchase)+"€");
				}
			});
		}
		return btnAdd;
	}
	private JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spinner.setBackground(Color.WHITE);
		}
		return spinner;
	}
	private JCheckBox getCh10() {
		if (ch10 == null) {
			ch10 = new JCheckBox("<10");
			ch10.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					manageCheckBoxes();
				}
			});
		}
		return ch10;
	}
	private JCheckBox getCh30() {
		if (ch30 == null) {
			ch30 = new JCheckBox(">30");
			ch30.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					manageCheckBoxes();
				}
			});
		}
		return ch30;
	}
	private void manageCheckBoxes()
	{
		model.clear();
		if(ch10.isSelected())
		{
			for(Book b: cat)
			{
				if(b.getPrice()<18)
					
					{
					model.addElement(b);
					
					}
			}
		}
		if(ch20.isSelected())
		{
			for(Book b: cat)
			{
				if(b.getPrice()>18 &&b.getPrice()<30)
					model.addElement(b);
			}
		}
		 if(ch30.isSelected())
		{
			for(Book b: cat)
			{
				if(b.getPrice()>30)
				model.addElement(b);
			}
		}
		
		else
		{
			for(Book b: cat)
			{				
				model.addElement(b);
			}
		}
		
	}

	public void setUser(User u) {
	 this.u = u;
		
	}
	public User getUser()
	{
		return u;
	}
	public double getTotal()
	{
		return Library.getTotalPrice(purchase);
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

		   hb.enableHelpKey(getRootPane(),"menu", hs);
		   hb.enableHelpOnButton(mntmHelp, "menu", hs);
		   hb.enableHelp(btnCancel, "reset", hs);
		   
		 }

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}
	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.setMnemonic('F');
			mnFile.add(getMntmReset());
		}
		return mnFile;
	}
	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.setMnemonic('H');
			mnHelp.add(getMntmInfo());
			mnHelp.add(getSeparator());
			mnHelp.add(getMntmHelp());
		}
		return mnHelp;
	}
	private JMenuItem getMntmReset() {
		if (mntmReset == null) {
			mntmReset = new JMenuItem("reset");
			mntmReset.setMnemonic('r');
			mntmReset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					reset();
				}
			});
		}
		return mntmReset;
	}
	private JMenuItem getMntmInfo() {
		if (mntmInfo == null) {
			mntmInfo = new JMenuItem("About");
			mntmInfo.setMnemonic('A');
			mntmInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Copyright (c) Astrid Gamoneda"+
								" * 2018"+
								 "* desde aqui para toda la peñita dale purpurina ahi"+
								 "* dale purpurina ahi"
								);
				}
			});
		}
		return mntmInfo;
	}
	private JMenuItem getMntmHelp() {
		if (mntmHelp == null) {
			mntmHelp = new JMenuItem("Help");
			mntmHelp.setMnemonic('l');
		}
		return mntmHelp;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
}
