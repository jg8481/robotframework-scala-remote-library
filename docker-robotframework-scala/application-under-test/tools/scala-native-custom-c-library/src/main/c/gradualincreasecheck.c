#include <sys/resource.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int increaseusage() {
  int i = 0;
  struct rusage r_usage;
  while (++i <= 10) {
      void *gradualincrease = malloc(20*1024*1024);
      memset(gradualincrease,0,20*1024*1024);
      getrusage(RUSAGE_SELF,&r_usage);
      printf("Check memory usage (should be increasing) = %ld\n",r_usage.ru_maxrss);
      sleep (2);
  }
  printf("\nWait then check memory usage again...\n\n");
  sleep (10);
  getrusage(RUSAGE_SELF,&r_usage);
  printf("Check memory usage (should have stopped) = %ld\n",r_usage.ru_maxrss);
  return 0;
}
