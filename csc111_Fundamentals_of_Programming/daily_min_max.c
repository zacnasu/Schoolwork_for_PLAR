//Zachary Nasu
//V00911790

#include <stdio.h>

int main() {
   float min[13][32], max[13][32];
   int count[13][32];
    for(int k = 0; k<13; k++){
        for(int p= 0;p<32; p++){
            min[k][p]=999999;
            max[k][p]=-999999;
            count[k][p]=0;
        }
    }
    FILE* input = fopen("input_data.txt","r");
    int day, month, station, hour, minute=0;
    float temp = 0;
    while( fscanf(input,"%d %d %d %d %d %f", &month, &day ,&hour, &minute, &station, &temp) == 6){
        if(temp<min[month][day]){
            min[month][day]=temp;
        }
        if(temp>max[month][day]){
            max[month][day]=temp;
        }
        count[month][day] = 1;
    }
    fclose(input);
    FILE* output = fopen("daily_minimum_maximum_summary.txt" , "w");
    for(int i = 0; i< 13; i++) {
        for (int j = 0; j < 32; j++) {
            if (count[i][j] > 0) {
                fprintf(output, "%d %d %.2f %.2f\n", i, j, min[i][j], max[i][j]);
            }
        }
    }
    fclose(output);
    return 0;
}
