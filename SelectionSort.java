class SelectionSort{
    public static void main(String args[]){
        int a[] = {46, 21, 54, 97, 71, 57, 40, 34, 17, 33};
        int n = a.length;
        selectionSort(a,n);
        for(int i: a){
            System.out.print(i+" ");
        }
    }
    public static void selectionSort(int a[], int n){
        for(int i=0; i<n; i++){
            int min = i;
            for(int j=i+1; j<n; j++){
                if(a[j] < a[min]){
                    min = j;
                }
            }
            int temp = a[min];
            a[min] = a[i];
            a[i] = temp;
        }
    }
}