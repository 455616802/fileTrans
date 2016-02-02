import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
    public static void main(String[] args) {
        Socket socket = null;
        ServerSocket server = null;
        try {
            server = new ServerSocket(8888);
            while (true) {
                socket = server.accept();
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                byte[] b = new byte[1024];
                is.read(b);
                String fileSuffix = new String(b).split("--")[0];//获取后缀，特殊处理
                FileOutputStream fos = new FileOutputStream("E:\\" + System.currentTimeMillis() + fileSuffix);
                int length;
                while ((length = is.read(b)) != -1) {
                    fos.write(b, 0, length);
                }
                fos.close();
                os.flush();
                os.close();
                is.close();
            }
        } catch (IOException e) {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (server != null) {
                    server.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}