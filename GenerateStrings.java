class GenerateStrings {
    public static void main(String[] args) {
        char[] lowerCaseAlphabets = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        //generate all possible 5 length lower case strings
        for(int i = 0; i < lowerCaseAlphabets.length; i++) {
            for(int j = 0; j < lowerCaseAlphabets.length; j++) {
                for(int k = 0; k < lowerCaseAlphabets.length; k++) { 
                    for(int l = 0; l < lowerCaseAlphabets.length; l++) { 
                        for(int r = 0; r < lowerCaseAlphabets.length; r++) {
                            System.out.println( "" + lowerCaseAlphabets[i] + lowerCaseAlphabets[j] + lowerCaseAlphabets[k] + lowerCaseAlphabets[l] + lowerCaseAlphabets[r]);
                        }
                    }
                }
            }
        }
    }
}