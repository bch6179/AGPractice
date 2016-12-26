/**
 * Given a binary tree (not a binary search tree) and two values say n1 and n2,
 * write a program to find the least common ancestor.
 * Allow a node to be a descendant of itself
 *
 * Tags: Tree
 */
class LowestCommonAncestor {
    public static void main(String[] args) {

    }

    /**
     * If root is null, just return null
     * If root's value matches with n1 or n2, return root
     * If not, find lca recursively in both left and right subtrees
     * If both are not null, one value in left and the other in right,
     * return root
     * If one is not null, return that one
     */
    public Node findLca(Node root, int n1, int n2) {
        if (root == null) return null;
        if (root.val == n1 || root.val == n2) return root;

        Node leftLca = findLca(root.left, n1, n2);
        Node rightLca = findLca(root.right, n1, n2);
        if (leftLca != null && rightLca != null) return root;
        return leftLca != null ? leftLca : rightLca;
    }

    class Node {
        int val;
        Node left;
        Node right;

        public Node() {}

        public Node(int v) {
            val = v;
        }
    }
}
class solution{
  BinaryTreeNode<int>* LCA(BinaryTreeNode<int>* root, int x, int y){
        if (root == NULL) return NULL;

        if ((root->val > x) && (root->val > y)) return LCA(root->left, x,y);
        else if ((root->val < x) && (root->val < y)) return LCA(root->right, x,y);
        else return root;

  }

    BinaryTreeNode<int>* LCA_Iter(BinaryTreeNode<int>* root, int x, int y){
        if (root == NULL) return NULL;
        while(root ) {
          if ((root->val > x) && (root->val > y))  root = root->left ;
          else if ((root->val < x) && (root->val < y)) root=root->right;
          else return root;

        }
        return NULL;
  }

};

int GetDepth(const BinaryTreeNode<int>*);

// @include
BinaryTreeNode<int>* LCA(const unique_ptr<BinaryTreeNode<int>>& node_0,
                         const unique_ptr<BinaryTreeNode<int>>& node_1) {
  auto* iter_0 = node_0.get(), *iter_1 = node_1.get();
  int depth_0 = GetDepth(iter_0), depth_1 = GetDepth(iter_1);
  // Makes iter_0 as the deeper node in order to simplify the code.
  if (depth_1 > depth_0) {
    swap(iter_0, iter_1);
  }
  // Ascends from the deeper node.
  int depth_diff = abs(depth_0 - depth_1);
  while (depth_diff--) {
    iter_0 = iter_0->parent;
  }

  // Now ascends both nodes until we reach the LCA.
  while (iter_0 != iter_1) {
    iter_0 = iter_0->parent, iter_1 = iter_1->parent;
  }
  return iter_0;
}

int GetDepth(const BinaryTreeNode<int>* node) {
  int depth = 0;
  while (node) {
    ++depth, node = node->parent;
  }
  return depth;
}