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
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.util.List;

import java.net.*;
import javax.help.*;

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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import logic.Catalog;
import logic.Cruise;
import logic.Ticket;

public class MainWincru extends JFrame {

	private JPanel contentPane;
	private JPanel logopanel;
	private JLabel lblCruises;
	private JLabel lbllogo;
	private JSplitPane splitCenterpanel;
	private JSplitPane splitPane;
	private JPanel panelFilters;
	private JCheckBox chckbxAmerica;
	private JCheckBox chckbxafrica;
	private JCheckBox chckbxeurope;
	private JCheckBox chckbxAsia;
	private JCheckBox chckbxOceania;
	private JScrollPane scrollPane;
	
	private JPanel panelright;
	private JScrollPane scrollPaneTA;
	private JTextArea tADescription;
	private JPanel pnPay;
	private JPanel panelBotons;
	private JButton btnCancel;
	private JButton btnNext;
	private JCheckBox chckbxExtraPay;
	private JTextField txtPrice;
	private JLabel lblTotal;
	private JLabel lblPeople;
	private JSpinner spinner;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnHepl;
	private JMenuItem mntmOpen;
	private JSeparator separator;
	private JMenuItem mntmExit;
	private JMenuItem mntmHelp;
	private JMenuItem mntmAbout;
	
	private static Catalog catalog ;
	private static List<Cruise> cruises;
	private JList listcruises;
	private DefaultListModel<Cruise> model;
	
	private DialogPayment dP;
	private MainWincru mW;
	private LogInDialog lI;
	private JButton btnAdd;

	
	private static Ticket ticket;
	private JScrollPane scrollPaneCruises;
	private JTextArea tAcruises;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					catalog = new Catalog();
					ticket = new Ticket();
					cruises =catalog.getCruises() ;
					MainWincru frame = new MainWincru();
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
	public MainWincru() {
		setTitle("Cruises");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWincru.class.getResource("/img/agencia.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 725, 580);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getLogopanel(), BorderLayout.NORTH);
		contentPane.add(getSplitCenterpanel(), BorderLayout.CENTER);
		contentPane.add(getPanelBotons(), BorderLayout.SOUTH);
		
		
		cargaAyuda();		
		mW= this;
		//JOptionPane.showMessageDialog(this, "login in");
		
		lI = new LogInDialog();
		lI.setLocationRelativeTo(mW);
		lI.setModal(true);
		lI.setVisible(true);
		
		
		
		
	}

	private JPanel getLogopanel() {
		if (logopanel == null) {
			logopanel = new JPanel();
			logopanel.setBackground(Color.WHITE);
			logopanel.setLayout(new GridLayout(0, 2, 0, 0));
			logopanel.add(getLbllogo());
			logopanel.add(getLblCruises());
		}
		return logopanel;
	}
	private JLabel getLblCruises() {
		if (lblCruises == null) {
			lblCruises = new JLabel("Cruises");
			lblCruises.setFont(new Font("Tahoma", Font.BOLD, 20));
		}
		return lblCruises;
	}
	private JLabel getLbllogo() {
		if (lbllogo == null) {
			lbllogo = new JLabel("");
			lbllogo.setIcon(new ImageIcon(MainWincru.class.getResource("/img/agencia.jpg")));
		}
		return lbllogo;
	}
	private JSplitPane getSplitCenterpanel() {
		if (splitCenterpanel == null) {
			splitCenterpanel = new JSplitPane();
			splitCenterpanel.setLeftComponent(getSplitPane());
			splitCenterpanel.setRightComponent(getPanelright());
		}
		return splitCenterpanel;
	}
	private JSplitPane getSplitPane() {
		if (splitPane == null) {
			splitPane = new JSplitPane();
			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane.setLeftComponent(getPanelFilters());
			splitPane.setRightComponent(getScrollPane());
		}
		return splitPane;
	}
	private JPanel getPanelFilters() {
		if (panelFilters == null) {
			panelFilters = new JPanel();
			panelFilters.setLayout(new GridLayout(1, 0, 0, 0));
			panelFilters.add(getChckbxAmerica());
			panelFilters.add(getChckbxeurope());
			panelFilters.add(getChckbxafrica());
			panelFilters.add(getChckbxAsia());
			panelFilters.add(getChckbxOceania());
		}
		return panelFilters;
	}

	private void manageFilters() {
				
		model.clear();
	
		if(chckbxAmerica.isSelected())
		{
			for(Cruise cru : cruises )
			{
				if(cru.getContinent().toString().equals("América"))
				{
					model.addElement(cru);
				}
			}
		}
		
		 if(chckbxafrica.isSelected())
		{
			for(Cruise cru : cruises )
			{
				System.out.println(cru.getContinent());
				if(cru.getContinent().toString().equals("Africa"))
				{
					model.addElement(cru);
				}
			}
		}
		
		 if(chckbxeurope.isSelected())
		{
			for(Cruise cru : cruises )
			{
				if(cru.getContinent().toString().equals("Europa"))
				{
					model.addElement(cru);
				}
			}
		}
		 if(chckbxAsia.isSelected())
		{
			for(Cruise cru : cruises )
			{
				if(cru.getContinent().toString().equals("Asia"))
				{
					model.addElement(cru);
				}
			}
		}
		 if(chckbxOceania.isSelected())
		{
			for(Cruise cru : cruises )
			{
				if(cru.getContinent().toString().equals("Oceanía"))
				{
					model.addElement(cru);
				}
			}
		}
		
	}
	
	private JCheckBox getChckbxAmerica() {
		if (chckbxAmerica == null) {
			chckbxAmerica = new JCheckBox("America");
			chckbxAmerica.setSelected(true);
			chckbxAmerica.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					manageFilters();
				}

			});
		}
		return chckbxAmerica;
	}
	private JCheckBox getChckbxafrica() {
		if (chckbxafrica == null) {
			chckbxafrica = new JCheckBox("Africa");
			chckbxafrica.setSelected(true);
			chckbxafrica.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					manageFilters();
				}

			});
			
		}
		return chckbxafrica;
	}
	private JCheckBox getChckbxeurope() {
		if (chckbxeurope == null) {
			chckbxeurope = new JCheckBox("Europe");
			chckbxeurope.setSelected(true);
			chckbxeurope.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					manageFilters();
				}

			});
			
		}
		return chckbxeurope;
	}
	private JCheckBox getChckbxAsia() {
		if (chckbxAsia == null) {
			chckbxAsia = new JCheckBox("Asia");
			chckbxAsia.setSelected(true);
			chckbxAsia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					manageFilters();
				}

			});
		}
		return chckbxAsia;
	}
	private JCheckBox getChckbxOceania() {
		if (chckbxOceania == null) {
			chckbxOceania = new JCheckBox("Oceania");
			chckbxOceania.setSelected(true);
			chckbxOceania.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					manageFilters();
				}

			});
		}
		return chckbxOceania;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getListcruises());
		}
		return scrollPane;
	}
	private JList<Cruise> getListcruises() {
		if (listcruises == null) {
			model = new DefaultListModel<Cruise>();
			listcruises = new JList<Cruise>(model);
			for(Cruise c: cruises)
			{
				model.addElement(c);
			}
			listcruises.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listcruises.addListSelectionListener(new ListSelectionListener() {
				
				public void valueChanged(ListSelectionEvent e) {
					
					JList<Cruise> cruisesl = (JList<Cruise>)e.getSource();
					if(cruisesl.isSelectionEmpty())
					{
						tADescription.setText(" ");
						tAcruises.setText(" ");
						spinner.setEnabled(false);
						txtPrice.setText(new Float(0.00)+"€");
					}
					else
					{
						
						tADescription.setText(cruisesl.getSelectedValue().getDescription());
						if(ticket!=null)tAcruises.setText(ticket.ToString());
						spinner.setEnabled(true);
						txtPrice.setEnabled(true);						
						btnNext.setEnabled(true);
						
						if(chckbxExtraPay.isSelected())
						{
							ticket.setprice(cruisesl.getSelectedValue().getBpriceplus()*((int)spinner.getValue()));
							
							
						}
						else
						{
							ticket.setprice((cruisesl.getSelectedValue().getPprice())*((int)spinner.getValue()));
							
							
						}
					}
				}
			});
			
			
		}
		return listcruises;
	}
	private JPanel getPanelright() {
		if (panelright == null) {
			panelright = new JPanel();
			panelright.setLayout(new BorderLayout(0, 0));
			panelright.add(getScrollPaneTA(), BorderLayout.CENTER);
			panelright.add(getPnPay(), BorderLayout.EAST);
		}
		return panelright;
	}
	private JScrollPane getScrollPaneTA() {
		if (scrollPaneTA == null) {
			scrollPaneTA = new JScrollPane();
			scrollPaneTA.setViewportView(getTADescription());
		}
		return scrollPaneTA;
	}
	private JTextArea getTADescription() {
		if (tADescription == null) {
			tADescription = new JTextArea();
			tADescription.setWrapStyleWord(true);
			tADescription.setLineWrap(true);
		}
		return tADescription;
	}
	private JPanel getPnPay() {
		if (pnPay == null) {
			pnPay = new JPanel();
			pnPay.setBackground(Color.WHITE);
			GridBagLayout gbl_pnPay = new GridBagLayout();
			gbl_pnPay.columnWidths = new int[]{0, 0, 0};
			gbl_pnPay.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_pnPay.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_pnPay.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			pnPay.setLayout(gbl_pnPay);
			GridBagConstraints gbc_lblPeople = new GridBagConstraints();
			gbc_lblPeople.insets = new Insets(0, 0, 5, 5);
			gbc_lblPeople.gridx = 0;
			gbc_lblPeople.gridy = 1;
			pnPay.add(getLblPeople(), gbc_lblPeople);
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.insets = new Insets(0, 0, 5, 0);
			gbc_spinner.gridx = 1;
			gbc_spinner.gridy = 1;
			pnPay.add(getSpinner(), gbc_spinner);
			GridBagConstraints gbc_btnAdd = new GridBagConstraints();
			gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
			gbc_btnAdd.gridx = 1;
			gbc_btnAdd.gridy = 3;
			pnPay.add(getBtnAdd(), gbc_btnAdd);
			GridBagConstraints gbc_chckbxExtraPay = new GridBagConstraints();
			gbc_chckbxExtraPay.insets = new Insets(0, 0, 5, 0);
			gbc_chckbxExtraPay.anchor = GridBagConstraints.WEST;
			gbc_chckbxExtraPay.gridx = 1;
			gbc_chckbxExtraPay.gridy = 4;
			pnPay.add(getChckbxExtraPay(), gbc_chckbxExtraPay);
			GridBagConstraints gbc_lblTotal = new GridBagConstraints();
			gbc_lblTotal.insets = new Insets(0, 0, 5, 5);
			gbc_lblTotal.anchor = GridBagConstraints.EAST;
			gbc_lblTotal.gridx = 0;
			gbc_lblTotal.gridy = 5;
			pnPay.add(getLblTotal(), gbc_lblTotal);
			GridBagConstraints gbc_txtPrice = new GridBagConstraints();
			gbc_txtPrice.insets = new Insets(0, 0, 5, 0);
			gbc_txtPrice.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPrice.gridx = 1;
			gbc_txtPrice.gridy = 5;
			pnPay.add(getTxtPrice(), gbc_txtPrice);
			GridBagConstraints gbc_scrollPaneCruises = new GridBagConstraints();
			gbc_scrollPaneCruises.fill = GridBagConstraints.BOTH;
			gbc_scrollPaneCruises.gridx = 1;
			gbc_scrollPaneCruises.gridy = 7;
			pnPay.add(getScrollPaneCruises(), gbc_scrollPaneCruises);
		}
		return pnPay;
	}
	private JPanel getPanelBotons() {
		if (panelBotons == null) {
			panelBotons = new JPanel();
			panelBotons.setBackground(Color.WHITE);
			panelBotons.add(getBtnCancel());
			panelBotons.add(getBtnNext());
		}
		return panelBotons;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {					
					reset();
				}
			});
		}
		return btnCancel;
	}
	public void reset()
	{
		txtPrice.setText(" ");
		listcruises.clearSelection();
		ticket = new Ticket();
	}
	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("Next");
			btnNext.addActionListener(new ActionListener() {
				

				public void actionPerformed(ActionEvent e) {
					
					if(isfilled())
					{
						dP=new DialogPayment(mW, ticket);
						dP.setLocationRelativeTo(mW);
						dP.setModal(true);
						dP.setVisible(true);
					}
					
					
				}
			});
			btnNext.setEnabled(false);
		}
		return btnNext;
	}
	private boolean isfilled()
	{
		if(!txtPrice.getText().isEmpty())
		{
			return true;
		}
		
		return false;
	}
	
	private JCheckBox getChckbxExtraPay() {
		if (chckbxExtraPay == null) {
			chckbxExtraPay = new JCheckBox("extra pay");
			chckbxExtraPay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Cruise thiscruise = (Cruise) listcruises.getSelectedValue();
					if(chckbxExtraPay.isSelected())
					{						
						txtPrice.setText(thiscruise.getBpriceplus()*(int)spinner.getValue()+"€");						
					}
					else
					{
						txtPrice.setText(thiscruise.getPprice()*(int)spinner.getValue()+"€");
					}
				}
			});
			
		}
		return chckbxExtraPay;
	}
	private JTextField getTxtPrice() {
		if (txtPrice == null) {
			txtPrice = new JTextField();
			txtPrice.setBackground(Color.WHITE);
			txtPrice.setEnabled(false);
			txtPrice.setEditable(false);
			txtPrice.setColumns(10);
		}
		return txtPrice;
	}
	private JLabel getLblTotal() {
		if (lblTotal == null) {
			lblTotal = new JLabel("total:");
		}
		return lblTotal;
	}
	private JLabel getLblPeople() {
		if (lblPeople == null) {
			lblPeople = new JLabel("people:");
		}
		return lblPeople;
	}
	private JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					if(!listcruises.isSelectionEmpty())
					{
						Cruise thecruise =(Cruise) listcruises.getSelectedValue();
						if(chckbxExtraPay.isSelected())
						{
						
						txtPrice.setText(thecruise.getBpriceplus()*(int)spinner.getValue()+"€");						
						}
						else
						{
							txtPrice.setText(thecruise.getPprice()*(int)spinner.getValue()+"€");
						}
					}
				}
			});
			spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		}
		return spinner;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnHepl());
		}
		return menuBar;
	}
	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.add(getMntmOpen());
			mnFile.add(getSeparator());
			mnFile.add(getMntmExit());
		}
		return mnFile;
	}
	private JMenu getMnHepl() {
		if (mnHepl == null) {
			mnHepl = new JMenu("Help");
			mnHepl.add(getMntmAbout());
			mnHepl.add(getMntmHelp());
		}
		return mnHepl;
	}
	private JMenuItem getMntmOpen() {
		if (mntmOpen == null) {
			mntmOpen = new JMenuItem("Next");
			mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		}
		return mntmOpen;
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
			mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return mntmExit;
	}
	public void close()
	{
		System.exit(0);
	}
	private JMenuItem getMntmHelp() {
		if (mntmHelp == null) {
			mntmHelp = new JMenuItem("Help");
		}
		return mntmHelp;
	}
	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		}
		return mntmAbout;
	}
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Add");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ticket.addCruise((Cruise) listcruises.getSelectedValuesList().get(0));
					tAcruises.setText(ticket.ToString());
					txtPrice.setText((ticket.gettprice()+"€"));
				
				}
			});
		}
		return btnAdd;
	}
	private JScrollPane getScrollPaneCruises() {
		if (scrollPaneCruises == null) {
			scrollPaneCruises = new JScrollPane();
			scrollPaneCruises.setViewportView(getTAcruises());
		}
		return scrollPaneCruises;
	}
	private JTextArea getTAcruises() {
		if (tAcruises == null) {
			tAcruises = new JTextArea();
			tAcruises.setWrapStyleWord(true);
			tAcruises.setEditable(false);
		}
		return tAcruises;
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
		   hb.enableHelp(btnCancel, "cancel", hs);
		   
		 }

}
