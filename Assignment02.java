import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Objects;

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
                entry.setOpen(true);
                if (!entry.getZip().equals("") && !entry.getNAICS().equals("") &&
                        !entry.getHood().equals("") && !entry.getType().equals("")) {
                            tags.add(entry);
                        }
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
                entry.setOpen(true);
                if (!entry.getZip().equals("") && !entry.getNAICS().equals("") &&
                        !entry.getHood().equals("") && !entry.getType().equals("")) {
                            tags.add(entry);
                        }
            }
        } catch (IOException ex) { //handle an exception here
            ex.printStackTrace();
        }
        return tags;
    }

    public static boolean UserIn() {

        return false;
    }

    public static void naicsSum(List<Obj> list) {

    }

    public static void zipSum(String zip, List<Obj> list) {
        int count = 0;
        HashSet<String> types = new HashSet<String>();
        HashSet<String> hoods = new HashSet<String>();
        for (int i = 0; i < list.size(); i++ ) {
            Obj item = list.get(i);
            if (item.getZip().equals(zip)) {
                types.add(item.getType());
                hoods.add(item.getHood());
                count++;
            }
        }

        System.out.println(zip + "business summary");
        System.out.println("Total Businesses: " + count);
        System.out.println("Business Types: " + types.size());
        System.out.println("Neighborhood: " + hoods.size());
    }

    public static void sumSum() {

    }






    public static void main(String[] args) {
        String MyFile = "Registered_Business_Locations_-_San_Francisco.csv";
        String type = "AL";
        String zip = "94108";
        if (MyFile != null) {
            if (type == "AL") {
                List<Obj> aList = new ArrayList<>();
                aList = ReadFileAL(MyFile);
                zipSum(zip, aList);
            } else if (type == "LL") {
                List<Obj> lList = new LinkedList<>();
                lList = ReadFileLL(MyFile);
                lList.print();
            } else {
                System.out.println("enter a valid list type");
            }
        } else {
            System.out.println("enter a valid file");
        }
    }
}
