package ua.nure.havrysh.robomatics.utils;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class DataSender extends AsyncTask<Void, Void, Void> {

    private OutputParams outputParams;

    TextView debugView;

    int interval = 100;

    private Socket socket;
    private OutputStreamWriter stream;

    public DataSender(String address, OutputParams outputParams) throws IOException {
        this.outputParams = outputParams;
        String[] tokens = address.split(":");
        String host = tokens[0];
        int port = Integer.valueOf(tokens[1]);

        socket = new Socket(host, port);
        socket.setTcpNoDelay(true);
        socket.setTrafficClass(0x10);
        socket.setPerformancePreferences(-1, 1, 1);

        stream = new OutputStreamWriter(socket.getOutputStream());
    }

public void stop() throws IOException {
    socket.close();
    socket=null;
}

    private void debug(final String s) {
        if (debugView != null) {
            debugView.post(new Runnable() {
                @Override
                public void run() {
                    debugView.setText(s);
                }
            });
        }
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void setDebugView(TextView debugView) {
        this.debugView = debugView;
    }

    private String getCommandString() {
        String res = String.format("m=%3ds=%3d", (int)outputParams.getThrottle(),
                (int)outputParams.getSteer()).replace(' ', '0');
        return res;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            while (true) {
                String command = getCommandString();
                Log.d("qaz", command);
                stream.write(command);
                stream.flush();

                debug(command);
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            debug(e.getMessage());
        }
        return null;
    }
}
