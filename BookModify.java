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
import java.awt.event.ActionEvent;

public class BookModify extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	JInternalFrame frame;
	private JButton btnModify;
	
	/**
	 * Create the frame.
	 */
	public BookModify(String s1,String s2,String s3,String s4,String s5) {
		frame= new JInternalFrame("Book Modify",true,true,true,true);
		frame.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setBounds(25, 27, 88, 13);
		contentPane.add(lblBookId);
		
		JLabel lblBookName = new JLabel("Book Name");
		lblBookName.setBounds(25, 64, 88, 13);
		contentPane.add(lblBookName);
		
		JLabel lblPublisher = new JLabel("Publisher");
		lblPublisher.setBounds(25, 109, 88, 13);
		contentPane.add(lblPublisher);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(25, 152, 88, 13);
		contentPane.add(lblCategory);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(25, 193, 88, 13);
		contentPane.add(lblQuantity);
		
		textField = new JTextField();
		textField.setBounds(144, 24, 180, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(144, 61, 180, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(144, 106, 180, 19);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(144, 149, 180, 19);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(144, 190, 180, 19);
		contentPane.add(textField_4);
		
		textField.setText(s1);
		textField.setEnabled(false);
		textField_1.setText(s2);
		textField_2.setText(s3);
		textField_3.setText(s4);
		textField_4.setText(s5);
		frame.getContentPane().add(contentPane);
		
		btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
try {
					
					Connection cn;
					cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=123456789");
					String q1="update book set bname=?,bpub=?,cat=?,qty=? where bid=?";
					PreparedStatement st1= cn.prepareStatement(q1);
					st1.setInt(5,Integer.parseInt(textField.getText()));
					st1.setString(1, textField_1.getText());
					st1.setString(2, textField_2.getText());
					st1.setString(3, textField_3.getText());
					st1.setString(4, textField_4.getText());
					st1.executeUpdate();	
					JOptionPane.showMessageDialog(null, "Data Modified");
					
					cn.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage());			
					}
				
			}
		});
		btnModify.setBounds(144, 273, 85, 21);
		contentPane.add(btnModify);
		frame.setSize(400,400);
		Library.desktopPane.add(frame);
	}
}
