/**
 *
 * @author Desi Reddy
 * @version 2.00, 15 October 2021
 */


//****************Import required java packages we will be using in our program*****************************************************

import javax.swing.JOptionPane;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//***********************************************************************************************************************************
//***********************************************************************************************************************************

//Please note that for the below, I setup the PoisePMS database and created four tables: Project, Architect, Contractor and Customer.
//To run run this you also need to create an 'otheruser'... the password I used was Sharks_1124
//however you can change the password to the one of your own.

//Also for the tables that I created, I have written code to populate them if there is a table this empty, so you only
//need to create the PoisePMS database and four tables (mentioned above) and the code will populate the tables if needed.

//********************************************************************************************************************************
/**
*
* The PoisePMS class runs our java program.
* 
*/
public class PoisePMS {
	
		//The main class starts our java program
		/**
		 *
		 * The main class starts our java program.
		 * 
		 * @param args returns the output for our java program.
		 */
		public static void main (String [] args)
	   {
		

//*********************************Create arrays and variables*******************************************************************
			
			//Firstly I create arrays for the projects, architects, contractors and customers.
			//I will append or add the respective objects to each of these arrays.
			List<Project> projects = new ArrayList<Project>();
			List<Architect> architects = new ArrayList<Architect>();
			List<Contractor> contractors = new ArrayList<Contractor>();
			List<Customer> customers = new ArrayList<Customer>();
		 
			//Declare user option variable - this will store the user's option for the task they want to perform
			int user_choice;
			
			//The outstanding variable stores the value outstanding for the respective project
			float outstanding = 0;
			
			
			//The below variables are created and used in the program
			//The prj_xxx variables are used to store information used to create a project Object
            int prj_num;
            String prj_name;
            String prj_type;
            String prj_address;
            int prj_erf;
            float prj_charged;
            float prj_paid;
            String prj_deadline;
            String prj_overdue;
            String prj_completion;
            String prj_status;
            String prj_string;
            
            
           //The arc_xxx variables are used to store information used to create a architect Object
            String arc_name;
            String arc_tel;
            String arc_email;
            String arc_address;
            String arc_string;
            
            
          //The con_xxx variables are used to store information used to create a contractor Object
            String con_name;
            String con_tel;
            String con_email;
            String con_address;
            String con_string;
            
            
          //The cus_xxx variables are used to store information used to create a customer Object
            String cus_name;
            String cus_tel;
            String cus_email;
            String cus_address;
			String cus_string;
			
//*********************************************************************************************************************************			
			
			
			try 
			{
				
	            // Connect to the library_db database, via the jdbc:mysql: channel on localhost (this PC)
	            // Use username "otheruser", password "Sharks_1124".
	            Connection connection = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
	                    "otheruser",
	                    "Sharks_1124"
	                    );
	            
	            // Create a direct line to the database for running our queries
	            Statement statement = connection.createStatement();
	            ResultSet results;
	            int rowsAffected;
	            
//*****If the current tables are empty, then I populate this table - This code is ONLY run if the existing table is empty***************************************************
	            
	            //Checks if Architect table is empty if it is then it populates with two entries
	            results = statement.executeQuery("SELECT * FROM Architect");
	            if(results.next() == false) {
	            	rowsAffected = statement.executeUpdate("INSERT INTO Architect VALUES ('Becky Lynch', '082 111 1111', 'blynch@gmail.com', '1 Alpine Road')");
	            	rowsAffected = statement.executeUpdate("INSERT INTO Architect VALUES ('Roman Reigns', '073 222 2222', 'rreigns@gmail.com', '2 Forest Drive')");
	            }
	            
	           //Checks if Contractor table is empty if it is then it populates with two entries
	            results = statement.executeQuery("SELECT * FROM Contractor");
	            if(results.next() == false) {
	            	rowsAffected = statement.executeUpdate("INSERT INTO Contractor VALUES ('Sasha Banks', '064 333 3333', 'sbanks@gmail.com', '3 Pineapple Avenue')");
	            	rowsAffected = statement.executeUpdate("INSERT INTO Contractor VALUES ('Keith Lee', '082 444 4444', 'klee@gmail.com', '4 Tree Lane')");
	            }
	            
	            
	          //Checks if Customer table is empty if it is then it populates with two entries
	            results = statement.executeQuery("SELECT * FROM Customer");
	            if(results.next() == false) {
	            	rowsAffected = statement.executeUpdate("INSERT INTO Customer VALUES ('Bianca Belair', '073 555 5555', 'bbelair@gmail.com', '5 High Terrace')");
	            	rowsAffected = statement.executeUpdate("INSERT INTO Customer VALUES ('Randy Orton', '064 666 6666', 'rorton@gmail.com', '6 Hathaway Place')");
	            }
	            
	          //Checks if Customer table is empty if it is then it populates with two entries
	            results = statement.executeQuery("SELECT * FROM Project");
	            if(results.next() == false) {
	            	rowsAffected = statement.executeUpdate("INSERT INTO Project VALUES (1124, 'Tree House', 'House', '223 High Terrace', 1357, 19999, 12500, '30 Nov 2021', 'No', 'Not Complete', 'Not Finalised', 'Becky Lynch', 'Sasha Banks', 'Bianca Belair')");
	            	rowsAffected = statement.executeUpdate("INSERT INTO Project VALUES (2479, 'London House', 'Complex', '3 Blue Mountain Road', 5612, 16999, 10500, '30 Oct 2021', 'No', 'Not Complete', 'Not Finalised', 'Roman Reigns', 'Keith Lee', 'Randy Orton')");
	            }
	            
//***********************************************************************************************************************************************************************************************************************************************************************	            
//The below set of code retrieves from all the tables in the SQL database and then uses this create the Project, Architect, Contractor and Customer Objects
//The objects are then added to the respective arrays	            
	            
	            
	            results = statement.executeQuery("SELECT * FROM Project "
	            		+ "INNER JOIN Architect on Architect.arc_name = Project.architect "
	            		+"INNER JOIN Contractor on Contractor.con_name = Project.contractor "
	            		+"INNER JOIN Customer on Customer.cus_name = Project.customer;");
	            
	            while (results.next()) {
		            //From here we populate variables to hold the project details
		            prj_num = results.getInt("number");
		            prj_name = results.getString("name");
		            prj_type = results.getString("type");
		            prj_address = results.getString("address");
		            prj_erf = results.getInt("erf");
		            prj_charged = results.getFloat("charged");
		            prj_paid = results.getFloat("paid");
		            prj_deadline = results.getString("deadline");
		            prj_overdue = results.getString("overdue");
		            prj_completion = results.getString("completion");
		            prj_status = results.getString("status");
		            
		           //From here we populate variables to hold the architect details
		            arc_name = results.getString("arc_name");
		            arc_tel = results.getString("arc_telNumber");
		            arc_email = results.getString("arc_email");
		            arc_address = results.getString("arc_address");
		        
		            //Create architect object and add to architects array
		            Architect arc = new Architect(arc_name, arc_tel, arc_email, arc_address);
		            architects.add(arc);
		            
		          //From here we populate variables to hold the contractor details
		            con_name = results.getString("con_name");
		            con_tel = results.getString("con_telNumber");
		            con_email = results.getString("con_email");
		            con_address = results.getString("con_address");
		          //Create contractor object and add to contractor array
		            Contractor con = new Contractor(con_name, con_tel, con_email, con_address);
		            contractors.add(con);
		            
		          //From here we populate variables to hold the customer details
		            cus_name = results.getString("cus_name");
		            cus_tel = results.getString("cus_telNumber");
		            cus_email = results.getString("cus_email");
		            cus_address = results.getString("cus_address");
		          //Create customer object and add to customers array
		            Customer cus = new Customer(cus_name, cus_tel, cus_email, cus_address);
		            customers.add(cus);
		            
		            
		          //Create project object and add to projects array
		            Project proj = new Project(prj_num, prj_name, prj_type, prj_address, prj_erf, prj_charged, prj_paid, prj_deadline, prj_overdue, prj_completion, prj_status, arc, con, cus);
		            projects.add(proj);
	            }
	            
				

//************************Next if there are existing projects I print out details that user can use to identify the projects*************
			 
	            //If there is existing project then print project identifiers
	            extractProjects(projects);
			 
			 
	            //If there is existing architects then print architect identifiers
	            extractArchitects(architects);
			 
			 
	            //If there is existing contractors then print contractor identifiers
	            extractContractors(contractors);
			 
			 
	            //If there is existing customers then print customer identifiers
	            extractCustomers(customers);
		
//***********Next I execute a do while loop that performs the users chosen task so long as the user does not want to exit the program***********************		
			
			//Do-While loop
			do 
			{
				//Create a GUI for the user to choose which task/option they want to perform
				user_choice = user_options();

				//If user selects 1, then we add the project instance with the user inputs
				if(user_choice == 1) {
					
					//Create skeleton objects and append to respective array
					Architect architect = new Architect(" ", " ", " ", " ");
					architects.add(architect);
					Contractor contractor = new Contractor(" ", " ", " ", " ");
					contractors.add(contractor);
					Customer customer = new Customer(" ", " ", " ", " ");
					customers.add(customer);
					Project project = new Project(0, " ", " ", " ", 0, 0, 0, " ", "No","Not Complete", "Not Finalised", architect, contractor, customer); 
					projects.add(project);
		
					//Ask user for project details by calling the appropriate extract method
					project.number = extractInt("Project Number? ");
					project.name = extractString("Project Name? ");
					project.type = extractString("Project Type? (building, apartment, etc...) ");
					project.address = extractString("Project Address? ");
					project.erf = extractInt("ERF Number? ");
					project.charged = extractFloat("Amount Charged?: ");
					project.paid = extractFloat("Amount Paid?: ");
					project.deadline = extractString("Project Deadline? ");
				
					//Here we calculate the amount outstanding
					outstanding = (project.charged - project.paid);
					

					//Ask user for architect details by calling the appropriate extract method
					architect.name = extractString("Architect Name? ");
					architect.telNumber = extractString("Architect Telephone? ");
					architect.email = extractString("Architect Email? ");
					architect.address = extractString("Achitect Address ");
					
					arc_string = "INSERT INTO Architect VALUES("+architect.name+", "+architect.telNumber+", "+architect.email+", "+architect.address+")";
					rowsAffected = statement.executeUpdate(arc_string);
					
					//Ask user for contractor details by calling the appropriate extract method
					contractor.name = extractString("Contractor Name? ");
					contractor.telNumber = extractString("Contractor Telephone? ");
					contractor.email = extractString("Contractor Email? ");
					contractor.address = extractString("Contractor Address? ");
					
					con_string = "INSERT INTO Contractor VALUES("+contractor.name+", "+contractor.telNumber+", "+contractor.email+", "+contractor.address+")";
					rowsAffected = statement.executeUpdate(con_string);
					
					//Ask user for customer details by calling the appropriate extract method
					customer.name = extractString("Customer Name? ");
					customer.telNumber = extractString("Customer Telephone? ");
					customer.email = extractString("Customer Email? ");
					customer.address = extractString("Customer Address? ");
					
					cus_string = "INSERT INTO Customer VALUES("+customer.name+", "+customer.telNumber+", "+customer.email+", "+customer.address+")";
					rowsAffected = statement.executeUpdate(cus_string);
					
					
					//Here we check if the user entered a project name, if not we make the project name equal
					//to the prject.type and surname of the customer
					String[] arr = customer.name.split(" ");
					String surname = arr[(arr.length - 1)];
					String projName = project.type+" "+surname;
					if (project.name.equals("") || project.name.equals(" ")) {
						project.name = projName;
					}
					
					//Now we populate the statement that will be used to insert a new entry into the Project Table
					prj_string = "INSERT INTO Project Values ("
							+project.number+", "
							+project.name+", "
							+project.type+", "
							+project.address+", "
							+project.erf+", "
							+project.charged+", "
							+project.paid+", "
							+project.deadline+", "
							+project.overdue+", "
							+project.completion+", "
							+project.status+", "
							+architect.name+", "
							+contractor.name+", "
							+customer.name+")";
					
					//Here we use the above string to add a new record to the Project Table
					 rowsAffected = statement.executeUpdate(prj_string);
				}

			
				//If the user selects choice 2 then we allow the user to update the project details
				else if (user_choice == 2) {
					Project project = null;
					
					
					//We first need to identify the project the user wants to edit
					//The user can identify the project either by name or by number
					//we call the refactored project_filter method for this
					int identity_project = project_filter();
					
						if(identity_project == 1) {
							
							int num = Integer.parseInt(JOptionPane.showInputDialog("Project Number? "));
							
							for (Project prj : projects ) {
								if(prj.number == num) {
									project = prj;
								}
							}
						
						} else if (identity_project == 2) {
							
							//String name = extractString("Project Name? ");
							String name = JOptionPane.showInputDialog("Project Name? ");
							
							for (Project prj : projects ) {
								if(prj.name.equalsIgnoreCase(name)) {
									project = prj;
								}
							}
						}
				
					//Once the project has been identified we ask the user what the details they would like to change	
					if (project != null) {
						
						//We create a project choice variable and then in the do loop, the user can select the field they want to edit
						
						int project_choice;	
					
					do {
						
						//We call the refactored project_options method to get the filed the user wants to edit
						project_choice = project_options();
						
						if (project_choice == 1) {
							project.number = extractInt("Project Number? ");
							rowsAffected = statement.executeUpdate("UPDATE Project SET number="+project.number+"WHERE (name="+project.name+")"); 						}
						else if (project_choice == 2) {
							project.name = extractString("Project Name? ");
							rowsAffected = statement.executeUpdate("UPDATE Project SET name="+project.name+"WHERE (number="+project.number+")"); 
						} else if (project_choice == 3 ) {
							project.type = extractString("Project Type? (building, apartment, etc...) ");
							rowsAffected = statement.executeUpdate("UPDATE Project SET type="+project.type+"WHERE (number="+project.number+")"); 
						} else if (project_choice == 4) {
							project.address = extractString("Project Address? ");
							rowsAffected = statement.executeUpdate("UPDATE Project SET address="+project.address+"WHERE (number="+project.number+")"); 
						} else if (project_choice ==5) {
							project.erf = extractInt("ERF Number? ");
							rowsAffected = statement.executeUpdate("UPDATE Project SET erf="+project.erf+"WHERE (number="+project.number+")"); 
						} else if (project_choice == 6) {
							project.charged = extractFloat("Amount Charged? (floating number): ");
							rowsAffected = statement.executeUpdate("UPDATE Project SET charged="+project.charged+"WHERE (number="+project.number+")"); 
						} else if (project_choice == 7) {
							project.paid = extractFloat("Amount Paid? (floating number): ");
							rowsAffected = statement.executeUpdate("UPDATE Project SET paid="+project.paid+"WHERE (number="+project.number+")"); 
						} else if (project_choice == 8) {
							project.deadline = extractString("Project Deadline? ");
							rowsAffected = statement.executeUpdate("UPDATE Project SET deadline="+project.deadline+"WHERE (number="+project.number+")"); 
						} else if(project_choice == 9) {
							project.overdue = extractString("Is the project overdue (Yes or No)? ");
							
							//Below we do some defensive programming to ensure the user on enters "Yes" or "No" for overdue
							String[] answers = {"Yes","No"};
							
							boolean present = Arrays.asList(answers).contains(project.overdue);
							
							do {
								project.overdue = extractString("Is the project overdue (Yes or No)? ");
								 present = Arrays.asList(answers).contains(project.overdue);
							} while (!present);
							
							rowsAffected = statement.executeUpdate("UPDATE Project SET overdue="+project.overdue+"WHERE (number="+project.number+")"); 
							
						} else if (project_choice == 10) {
							project.status = extractString("Project Status (Finalised or Not Finalised)? ");
							
							//Below we do some defensive programming to ensure the user on enters "Finalised" or "Not Finalised" for status
							String[] answers = {"Finalised","Not Finalised"};
							
							boolean present = Arrays.asList(answers).contains(project.status);
							
							do {
								project.status = extractString("Project Status (Finalised or Not Finalised)? ");
								 present = Arrays.asList(answers).contains(project.status);
							} while (!present);
							
							rowsAffected = statement.executeUpdate("UPDATE Project SET status="+project.status+"WHERE (number="+project.number+")"); 
						}
						
						
						//We then calculate the new amount outstanding
						outstanding = (project.charged - project.paid);
						//Here we check if the user entered a project name, if not we make the project name equal
						//to the prject.type and surname of the customer
						String[] arr = project.customer.name.split(" ");
						String surname = arr[(arr.length - 1)];
						String projName = project.type+" "+surname;
						if (project.name.equals("")) {
							project.name = projName;
						}
					
					}while (project_choice != -1);
				
				} else {
					System.out.println("Cannot find Project");
				}
				
				}
			
			
				//If the user selects option 3 then we allow the user to update architect details
				else if (user_choice == 3) {

					//We first need to identify the architect the user wants to edit
					//The user can identify the architect by name
					String architectName = JOptionPane.showInputDialog("Architect's Name? ");
					
					Architect architect = null;
					
					for (Architect arch : architects ) {
						if(arch.name.equalsIgnoreCase(architectName)) {
							architect = arch;
						}
					}
					
					
					//If architect has been identified then we provide the user with options for the field they want to change
					if(architect != null) {
						
						int architect_details;
		
						do 
						{
							architect_details = person_options();
							if (architect_details == 1) {
								architect.name = extractString("Update Architect Name? ");
								rowsAffected = statement.executeUpdate("UPDATE Architect SET arc_name="+architect.name+"WHERE (arc_telNumber="+architect.telNumber+")");
								architect_details = person_options();
							} else if (architect_details == 2) {
								architect.telNumber = extractString("Update Architect Telephone? ");
								rowsAffected = statement.executeUpdate("UPDATE Architect SET arc_telNumber="+architect.telNumber+"WHERE (arc_name="+architect.name+")");
								architect_details = person_options();
							} else if (architect_details == 3) {
								architect.email = extractString("Update Architect Email? ");
								rowsAffected = statement.executeUpdate("UPDATE Architect SET arc_email="+architect.email+"WHERE (arc_name="+architect.name+")");
								architect_details = person_options();
							} else if (architect_details == 4) {
								architect.address = extractString("Update Architect Address? ");
								rowsAffected = statement.executeUpdate("UPDATE Architect SET arc_address="+architect.address+"WHERE (arc_name="+architect.name+")");
								architect_details = person_options();
							}
					
						}while(architect_details != -1);
					
					}
					else {
						System.out.println("Cannot find architect");
					}
				}
		
				//Choice 4 allows the user to edit the contractor details
				else if (user_choice == 4) {
					
					//We first need to identify the contractor the user wants to edit
					//The user can identify the contractor either by name

					String contractorName = JOptionPane.showInputDialog("Contractor's Name? ");
					Contractor contractor = null;
					
					for (Contractor cont : contractors ) {
						if(cont.name.equalsIgnoreCase(contractorName)) {
							contractor = cont;
						}
					}
					
					//If contractor has been identified then we provide the user with options for the field they want to change
					if (contractor != null) {
						int contractor_details;
						
						do 
						{
							contractor_details = person_options();
							if (contractor_details == 1) {
								contractor.name = extractString("Update Contractor Name? ");
								rowsAffected = statement.executeUpdate("UPDATE Contractor SET con_name="+contractor.name+"WHERE (con_telNumber="+contractor.telNumber+")");
								contractor_details = person_options();
							} else if (contractor_details == 2) {
								contractor.telNumber = extractString("Update Contractor Telephone? ");
								rowsAffected = statement.executeUpdate("UPDATE Contractor SET con_telNumber="+contractor.telNumber+"WHERE (con_name="+contractor.name+")");
								contractor_details = person_options();
							} else if (contractor_details == 3) {
								contractor.email = extractString("Update Contractor Email? ");
								rowsAffected = statement.executeUpdate("UPDATE Contractor SET con_email="+contractor.email+"WHERE (con_name="+contractor.name+")");
								contractor_details = person_options();
							} else if (contractor_details == 4) {
								contractor.address = extractString("Update Contractor Address? ");
								rowsAffected = statement.executeUpdate("UPDATE Contractor SET con_address="+contractor.address+"WHERE (con_name="+contractor.name+")");
								contractor_details = person_options();
							}
					
						}while(contractor_details != -1);
					
					} else {
						System.out.println("Cannot find contractor");
					}
				}
				
				
				//Choice 5 allows the user to edit the customer details
				else if (user_choice == 5) {
					
					//We first need to identify the customer the user wants to edit
					//The user can identify the customer by name
					String customerName = JOptionPane.showInputDialog("Customer's Name? ");
					Customer customer = null;
					
					for (Customer cust : customers) {
						if(cust.name.equalsIgnoreCase(customerName)) {
							customer = cust;
						}
					}
					
					//If customer has been identified then we provide the user with options for the field they want to change
					if(customer != null) {
						int customer_details;
						
						do 
						{
							customer_details = person_options();
							if (customer_details == 1) {
								customer.name = extractString("Update Customer Name? ");
								rowsAffected = statement.executeUpdate("UPDATE Customer SET cus_name="+customer.name+"WHERE (cus_telNumber="+customer.telNumber+")");
								customer_details = person_options();
							} else if (customer_details == 2) {
								customer.telNumber = extractString("Update Customer Telephone? ");
								rowsAffected = statement.executeUpdate("UPDATE Contractor SET cus_telNumber="+customer.telNumber+"WHERE (cus_name="+customer.name+")");
								customer_details = person_options();
							} else if (customer_details == 3) {
								customer.email = extractString("Update Customer Email? ");
								rowsAffected = statement.executeUpdate("UPDATE Contractor SET cus_email="+customer.email+"WHERE (cus_name="+customer.name+")");
								customer_details = person_options();
							} else if (customer_details == 4) {
								customer.address = extractString("Update Customer Address? ");
								rowsAffected = statement.executeUpdate("UPDATE Contractor SET cus_address="+customer.address+"WHERE (cus_name="+customer.name+")");
								customer_details = person_options();
							}
					
						}while(customer_details != -1);
					
					} else {
						System.out.println("Cannot find customer");
					}
				}
			
				//If the user selects choice 6 then an invoice to the customer for the project is printed on screen
				else if (user_choice == 6) {	
					
					Project project = null;
						
					//We first identify the project either by name or by number
					int identity_project = project_filter();
					
						if(identity_project == 1) {
							
							int num = Integer.parseInt(JOptionPane.showInputDialog("Project Number? "));
							
							for (Project prj : projects ) {
								if(prj.number == num) {
									project = prj;
								}
							}
						
						} else if (identity_project == 2) {
							
								String name = JOptionPane.showInputDialog("Project Name? ");
							
							for (Project prj : projects ) {
								if(prj.name.equalsIgnoreCase(name)) {
									project = prj;
								}
							}
						}
					
						
					//Once the project has been identified we do the below
					if (project != null) {
						
						System.out.println("\n");
						System.out.println("Finalise Project:");
						
						//Check if there is an amount outstanding or if the project has been paid
						//If the project has been paid the no invoice is generated
						outstanding = project.charged - project.paid;
						
						if (outstanding > 0) {
				
							//if(project.type != " ") {
							if(!(" ".equals(project.type))) {
								//Here we ask the user for a project completion date
								project.completion = extractString("Project Completion Date? ");
								rowsAffected = statement.executeUpdate("UPDATE Project SET completion="+project.completion+"WHERE (number="+project.number+")");
								
								
								//Here we update the project status to finalised
								project.status = "Finalised";
								rowsAffected = statement.executeUpdate("UPDATE Project SET status="+project.status+"WHERE (number="+project.number+")");
			
								//If the project is finalised then we call the finalise method to print the report
								finalise(project.customer, project, outstanding);
			
							} else {
								System.out.print("Please complete project details \n");
							}
				
						}
			
						else {
							//If the amount outstanding is zero then no invoice is generated and we print 
							//the project ahs been paid in full
							System.out.print("Project Fully Paid \n");
						}
			
					} else {
						System.out.println("Cannot find project");
					}
				}
				
				//If the user selects 7 then we print a list of projects that are not complete by calling the notCompleted method
				else if (user_choice == 7) {
					
					System.out.println("Projects Not Complete: \n");
					
					notCompleted(projects);
					
					
				//If the user selects 8 then we print a list of projects that are overdue by calling the overdue method
				} 
				else if (user_choice == 8) {
					
					System.out.println("Projects Overdue: \n");
					
					overdue(projects);
				
				}
				
				//If the User selects 9 then the user can choose to see further details of the project, architect, contractor or customer that they choose
				else if (user_choice == 9) {
					int search_details;
					
					do {
						
						//The search_options gives the user a GUI to select which object they want to see more details for
						search_details = search_options();
						
						//If 1 is selected the used can see further details for a chosen project
						if(search_details == 1) {
							
							//The below code is used to identify the project either by name or number
							Project project = null;
							int identity_project = project_filter();
							
							//Find project by number
							if(identity_project == 1) {
	
								int num = Integer.parseInt(JOptionPane.showInputDialog("Project Number? "));
								for (Project prj : projects ) {
									if(prj.number == num) {
										project = prj;
									}
								}
							
								//Find project by name
							} else if (identity_project == 2) {

								String name = JOptionPane.showInputDialog("Project Name? ");
								
								for (Project prj : projects ) {
									if(prj.name.equalsIgnoreCase(name)) {
										project = prj;
									}
								}
							}	
						
							
							//If a project is found then we print project details if not then we show a message saing the project has not been found
							if(project != null) {
							System.out.println("Project Found:");
							System.out.println("Name: "+project.name);
							System.out.println("Number: "+project.number);
							System.out.println("deadline: "+project.deadline);
							System.out.println("overdue: "+project.overdue);
							System.out.println("status: "+project.status);
							System.out.println("Architect: "+project.architect.name);
							System.out.println("Contractor: "+project.contractor.name);
							System.out.println("Customer: "+project.customer.name);
							}
							else {
							System.out.println("Project Not Found");
						}
					} 
					
						//if user chooses 2 then details for a chosen architect is shown
					else if (search_details == 2) {
						
						//Ask user to enter the name of the architect they want further details for
						String architectName = JOptionPane.showInputDialog("Architect's Name? ");
						
						Architect architect = null;
						
						for (Architect arch : architects ) {
							if(arch.name.equalsIgnoreCase(architectName)) {
								architect = arch;
							}
						}
						
						//If architect is found then show details otherwise say that no architect has been found
						if(architect != null) {
							System.out.println("Architect Found:");
							System.out.println("Name: "+architect.name);
							System.out.println("telNumber: "+architect.telNumber);
							System.out.println("email: "+architect.email);
							System.out.println("address: "+architect.address);
						} else {
							System.out.println("Architect Not Found:");
						}
				
						//If user chooses 3 the details for a chosen contractor is shown
					} else if (search_details == 3) {
						
						//Ask user to enter the name of the contractor they want further details for
						String contractorName = JOptionPane.showInputDialog("Contractor's Name? ");
						
						Contractor contractor = null;
						
						for (Contractor con : contractors ) {
							if(con.name.equalsIgnoreCase(contractorName)) {
								contractor = con;
							}
						}
						
						//If contractor is found then show details otherwise say that no contractor has been found
						if(contractor != null) {
							System.out.println("Contractor Found:");
							System.out.println("Name: "+contractor.name);
							System.out.println("telNumber: "+contractor.telNumber);
							System.out.println("email: "+contractor.email);
							System.out.println("address: "+contractor.address);
						} else {
							System.out.println("Contractor Not Found:");
						}

						//If user chooses 4 the details for a chosen customer is shown
					} else if (search_details == 4) {

						//Ask user to enter the name of the customer they want further details for
						String customerName = JOptionPane.showInputDialog("Customer's Name? ");
						
						Customer customer = null;
						
						for (Customer cus : customers ) {
							if(cus.name.equalsIgnoreCase(customerName)) {
								customer = cus;
							}
						}
						
						//If contractor is found then show details otherwise say that no contractor has been found
						if(customer != null) {
							System.out.println("Customer Found:");
							System.out.println("Name: "+customer.name);
							System.out.println("telNumber: "+customer.telNumber);
							System.out.println("email: "+customer.email);
							System.out.println("address: "+customer.address);
						} else {
							System.out.println("Customer Not Found:");
						}					
					}
						
						
					} while(search_details != -1);

			
			}
				
				
				//If the user selects 10 then we show alls details for the chosen objects
				else if (user_choice == 10) {
				
					int show_details;
					
					do {
						
						//asks user which object they want to see all details for
						show_details = show_options();
						
						//if user chooses 1 then show details for all current projects
						if(show_details == 1) {
							extractProjects(projects);
						}
						//if user chooses 2 then show details for all current architects
						else if (show_details == 2) {
							extractArchitects(architects);
						} 
						//if user chooses 3 then show details for all current contractors
						else if(show_details == 3) {
							extractContractors(contractors);
						}
						//if user chooses 4 then show details for all current customers
						else if(show_details == 4) {
							extractContractors(contractors);
						}
						//if user chooses 5 then show all infor for all the objects
						else if(show_details == 5) {
							
							 //If there is existing project then print project identifiers
							 extractProjects(projects);
							 
							 
							 //If there is existing architects then print architect identifiers
							 extractArchitects(architects);
							 
							 
							//If there is existing contractors then print contractor identifiers
							 extractContractors(contractors);
							 
							 
							//If there is existing customers then print customer identifiers
							 extractCustomers(customers);
						
							
						}
						
					} while (show_details != -1);
					
				} 
				
				//This option allows the user to delete a project from the projects array and from the SQL data base
				else if(user_choice == 11) {
					
							Project project = null;
							int identity_project;
							
							do {
								//We first need to identify the project - project filter asks the user to identify project by name or number
								identity_project = project_filter();
							
								//if user chooses 1 then identify project by number
								if(identity_project == 1) {
								
								int num = Integer.parseInt(JOptionPane.showInputDialog("Project Number? "));
								
								//if project is found then remove from projects array and from SQL database
								for (Project prj : projects ) {
									if(prj.number == num) {
										project = prj;
										rowsAffected = statement.executeUpdate("DELETE FROM Project Where (number="+prj.number+")");
										projects.remove(prj);
									}
								}
							
								//if user chooses 2 then identify project by name
								} else if (identity_project == 2) {

									String name = JOptionPane.showInputDialog("Project Name? ");
								
									//if project is found then remove from projects array and from SQL database
								for (Project prj : projects ) {
									if(prj.name.equalsIgnoreCase(name)) {
										project = prj;
										rowsAffected = statement.executeUpdate("DELETE FROM Project Where (name="+prj.name+")");
										projects.remove(prj);
									}
								}
							}	
						
							if(project != null) {
								System.out.println("Project Removed");
							}
							else {
							System.out.println("Project Not Found");
						}
					} while(identity_project != -1);
				}
				} while (user_choice != -1);    //we continue to prompt the user until they enter -1 to exit the program
		
				} catch (SQLException e) {
					//catch (Exception e) {
					// We only want to catch a SQLException - anything else is off-limits for now.
					e.printStackTrace();
				}
	   			}




//******************************************************************************************************************************
//************************Below are Methods that I have Refactored**************************************************************
	  
	 
	//The below method displays a GUI user option for the user. The user can then select which action they want to perform
	//If the user does not enter a valid input then the GUI will pop up again and continue to ask the user until a valid 
	//input is entered
		
	 /**
	 *
	 * This method displays a GUI user option for the user. The user can then select which action they want to perform
	 * If the user does not enter a valid input then the GUI will pop up again and continue to ask the user until a valid
	 *input is entered.
	 *
	 *@return The users selected option.
	 */
	public static int user_options() {
		String user_option;
		user_option = JOptionPane.showInputDialog( "User Options\n"+
				"1. Add Project\n"+
				"2. Update Project Details\n"+
				"3. Update Architect Details\n"+
				"4. Update Contractor Details\n"+
				"5. Update Customer Details\n"+
				"6. Finalise Project\n"+
				"7. Projects Not Complete\n"+
				"8. Projects Overdue\n"+
				"9. Search Details\n"+
				"10. Show Data Details\n"+
				"11. Delete Items\n"+
				"-1 Quit Program");
		
		//Below we do some defensive programming to ensure the user selects the right option
		String[] options = {"-1", "1", "2", "3","4","5","6","7","8", "9","10", "11"};
		
		boolean present = Arrays.asList(options).contains(user_option);
		
		if (present) {
			
			try {
				int user_choice = Integer.parseInt(user_option);
				return user_choice;
			} 
			catch (Exception e){
				return user_options();
			}
			
			
		} else {
			return user_options();
		}		
		}
	
	
	
	//This method asks the user to select a field they want to change
	 /**
	 *
	 * This method asks the user to select a field from a Project Object they want to change.
	 *
	 * @return The users selected option.
	 */
	public static int project_options() {
		String user_option;
		user_option = JOptionPane.showInputDialog( "Change Project Details\n"+
				"1. Project Number\n"+
				"2. Project Name\n"+
				"3. Project Type\n"+
				"4. Project Address\n"+
				"5. Project ERF Number\n"+
				"6. Project Amount Charged\n"+
				"7. Project Amount Paid\n"+
				"8. Project Deadline\n"+
				"9. Project Overdue\n"+
				"10. Project Status\n"+
				"-1 Quit Program");
		
		
		//Below we do some defensive programming to ensure the user selects the right option
		String[] options = {"-1", "1", "2", "3","4","5","6","7","8","9", "10"};
		
		boolean present = Arrays.asList(options).contains(user_option);
		
		if (present) {
			
			try {
				int user_choice = Integer.parseInt(user_option);
				return user_choice;
			} 
			catch (Exception e){
				return project_options();
			}
			
			
		} else {
			return project_options();
		}		
	}
	
	
	
	//The below method asks the user to select a field of the persons details they want to change
	 /**
	 *
	 * This method asks the user to select a field of the respective persons Object they want to change.
	 *
	 * @return The users selected option.
	 */
	public static int person_options() {
		String user_option;
		user_option = JOptionPane.showInputDialog( "Update Person Details\n"+
				"1. Name\n"+
				"2. telNumber\n"+
				"3. Email\n"+
				"4. Address\n"+
				"-1 Quit Program");
		
		//Below we do some defensive programming to ensure the user selects the right option
		String[] options = {"-1", "1", "2", "3","4"};
		
		boolean present = Arrays.asList(options).contains(user_option);
		
		if (present) {
			
			try {
				int user_choice = Integer.parseInt(user_option);
				return user_choice;
			} 
			catch (Exception e){
				return person_options();
			}
			
			
		} else {
			return person_options();
		}		
	}
	
	
	//The below method is used find out how the user wants to identify the project either by name or number
	 /**
	 *
	 * This method is used find out how the user wants to identify the project either by name or number.
	 * 
	 * @return The users selected option.
	 *
	 */
	public static int project_filter() {
		String user_option;
		user_option = JOptionPane.showInputDialog( "Identify Project by: \n"+
				"1. Number\n"+
				"2. Name\n"+
				"-1 Quit Program");
		
		//Below we do some defensive programming to ensure the user selects the right option
		String[] options = {"-1", "1", "2"};
		
		boolean present = Arrays.asList(options).contains(user_option);
		
		if (present) {
			
			try {
				int user_choice = Integer.parseInt(user_option);
				return user_choice;
			} 
			catch (Exception e){
				return project_filter();
			}
			
			
		} else {
			return project_filter();
		}		
	}
	
	
	
	
	//The below method gives the user a list of options for which they can search for
	 /**
	 *
	 * This method gives the user a list of options of Objects they can search for.
	 * 
	 * @return The users selected option.
	 *
	 */
	public static int search_options() {
		String user_option;
		user_option = JOptionPane.showInputDialog( "Search Options\n"+
				"1. Search Project\n"+
				"2. Search Architect\n"+
				"3. Search Contractor\n"+
				"4. Search Customer\n"+
				"-1 Quit Program");
		
		//Below we do some defensive programming to ensure the user selects the right option
		String[] options = {"-1", "1", "2", "3","4"};
		
		boolean present = Arrays.asList(options).contains(user_option);
		
		if (present) {
			
			try {
				int user_choice = Integer.parseInt(user_option);
				return user_choice;
			} 
			catch (Exception e){
				return search_options();
			}
			
			
		} else {
			return search_options();
		}		
	}
	
		
	//The below method gives the user a list of options for which they can they can get all info for - so the user
	//need to choose the objects they want to see all the current details for
	 /**
	 *
	 * The below method gives the user a list of options for which they can they can get all info for - so the user
	 * need to choose the objects they want to see all the current details for.
	 *
	 * @return The users selected option.
	 */
	public static int show_options() {
		String user_option;
		user_option = JOptionPane.showInputDialog( "Search Options\n"+
				"1. Show Projects\n"+
				"2. Show Architects\n"+
				"3. Show Contractors\n"+
				"4. Show Customers\n"+
				"5. Show All\n"+
				"-1 Quit Program");
		
		//Below we do some defensive programming to ensure the user selects the right option
		String[] options = {"-1", "1", "2", "3","4","5","6","7","8"};
		
		boolean present = Arrays.asList(options).contains(user_option);
		
		if (present) {
			
			try {
				int user_choice = Integer.parseInt(user_option);
				return user_choice;
			} 
			catch (Exception e){
				return show_options();
			}
			
			
		} else {
			return show_options();
		}		
		}

		//The below method asks a user for an integer input. The method also checks that a valid integer has been entered
		//If not then the method will continue to ask the user until a valid integer is entered
	 	/**
	 	 *
	 	 * The below method asks a user for an integer input. The method also checks that a valid integer has been entered
	 	 * If not then the method will continue to ask the user until a valid integer is entered.
	 	 *
	 	 * @param input_field String used to ask the user a question.
	 	 * @return the users entered integer.
	 	 */
		public static int extractInt(String input_field) {

			String number_input = JOptionPane.showInputDialog(input_field);
			
			try {
				return Integer.parseInt(number_input);
			} catch(Exception e) {
				System.out.println("Please enter a valid number: ");
				return extractInt(input_field);
			}
		}
		
		
		
		//The below method asks a user for a string input. The method also checks that a valid string has been entered
		//If not then the method will continue to ask the user until a valid string is entered
	 	/**
	 	 *
	 	 * The below method asks a user for a string input. The method also checks that a valid string has been entered
	 	 * If not then the method will continue to ask the user until a valid string is entered.
	 	 *
	 	 * @param input_field String used to ask the user a question.
	 	 * @return the users entered string.
	 	 */
		public static String extractString(String input_field) {

			String string_input = JOptionPane.showInputDialog(input_field);
			
			try {
				return string_input;
			} catch(Exception e) {
				System.out.println("Please enter a valid input: ");
				return extractString(input_field);
			}
		}
		
		
		//The below method asks a user for a float input. The method also checks that a valid float has been entered
		//If not then the method will continue to ask the user until a valid float is entered
	 	/**
	 	 *
	 	 * The below method asks a user for a float input. The method also checks that a valid float has been entered
	 	 * If not then the method will continue to ask the user until a valid float is entered.
	 	 *
	 	 * @param input_field String used to ask the user a question.
	 	 * @return the users entered float.
	 	 */
		public static Float extractFloat(String input_field) {

			String string_input = JOptionPane.showInputDialog(input_field);
			
			try {
				return Float.parseFloat(string_input);
			} catch(Exception e) {
				System.out.println("Please enter a valid (floating) number: ");
				return extractFloat(input_field);
			}
		}
		

		//The below method prints the report when the project is finalised
		 /**
		 *
		 * The below method prints the report when the project is finalised.
		 * 
		 * @param customer the customer object
		 * @param project the project object
		 * @param outstanding the outstanding project amount
		 *
		 */
		public static void finalise(Customer customer, Project project, float outstanding) {
			PrintStream out = System.out;
			out.println("\n");
			out.println("Invoice:");
			out.println("Customer Details:");
			out.println("Name: \t\t"+customer.name);
			out.println("Telephone: \t"+customer.telNumber);
			out.println("Email: \t\t"+customer.email);
			out.println("Address: \t"+customer.address);
			out.println("\nProject Details:");
			out.println("Name: \t\t"+project.name);
			out.println("Status: \t"+project.status);
			out.println("Completion: \t"+project.completion);
			out.println("Oustanding: \t"+outstanding);
			out.println("\n");
		}
		

		//Here we identify overdue projects and print their details
		 /**
		 *
		 * Here we identify overdue projects and print their details.
		 * 
		 * @param projects the projects array
		 *
		 */
		public static void overdue(List<Project> projects) {
			for (Project pj : projects) {
				
				if (pj.overdue.equals("Yes")) {
					String out = "";
					out += "Name: "+pj.name+"\n";
					out += "Number: "+pj.number+"\n\n";
					System.out.println(out);
				}
			}
		}

		//Here we identify not completed projects and print their details
		 /**
		 *
		 * Here we identify not completed projects and print their details
		 * 
		 * @param projects the projects array
		 *
		 */
		public static void notCompleted(List<Project> projects) {
			for (Project pj : projects) {

				if (pj.status.equals("Not Finalised")) {
					String out = "";
					out += "Name: "+pj.name+"\n";
					out += "Number: "+pj.number+"\n\n";
					System.out.println(out);
				}
			}
		}
		
		
		
		//This method prints out details for all customers in the customers array
		 /**
		 *
		 * This method prints out details for all customers in the customers array.
		 * 
		 * @param customers the customers array
		 *
		 */
		public static void extractCustomers(List<Customer> customers) {
			if (customers.size() > 0) {
				 System.out.println("Current customers:");
				 
				 for(int i=0; i<customers.size(); i++) {
					 int j = i+1;
					 System.out.println("Customer "+j);
					 System.out.println("Name: "+customers.get(i).name);
					 System.out.println("Email: "+customers.get(i).email+"\n");	 
				 }
			 }
		}


		//This method prints out details for all contractors in the contractors array
		 /**
		 *
		 * This method prints out details for all contractors in the contractors array.
		 * 
		 * @param contractors the contractors array
		 *
		 */
		public static void extractContractors(List<Contractor> contractors) {
			if (contractors.size() > 0) {
				 System.out.println("Current contractors:");
				 
				 for(int i=0; i<contractors.size(); i++) {
					 int j = i+1;
					 System.out.println("Contractor "+j);
					 System.out.println("Name: "+contractors.get(i).name);
					 System.out.println("Email: "+contractors.get(i).email+"\n");	 
				 }
			 }
		}

		
		//This method prints out details for all architects in the architects array
		 /**
		 *
		 * This method prints out details for all architects in the architects array.
		 * 
		 * @param architects the architects array
		 *
		 */
		public static void extractArchitects(List<Architect> architects) {
			if (architects.size() > 0) {
				 System.out.println("Current Architects:");
				 
				 for(int i=0; i<architects.size(); i++) {
					 int j = i+1;
					 System.out.println("Architect "+j);
					 System.out.println("Name: "+architects.get(i).name);
					 System.out.println("Email: "+architects.get(i).email+"\n"); 
				 }
			 }
		}

		
		//This method prints out details for all projects in the projects array
		 /**
		 *
		 * This method prints out details for all projects in the projects array.
		 * 
		 * @param projects the projects array
		 *
		 */
		public static void extractProjects(List<Project> projects) {
			if (projects.size() > 0) {
				 System.out.println("Current Projects:");
				 
				 for(int i=0; i<projects.size(); i++) {
					 int j = i+1;
					 System.out.println("Project "+j);
					 System.out.println("Name: "+projects.get(i).name);
					 System.out.println("Number: "+projects.get(i).number+"\n");
					 
				 }
			 }
		}
	   
				
//*************************************end of refactored methods*******************************************************************
//*********************************************************************************************************************************
}
