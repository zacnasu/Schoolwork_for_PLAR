//Zachary Nasu
//V00911790

#include <stdio.h>

int main() {
    float store[13][32];
    float count[13][32];
    for(int k = 0; k<13; k++){
        for(int p= 0;p<32; p++){
            store[k][p]= 0;
            count[k][p]= 0;

        }
    }

    FILE* input = fopen("input_text.txt", "r");

    int day, month, station, hour , minute = 0;
    float temp = 0;
    while(fscanf(input,"%d %d %d %d %d %f", &month, &day ,&hour, &minute, &station, &temp)>5){
        store[month][day] = store[month][day] + temp;
        count[month][day]++;
    }

    fclose(input);
    FILE* output = fopen("daily_averages_summary.txt" , "w");
    for(int i = 0; i< 13; i++){
        for(int j = 0; j< 32; j++){
            if(count[i][j]>0){
                fprintf(output,"%d %d %.2f\n", i, j, store[i][j]/count[i][j]);
            }
        }
    }
    fclose(output);
    return 0;
}