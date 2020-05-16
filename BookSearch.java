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

public class BookSearch extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	DefaultTableModel tm;
	JInternalFrame frame;
	JScrollPane jp;
	private JButton btnModify;
	int row;
	private JButton btnDelete;
	/**
	 * Create the frame.
	 */
	public BookSearch() {
		
		frame = new JInternalFrame("Book Search",true,true,true,true);
		frame.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(249, 23, 201, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterBookNamepublisher = new JLabel("Enter Book Name/Publisher");
		lblEnterBookNamepublisher.setBounds(20, 26, 188, 13);
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
		
		jp.setBounds(20, 80, 548, 173);
		contentPane.add(jp);
		
		
		frame.getContentPane().add(contentPane);
		
		
		btnSearch.setBounds(483, 22, 85, 21);
		contentPane.add(btnSearch);
		
		btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					
					String str= String.valueOf(table.getValueAt(row,0));
					Connection cn;
					cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=123456789");
					String q1="select * from book where bid=?";
					PreparedStatement st1= cn.prepareStatement(q1);
					st1.setInt(1,Integer.parseInt(str));
					ResultSet rs=st1.executeQuery();
					if(rs.next())
						new BookModify(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
					cn.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage());			
					}
			}
		});
		btnModify.setBounds(111, 294, 85, 21);
		contentPane.add(btnModify);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				int op=JOptionPane.showConfirmDialog(null,"Are you sure?");
				if(op==0)
				{
					try {
					
					String str= String.valueOf(table.getValueAt(row,0));
					Connection cn;
					cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=123456789");
					String q1="delete from book where bid=?";
					PreparedStatement st1= cn.prepareStatement(q1);
					st1.setInt(1,Integer.parseInt(str));
					st1.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data Deleted");	
					cn.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage());			
					}
				}
			}
		});
		btnDelete.setBounds(318, 294, 85, 21);
		contentPane.add(btnDelete);
		frame.setSize(626,400);
		Library.desktopPane.add(frame);
	}
}
