package program;

import java.time.LocalTime;


public class test {
    private static Queue q = new Queue();

    public static void main(String[] args) {
        System.out.println("running");
//        q.add(new Node(new TreeChoppingUnder35(null,null),"Tree"));
//
//
//        q.add(new Node(new TreeChoppingUnder35(null,null),"Tree"));
//
        q.print();
        System.out.println(java.time.LocalTime.now());
        LocalTime v = java.time.LocalTime.now();

        while (q.hasNode()) {
            try{
                Node n = q.pop();
            }catch (NullPointerException e){
                System.out.println("Queue is empty : " + e);
            }finally {

                if(q.hasNode())
                    q.print();
            }

//            n.getData().execute();
        }




//        script.program.Queue q = new script.program.Queue(new program.Node("firstnode","aaa"));
//        q.add(new program.Node("secondnode","wood"));
//        q.add(new program.Node("3", "Log"));
//        q.add(new program.Node("data", "Log"));
//        q.add(new program.Node("data", "hunt"));
//        q.add(new program.Node("data", "mine"));
//        q.add(new program.Node("data", "ben"));
//        q.add(new program.Node("data", "bb"));
    }
}