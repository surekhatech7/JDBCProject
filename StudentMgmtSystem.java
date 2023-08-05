import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentMgmtSystem {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = null;
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBCdb","root","Surekha@786");
		System.out.println("connected"+con);
		
		int id;
		String name;
		String qualification;
		int admission_year;
		String city;
		
		int choice;
		
		do
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("\n1.Add new student"+"\n2.Display all records"+"\n3.Update student data"+"\n4.Delete student data"+"\n5.Search student by name or qualification"+"\n6.exit");
			System.out.println("\nEnter which operation you have to perform");
			choice = sc.nextInt();
			
			switch(choice)
			{
				case 1:
					System.out.println("\nEnter student id");
					id=sc.nextInt();
					
					System.out.println("Enter student name");
					name=sc.next();
					
					System.out.println("Enter qualification");
					qualification=sc.next();
					
					System.out.println("Enter admission_year");
					admission_year=sc.nextInt();
					
					System.out.println("Enter city");
					city=sc.next();
					
					String insertRecord = "insert into student values(?,?,?,?,?)"; 
					PreparedStatement stmt = con.prepareStatement(insertRecord);
					
					stmt.setInt(1, id);
					stmt.setString(2, name);
					stmt.setString(3, qualification);
					stmt.setInt(4, admission_year);
					stmt.setString(5, city);
					
					int n = stmt.executeUpdate();
					System.out.println();
					System.out.println(n+" student record added successfully");
					
					break;
					
				case 2:
					String fetchRecords = "select * from student";
					Statement stmt1 = con.createStatement();
					ResultSet rs = stmt1.executeQuery(fetchRecords);
					System.out.println();
					while(rs.next())
					{
						id = rs.getInt(1);
						name = rs.getString(2);
						qualification = rs.getString(3);
						admission_year = rs.getInt(4);
						city = rs.getString(5);
						
						System.out.println("Student id:"+id+"  Student Name:"+name+"  Qualifications:"+qualification+"  Admission year:"+admission_year+"  city:"+city);
					}
					
					break;
					
				case 3:
					System.out.println("\n1.update name"+"\n2.update qualification"+"\n3.update admission year"+"\n4.update city");
					System.out.println("\nplease enter the option");
					int choice1 = sc.nextInt();
					switch(choice1)
					{
						case 1:
							PreparedStatement stmt2 = con.prepareStatement("update student set name=? where id=?");
							
							System.out.println("\nplease enter id  in which you want to update the name");
							id = sc.nextInt();
							System.out.println("enter new name:");
							name = sc.next();
							
							stmt2.setString(1, name);
							stmt2.setInt(2, id);
							
							
							int n1 = stmt2.executeUpdate();	
							System.out.println();
							System.out.println(n1+" name updated successfully");
									
							break;
							
						case 2:
							PreparedStatement stmt3 = con.prepareStatement("update student set qualification=? where id=?");
							
							System.out.println("\nplease enter id  in which you want to update the qualification");
							id = sc.nextInt();
							System.out.println("enter new qualification:");
							qualification = sc.next();
							
							stmt3.setString(1, qualification);
							stmt3.setInt(2, id);
							
							
							int n2 = stmt3.executeUpdate();	
							System.out.println();
							System.out.println(n2+" qualification updated successfully");
									
							break;
							
						case 3:
							PreparedStatement stmt4 = con.prepareStatement("update student set admission_year=? where id=?");
							
							System.out.println("\nplease enter id  in which you want to update the admission year");
							id = sc.nextInt();
							System.out.println("enter new admission year:");
							admission_year = sc.nextInt();
							
							stmt4.setInt(1, admission_year);
							stmt4.setInt(2, id);
							
							
							int n3 = stmt4.executeUpdate();	
							System.out.println();
							System.out.println(n3+" admission year updated successfully");
									
							break;
							
						case 4 :
							PreparedStatement stmt5 = con.prepareStatement("update student set city=? where id=?");
							
							System.out.println("\nplease enter id  in which you want to update the city");
							id = sc.nextInt();
							System.out.println("enter new city:");
							city = sc.next();
							
							stmt5.setString(1, city);
							stmt5.setInt(2, id);
							
							
							int n4 = stmt5.executeUpdate();	
							System.out.println();
							System.out.println(n4+" city updated successfully");
									
							break;	
					}
					break;
					
				case 4:
					PreparedStatement stmt3 = con.prepareStatement("delete from student where id=?");
					
					System.out.println("\nplease enter id which you want to delete");
					id = sc.nextInt();
					stmt3.setInt(1,id);
					
					int n2 = stmt3.executeUpdate();		
					System.out.println(n2+" record deleted");
							
					break;
				
				case 5:
					System.out.println("\n1.search by name"+"\n2.search by qualification");
					System.out.println("\nplease enter the option");
					int choice2 = sc.nextInt();
					switch(choice2)
					{
						case 1:
							String fetchSameRecords = "Select * from student where name = ?";
							PreparedStatement stmt4 = con.prepareStatement(fetchSameRecords);
							
							System.out.println("\nenter name that you want to search");
							name = sc.next();
							stmt4.setString(1, name);
							
							ResultSet rs1 = stmt4.executeQuery();
							System.out.println();
							
							while(rs1.next())
							{
								id = rs1.getInt(1);
								name = rs1.getString(2);
								qualification = rs1.getString(3);
								admission_year = rs1.getInt(4);
								city = rs1.getString(5);
								
								System.out.println("Student id:"+id+"  Student Name:"+name+"  Qualifications:"+qualification+"  Admission year:"+admission_year+"  city:"+city);
							}
							break;
							
						case 2:
							String fetchSameRecords1 = "Select * from student where qualification = ?";
							PreparedStatement stmt5 = con.prepareStatement(fetchSameRecords1);
							
							System.out.println("\nenter qualification that you want to search");
							qualification = sc.next();
							stmt5.setString(1, qualification);
							
							ResultSet rs2 = stmt5.executeQuery();
							System.out.println();
							
							while(rs2.next())
							{
								id = rs2.getInt(1);
								name = rs2.getString(2);
								qualification = rs2.getString(3);
								admission_year = rs2.getInt(4);
								city = rs2.getString(5);
								
								System.out.println("Student id:"+id+"  Student Name:"+name+"  Qualifications:"+qualification+"  Admission year:"+admission_year+"  city:"+city);
							}
							break;
					}
					break;
				
				case 6:
					System.out.println("program exit");
					//System.exit(0);
					break;
			}
		
		}while(choice!=6);
	
	}
	
}