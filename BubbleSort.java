class BubbleSort{
    public static void main(String args[]){
        int a[] = {46, 21, 54, 97, 71, 57, 40, 34, 17, 33};
        int n = a.length;
        bubbleSort(a,n);
        for(int i: a){
            System.out.print(i+" ");
        }
    }
    public static void bubbleSort(int a[], int n){
        for(int i=0; i<n; i++){
            for(int j=0; j<n-i-1; j++){
                 if(a[j] > a[j+1]){
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
    }
}