package script;

import script.ClassNodes.movmentNode;

public class Test {
    public static Queue q = new Queue();

    public static void main(String[] args) {
        System.out.println("running");

        q.add(new Node(new movmentNode(null), "Walk"));
        q.add(new Node(new movmentNode(null), "Walk"));
        q.print();
        System.out.println(java.time.LocalTime.now());
        Node n = q.pop();
        n.getData().execute();
//        script.Queue q = new script.Queue(new Node("firstnode","aaa"));
//        q.add(new Node("secondnode","wood"));
//        q.add(new Node("3", "Log"));
//        q.add(new Node("data", "Log"));
//        q.add(new Node("data", "hunt"));
//        q.add(new Node("data", "mine"));
//        q.add(new Node("data", "ben"));
//        q.add(new Node("data", "bb"));
    }
}
