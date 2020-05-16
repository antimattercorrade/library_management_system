import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookSearchReturn extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	DefaultTableModel tm;
	JInternalFrame frame;
	JScrollPane jp;
	int row;
	private JButton btnSelect;
	private JLabel label;
	private JTextField textField_1;
	private JButton button;
	private JButton button_1;
	private JLabel lblBookId;
	private JTextField textField_2;
	private JLabel lblCustomerId;
	private JTextField textField_3;
	private JButton btnSelect_1;
	/**
	 * Create the frame.
	 */
	public BookSearchReturn() {
		
		frame = new JInternalFrame("Book Search",true,true,true,true);
		frame.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(226, 26, 201, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterBookNamepublisher = new JLabel("Enter Book Name/Publisher");
		lblEnterBookNamepublisher.setBounds(20, 26, 182, 13);
		contentPane.add(lblEnterBookNamepublisher);
		
		table = new JTable();
		jp=new JScrollPane(table);
		jp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row= table.getSelectedRow();
			}
		});
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				
				Connection cn= DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=123456789");
				String q1="select * from book where bname=? or bpub=?";
				PreparedStatement st1= cn.prepareStatement(q1);
				st1.setString(1,textField.getText());
				st1.setString(2,textField.getText());
				ResultSet rs=st1.executeQuery();
				tm= new DefaultTableModel();
				tm.addColumn("Book Id");
				tm.addColumn("Book Name");
				tm.addColumn("Publisher");
				tm.addColumn("Category");
				tm.addColumn("Quantity");
				while(rs.next())
				{
					tm.addRow(new String[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
				}
				cn.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage());
				}
				table.setModel(tm);
				
				
			}
		});
		
		jp.setBounds(20, 151, 597, 173);
		contentPane.add(jp);
		
		
		frame.getContentPane().add(contentPane);
		
		
		btnSearch.setBounds(437, 22, 85, 21);
		contentPane.add(btnSearch);
		
		btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str=String.valueOf(table.getValueAt(row,0));
				
				Return.s1=str;
				textField_2.setText(str);
			
				
			}
		});
		btnSelect.setBounds(532, 22, 85, 21);
		contentPane.add(btnSelect);
		
		label = new JLabel("Enter Customer Name");
		label.setBounds(20, 84, 140, 13);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(226, 84, 201, 19);
		contentPane.add(textField_1);
		
		button = new JButton("Search");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				
				Connection cn= DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=123456789");
				String q1="select * from customer where cname=? ";
				PreparedStatement st1= cn.prepareStatement(q1);
				st1.setString(1,textField_1.getText());
				ResultSet rs=st1.executeQuery();
				tm= new DefaultTableModel();
				tm.addColumn("Customer Id");
				tm.addColumn("Customer Name");
				tm.addColumn("Phone Number");
				while(rs.next())
				{
					tm.addRow(new String[]{rs.getString(1),rs.getString(2),rs.getString(3)});
				}
				cn.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage());
				}
				table.setModel(tm);
				
				
			}
		});
		button.setBounds(437, 80, 85, 21);
		contentPane.add(button);
		
		button_1 = new JButton("Select");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
String str=String.valueOf(table.getValueAt(row,0));
				
				
				textField_3.setText(str);
			
				
				
				
			}
		});
		button_1.setBounds(532, 80, 85, 21);
		contentPane.add(button_1);
		
		lblBookId = new JLabel("Book ID");
		lblBookId.setBounds(20, 49, 145, 25);
		contentPane.add(lblBookId);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(226, 55, 201, 19);
		contentPane.add(textField_2);
		
		lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setBounds(20, 117, 140, 13);
		contentPane.add(lblCustomerId);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(226, 113, 201, 19);
		contentPane.add(textField_3);
		
		btnSelect_1 = new JButton("Select");
		btnSelect_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				
				Connection cn= DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=123456789");
				String q1="select tid from transaction where bid=? and cid=?";
				PreparedStatement st1= cn.prepareStatement(q1);
				st1.setString(1,textField_2.getText());
				st1.setString(2,textField_3.getText());
				ResultSet rs=st1.executeQuery();
				
				while(rs.next())
				{
					Return.textField.setText(rs.getString(1));
					
				}
				cn.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage());
				}
				
				Return.textField.setEditable(false);
				
				frame.dispose();
				
			}
		});
		btnSelect_1.setBounds(270, 368, 85, 21);
		contentPane.add(btnSelect_1);
		frame.setSize(643,451);
		Library.desktopPane.add(frame);
	}
}
