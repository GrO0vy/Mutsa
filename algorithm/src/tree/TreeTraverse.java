package tree;

public class TreeTraverse {
    private int nodes;
    private int[] arr; // 이진 트리를 표현하기 위한 배열 ( 완전 이진 트리 )

    public void setArr(int[] arr) {
        this.arr = arr;
        this.nodes = arr.length;
    }

    // 전위 순회 : 나 -> 왼 -> 오
    // preorder(): sout(나) -> preorder(왼) -> preorder(오)
    public void traversePreorder(int node) {
        if (node < this.nodes) {
            System.out.print(arr[node] + ", ");
            this.traversePreorder(node * 2);
            this.traversePreorder(node * 2 + 1);
        }
    }

    // 중위 순회 : 왼 -> 나 -> 오
    // inorder(): preorder(왼) -> sout(나) -> preorder(오)
    public void traverseInorder(int node) {
        if (node < this.nodes) {
            this.traverseInorder(node * 2);
            System.out.print(arr[node] + ", ");
            this.traverseInorder(node * 2 + 1);
        }
    }

    // 후위 순회 : 왼 -> 오 -> 나
    // postorder(): preorder(왼) -> preorder(오) -> sout(나)
    public void traversePostorder(int node) {
        if (node < this.nodes) {
            this.traversePostorder(node * 2);
            this.traversePostorder(node * 2 + 1);
            System.out.print(arr[node] + ", ");
        }
    }



    public static void main(String[] args) {
        TreeTraverse tree = new TreeTraverse();
        tree.setArr(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
        tree.traversePreorder(1);
        System.out.println();
        tree.traverseInorder(1);
        System.out.println();
        tree.traversePostorder(1);
    }
}
