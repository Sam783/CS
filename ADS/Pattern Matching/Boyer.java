import java.util.Arrays;
class Boyer{
    public static void main(String args[]){
        String str = "ababcabcababc";
        String pattern = "abc";

        int n = str.length();
        int m = pattern.length();

        int badChar[] = new int[256];
        Arrays.fill(badChar, -1);

        for(int i=0; i<m; i++){
            badChar[pattern.charAt(i)] = i;
        }
        
        int shift = 0;
        while(shift <= n-m){
            int j = m-1;
            while(j >= 0 && pattern.charAt(j) == str.charAt(shift+j)){
                j--;
            }
            if(j < 0){
                System.out.println("Pattern found at index "+ shift);
                shift += (shift+m < n)? m-badChar[str.charAt(shift+m)] : 1;
            }else {
                shift += Math.max(1, j-badChar[str.charAt(shift+j)]);
            }
        }
    }
}