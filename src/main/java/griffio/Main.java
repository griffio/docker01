package griffio;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Preconditions.checkArgument(args.length == 2, "host and port arguments must be supplied");

        try {

            String hostArg = args[0];

            String portArg = args[1];

            InetAddress host = InetAddress.getByName(hostArg);

            InetSocketAddress socketAddress = new InetSocketAddress(host, Integer.parseInt(portArg));

            ChatApplication application = new ChatApplication(socketAddress);

            application.start();

        } catch (Exception e) {

            log.error("application error", e);

            Throwables.propagate(e);
        }

    }

}
