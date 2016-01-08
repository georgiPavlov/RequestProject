import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by georgipavlov on 08.01.16.
 */
public class Client implements Runnable {
    Socket socket;
    Scanner scannerSoc;
    Scanner scanner;
    PrintStream writer;

    public Client(int i) {
        try {
            socket = new Socket("localhost", i);
            scannerSoc = new Scanner(socket.getInputStream());
            writer = new PrintStream(socket.getOutputStream());
            scanner = new Scanner(System.in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        System.out.println(scannerSoc.nextLine());
        int result = scanner.nextInt();
        writer.print(result);
        while (scannerSoc.hasNext()) {
            System.out.println(scannerSoc.nextLine());
            writer.print(scanner.nextLine());
        }

        scannerSoc.close();
        scanner.close();
        writer.close();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
