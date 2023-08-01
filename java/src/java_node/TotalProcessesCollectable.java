package java_node;

import com.ericsson.otp.erlang.*;

public class TotalProcessesCollectable extends Collectable {
    public TotalProcessesCollectable(OtpNode localNode) {
        super(localNode);
    }

    @Override
    protected String messageKey() {
        return "total_processes";
    }

    @Override
    protected void handleMessage(OtpErlangObject message) {
        System.out.println("Erlang term in string representation: " + message.toString());
    }
}