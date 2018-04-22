package case1;

public class CallerOfCaller {
    Caller caller = new Caller();
    public void callTheCaller() {
        caller.callCallee();
    }
}
