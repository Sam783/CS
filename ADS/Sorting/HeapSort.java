class HeapSort{
    public static void main(String args[]){
        int a[] = {46, 21, 54, 97, 71, 57, 40, 34, 17, 33};
        int n = a.length;
        heapSort(a,n);
        for(int i: a){
            System.out.print(i+" ");
        }
    }

    public static void heapSort(int a[], int n){
        for(int i=n/2-1; i>=0; i--){
            heapify(a, n, i);
        }
        for(int i=n-1; i>0; i--){
            int temp = a[i];
            a[i] = a[0];
            a[0] = temp;
            heapify(a,i,0);
        }
    }
    
    public static void heapify(int[] a, int n, int i) {
        int largest = i;
        int l = (2*i)+1;
        int r = (2*i)+2;
        if(l < n && a[l] > a[largest]){
            largest = l;
        }
        if(r < n && a[r] > a[largest]){
            largest = r;
        }
        if(largest != i){
            int temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;
            heapify(a, n, largest);
        }
    }
}