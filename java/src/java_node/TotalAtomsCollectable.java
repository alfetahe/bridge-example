package java_node;

import com.ericsson.otp.erlang.*;
import java.io.IOException;

public class TotalAtomsCollectable extends Collectable {
    public TotalAtomsCollectable(OtpNode localNode) {
        super(localNode);
    }

    @Override
    protected String messageKey() {
        return "total_atoms";
    }

    @Override
    protected void handleMessage(OtpErlangObject message) {
        OtpErlangTuple tuple = (OtpErlangTuple) message;
        OtpErlangAtom key = (OtpErlangAtom) tuple.elementAt(0);
        OtpErlangLong value = (OtpErlangLong) tuple.elementAt(1);

        System.out.println("Key " + key + " has value " + value);
    }
}