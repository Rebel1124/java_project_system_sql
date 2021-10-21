/**
*
* The Project class stores the attributes for the projects.
* 
*/
public class Project {
	// Attributes
		int number;
		String name; 
		String type;
		String address;
		int erf;
		float charged;
		float paid;
		String deadline;
		String overdue;
		String completion;
		String status;
		Architect architect;
		Contractor contractor;
		Customer customer;
		

		// Methods (Constructor)
		public Project(int number, String name, String type, String address, int erf, float charged, 
				float paid, String deadline, String overdue, String completion, String status, Architect architect, Contractor contractor, Customer customer) {
			
			this.number = number;
			this.name = name;
			this.type = type;
			this.address = address;
			this.erf = erf;
			this.charged = charged;
			this.paid = paid;
			this.deadline = deadline;
			this.overdue = overdue;
			this.completion = completion;
			this.status = status;
			this.architect = architect;
			this.contractor = contractor;
			this.customer = customer;		
		}
}
