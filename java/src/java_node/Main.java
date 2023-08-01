package java_node;

import com.ericsson.otp.erlang.*;
import java.io.IOException;

public class Main {
    private final Collectable collectables[];

    private Main() throws IOException {
        OtpNode localNode = new OtpNode("java_node", "cookie");

        collectables = new Collectable[] {
            new TotalAtomsCollectable(localNode),
            new TotalProcessesCollectable(localNode)
        };
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();

        for (Collectable c : main.collectables) {
            c.start();
        }

        // Keep the program running indefinitely.
        while (true) {}
    }
}