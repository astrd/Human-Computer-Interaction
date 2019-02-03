package igu;

import java.awt.BorderLayout;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import logic.Jewel;
import logic.Jewelery;

public class MainJewel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnTitle;
	private JLabel lblIcon;
	private JLabel lblJewelery;
	private JPanel pnprice;
	private JLabel lblTotal;
	private JTextField txtTotal;
	private JSpinner spinner;
	private JButton btnAdd;
	private JLabel lblPrice;
	private JTextField txtPrice;
	private JMenuBar menuBar;
	private JMenu mnApplication;
	private JMenu mnHelp;
	private JMenuItem mntmReset;
	private JMenuItem mntmAbout;
	private JSeparator separator;
	private JMenuItem mntmExit;
	private JSeparator separator_1;
	private JMenuItem mntmHelp;
	private JPanel pnButtons;
	private JButton btnNext;
	private JButton btnCancel;
	private JSplitPane sParea;
	private JTextArea tAdecription;
	private JSplitPane spfilters;
	private JList<Jewel> list;
	private JPanel panel;
	private JCheckBox chb50;
	private JCheckBox chb100;
	private JCheckBox chbm100;
	private DefaultListModel<Jewel> model;
	
	static List<Jewel> catalog;
	static List<Jewel> ticket;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					catalog = Jewelery.parse();
					ticket = new ArrayList<Jewel>();
					MainJewel frame = new MainJewel();
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
	public MainJewel() {
		setTitle("jewels!!!!");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainJewel.class.getResource("/img/jewel.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 417);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnTitle(), BorderLayout.NORTH);
		contentPane.add(getPnprice(), BorderLayout.EAST);
		contentPane.add(getPnButtons(), BorderLayout.SOUTH);
		contentPane.add(getSParea(), BorderLayout.CENTER);
		cargaAyuda();
	}

	private JPanel getPnTitle() {
		if (pnTitle == null) {
			pnTitle = new JPanel();
			pnTitle.setLayout(new GridLayout(1,0, 0, 0));
			pnTitle.add(getLblIcon());
			pnTitle.add(getLblJewelery());
		}
		return pnTitle;
	}
	private JLabel getLblIcon() {
		if (lblIcon == null) {
			lblIcon = new JLabel("");
			lblIcon.setIcon(new ImageIcon(MainJewel.class.getResource("/img/jewel1.png")));
		}
		return lblIcon;
	}
	private JLabel getLblJewelery() {
		if (lblJewelery == null) {
			lblJewelery = new JLabel("Jewelery");
			lblJewelery.setFont(new Font("Stencil", Font.PLAIN, 50));
		}
		return lblJewelery;
	}
	private JPanel getPnprice() {
		if (pnprice == null) {
			pnprice = new JPanel();
			GridBagLayout gbl_pnprice = new GridBagLayout();
			gbl_pnprice.columnWidths = new int[]{30, 159, 0};
			gbl_pnprice.rowHeights = new int[]{14, 0, 0, 0, 0, 0};
			gbl_pnprice.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_pnprice.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			pnprice.setLayout(gbl_pnprice);
			GridBagConstraints gbc_lblPrice = new GridBagConstraints();
			gbc_lblPrice.anchor = GridBagConstraints.EAST;
			gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
			gbc_lblPrice.gridx = 0;
			gbc_lblPrice.gridy = 1;
			pnprice.add(getLblPrice(), gbc_lblPrice);
			GridBagConstraints gbc_txtPrice = new GridBagConstraints();
			gbc_txtPrice.insets = new Insets(0, 0, 5, 0);
			gbc_txtPrice.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPrice.gridx = 1;
			gbc_txtPrice.gridy = 1;
			pnprice.add(getTxtPrice(), gbc_txtPrice);
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.insets = new Insets(0, 0, 5, 5);
			gbc_spinner.gridx = 0;
			gbc_spinner.gridy = 2;
			pnprice.add(getSpinner(), gbc_spinner);
			GridBagConstraints gbc_btnAdd = new GridBagConstraints();
			gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
			gbc_btnAdd.gridx = 1;
			gbc_btnAdd.gridy = 2;
			pnprice.add(getBtnAdd(), gbc_btnAdd);
			GridBagConstraints gbc_lblTotal = new GridBagConstraints();
			gbc_lblTotal.anchor = GridBagConstraints.EAST;
			gbc_lblTotal.insets = new Insets(0, 0, 0, 5);
			gbc_lblTotal.gridx = 0;
			gbc_lblTotal.gridy = 4;
			pnprice.add(getLblTotal(), gbc_lblTotal);
			GridBagConstraints gbc_txtTotal = new GridBagConstraints();
			gbc_txtTotal.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtTotal.gridx = 1;
			gbc_txtTotal.gridy = 4;
			pnprice.add(getTxtTotal(), gbc_txtTotal);
		}
		return pnprice;
	}
	private JLabel getLblTotal() {
		if (lblTotal == null) {
			lblTotal = new JLabel("total :");
			lblTotal.setLabelFor(getTxtTotal());
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
	private JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
		}
		return spinner;
	}
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("add");
			btnAdd.setEnabled(false);
			btnAdd.setMnemonic('d');
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int counter = (int)spinner.getValue();
					while(counter>0)
					{
						ticket.add(list.getSelectedValue());
						counter--;
					}
					txtTotal.setText(Jewelery.getTotalPrice(ticket)+"€");
				}
			});
		}
		return btnAdd;
	}
	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel("  price : ");
			lblPrice.setLabelFor(getTxtPrice());
		}
		return lblPrice;
	}
	private JTextField getTxtPrice() {
		if (txtPrice == null) {
			txtPrice = new JTextField();
			txtPrice.setEditable(false);
			txtPrice.setColumns(10);
		}
		return txtPrice;
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
			mnApplication.setMnemonic('a');
			mnApplication.add(getMntmReset());
			mnApplication.add(getSeparator());
			mnApplication.add(getMntmExit());
		}
		return mnApplication;
	}
	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.setMnemonic('h');
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
			mntmReset.setMnemonic('r');
		}
		return mntmReset;
	}
	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Done by AStrid GAmoneda");
				}
			});
			mntmAbout.setMnemonic('b');
		}
		return mntmAbout;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			mntmExit.setMnemonic('e');
		}
		return mntmExit;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	private JMenuItem getMntmHelp() {
		if (mntmHelp == null) {
			mntmHelp = new JMenuItem("Help");
			mntmHelp.setMnemonic('l');
		}
		return mntmHelp;
	}
	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			pnButtons.add(getBtnNext());
			pnButtons.add(getBtnCancel());
		}
		return pnButtons;
	}
	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("Next");
			btnNext.setMnemonic('n');
		}
		return btnNext;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					reset();
				}

				
			});
			btnCancel.setMnemonic('c');
		}
		return btnCancel;
	}
	private void reset() {
		list.clearSelection();
		txtPrice.setText(" ");
		txtTotal.setText(" ");
		tAdecription.setText(" ");
		ticket = new ArrayList<Jewel>();
		btnAdd.setEnabled(false);
		
	}
	private JSplitPane getSParea() {
		if (sParea == null) {
			sParea = new JSplitPane();
			sParea.setContinuousLayout(true);
			sParea.setRightComponent(getTAdecription());
			sParea.setLeftComponent(getSpfilters());
		}
		return sParea;
	}
	private JTextArea getTAdecription() {
		if (tAdecription == null) {
			tAdecription = new JTextArea();
			tAdecription.setEditable(false);
			tAdecription.setText("description:");
		}
		return tAdecription;
	}
	private JSplitPane getSpfilters() {
		if (spfilters == null) {
			spfilters = new JSplitPane();
			spfilters.setOrientation(JSplitPane.VERTICAL_SPLIT);
			spfilters.setRightComponent(getList());
			spfilters.setLeftComponent(getPanel());
		}
		return spfilters;
	}
	private JList<Jewel> getList() {
		if (list == null) {
			model = new DefaultListModel<Jewel>();
			list = new JList<Jewel>(model);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			for(Jewel j : catalog)
			{
				model.addElement(j);
			}
			list.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					JList<Jewel> jlist = (JList<Jewel>)e.getSource();
					Jewel j =jlist.getSelectedValue();
					if(jlist.isSelectionEmpty())
					{
						tAdecription.setText(" ");
						txtPrice.setText(" ");
					}
					else
					{
						tAdecription.setText(j.getDes());
						txtPrice.setText(j.getPrice()* (int)spinner.getValue()+"€");
						btnAdd.setEnabled(true);
					}
				}
			});
			
		}
		return list;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getChb50());
			panel.add(getChb100());
			panel.add(getChbm100());
		}
		return panel;
	}
	private JCheckBox getChb50() {
		if (chb50 == null) {
			chb50 = new JCheckBox("<50");
			chb50.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					handlefilters();
				}
			});
		}
		return chb50;
	}
	private JCheckBox getChb100() {
		if (chb100 == null) {
			chb100 = new JCheckBox("50-100");
			chb100.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					handlefilters();
				}
			});
		}
		return chb100;
	}
	private JCheckBox getChbm100() {
		if (chbm100 == null) {
			chbm100 = new JCheckBox(">100");
			chbm100.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					handlefilters();
				}

			});
		}
		return chbm100;
	}

	private void handlefilters() {
		model.clear();
		if(chb100.isSelected() || chb50.isSelected()|| chbm100.isSelected())
		{
			if(chb50.isSelected())
			{
				for(Jewel j : catalog)
				{
					if(j.getPrice()<50)
					{
					model.addElement(j);
					}
				}
			}
			if(chb100.isSelected())
			{
				for(Jewel j : catalog)
				{
					if(j.getPrice()<=100&& j.getPrice()>=50)
					{
					model.addElement(j);
					}
				}
			}
			if(chbm100.isSelected())
			{
				for(Jewel j : catalog)
				{
					if(j.getPrice()>100)
					{
					model.addElement(j);
					}
				}
			}
		}
		else
		{
			for(Jewel j : catalog)
			{
				model.addElement(j);
			}
		}
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
		   hb.enableHelpOnButton(mntmHelp, "inicio", hs);
		   
		 }
}
