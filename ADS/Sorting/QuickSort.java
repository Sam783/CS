class QuickSort{
    public static void main(String args[]){
        int a[] = {46, 21, 54, 97, 71, 57, 40, 34, 17, 33};
        int n = a.length;
        quickSort(a,0,a.length-1);
        for(int i: a){
            System.out.print(i+" ");
        }
    }

    public static void quickSort(int a[], int l, int r){
        if(l < r){
            int pi = partition(a,l,r);
            quickSort(a,l,pi-1);
            quickSort(a,pi+1,r);
        }
    }
    
    public static int partition(int a[], int l, int r){
        int pivot = a[r];
        int i=l-1;
        for(int j=l; j<r; j++){
            if(a[j] < pivot){
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        int temp = a[i+1];
        a[i+1] = a[r];
        a[r] = temp;
        return i+1;
    }
}