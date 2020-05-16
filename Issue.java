import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Issue extends JFrame {

	private JPanel contentPane;

	JInternalFrame frame;
	public static JTextField textField;
	public static JTextField textField_1;
	
	/**
	 * Create the frame.
	 */
	
	public static int getQty(int bid)
	{
		int qty = 0;
		try {
			
			
			Connection cn;
			cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=123456789");
			String q1="select qty from book where bid=?";
			PreparedStatement st1= cn.prepareStatement(q1);
			st1.setInt(1,bid);
			ResultSet rs=st1.executeQuery();
			if(rs.next())
				qty=Integer.parseInt(rs.getString(1));
			cn.close();
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage());			
			}
		return qty;
	}
	
	
	public static void setQty(int bid,int amt)
	{
try {
			
			
			Connection cn;
			cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=123456789");
			String q1="update book set qty=qty+? where bid=?";
			PreparedStatement st1= cn.prepareStatement(q1);
			st1.setInt(2,bid);
			st1.setInt(1, amt);
			st1.executeUpdate();
			cn.close();
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage());			
			}
	}
	
	public Issue() {
		frame= new JInternalFrame("Issue",true,true,true,true);
		frame.setVisible(true);
		frame.setSize(502, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 569, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setBounds(30, 55, 86, 13);
		contentPane.add(lblBookId);
		
		textField = new JTextField();
		textField.setBounds(188, 52, 181, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("Customer ID");
		label.setBounds(30, 114, 86, 13);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(188, 111, 181, 19);
		contentPane.add(textField_1);
		
		JButton btnIssue = new JButton("Issue");
		btnIssue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int q=getQty(Integer.parseInt(textField.getText()));
				if(q==0)
					JOptionPane.showMessageDialog(null, "This book is currently not available");	
				else
				{
					setQty(Integer.parseInt(textField.getText()),-1);
				}
				
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection cn= DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=123456789");
					String q1="select * from transaction";
					PreparedStatement st1= cn.prepareStatement(q1);
					ResultSet rs=st1.executeQuery();
					int tid=(int) (Math.random()*9999);
					while(rs.next())
					{
						if(tid==Integer.parseInt(rs.getString(1)))
						{
							tid=(int) (Math.random()*9999);
							rs=st1.executeQuery();
						}
					}
					
					String query= "insert into transaction values(?,?,?,?,?)";
					PreparedStatement st= cn.prepareStatement(query);
					st.setInt(1, tid);
					st.setInt(2,Integer.parseInt(textField.getText()));
					st.setInt(3, Integer.parseInt(textField_1.getText()));
					Date d=new Date();
					st.setString(4, d.toString());
					st.setString(5, "Not Available");
					st.executeUpdate();
					cn.close();
					JOptionPane.showMessageDialog(null, "Data Added");
					textField.setText("");
					textField_1.setText("");
				} catch (Exception e1) {
					
					JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage());
				}
				
				
				
			}
		});
		btnIssue.setBounds(118, 196, 85, 21);
		contentPane.add(btnIssue);
		
		JButton button = new JButton("...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookSearchIssue();
				
				
			}
		});
		button.setBounds(379, 51, 85, 21);
		contentPane.add(button);
		
		JButton button_1 = new JButton("...");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new CustomerSearchIssue();
				
				
			}
		});
		button_1.setBounds(379, 110, 85, 21);
		contentPane.add(button_1);
		frame.getContentPane().add(contentPane);
		Library.desktopPane.add(frame);
	}

}
