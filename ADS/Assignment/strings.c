#include<stdio.h>
#include<string.h>

int strLength(char a[]){
    int i = 0;
    int length = 0;
    while(a[i] != '\0'){
        length++;
        i++;
    }
    return length;
}

char* strConcat(char a[], char b[]){
    int i = 0;
    int j = 0;

    while(a[i] != '\0'){
        i++;
    }

    while (b[j] != '\0') {
        a[i] = b[j];
        i++;
        j++;
    }
    return a;
}

void strCopy(char a[], char b[]){
    int i = 0;
    while(b[i] != '\0'){
        a[i] = b[i];
        i++;
    }
}

int strCompare(char a[], char b[]) {
    int i = 0;
    while(a[i] != '\0' && b[i] != '\0'){
        if(a[i] != b[i]){
            return a[i]-b[i];
        }
        i++;
    }
    return a[i]-b[i];
}

int strsubString(char a[], char b[]){
    int s = strLength(a);
    int p = strLength(b);
    for(int i=0; i<s-p; i++){
        for(int j=0; j<p; j++){
            if(a[i+j] != b[j]){
                break;
            }
            if(j == p-1){
                return i;
            }
        }
    }
    return -1;
}

void main() {
    char a[100];
    char b[100];
    char result[100];
    FILE *input;
    FILE *output;

    input = fopen("input.txt", "r");
    if(input == NULL){
        printf("File Not Found");
        return;
    }

    fscanf(input, "%s", a);
    fscanf(input, "%s", b);
    fclose(input);

    printf("\nString a: %s", a);
    printf("\nString b: %s", b);
    printf("\nLength of String a: %ld", strLength(a));
    printf("\nComparison of Strings a and b: %d", strCompare(a, b));
    char* res = strConcat(a, b);
    printf("\nConcatenation of two Strings: %s", res);

    strCopy(a, b);
    printf("\nCopy of b into a: %s", a);

    int idx = strsubString(a, "def");
    if(idx != -1){
        printf("\nPattern is found in a at position: %ld", idx);
    }else{
        printf("\nPattern not found in a");
    }

    output = fopen("output.txt", "w");
    if(output == NULL){
        printf("Unable to save");
        return;
    }

    printf("\nFinal String a: %s", a);
    printf("\nFinal String b: %s", b);

    fprintf(output, "%s\n", a);
    fprintf(output, "%s\n", b);
    fclose(output);
}