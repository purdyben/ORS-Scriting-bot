package script;

public class MyExceptions extends Exception {

    public MyExceptions(String Message) {
        super(Message);
    }
}
class DoesNotContainException extends Exception {

    public DoesNotContainException(String Message) {
        super(Message);
    }
}