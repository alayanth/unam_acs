#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

int main(){
  
    pid_t id_hijo; //Crea una estructura del tipo pid_t que sirve para identificar procesos
    int i; 		//Variable de tipo int
    printf("1: el id del proceso main es %d\n", getpid()); // Se imprime el ID del proceso padre u proceso original
    id_hijo = fork();					   // Se crea el proceso hijo identico al proceso padre
    printf("2: el id del proceso hijo es %d\n", id_hijo);  // Se imprime el ID del proceso hijo
    
    if(id_hijo > 0){  //Esta condición verifica que el proceso hijo se crea correctamente y entra en la condición
      
      for(i=0; i<= 10; i++ ){
	printf("Padre: %d\n", i); //Se imprime el Padre n desde 0 hasta que i toma el valor de 10 y termina el proceso padre
      }
    }
    else{
      sleep(10);	// El proceso espera 10 segundos
      for(i=10; i>=0; i--){
	printf("Hijo: %d\n", i); //Se imprime el Hijo: n desde 10 hasta que i toma el valor de 0
      }
    printf("getppid()= %d\n", getppid()); // se imprime el ID del proceso padre del padre es decir del proceso init
    }
    return 0;
}

/*El proceso no termina hasta que se da un enter dado que el proceso init nunca termina de ejecutarse y solo puede terminarse
la ejecución del proceso al presionar enter. */