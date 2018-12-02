import java.util.function.*;
import java.util.Optional;

public class B4 {

    static class BTree { 
	int data;
	Optional<BTree> left, right;
   
	public BTree(int item) {
	    data = item;
	    left = right = Optional.empty();
	}
	
    }

    public static int size(BTree tree){
	return 1
	    + tree.left.map(B4::size).orElse(0)
	    + tree.right.map(B4::size).orElse(0); 
    }
    
    public static boolean check(BTree tree, int i, int size){
	if(i >= size)
	    return false;
	else
	    return
		tree.left.map((l) -> check(l, 2 * i + 1, size)).orElse(true)
		&&
		tree.right.map((r) -> check(r, 2 * i + 2, size)).orElse(true);
    }
    
    private static boolean isComplete(BTree tree){
	if(tree == null)
	    return true;
	else
	    return check(tree,0,size(tree));
    }
    
}
