import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerSearch extends JFrame {

	JInternalFrame frame;
	private JTextField textField;
	DefaultTableModel tm;
	JTable table;
	int row;
	/**
	 * Create the frame.
	 */
	public CustomerSearch() {
		frame=new JInternalFrame("Customer Search",true,true,true,true);
		frame.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 653, 443);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		//getContentPane().add(panel, BorderLayout.CENTER);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(280, 23, 201, 19);
		panel.add(textField);
		
		JLabel label = new JLabel("Enter Customer Name/Phone Number");
		label.setBounds(20, 26, 230, 13);
		panel.add(label);
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 80, 548, 173);
		panel.add(scrollPane);
		
		
		
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row= table.getSelectedRow();
			}
		});
		
		
		
		JButton button = new JButton("Search");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				
				Connection cn= DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=123456789");
				String q1="select * from customer where cname=? or phno=?";
				PreparedStatement st1= cn.prepareStatement(q1);
				st1.setString(1,textField.getText());
				st1.setString(2,textField.getText());
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
		button.setBounds(514, 22, 85, 21);
		panel.add(button);
		
		JButton button_1 = new JButton("Modify");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
try {
					
					String str= String.valueOf(table.getValueAt(row,0));
					Connection cn;
					cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=123456789");
					String q1="select * from customer where cid=?";
					PreparedStatement st1= cn.prepareStatement(q1);
					st1.setInt(1,Integer.parseInt(str));
					ResultSet rs=st1.executeQuery();
					if(rs.next())
						new CustomerModify(rs.getString(1),rs.getString(2),rs.getString(3));
					cn.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage());			
					}
				
				
			}
		});
		button_1.setBounds(111, 294, 85, 21);
		panel.add(button_1);
		
		JButton button_2 = new JButton("Delete");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int op=JOptionPane.showConfirmDialog(null,"Are you sure?");
				if(op==0)
				{
					try {
					
					String str= String.valueOf(table.getValueAt(row,0));
					Connection cn;
					cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=123456789");
					String q1="delete from customer where cid=?";
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
		button_2.setBounds(318, 294, 85, 21);
		panel.add(button_2);
		frame.getContentPane().add(panel);
		frame.setSize(643,426);
		Library.desktopPane.add(frame);
	}
}
