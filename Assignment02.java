import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Queue;
import java.util.*;

public class Assignment02 {
    public static ArrayList<Obj> ReadFileAL(String MyFile) {
        Path p2 = Path.of(MyFile); /* newer way */
        ArrayList<Obj> tags = new ArrayList<Obj>();
        String[] tag = null;
        /* using a try-with-resource clause to prevent resource leak*/
        try (BufferedReader reader = Files.newBufferedReader(p2, StandardCharsets.UTF_8)) {
            //String currentLine = null;
            System.out.println("Reading data file .....");
            //while there is content on the current line

            String currentLine = reader.readLine();
            while ((currentLine = reader.readLine()) != null) {
                currentLine = currentLine.trim(); //adds line into a list
                tag = currentLine.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");

                Obj entry = new Obj();
                entry.setZip(tag[14]);
                entry.setNAICS(tag[16]);
                entry.setHood(tag[23]);
                entry.setType(tag[17]);
                entry.setsDate(tag[8]);
                if (tag[9].equals("")) {
                    entry.setOpen(true);
                } else {
                    entry.setOpen(false);
                }
                tags.add(entry);
            }
        } catch (IOException ex) { //handle an exception here
            ex.printStackTrace();
        }
        return tags;
    }

    public static LinkedList<Obj> ReadFileLL(String MyFile) {
        Path p2 = Path.of(MyFile); /* newer way */
        LinkedList<Obj> tags = new LinkedList<>();
        String[] tag = null;
        /* using a try-with-resource clause to prevent resource leak*/
        try (BufferedReader reader = Files.newBufferedReader(p2, StandardCharsets.UTF_8)) {
            //String currentLine = null;
            System.out.println("Reading data file .....");
            //while there is content on the current line
            String currentLine = reader.readLine();
            while ((currentLine = reader.readLine()) != null) {
                currentLine = currentLine.trim(); //adds line into a list
                tag = currentLine.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");

                Obj entry = new Obj();
                entry.setZip(tag[14]);
                entry.setNAICS(tag[16]);
                entry.setHood(tag[23]);
                entry.setType(tag[17]);
                entry.setsDate(tag[8]);
                if (tag[9].equals("")) {
                    entry.setOpen(true);
                } else {
                    entry.setOpen(false);
                }
                tags.add(entry);
            }
        } catch (IOException ex) { //handle an exception here
            ex.printStackTrace();
        }
        return tags;
    }

    public static boolean UserIn(List<Obj> list, Deque<String> queue) {
        Scanner sc = new Scanner(System.in);
        String in = "";
        String[] line = null;
        System.out.println("enter commands, or enter EXIT to exit");
        in = sc.nextLine();
        queue.add(in);
        while (!in.equals("EXIT")) {
            if (in.contains("Zip")) {
                line = in.split(" ");
                String zip = line[1];
                zipSum(zip, list);
                UserIn(list, queue);
            } else if (in.contains("NAICS")) {
                line = in.split(" ");
                String naics = line[1];
                naicsSum(naics, list);
                UserIn(list, queue);
            } else if (in.contains("Summary")) {
                sumSum(list);
                UserIn(list, queue);
            } else if (in.contains("History")) {
                for (String element : queue) {
                    System.out.println(element);
                }
                UserIn(list, queue);
            } else {
                System.out.println("enter a valid command!");
                UserIn(list, queue);
            }
            return false;
        }
        return false;
    }

    public static void naicsSum(String naics, List<Obj> list) {
        int count = 0;
        HashSet<String> zips = new HashSet<String>();
        HashSet<String> hoods = new HashSet<String>();
        Iterator<Obj> iterator = list.Iterator();
        int naic = Integer.parseInt(naics);
        while(iterator.hasNext()) {
            Obj item = iterator.next();
            String nrange = item.getNAICS();
            String[] mult = nrange.split(" ");
            for (int i = 0; i < mult.length; i++) {
                String[] two = mult[i].split("-");
                if (!two[0].equals("")) {
                    String lo = two[0];
                    String hi = two[1];
                    if (naic <= Integer.parseInt(hi) && naic >= Integer.parseInt(lo)) {
                        zips.add(item.getZip());
                        hoods.add(item.getHood());
                        count++;
                    }
                }
            }
        }

        System.out.println(naics + " Business Summary");
        System.out.println("Total Businesses: " + count);
        System.out.println("Zip codes: " + zips.size());
        System.out.println("Neighborhood: " + hoods.size());
    }

    public static void zipSum(String zip, List<Obj> list) {
        int count = 0;
        HashSet<String> types = new HashSet<String>();
        HashSet<String> hoods = new HashSet<String>();
        Iterator<Obj> iterator = list.Iterator();
        while (iterator.hasNext()) {
            Obj item = iterator.next();
            if (item.getZip().equals(zip)) {
                types.add(item.getType());
                hoods.add(item.getHood());
                count++;
            }
        }
        System.out.println(zip + " Business Summary");
        System.out.println("Total Businesses: " + count);
        System.out.println("Business Types: " + types.size());
        System.out.println("Neighborhood: " + hoods.size());
    }

    public static void sumSum(List<Obj> list) {
        int count = 0;
        int count2 = 0;
        String[] year = null;
        int current = 2023;
        Iterator<Obj> iterator = list.Iterator();
        while (iterator.hasNext()) {
            Obj item = iterator.next();
            if (!item.isOpen()) {
                count++;
            }
            String date = item.getsDate();
            year = date.split("/");
            int theYear = Integer.parseInt(year[2]);
            if (current - theYear < 2) {
                count2++;
            }
        }
        System.out.println("Total Business Summary");
        System.out.println("Total Businesses: " + list.size());
        System.out.println("Closed Businesses: " + count);
        System.out.println("New Business in last year: " + count2);
    }

    public static void main(String[] args) {
        String MyFile = "Registered_Business_Locations_-_San_Francisco.csv";
        String type = "LL";
        Deque<String> queue = new ArrayDeque<>();
        if (MyFile != null) {
            if (type == "AL") {
                List<Obj> aList = new ArrayList<>();
                aList = ReadFileAL(MyFile);
                UserIn(aList, queue);
            } else if (type == "LL") {
                List<Obj> lList = new LinkedList<>();
                lList = ReadFileLL(MyFile);
                UserIn(lList, queue);
            } else {
                System.out.println("enter a valid list type");
            }
        } else {
            System.out.println("enter a valid file");
        }
    }
}
