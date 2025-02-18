class InsertionSort {
    public static void main(String args[]){
        int a[] = {7, 41, 13, 59, 99, 1, 5};
        int n = a.length;
        insertionSort(a, n);
        for (int i : a){
            System.out.print(i + " ");
        }
    }

    public static void insertionSort(int a[], int n){
        for(int i=0; i<n; i++){
            for(int j=i; j>0; j--){
                if(a[j-1] > a[j]){
                    int temp = a[j-1];
                    a[j-1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
}
