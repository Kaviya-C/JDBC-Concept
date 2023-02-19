package com.maven.start;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class TableDatabasePro {

	static Scanner input=new Scanner(System.in);
	public static Statement getStatement() throws ClassNotFoundException,SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");//connect to database
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/SampleDatabase", "root", "root123");
		//statement object
		Statement statement=connection.createStatement();//execute the statement
		return statement;
	}
	//------------------------------------------------------------------------------------------------------------------

	public static  int insertData() throws ClassNotFoundException,SQLException
	{
		int result = 0;
		int count=0;
		System.out.println("enter the count of how many student u want to add??");
		count=input.nextInt();
		Statement statement=getStatement();
		for(int index=0;index<count;index++)
		{
			System.out.println("enter the id: ");
			int stdId = input.nextInt();
			System.out.println("enter the name: ");
			String stdName=input.next();
			System.out.println("enter the marks: ");
			double marks=input.nextDouble();
			System.out.println("enter the phone number: ");
			String phoneNum=input.next();
			String datas="insert into Student values('"+stdId+"','"+stdName+"','"+marks+"','"+phoneNum+"')";

			result=statement.executeUpdate(datas);
		}
		return result;
	}

	//------------------------------------------------------------------------------------------------------------------

	public static  int updateData( ) throws ClassNotFoundException,SQLException
	{
		Statement statement=getStatement();
		System.out.println("enter the name of the student which u want to modify: ");
		String name=input.next();

		System.out.println("enter the updated id for that student: ");
		int studid=input.nextInt();


		String update="update Student set stdId='"+studid+"' where stname='"+name+"'";
		int done=statement.executeUpdate(update);
		return done;
	}
	//------------------------------------------------------------------------------------------------------------------

	public static  int deleteData( ) throws ClassNotFoundException,SQLException
	{
		Statement statement=getStatement();
		System.out.println("enter the student id which u want to delete: ");
		int stdId=input.nextInt();
		String delete="DELETE from Student where stdId='"+stdId+"'";	
		//System.out.println(delete);
		int del=statement.executeUpdate(delete);
		//System.out.println(del);
		return del;
	}

	//------------------------------------------------------------------------------------------------------------------
	public static void main(String[] a) throws IOException {
		String wish=null;
		BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(System.in)); 
		
		do
		{
			System.out.println("1 Insert Data  "); 
			System.out.println("2 Update Data  ");
			System.out.println("3 Delete Data ");
			System.out.println("Enter option 1/2/3  "); 
			int option=input.nextInt(); 
			try {
				switch(option) { 
				case 1: 
					//insert the data by user entered id name marks phoneNumbers......!
					int result=insertData();
					if(result==1) 
					{
						System.out.println("data are inserted successfully..!");
					}
					break;

				case 2:
					//updating the data by means of user entered names..!
					int done=updateData();
					if(done>0)
					{
						System.out.println("data updated successfully");
					}
					break; 
				case 3: 
					//deleting the data by means of user entered id in the table...! 
					int del=deleteData();
					if(del>0) {
						System.out.println("data deleted successfully");
					}
					break; 
				} 
			}catch(Exception e)
			{
				System.out.println(e.getMessage()); 
			}
			System.out.println("Do you want to continue to "
					+ "change the properties of the table ?Type yes or No"); 
			wish=bufferedReader.readLine();
		}while(wish.equals("yes"));
	}
}






/*
 * String str="create table student" +"(stdId int," +"stname varchar(30),"
 * +"marks double," +"mobileno varchar(10))"; statement.execute(str);
 */







