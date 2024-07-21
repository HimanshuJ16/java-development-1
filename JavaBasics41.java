public class JavaBasics41 {
  static class Node {
    int data;
    Node right;
    Node left;

    Node(int data) {
      this.data = data;
    }
  }

  public static Node insert(Node root, int val) {
    if (root == null) {
      root = new Node(val);
      return root;
    }

    if (root.data > val) {
      root.left = insert(root.left, val);
    } else {
      root.right = insert(root.right, val);
    }
    
    return root;
  } 

  public static void inorder(Node root) {
    if (root == null) {
      return;
    }

    inorder(root.left);
    System.out.print(root.data + " ");
    inorder(root.right);
  }

  public static boolean search(Node root, int key) {
    if (root == null) {
      return false;
    }

    if (root.data == key) {
      return true;
    }

    if (root.data > key) {
      return search(root.left, key);
    } else {
      return search(root.right, key);
    }
  }

  public static Node delete(Node root, int val) {
    if (root.data < val) {
      root.right = delete(root.right, val);
    } else if (root.data > val) {
      root.left = delete(root.left, val);
    } else {
      //case1 - leaf node
      if (root.left == null && root.right == null) {
        return null;
      }

      //case2 - single child
      if (root.left == null) {
        return root.right;
      } else if (root.right == null) {
        return root.left;
      }

      //case3 - both children
      Node IS = findInorderSuccessor(root.right);
      root.data = IS.data;
      root.right = delete(root.right, IS.data);
    }

    return root;
  }

  public static Node findInorderSuccessor(Node root) {
    while (root.left != null) {
      root = root.left;
    }
    return root;
  }
  public static void main(String[] args) {
    int values[] = {8, 5, 3, 1, 4, 6, 10, 11, 14};
    Node root = null;

    for(int i=0; i<values.length; i++) {
      root = insert(root, values[i]);
    }

    inorder(root);
    System.out.println();

    if (search(root, 1)) {
      System.out.println("found");
    } else {
      System.out.println("not found");
    }

    root = delete(root, 1);
    System.out.println();

    inorder(root);
  }
}
