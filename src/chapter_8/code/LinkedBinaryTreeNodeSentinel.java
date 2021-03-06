package chapter_8.code;

import chapter_7.code.positional_list.Position;

/*
 * Created by jjmacagnan on 27/05/2017.
 */

/*Podemos simplificar partes de nossa implementação LinkedBinaryTree se fizermos uso de um único nó sentinela,
de modo que a sentinela seja o pai da raiz real da árvore e a raiz é referenciada como o filho esquerdo da sentinela.
Além disso, o sentinela ocupará o lugar nulo como o valor do membro esquerdo ou direito para um nó sem essa criança. Dê uma nova implementação dos métodos de atualização remover e anexar, assumindo tal representação*/
public class LinkedBinaryTreeNodeSentinel<E> extends AbstractBinaryTree<E> {

    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild;
        }

        @Override
        public E getElement() throws IllegalStateException {
            return element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setElement(E e) {
            this.element = e;
        }

        public void setParent(Node<E> parentNode) {
            this.parent = parentNode;
        }

        public void setLeft(Node<E> leftChild) {
            this.left = leftChild;
        }

        public void setRight(Node<E> rightChild) {
            this.right = rightChild;
        }
    }

    protected Node<E> createNodde(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<>(e, parent, left, right);
    }

    protected Node<E> sentinel = null;
    private int size = 0;

    public LinkedBinaryTreeNodeSentinel() {
    }

    protected Node<E> validate(Position<E> p) throws IllegalStateException {
        if (!(p instanceof Node))
            throw new IllegalStateException("not valide position type");
        Node<E> node = (Node<E>) p;
        if ((node.getParent() == node))
            throw new IllegalStateException("p is no longer in the tree");
        return node;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Position<E> root() {
        return sentinel.getLeft();
    }

    @Override
    public Position<E> parent(Position<E> p) throws IllegalStateException {
        Node<E> node = validate(p);
        return node.getParent();
    }

    @Override
    public Position<E> left(Position<E> p) throws IllegalStateException {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    @Override
    public Position<E> right(Position<E> p) throws IllegalStateException {
        Node<E> node = validate(p);
        return node.getRight();
    }

    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty())
            throw new IllegalStateException("tree is not empty");

        sentinel = new Node<>((E) "sentinel", null, null, null);

        Node<E> node = createNodde(e, sentinel, null, null);
        sentinel.setLeft(node);
        size = 1;
        return node;
    }

    public Position<E> addLeft(Position<E> p, E e) throws IllegalStateException {
        Node<E> parent = validate(p);
        if (parent.getLeft() != null)
            throw new IllegalStateException("p already has a left child");
        Node<E> child = createNodde(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    public Position<E> addRight(Position<E> p, E e) throws IllegalStateException {
        Node<E> parent = validate(p);
        if (parent.getRight() != null)
            throw new IllegalStateException("p already has a right child");
        Node<E> child = createNodde(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    public E set(Position<E> p, E e) throws IllegalStateException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    public static <E> int countLeftLeaves(BinaryTree<E> binaryTree, Position<E> position) {
        int count = 0;

        if (binaryTree.isEmpty() || binaryTree.size() == 1) {
            return count;
        }

        Position<E> parent = binaryTree.parent(position);

        if (binaryTree.isExternal(position) && binaryTree.left(parent) == position) {
            return count + 1;
        } else {
            if (binaryTree.left(position) != null) {
                count += countLeftLeaves(binaryTree, binaryTree.left(position));
            }

            if (binaryTree.right(position) != null) {
                count += countLeftLeaves(binaryTree, binaryTree.right(position));
            }
        }

        return count;
    }

    public int pathLength(Position<E> p) {
        int count = 0;

        for (Position<E> c : children(p)) {
            count += depth(c);
            count += pathLength(c);
        }

        return count;
    }

    public int pathLengthInternal(Position<E> p) {
        int count = 0;

        for (Position<E> c : children(p)) {
            if (isInternal(c)) {
                count += depth(c);
            }
            count += pathLengthInternal(c);
        }

        return count;
    }

    public int pathLengthExternal(Position<E> p) {
        int count = 0;

        for (Position<E> c : children(p)) {
            if (isExternal(c)) {
                count += depth(c);
            }
            count += pathLengthExternal(c);
        }

        return count;
    }

    public <E> boolean isIsomorphic(Position<E> p, Position<E> q) {

        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        if (p.getElement() != q.getElement()) {
            return false;
        }

        Node<E> n1 = (Node<E>) p;
        Node<E> n2 = (Node<E>) q;

        /*Existem dois casos possíveis para que n1 e n2 sejam isomórficos
            Caso 1: as subveres enraizadas nesses nós não foram viradas.

          Essas duas sub-estruturas devem ser isomórficas.
            Caso 2: as subveres enraizadas nesses nós foram viradas*/
        return (isIsomorphic(n1.left, n2.left) && isIsomorphic(n1.right, n2.right))
                || (isIsomorphic(n1.left, n2.right) && isIsomorphic(n1.right, n2.left));
    }


    @Override
    public int depth(Position<E> p) throws IllegalStateException {
        if (isRoot(p))
            return 0;
        else
            return 1 + depth(parent(p));
    }

    public int height(Position<E> p) {
        int h = 0;
        for (Position<E> c : children(p))
            h = Math.max(h, 1 + height(c));

        return h;
    }

    public int pruneSubtree(Position<E> p) {
        int r = 0;
        if(!isRoot(p)) {
            if (p != null) {
                if (p == left(parent(p))) {
                    validate(parent(p)).setLeft(null);
                    size--;
                } else {
                    validate(parent(p)).setRight(null);
                    size--;
                }
                for (Position<E> c : children(validate(p))) {
                    r += 1 + pruneSubtree(left(validate(c))) + pruneSubtree(right(validate(c)));

                    if (c == left(parent(validate(c)))) {
                        validate(c).parent.setLeft(null);
                    } else {
                        validate(c).parent.setRight(null);
                    }
                }
                validate(p).setParent(null);
            }
        }
        size = size -r;
        return r;
    }

    public void swap(Position<E> p, Position<E> q) {
        set(p, set(q, p.getElement()));
    }


    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTreeNodeSentinel<E> t1, LinkedBinaryTreeNodeSentinel<E> t2) throws IllegalStateException {
        Node<E> node = validate(p);
        if (isInternal(p))
            throw new IllegalStateException("p must be a left");
        size += t1.size() + t2.size();

        if (!t1.isEmpty()) { // attach t1 as left subtree of node
            t1.sentinel.left.setParent(node);
            node.setLeft(t1.sentinel.left);
            t1.sentinel.left = null;
            t1.size = 0;
        }

        if (!t2.isEmpty()) {  // attach t2 as right subtree of node
            t2.sentinel.left.setParent(node);
            node.setRight(t2.sentinel.left);
            t2.sentinel.left = null;
            t2.size = 0;
        }
    }

    public E remove(Position<E> p) throws IllegalStateException {
        Node<E> node = validate(p);
        if (numChildren(p) == 2)
            throw new IllegalStateException("p has two children");
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if (child != null)
            child.setParent(node.getParent());
        if (node == sentinel.left)
            sentinel.left = child;
        else {
            Node<E> parent = node.getParent();
            if (node == parent.getLeft())
                parent.setLeft(child);
            else
                parent.setRight(child);
        }
        size--;
        E temp = node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);
        return temp;
    }


}
