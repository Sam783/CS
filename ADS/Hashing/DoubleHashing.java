import java.util.Arrays;

class DoubleHashing {
    public static int size = 11;
    public static int hash[] = new int[size];
    public static int c = 5;

    public static int hash1(int key){
        return key % size;
    }

    public static int hash2(int key){
        return c-(key%c);
    }

    public static void insert(int key){
        int idx = hash1(key);
        int step = hash2(key);
        int i = 0;
        while(hash[(idx + i*step) % size] != -1){
            i++;
            if(i == size){
                System.out.println("Hash table is full");
                return;
            }
        }
        hash[(idx + i*step) % size] = key;
    }

    public static void display(int hash[], int size){
        for(int i=0; i<size; i++){
            System.out.println(i + " " + hash[i]);
        }
    }

    public static void main(String[] args) {
        Arrays.fill(hash,-1);
        insert(14);
        insert(17);
        insert(25);
        insert(37);
        insert(34);
        insert(16);
        insert(26);
        display(hash, size);
    }
}
