#include <stdio.h>
#include <stdlib.h>

int main(){
  
  int return_value;
  return_value = system("ps -u david -o pid,ppid,stat,cmd");
  printf("Valor devuelto= %d\n", return_value);
  return return_value;
}