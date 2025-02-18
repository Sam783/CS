class MergeSort{
    public static void main(String args[]){
        int a[] = {7,41,13,59,99,1,5};
        int n = a.length;
        mergeSort(a,0,a.length-1);
        for(int i : a){
        System.out.print(i+" ");
        }
    }
    
    public static void mergeSort(int a[], int l, int r){
        if(l < r){
            int mid = (l+r)/2;
            mergeSort(a,l,mid);
            mergeSort(a,mid+1,r);
            merge(a,l,mid,r);
        }
    }

    public static void merge(int a[], int l, int mid, int r){
        int temp[] = new int[r-l+1];
        int i=l, j=mid+1, k=0;
        while(i <= mid && j <= r){
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        while(i <= mid){
            temp[k++] = a[i++];
        }

        while(j <= r){
            temp[k++] = a[j++];
        }

        for(int x=0; x<temp.length; x++){
            a[l + x] = temp[x];
        }
    }
}