import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
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
        if(yes == null && no == null){
            yes = new ArrayList<>();
            no = new ArrayList<>();
        }

    }


    @Override
    public void run() {
        System.out.println(scannerSoc.nextLine());
        int result = scanner.nextInt();
        writer.print(result);
        String r;
        while (scannerSoc.hasNext()) {
            r= scannerSoc.nextLine();
            System.out.println(r);
            writer.print(scanner.nextLine());
            if(r.contains("#")){
                requestP(r);
            }


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

    static ArrayList<Request> yes;
    static ArrayList<Request> no;

    public void requestP(String r){
        boolean add=true;
        String[] arr = r.split("[#]+");
        for (int i = 0; i < arr.length; i++) {
           //opeartion
        }
        if(add){
            yes.add(new Request(arr[0],Double.parseDouble(arr[1]),Double.parseDouble(arr[2]),
                    arr[3],Integer.parseInt(arr[4])));
        }else {
            no.add(new Request(arr[0],Double.parseDouble(arr[1]),Double.parseDouble(arr[2]),
                    arr[3],Integer.parseInt(arr[4])));
        }
    }
}
