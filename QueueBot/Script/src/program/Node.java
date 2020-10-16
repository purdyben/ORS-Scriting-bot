package program;

import program.ClassNodes.ClassAbstraction;


public class Node<T> {

    private String id = null;
    private ClassAbstraction<T> data;
    private Node<T> next;

    public Node(ClassAbstraction<T> data,String id , Node<T> next) {
        this.id = id;
        this.data = data;
        this.next = next;
    }
    public Node(ClassAbstraction<T>  data, String id) {
        this.id = id;
        this.data = data;
        this.next = null;
    }

    public ClassAbstraction<T>  getData() {
        return this.data;
    }
    public String getid(){
        return this.id;
    }

    public Node next() {
        return this.next;
    }

    public void setNext(Node n) {
        next = n;


    }

    public String print() {
        return getid();
    }


}