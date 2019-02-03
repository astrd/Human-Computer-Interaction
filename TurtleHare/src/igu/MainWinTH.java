package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class MainWinTH extends JFrame {

	private JPanel contentPane;
	private JPanel pnRace;
	private JPanel pnTitle;
	private JTextField txtTpoints;
	private JLabel lblTurtlePoints;
	private JLabel lblHare;
	private JTextField txtHarepoints;
	private JPanel pnDice;
	private JButton btnDice;
	private JPanel pnButtons;
	private JButton btnReset;
	private JPanel pnTurtle;
	private JPanel pnHare;
	private JLabel lblTurtle;
	private JLabel lblHare_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWinTH frame = new MainWinTH();
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
	public MainWinTH() {
		setTitle("Turtle and Hare");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWinTH.class.getResource("/img/sea-turtle.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 576, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnRace(), BorderLayout.CENTER);
		contentPane.add(getPnTitle(), BorderLayout.NORTH);
		contentPane.add(getPnDice(), BorderLayout.WEST);
		contentPane.add(getPnButtons(), BorderLayout.SOUTH);
	}

	private JPanel getPnRace() {
		if (pnRace == null) {
			pnRace = new JPanel();
			pnRace.setLayout(null);
			pnRace.add(getPnTurtle());
			pnRace.add(getPnHare());
			pnRace.add(getLblTurtle());
			pnRace.add(getLblHare_1());
		}
		return pnRace;
	}
	private JPanel getPnTitle() {
		if (pnTitle == null) {
			pnTitle = new JPanel();
			pnTitle.setLayout(new GridLayout(1, 0, 0, 0));
			pnTitle.add(getLblTurtlePoints());
			pnTitle.add(getTxtTpoints());
			pnTitle.add(getLblHare());
			pnTitle.add(getTxtHarepoints());
		}
		return pnTitle;
	}
	private JTextField getTxtTpoints() {
		if (txtTpoints == null) {
			txtTpoints = new JTextField();
			txtTpoints.setEnabled(false);
			txtTpoints.setEditable(false);
			txtTpoints.setText("0");
			txtTpoints.setColumns(10);
		}
		return txtTpoints;
	}
	private JLabel getLblTurtlePoints() {
		if (lblTurtlePoints == null) {
			lblTurtlePoints = new JLabel("turtle:");
		}
		return lblTurtlePoints;
	}
	private JLabel getLblHare() {
		if (lblHare == null) {
			lblHare = new JLabel("Hare:");
		}
		return lblHare;
	}
	private JTextField getTxtHarepoints() {
		if (txtHarepoints == null) {
			txtHarepoints = new JTextField();
			txtHarepoints.setEnabled(false);
			txtHarepoints.setEditable(false);
			txtHarepoints.setText("0");
			txtHarepoints.setColumns(10);
		}
		return txtHarepoints;
	}
	private JPanel getPnDice() {
		if (pnDice == null) {
			pnDice = new JPanel();
			pnDice.setLayout(new GridLayout(0, 1, 0, 0));
			pnDice.add(getBtnDice());
		}
		return pnDice;
	}
	private JButton getBtnDice() {
		if (btnDice == null) {
			btnDice = new JButton("Dice");
		}
		return btnDice;
	}
	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			pnButtons.add(getBtnReset());
		}
		return pnButtons;
	}
	private JButton getBtnReset() {
		if (btnReset == null) {
			btnReset = new JButton("Reset");
		}
		return btnReset;
	}
	private JPanel getPnTurtle() {
		if (pnTurtle == null) {
			pnTurtle = new JPanel();
			pnTurtle.setBounds(122, 11, 365, 81);
			pnTurtle.setLayout(new GridLayout(1, 0, 0, 0));
		}
		return pnTurtle;
	}
	private JPanel getPnHare() {
		if (pnHare == null) {
			pnHare = new JPanel();
			pnHare.setBounds(122, 103, 357, 94);
			pnHare.setLayout(new GridLayout(1, 0, 0, 0));
		}
		return pnHare;
	}
	private JLabel getLblTurtle() {
		if (lblTurtle == null) {
			lblTurtle = new JLabel("");
			lblTurtle.setBackground(Color.GREEN);
			lblTurtle.setIcon(new ImageIcon(MainWinTH.class.getResource("/img/sea-turtle.png")));
			lblTurtle.setBounds(9, 11, 103, 81);
		}
		return lblTurtle;
	}
	private JLabel getLblHare_1() {
		if (lblHare_1 == null) {
			lblHare_1 = new JLabel("");
			lblHare_1.setIcon(new ImageIcon(MainWinTH.class.getResource("/img/hare.jpg")));
			lblHare_1.setBackground(Color.GREEN);
			lblHare_1.setBounds(12, 114, 100, 83);
		}
		return lblHare_1;
	}
}
