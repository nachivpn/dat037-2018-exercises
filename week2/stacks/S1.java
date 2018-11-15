public class S1 {
    /**
     * Removes comments.
     *
     * Time complexity: O(N), given a string of length N.
     *
     * @param program the string to remove comments from
     * @throws IllegalArgumentException if program missing
     * @return the string without comments
     */
    public static String removeComments(String program){
        if( program == null ){
            throw new IllegalArgumentException();
        }
        int offset = 0;
        StringBuilder newprogram = new StringBuilder();
        int comments = 0;

        // O(N) loop: there are at most N iterations in the loop since
        // offset always increases, and each iteration always costs O(1).
        while( offset < program.length() ){
            if( offset+1 < program.length() &&
                    program.substring(offset,offset+2).equals("(*") ){
                offset += 2;
                comments++;
            } else if( offset+1 < program.length() &&
                    program.substring(offset,offset+2).equals("*)") ){
                offset += 2;
                comments--;
            } else {
                if( comments==0 ){
                    newprogram.append(program.charAt(offset));
                }
                offset++;
            }
        }
        if( comments > 0 ){
            throw new IllegalArgumentException("unterminated comment");
        }
        return newprogram.toString();
    }

    private static boolean runtest() {
        if( ! removeComments("c(*a(*b*)*)d").equals("cd") )return false;
        if( ! removeComments("(* a *) d (* b *)").equals(" d ") )return false;

        // test failure of unterminated comment
        try{
            removeComments("(* a (* b *)");
            return false;
        } catch(IllegalArgumentException ignored){

        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("S2: " + (runtest() ? "TEST OK" : "TEST FAILED"));
    }
}