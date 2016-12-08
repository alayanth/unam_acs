import java.util.*;

class CompanyApp extends Company{
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		in.useLocale(Locale.US);
		int opt;

		Company corp = new Company(10);
		Employee emp1 = new Employee("E1","David",23000.12,'H');
		Employee emp2 = new Employee("E2","Aurora",45020.32,'M');
		Employee emp3 = new Employee("E3","Germán",67040.32,'H');
		Employee emp4 = new Employee("E4","Fabiola",15500.32,'M');
		Employee emp5 = new Employee("E5","Santiago",17300.32,'H');
		corp.addEmp(emp1);
		corp.addEmp(emp2);
		corp.addEmp(emp3);
		corp.addEmp(emp4);
		corp.addEmp(emp5);
		

		System.out.println("\n\n");
		System.out.println("\033[31mUNIVERSIDAD NACIONAL AUTÓNOMA DE MÉXICO");
		System.out.println("FACULTAD DE INGENIERÍA");
		System.out.println("ARQUITECTURA CLIENTE SERVIDOR\033[0m");
		System.out.println("\n\n");

		System.out.println("Bienvenido al sistema de gestión de empleados\n");

		System.out.println("\033[30mPor favor seleccione una opción:");
		System.out.println("1. Crear un registro de empleado nuevo");
		System.out.println("2. Borrar un empleado");
		System.out.println("3. Mostrar todos los empleados registrados");
		System.out.println("4. Mostrar a los empleados por género");
		System.out.println("5. Salir\033[0m");
		opt = in.nextInt();
		in.nextLine();		

    		while (opt != 5) {
    			switch (opt) {
					case 1:
						Employee emp = new Employee();
						boolean c = true;
						while (c) {
							System.out.println("Escribe un número de empleado <String>: ");
							String numberEmp = in.next();
							if (corp.searchEmp(numberEmp) == -1) {
								emp.setId(numberEmp);
								c = false;							
							} else {
								System.out.println("Ese ID ya existe por favor seleccione uno distinto");
							}
						}
						System.out.println("Su nombre <String>: ");
						String name = in.next();
						emp.setName(name);
						System.out.println("Su salario <double>: ");
						double salario = in.nextDouble();
						emp.setSalary(salario);
						System.out.println("Su genero <char> :");
						String genero = in.next();
						char gen = genero.charAt(0);
						emp.setGender(gen);
						corp.addEmp(emp);
						opt = 0;
						break;
					case 2:
						System.out.println("Por favor para especifique el ID de empleado a borrar");
						String eid = in.next();
						corp.removeEmp(eid);
						opt = 0;
						break;
					case 3:
						corp.display();
						opt = 0;
						break;
					case 4:
						System.out.println("Seleccione:");
						System.out.println("1. Hombres");
						System.out.println("2. Mujeres");
						int a = in.nextInt();
						if(a == 1){
							corp.searchByGender('H');
						}else if (a == 2) {
							corp.searchByGender('M');
						}
						opt = 0;
						break;
					case 5:
						System.exit(0);
						break;
					default:
						System.out.println("\033[30mPor favor seleccione una opción valida:");
						System.out.println("1. Crear un registro de empleado nuevo");
						System.out.println("2. Borrar un empleado");
						System.out.println("3. Mostrar todos los empleados registrados");
						System.out.println("4. Mostrar a los empleados por género");
						System.out.println("5. Salir\033[0m");
						opt = in.nextInt();
						//in.nextLine();
						break;
				}
    		}
			

		
//		tmp.toString();
		
//		
//		corp.display();
//		
//		corp.removeEmp("E3");
//		corp.removeEmp("E2");
//		corp.removeEmp("E12");

//		corp.display();
		
//		corp.searchByGender('M');
//		corp.searchByGender('H');

			
		

	}
}
