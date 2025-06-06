package heap;

public class Solution {
    TreeNode previous;
    TreeNode head;
    public TreeNode treeToDoublyList(TreeNode root) {
        head = null;
        previous = null;
        inorder(root);
        // for doubly link list set head left = rightmost and rightmost node right = head
        head.left = previous;
        previous.right = head;
        return head;
    }

    private void inorder(TreeNode cur){
        if(cur == null) {
            return;
        }
        inorder(cur.left);
        if(previous == null){
            // if previous is null then it is leftmost node to be processed first so it is head;
            head = cur;
        }
        else{
            previous.right = cur;
            cur.left = previous;
        }
        previous = cur;
        inorder(cur.right);
    }
}
