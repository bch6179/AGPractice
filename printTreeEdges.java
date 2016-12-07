


  if (print || (!p->left && !p->right))
    cout << p->data << " ";
  printLeftEdges(p->left, print);
  printLeftEdges(p->right, (print && !p->left ? true : false));
}

void printRightEdges(BinaryTree *p, bool print) {
  if (!p) return;
  printRightEdges(p->left, (print && !p->right ? true : false));
  printRightEdges(p->right, print);
  if (print || (!p->left && !p->right))
    cout << p->data << " ";
}

void printOuterEdges(BinaryTree *root) {
  if (!root) return;
  cout << root->data << " ";
  printLeftEdges(root->left, true);
  printRightEdges(root->right, true);
}