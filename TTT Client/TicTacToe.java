import javax.swing.text.BadLocationException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class KolkoIKrzyzyk {

    public static int port = 4998;
    public static Socket socket;
    public static ObjectInputStream ois;
    public static ObjectOutputStream oos;

    public static void main(String args[]) throws IOException, ClassNotFoundException, BadLocationException {

        Okno oknoGlowne = new Okno();
        oknoGlowne.setVisible(true);

        InetAddress host = InetAddress.getLocalHost();
        try {
            socket = new Socket(host.getHostName(), port);
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());

            String msg = "";

            while (!msg.equals("exit")) {

                msg = (String) ois.readObject();
                System.out.println(msg);
                if (msg != "") {
                    if (msg.equals("restart"))  {
                        oknoGlowne.restart();
                    } else {
                        System.out.println(msg);
                        oknoGlowne.pole[Integer.parseInt(msg)].setText("O");
                        oknoGlowne.pole[Integer.parseInt(msg)].doClick();
                        oknoGlowne.ruch = !oknoGlowne.ruch;
                    }
                }
            }
        } catch (IOException ex){
            System.out.println("najpierw uruchom serwer, zrestartuj klienta");
            System.exit(0);
        }
    }
}
