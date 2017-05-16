package ua.nure.havrysh.robomatics.iot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.*;

import java.io.*;

public class NetworkController {
    private static final int PORT = 81;
    private ServerSocket serverSocket;
    private final NetworkControllerListener listener;
    private Thread networkThread;

    public NetworkController(NetworkControllerListener listener) {
        this.listener = listener;

    }

    public void start() {
        networkThread = new Thread(this::startServer);
        networkThread.start();
    }

    private void startServer() {
        ServerSocketHints serverSocketHints = new ServerSocketHints();
        serverSocketHints.acceptTimeout = 0;
        serverSocket = new NetJavaServerSocketImpl(Net.Protocol.TCP, PORT, serverSocketHints);

        SocketHints socketHints = new SocketHints();
        Socket client = serverSocket.accept(socketHints);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {
            if (client.isConnected()) {
                Gdx.app.postRunnable(listener::onConnected);
            } else {
                // TODO: 06.05.2017 handle
            }
            while (true) {
                if (reader.ready()) {
                    String command = reader.readLine();
                    Gdx.app.postRunnable(() -> listener.onCommand(command));

                    String response = listener.getResponseCommand();
                    if (response != null) {
                        writer.write(response);
                        writer.flush();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: 06.05.2017 handle
        }
    }

    public interface NetworkControllerListener {
        void onConnected();

        void onCommand(String command);

        String getResponseCommand();
    }
}
