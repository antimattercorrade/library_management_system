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
import java.awt.event.ActionEvent;

public class CustomerModify extends JFrame {

	private JPanel contentPane;
	JInternalFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	

	/**
	 * Create the frame.
	 */
	public CustomerModify(String s1,String s2,String s3) {
		frame= new JInternalFrame("Customer Modify",true,true,true,true);
		frame.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 447, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		//setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel label = new JLabel("Customer ID");
		label.setBounds(25, 27, 88, 13);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Customer Name");
		label_1.setBounds(25, 64, 88, 13);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Phone Number");
		label_2.setBounds(25, 109, 88, 13);
		panel.add(label_2);
		
		
		textField = new JTextField();
		
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(144, 24, 180, 19);
		panel.add(textField);
		
		textField_1 = new JTextField();
		
		textField_1.setColumns(10);
		textField_1.setBounds(144, 61, 180, 19);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		
		textField_2.setColumns(10);
		textField_2.setBounds(144, 106, 180, 19);
		panel.add(textField_2);
		
		textField.setText(s1);
		
		textField_1.setText(s2);
		textField_2.setText(s3);
		
		JButton button_1 = new JButton("Modify");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
try {
					
					Connection cn;
					cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=123456789");
					String q1="update customer set cname=?,phno=? where cid=?";
					PreparedStatement st1= cn.prepareStatement(q1);
					st1.setInt(3,Integer.parseInt(textField.getText()));
					st1.setString(1, textField_1.getText());
					st1.setString(2, textField_2.getText());
					st1.executeUpdate();	
					JOptionPane.showMessageDialog(null, "Data Modified");
					
					cn.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage());			
					}
				
				
			}
		});
		button_1.setBounds(157, 173, 85, 21);
		panel.add(button_1);
		frame.getContentPane().add(contentPane);
		frame.setSize(400,327);
		Library.desktopPane.add(frame);
	}

}
