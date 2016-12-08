#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

int main(){
  
    pid_t id_hijo;  //Crea una estructura del tipo pid_t que sirve para identificar procesos
    pid_t id_hijo2; // = fork();
    int i;	    //variable de tipo int
    printf("1: el id del proceso main es %d\n", getpid()); // Se imprime el ID del proceso padre u proceso original
    id_hijo = fork();					   // Se crea el proceso hijo identico al proceso padre
    //id_hijo2 = fork(); 
    printf("2: el id del proceso hijo es %d\n", id_hijo);  // Se imprime el ID del proceso hijo
    
    if(id_hijo > 0){
      
      for(i=0; i<= 0; i++ ){
	printf("Padre: %d\n", i); //Se imprime el Padre n desde 0 hasta que i toma el valor de 10 y termina el proceso padre
      }
    }
    else{
      
      for(i=0; i>=0; i--){
	printf("Hijo 1: %d\n", i); //Se imprime el Hijo: n desde 10 hasta que i toma el valor de 0
      }
      printf("getppid() hijo 1= %d\n", getppid()); //dado que el proceso padre ya termino se trae el valor de proceso init
      for(i=0; i>=0; i--){		  	   //por que el proceso padre ya no existe
	printf("Hijo 2: %d\n", i); //Se imprime el Hijo: n desde 10 hasta que i toma el valor de 0 
      }
      printf("getppid() hijo 2= %d\n", getppid()); // lo mismo para el proceso hijo 2, el proceso hijo 1 ya terminó al igual
      id_hijo2 = fork(); // Se crea un proceso hijo en el hijo 
      
    }						   // al igual que el proceso padre de manera que devuelve el valor ID del 	
    return 0;					  //proceso init
}