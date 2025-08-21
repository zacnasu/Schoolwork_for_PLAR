#include <stdio.h>
//Zachary Nasu
//V00911790

int main() {

    int size = 500;

    float store[size];
    int count[size];
    for(int p = 0; p<size; p++){
        store[p]=0;
        count[p]=0;
    }
    FILE* input = fopen("input_text.txt","r");

    int day, month, station, hour, minute=0;
    float temp = 0;
    while( fscanf(input,"%d %d %d %d %d %f", &month, &day ,&hour, &minute, &station, &temp) == 6){
        store[station] += temp;
        count[station]++;
    }
    fclose(input);
    FILE* output = fopen("station_averages_summary.txt" , "w");

    for(int i = 0; i< size; i++) {
        if(count[i]>0){
            fprintf(output, "%d %.2f\n",i,store[i]/count[i]);
        }
    }
    fclose(output);
    return 0;
}