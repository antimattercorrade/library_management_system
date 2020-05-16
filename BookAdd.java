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

public class BookAdd extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	JInternalFrame ba;
	

	/**
	 * Create the frame.
	 */
	public BookAdd() {
		ba=new JInternalFrame("Book Add",true,true,true,true);
		ba.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBookName = new JLabel("Book Name");
		lblBookName.setBounds(31, 40, 93, 22);
		contentPane.add(lblBookName);
		
		textField = new JTextField();
		textField.setBounds(183, 42, 173, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblBookPublisher = new JLabel("Book Publisher");
		lblBookPublisher.setBounds(31, 90, 105, 13);
		contentPane.add(lblBookPublisher);
		
		textField_1 = new JTextField();
		textField_1.setBounds(183, 87, 173, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(31, 139, 79, 13);
		contentPane.add(lblCategory);
		
		textField_2 = new JTextField();
		textField_2.setBounds(183, 136, 173, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(31, 180, 79, 13);
		contentPane.add(lblQuantity);
		
		textField_3 = new JTextField();
		textField_3.setBounds(183, 177, 173, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		ba.setSize(400,400);
		ba.getContentPane().add(contentPane);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection cn= DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=123456789");
					String q1="select * from book";
					PreparedStatement st1= cn.prepareStatement(q1);
					ResultSet rs=st1.executeQuery();
					int bid=(int) (Math.random()*9999);
					while(rs.next())
					{
						if(bid==Integer.parseInt(rs.getString(1)))
						{
							bid=(int) (Math.random()*9999);
							rs=st1.executeQuery();
						}
					}
					
					String query= "insert into book values(?,?,?,?,?)";
					PreparedStatement st= cn.prepareStatement(query);
					st.setInt(1, bid);
					st.setString(2,textField.getText());
					st.setString(3, textField_1.getText());
					st.setString(4, textField_2.getText());
					st.setInt(5, Integer.parseInt(textField_3.getText()));
					st.executeUpdate();
					cn.close();
					JOptionPane.showMessageDialog(null, "Data Added");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
				} catch (Exception e1) {
					
					JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage());
				}
				
			}
		});
		btnAdd.setBounds(122, 257, 85, 21);
		contentPane.add(btnAdd);
		Library.desktopPane.add(ba);
	}
}
