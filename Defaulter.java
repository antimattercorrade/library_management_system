import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Defaulter extends JFrame {

	private JPanel contentPane;

	JInternalFrame frame;
	private JTable table;
	DefaultTableModel tm;
	int f=0;
	/**
	 * Create the frame.
	 */
	public Defaulter() {
		frame= new JInternalFrame("Defaulter",true,true,true,true);
		frame.setVisible(true);
		frame.setSize(696, 472);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		frame.getContentPane().add(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 700, 450);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection cn= DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=123456789");
		String q1="select isdate from transaction";
		PreparedStatement st1= cn.prepareStatement(q1);
		Date d= new Date();
		String str1[]=(d.toString()).split(" ");
		
		
		ResultSet rs=st1.executeQuery();
		tm= new DefaultTableModel();
		tm.addColumn("Transaction ID");
		tm.addColumn("Book ID");
		tm.addColumn("Customer ID");
		tm.addColumn("Issue Date");
		tm.addColumn("Return Date");
		
		while(rs.next())
		{
			String str2[]=(rs.getString(1)).split(" ");
			if(str2[1].equals(str1[1]))
			{
				if((Integer.parseInt(str2[2])-Integer.parseInt(str1[2]))>6)
				{
					
					String q2="select * from transaction where isdate=?";
					PreparedStatement st2= cn.prepareStatement(q2);
					st2.setString(1,rs.getString(1));
					ResultSet rs1= st2.executeQuery();
					while(rs1.next())
					{
						tm.addRow(new String[] {rs1.getString(1),rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5)});
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "No Defaulters");
					f=1;
					frame.dispose();
				}
			}
			else
			{
				int day=0;
				if(str2[1].equals("Jan")||str2[1].equals("Mar")||str2[1].equals("May")||str2[1].equals("Jul")||str2[1].equals("Aug")||str2[1].equals("Oct")||str2[1].equals("Dec"))
				{
					 day=31;
				}
				else if(str2[1].equals("Apr")||str2[1].equals("Jun")||str2[1].equals("Sep")||str2[1].equals("Nov"))
				{
					day=30;
				}
				else if((Integer.parseInt(str2[5])%400==0)||((Integer.parseInt(str2[5])%4==0) && (Integer.parseInt(str2[5])%100!=0)))
						{
							day=29;
						}
				else
					day= 28;
				int day1=(Integer.parseInt(str1[2])-1)+(day-Integer.parseInt(str2[2]))+1;
				if(day1>6)
				{
					String q2="select * from transaction where isdate=?";
					PreparedStatement st2= cn.prepareStatement(q2);
					st2.setString(1,rs.getString(1));
					ResultSet rs1= st2.executeQuery();
					while(rs1.next())
					{
						tm.addRow(new String[] {rs1.getString(1),rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5)});
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "No Defaulters");
					f=1;
					frame.dispose();
				}
				
			}
		}
		
			
		
		cn.close();
		table.setModel(tm);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			if(f==0)
				JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage());
		}
		
		
		
		
		
		Library.desktopPane.add(frame);
	}
}
