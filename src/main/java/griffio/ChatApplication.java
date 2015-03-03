package griffio;

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Collections;

public class ChatApplication extends WebSocketServer {

    private static final Logger log = LoggerFactory.getLogger(ChatApplication.class);

    public ChatApplication(InetSocketAddress address) {
        super(address,Collections.<Draft>singletonList(new Draft_17()));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        this.sendToAll("new connection: " + handshake.getResourceDescriptor());
        log.debug(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the chat");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        this.sendToAll(conn + " has left the chat");
        log.debug(conn + " left the chat");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        this.sendToAll(message);
        log.debug(conn + ": " + message);
    }

    @Override
    public void onWebsocketMessageFragment(WebSocket conn, Framedata frame) {
        super.onWebsocketMessageFragment(conn, frame);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        log.error(conn.toString(), ex);
    }

    public void sendToAll(String text) {
        Collection<WebSocket> con = connections();
        for (WebSocket c : con) {
            c.send(text);
        }
    }

}