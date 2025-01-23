import java.util.Arrays;

class quadratic{
    static int size = 10;
    static int hash[] = new int[size];

    public static int getHash(int key, int size){
        return key % size;
    }

    public static void insert(int key){
        int i = 0;
        int idx = getHash(key, size);
        int orgidx = idx;
        while(hash[idx] != -1){
            i++;
            idx = (orgidx + i*i) % size;
            if(i == size){
                System.out.println("Hash Table is full");
                return;
            }
        }
        if(hash[idx] == -1){
            hash[idx] = key;
        }
    }

    public static int search(int key){
        int i = 0;
        int idx = getHash(key, size);
        int orgidx = idx;
        while(hash[idx] != -1){
            i++;
            if(hash[idx] == key){
                return idx;
            }
            idx = (orgidx + i*i) % size;
            if(i == size){
                break;
            }
        }
        return -1;
    }

    public static void display(int hash[], int size){
        for(int i=0; i<size; i++){
            System.out.println(i + " " + hash[i]);
        }
    }

    public static void main(String args[]){
        Arrays.fill(hash,-1);
        insert(10);
        insert(15);
        insert(16);
        insert(20);
        insert(30);
        insert(25);
        insert(26);
        insert(36);
        display(hash, size);
        int key = 16;
        System.out.printf("Key %d found at index : %d\n",key, search(key));
    }
}