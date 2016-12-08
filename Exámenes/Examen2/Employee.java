class Employee {

	private String name;			//atributos privados accesibles mendiante setters y getters
	private String id;
	private double salary;
	private char gender;

	public Employee(){

	}
	/*
	* Constructor de employee, recibe un string id, string name, un double salario y un char gender 
	*/
	public Employee(String id, String name, double salary, char gender){
		this.id = id;					
		this.name = name;
		this.salary = salary;
		this.gender = gender;
	}
	/*
	* Constructor de employee que recibe un objeto empleado 
	*/
	public  Employee(Employee emp){
		this.id = emp.id;
		this.name = emp.name;
		this.salary = emp.salary;
		this.gender = emp.gender;
	}
	/*
	* Método que devuelve un string con el nombre del empleado
	* su ID su salario y su genero;
	*/
	public String toString(){
		return " Name: "+getName()+ "\n"+
		" ID: "+getId()+ "\n"+
		" Salary: "+getSalary()+ "\n"+
		" Gender: "+getGender(); 
	}

	/*
	* setters y getters de los atributos de la clase
	*/

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setSalary(double salary){
		this.salary = salary;
	}

	public double getSalary(){
		return this.salary;
	}

	public void setGender(char gender){
		this.gender = gender;
	}

	public char getGender(){
		return this.gender;
	}

}