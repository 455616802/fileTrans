import java.io.*;
import java.net.Socket;

public class FileClient {

    public static void main(String[] args) {
        Socket client = null;
        try {
            client = new Socket("192.168.202.88", 8888);
            InputStream is = client.getInputStream();
            OutputStream os = client.getOutputStream();
            String filePath = "E:\\test.txt";
            File file = new File(filePath);
            String fileName = file.getName();
            String fileSuffix = fileName.substring(fileName.lastIndexOf('.')) + "--";//发送文件后缀，特殊处理
            os.write(fileSuffix.getBytes());
            FileInputStream fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) != -1) {
                os.write(b, 0, length);
            }
            os.close();
            fis.close();
            is.close();
        } catch (Exception e) {
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}