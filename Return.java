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

public class Return extends JFrame {

	private JPanel contentPane;

	JInternalFrame frame;
	public static JTextField textField;
	public static String s1;
	
	/**
	 * Create the frame.
	 */
	
	
	
	public Return() {
		frame= new JInternalFrame("Return",true,true,true,true);
		frame.setVisible(true);
		frame.setSize(579, 289);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 569, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBookId = new JLabel("Transaction ID");
		lblBookId.setBounds(30, 55, 86, 13);
		contentPane.add(lblBookId);
		
		textField = new JTextField();
		textField.setBounds(188, 52, 181, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnIssue = new JButton("Return");
		btnIssue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
				
					Issue.setQty(Integer.parseInt(s1),+1);
				
				
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection cn= DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=123456789");
					String q1="update transaction set rdate=? where tid=?";
					PreparedStatement st= cn.prepareStatement(q1);
					
					Date d=new Date();
					st.setString(1, d.toString());
					st.setInt(2, Integer.parseInt(textField.getText()));
					st.executeUpdate();
					cn.close();
					JOptionPane.showMessageDialog(null, "Data Added");
					textField.setText("");
				} catch (Exception e1) {
					
					JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage());
				}
				
				
				
			}
		});
		btnIssue.setBounds(187, 147, 85, 21);
		contentPane.add(btnIssue);
		
		JButton button = new JButton("...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookSearchReturn();
				
				
			}
		});
		button.setBounds(379, 51, 85, 21);
		contentPane.add(button);
		frame.getContentPane().add(contentPane);
		Library.desktopPane.add(frame);
	}

}
