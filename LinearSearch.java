class LinearSearch{
    public static void main(String args[]){
        int a[] = {1, 5, 6, 10, 25, 79, 100};
        int key = 100;
        int result = linearSearch.linearSearch(a, key, 0);
        if(result == -1){
            System.out.println("Key not found.");
        }else{
            System.out.println("Key found at index: " + result);
        }
    }

	// public int linearSearch(int a[], int key){
    //     for(int i=0; i<a.length; i++){
    //         if(a[i] == key){
    //             return i;
    //         }
    //     }
    //     return -1;
    // }

    public static int linearSearch(int a[], int key, int i){
        if(i >= a.length){
            return -1;
        }
        if(a[i] == key){
            return i;
        }
        return linearSearch(a, key, i+1);
    }

}
