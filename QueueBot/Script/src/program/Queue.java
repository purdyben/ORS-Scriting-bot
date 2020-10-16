package program;

public class Queue {

    private int size = 1;
    private Node head;

    Queue(Node StartNode) {
        this.head = StartNode;
    }

    Queue() {
        this.head = null;
        this.size = 0;
    }

    public void add(Node n) {
        Node listNode = head;
        if (head == null) {
            head = n;
            size++;
            return;
        } else if (size > 1) {
            while (hasNext(listNode)) {
                listNode = listNode.next();
            }
        }
        listNode.setNext(n);
        size++;
    }

    public Boolean hasNext(Node n) {
        if (n != null && n.next() != null) {
            return true;
        } else {
            return false;
        }
    }
    public Boolean hasNode(){
        return head != null;
    }

    public boolean peek() {
        return head != null;
    }

    public Node pop() {
        Node n = head;
        if (hasNode()) {
            head = head.next();
        }else{
            return null;
        }
        return n;


    }

    public void element() {

    }

    public void remove(String n) {
        Node listNode = head;
        Node match = null;
        while (hasNext(listNode)) {
            if (listNode.next().getid() == n) {
                match = listNode.next();
                break;
            }

            listNode = listNode.next();
        }
        System.out.println(listNode.getid());
        if (listNode.next() == null) {
            System.out.println("node: " + n + " was not found");
        } else if (match.next() == null) {
            listNode.setNext(null);
        } else listNode.setNext(match.next());


    }


    public void print() {
        String str = "";
        Node listNode = head;
        while (hasNext(listNode)) {
            str += " -> " + listNode.print();
            listNode = listNode.next();

        }
        try{
            str += " -> " + listNode.print();
            System.out.println(str);
        }catch (NullPointerException e){
            System.out.println("Queue is empty : " + e);
        }

    }


}


