import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by georgipavlov on 05.01.16.
 */
public class Server implements Runnable{
    Socket socket;
    Scanner scanner;
    PrintStream writer;
    boolean t;
    private static final int PASS =1111;
    static Queue<Request> requests;

    public Server(Socket socket) {
        this.socket = socket;

        try {
            scanner = new Scanner(socket.getInputStream());
            writer = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(requests == null){
            requests  = new LinkedList<>();
        }

    }

    @Override
    public void run() {
        boolean loop = true;
        String name;
        double mark;
        double moneyFamily;
        String fac;
        int type;
        p: while (loop) {
            writer.println("You are connected please make a choice \n 1 Log as student \n 2 log as admin");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    t = false;
                    System.out.println("Enter name");
                    name = scanner.nextLine();
                    System.out.println("Enter mark");
                    mark = Double.parseDouble(scanner.nextLine());
                    System.out.println("Enter money");
                    moneyFamily = Double.parseDouble(scanner.nextLine());
                    System.out.println("Enter face");
                    fac = scanner.nextLine();
                    System.out.println("Enter type 1 for standart 2 for special ");
                    type = Integer.parseInt(scanner.nextLine());
                    requests.add(new Request(name, mark, moneyFamily, fac, type));
                    loop = false;
                    continue p;
                }
                case 2: {
                    int pass;
                    writer.println("Please enter a pass");
                    pass = scanner.nextInt();
                    if (PASS == pass) {
                        t = true;
                    } else {
                        continue p;
                        //some custo exeption TO DO
                    }
                    int result = 0;
                    boolean tt=true;
                    pp: while (tt){
                    writer.println("Press 1 to download a request");
                    writer.println("Press 2 to exit");
                    result =scanner.nextInt();
                    switch (result){
                        case 1:{
                            String w = pop();
                            writer.print(w);
                            continue pp;
                        }case 2:{
                            tt=false;
                            continue pp;
                        }
                    }
                    }

                }
                case 3:{
                    break ;
                }

            }

        }
        scanner.close();
        writer.close();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private String pop(){
        Request request = requests.poll();
        StringBuilder b= new StringBuilder();
        b.append(request.getName());
        b.append(".");
        b.append(request.getMark());
        b.append(".");
        b.append(request.getMoneyFamily());
        b.append(".");
        b.append(request.getFac());
        b.append(".");
        b.append(request.getType());
        return b.toString();
    }
}
