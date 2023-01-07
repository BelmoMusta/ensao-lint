public class OperandsCondition{
	
	public static void main(String[] args) {
		String firstname = "Ali";
		String lastname = "Moussa";
		int age = 15; 
		Student student = new Student("Ali", "Moussa", 18);
		
		
		if(student.firstname().equals("Ali") && student.equals("Moussa") && age == 18)) {
			System.out.println("Good morning " + student.firstname() + ":-)");
		}
		
		int = 0;
		int counter;
		while (i < 3 && i > 0 && age > 10 ) {
			counter = i; 
			i++; 
		};
		
		do {
			counter--;
		}while(counter > 0 && counter < 3 && age > 10);
		
		for(int j= 0; j < i && age > 10 && j < 5; j++) counter++; 
		
		int newAge = age > 18 && age <= 24 && age == 20 ? 20 : -1; 
		
		(a > 5 && a > 6 || a < 20);
				
	}
	
	public boolean checkBooleanExpr() {
		int a = 5, b = 6; 
		return a > 5 && a > 6 || a < 20; 
	}
	
	record Student(String firstname, String lastname, int age) {}
}