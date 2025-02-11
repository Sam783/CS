class KMP{
    public static int[] computeLPS(String pattern){
        int m = pattern.length();
        int lps[] = new int[m];
        int len = 0;
        int i = 1;

        while(i < m){
            if(pattern.charAt(i) == pattern.charAt(len)){
                len++;
                lps[i] = len;
                i++;
            }else{
                if(len != 0){
                    len = lps[len-1];
                }else{
                    lps[i]=len;
                    i++;
                }
            }
        }
        return lps;
    }

    public static void main(String[] args) {
        String str = "ababcabcababc";
        String pattern = "abc";
        
        int n = str.length();
        int m = pattern.length();

        int[] lps = computeLPS(pattern);

        int i = 0;
        int j = 0;

        while(i < n){
            if(pattern.charAt(j) == str.charAt(i)){
                i++;
                j++;
            }
            if(j == m){
                System.out.println("Pattern found at index " + (i-j));
                j = lps[j - 1];
            }else if(i < n && pattern.charAt(j) != str.charAt(i)){
                if(j != 0){
                    j = lps[j-1];
                }else{
                    i++;
                }
            }
        }
    }
}