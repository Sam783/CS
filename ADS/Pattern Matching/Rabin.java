class Rabin{
    public static void main(String args[]){
        String text = "ababcababcabc";
        String pattern = "abc";
        int n = text.length();
        int m = pattern.length();

        int prime = 101;
        int patternHash = 0, windowHash = 0, base = 1;

        for(int i=0; i<m-1; i++){
            base = (base * 256) % prime;
        }

        for(int i=0; i<m; i++){
            patternHash = (patternHash * 256 + pattern.charAt(i)) % prime;
            windowHash = (windowHash * 256 + text.charAt(i)) % prime;
        }

        for(int i=0; i<=n-m; i++){
            if(patternHash == windowHash && text.substring(i,i+m).equals(pattern)){
                System.out.println("Pattern found at index " + i);
            }
            if(i<n-m){
                windowHash = ((windowHash - text.charAt(i) * base) * 256 + text.charAt(i + m)) % prime;
                if (windowHash < 0) windowHash += prime;
            }
        }
    }
}