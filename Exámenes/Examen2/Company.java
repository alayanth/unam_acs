class Company extends Employee{
	
	/*Atributos de la clase
	* accesible con setters y getters
	*/
	private String name;
	private Employee[] empleados;
	private int numEmp;

	/*
	* Contructor que recibe un int que denota el tamaño
	* del arreglo
	*/
	public Company(int size){
		empleados = new Employee[size];
		numEmp = 0;
	}

	public Company(){

	}

	public void addEmp(Employee emp){
		empleados[numEmp] = emp;
		numEmp++;
	}

	public void removeEmp(String id){
		int a = searchEmp(id);
		if(searchEmp(id) != -1){
			System.out.println("Se encontro al empleado con ID :" + id);
			System.out.println("Borrando empleado con ID:"+id+"\n");
			empleados[searchEmp(id)] = null;
			for (int i = a ; i < numEmp -1; i++) {
				empleados[i] = empleados[i+1];
			}
			numEmp--;
		}else if (searchEmp(id) == -1) {
			System.out.println("Error"+
							   " el empleado con ID: "+id+
							   " no existe!");
		}

	}

	public void searchByGender(char gen){
		if (gen == 'M') {
			System.out.println("Listando a las empleadas del genero femenino:");
		} else if (gen == 'H') {
			System.out.println("Listando a los empleados del genero masculino:");
		}
		for (int i = 0 ; i < numEmp ; i++) {
			if (empleados[i].getGender() == gen) {
				System.out.println(empleados[i].toString());
				System.out.println();
			}
		}
	}

	public int searchEmp(String id){
		for (int i = 0 ; i < numEmp ; i++) {
			if (empleados[i].getId().equals(id)) {
				return i;
			}
		}
		return -1;
	}

	public void display(){
		for(int i = 0; i < numEmp; i++){
			System.out.println(empleados[i].toString());
			System.out.println();
		}
		
	}

}