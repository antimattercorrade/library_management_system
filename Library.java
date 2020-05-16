import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class Library extends JFrame {

	private JPanel contentPane;
	public static JDesktopPane desktopPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Library frame = new Library();
					frame.setTitle("Library Management System");
					frame.setSize(750,700);
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
	public Library() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 531);
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnBooks = new JMenu("Books");
		menuBar.add(mnBooks);
		
		JMenuItem mntmAdd = new JMenuItem("Add");
		mntmAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookAdd();
				
			}
		});
		mnBooks.add(mntmAdd);
		
		JMenuItem mntmSearch = new JMenuItem("Search");
		mntmSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new BookSearch();
				
			}
		});
		mnBooks.add(mntmSearch);
		
		JMenu mnCustomer = new JMenu("Customer");
		menuBar.add(mnCustomer);
		
		JMenuItem mntmAdd_1 = new JMenuItem("Add");
		mntmAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomerAdd();
				
			}
		});
		mnCustomer.add(mntmAdd_1);
		
		JMenuItem mntmSearch_1 = new JMenuItem("Search");
		mntmSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new CustomerSearch();
			}
		});
		mnCustomer.add(mntmSearch_1);
		
		JMenu mnTransaction = new JMenu("Transaction");
		menuBar.add(mnTransaction);
		
		JMenuItem mntmIssue = new JMenuItem("Issue");
		mntmIssue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Issue();
			}
		});
		mnTransaction.add(mntmIssue);
		
		JMenuItem mntmReturn = new JMenuItem("Return");
		mntmReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Return();
			}
		});
		mnTransaction.add(mntmReturn);
		
		JMenuItem mntmDefaulter = new JMenuItem("Defaulter");
		mntmDefaulter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Defaulter();
				
			}
		});
		mnTransaction.add(mntmDefaulter);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 10, 710, 600);
		contentPane.add(desktopPane);
	}
}
