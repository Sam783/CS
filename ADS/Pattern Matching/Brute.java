class Brute{
    public static void main(String args[]){
        String str = "ababcabcababc";
        String pattern = "abc";
        int s = str.length();
        int p = pattern.length();
        for(int i=0; i<= s-p; i++){
            int j;
            for(j=0; j<p; j++){
                if(str.charAt(i+j) != pattern.charAt(j)) break;
            }
            if(j == p){
                System.out.println("Pattern found at index "+ i);
            }
        }
    }
}