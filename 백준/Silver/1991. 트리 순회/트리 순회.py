import sys

preorder_answer = []
inorder_answer = []
postorder_answer = []
left = 0
right = 1


def preorder(tree, root):
    if root == '.':
        return

    preorder_answer.append(root)
    preorder(tree, tree[root][left])
    preorder(tree, tree[root][right])


def inorder(tree, root):
    if root == '.':
        return

    inorder(tree, tree[root][left])
    inorder_answer.append(root)
    inorder(tree, tree[root][right])


def postorder(tree, root):
    if root == '.':
        return

    postorder(tree, tree[root][left])
    postorder(tree, tree[root][right])
    postorder_answer.append(root)


def input_data():
    n = int(sys.stdin.readline())
    tree = {}

    for _ in range(n):
        line = sys.stdin.readline().split()
        tree[line[0]] = [line[1], line[2]]

    return tree


def main():
    tree = input_data()
    preorder(tree, 'A')
    inorder(tree, 'A')
    postorder(tree, 'A')

    print(''.join(preorder_answer))
    print(''.join(inorder_answer))
    print(''.join(postorder_answer))


main()
