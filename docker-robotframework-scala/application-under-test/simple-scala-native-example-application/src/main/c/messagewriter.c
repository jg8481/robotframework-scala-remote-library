#include <stdio.h>

void messageInTextFile(const char *fName,const char *fContent)
{
   FILE *textfile = fopen(fName, "wb");

   fprintf(textfile,fContent);

   fclose(textfile);
}
