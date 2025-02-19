class BinarySearch {
    public static void main(String args[]) {
        int a[] = {1, 5, 6, 10, 25, 79, 100};
        int key = 6;
        int res = binarySearch(a, key, 0, a.length-1);
        if(res == -1){
            System.out.println("key not found.");
        }else{
            System.out.println("key found at index: " + res);
        }
    }

    // public static int binarySearch(int a[], int key){
    //     int l = 0;
    //     int r = a.length-1;

    //     while(l <= r){
    //         int mid = l+(r-l)/2;

    //         if(a[mid] == key){
    //             return mid;
    //         }else if (a[mid] < key){
    //             l = mid + 1;
    //         }else{
    //             r = mid - 1;
    //         }
    //     }
    //     return -1;
    // }

    public static int binarySearch(int a[], int key, int l, int r){
        while (l <= r) {
            int mid = l+(r-l)/2;
            if(a[mid] == key){
                return mid;
            }
            if(a[mid] < key){
                return binarySearch(a, key, mid + 1, r);
            }else{
                return binarySearch(a, key, l, mid - 1);
            }
        }
        return -1;
    }
}
