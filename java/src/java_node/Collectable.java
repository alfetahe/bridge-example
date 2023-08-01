package java_node;

import com.ericsson.otp.erlang.*;
import java.io.IOException;

abstract public class Collectable extends Thread {
    protected static final String erlangProcess = "handler";
    protected static final String beamNode = "beam_node";
    protected OtpNode localNode;
    protected OtpMbox mbox;
    protected String collectableName;

    public Collectable(OtpNode localNode) {
        this.localNode = localNode;
        collectableName = this.getClass().getSimpleName();
        mbox = localNode.createMbox(collectableName);
    }

    abstract protected String messageKey();
    abstract protected void handleMessage(OtpErlangObject message);

    @Override
    public void run() {
        while (true) {
            try {
                sendMessage(mbox);
                receiveMessage();
            } catch (OtpErlangDecodeException | OtpErlangExit | IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(5000); // Wait 5 seconds.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void sendMessage(OtpMbox mbox) throws IOException {
        OtpErlangObject elements[] = new OtpErlangObject[] {
            new OtpErlangAtom(messageKey()),
            mbox.self()
        };

        mbox.send(erlangProcess, beamNode, new OtpErlangTuple(elements));
    }

    private void receiveMessage() throws OtpErlangDecodeException, OtpErlangExit {
        OtpErlangObject message = mbox.receive(10000);
        handleMessage(message);
    }
}