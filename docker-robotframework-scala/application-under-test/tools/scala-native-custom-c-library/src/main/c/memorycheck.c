#include <sys/resource.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int memoryutilization() {
  struct rusage r_usage;
  getrusage(RUSAGE_SELF,&r_usage);
  printf("Check current memory usage (no allocation) = %ld\n",r_usage.ru_maxrss);
}
