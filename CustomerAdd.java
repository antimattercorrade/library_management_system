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

public class CustomerAdd extends JFrame {

	private JPanel contentPane;
	JInternalFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	

	/**
	 * Create the frame.
	 */
	public CustomerAdd() {
		frame=new JInternalFrame("Customer Add",true,true,true,true);
		frame.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		frame.getContentPane().add(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 386, 363);
		contentPane.add(panel);
		
		JLabel label = new JLabel("Customer Name");
		label.setBounds(31, 40, 93, 22);
		panel.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(183, 42, 173, 19);
		panel.add(textField);
		
		JLabel label_1 = new JLabel("Phone Number");
		label_1.setBounds(31, 90, 105, 13);
		panel.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(183, 87, 173, 19);
		panel.add(textField_1);
		
		
		JButton button = new JButton("Add");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection cn= DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=123456789");
					String q1="select * from customer";
					PreparedStatement st1= cn.prepareStatement(q1);
					ResultSet rs=st1.executeQuery();
					int cid=(int) (Math.random()*9999);
					while(rs.next())
					{
						if(cid==Integer.parseInt(rs.getString(1)))
						{
							cid=(int) (Math.random()*9999);
							rs=st1.executeQuery();
						}
					}
					
					String query= "insert into customer values(?,?,?)";
					PreparedStatement st= cn.prepareStatement(query);
					st.setInt(1, cid);
					st.setString(2,textField.getText());
					st.setString(3, textField_1.getText());
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
		button.setBounds(122, 257, 85, 21);
		panel.add(button);
		frame.setSize(400,400);
		Library.desktopPane.add(frame);
	}
}
