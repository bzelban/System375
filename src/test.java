import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;


public class test {


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";


    public static HashMap<Integer, ArrayList<String>> hashMap;

    private int shift;

    public void setShift(int s){
        shift = s;
    }

    public int getShift(){
        return shift;
    }

    private static String caseChoice;

    public void setCaseChoice(String chs){
        caseChoice = chs;
    }

    public static String getCaseChoice(){
        return caseChoice;
    }

    private static String colorChoice;

    public void setColorChoice(String color){
        colorChoice = color;
    }

    public static String getColorChoice(){
        return colorChoice;
    }

    static String read(File path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));

        String data = br.readLine();

        String content="";
        while (data!=null) {
            content+=data;
            data = br.readLine();
        }
        return content;
    }

    static void show(){
        Set<Integer> keys = hashMap.keySet();
        System.out.println("Original");
        for (Integer key : keys) {
            ArrayList arrayList = hashMap.get(key);
            System.out.print(arrayList.get(0).toString());
        }
        System.out.println("\nAfter Case Change");
        for (Integer key : keys) {
            ArrayList arrayList = hashMap.get(key);
            System.out.print(arrayList.get(1).toString());
        }
        System.out.println("\nAfter Shift");
        for (Integer key : keys) {
            ArrayList arrayList = hashMap.get(key);
            if(getCaseChoice().equalsIgnoreCase("U"))
                System.out.print(arrayList.get(2).toString().toUpperCase());

            if(getCaseChoice().equalsIgnoreCase("L"))
                System.out.print(arrayList.get(2).toString().toLowerCase());
        }
        System.out.println("\nAfter Color Change");
        for (Integer key : keys) {
            ArrayList arrayList = hashMap.get(key);

            if (getCaseChoice().equalsIgnoreCase("U")) {
                if (getColorChoice().equalsIgnoreCase("R"))
                    System.out.print(ANSI_RED + arrayList.get(3).toString().toUpperCase());

                if (getColorChoice().equalsIgnoreCase("Y"))
                    System.out.print(ANSI_YELLOW + arrayList.get(3).toString().toUpperCase());
            }

            if(getCaseChoice().equalsIgnoreCase("L")){
                if (getColorChoice().equalsIgnoreCase("R"))
                    System.out.print(ANSI_RED + arrayList.get(3).toString().toLowerCase());

                if (getColorChoice().equalsIgnoreCase("Y"))
                    System.out.print(ANSI_YELLOW + arrayList.get(3).toString().toLowerCase());
            }
        }
    }


    public static void main(String[] args) throws IOException {
        test test = new test();

        File file = new File("D:\\_IEU\\SE375\\System375\\System375\\a.txt");

        String content = read(file);
        hashMap = new HashMap<>();

        for(int i=0;i<content.length();i++){
            char c = content.charAt(i);
            ArrayList<String> arrayList= new ArrayList<>();
            arrayList.add(Character.toString(c));
            hashMap.put(i,arrayList);
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please state your choice...");
        System.out.println("UPPER case or lower case (U or L)");

        //st(scanner.next());//caseThread(scanner.next());

        test.setCaseChoice(scanner.next());
        //Thread caseThread = new Thread(new caseThread(test.getcaseChoise()));
        //caseThread.start();

        System.out.println("Please state your choice...");
        System.out.println("\nHow many characters to shift (number between 1-3):");

        //shiftThread(scanner.nextInt());

        test.setShift(scanner.nextInt());
        Thread shiftThread = new Thread(new shiftThread(test.getShift()));
        shiftThread.start();

        System.out.println("Please state your choice...");
        System.out.println("\nColor of characters (R or Y):");

        //colorThread(scanner.next());
        scanner.nextLine();
        String tempColor = scanner.next();

        test.setColorChoice(scanner.next());
        //Thread colorThread = new Thread(new colorThread(test.getColorChoise()));
        //colorThread.start();
        show();
    }

}
