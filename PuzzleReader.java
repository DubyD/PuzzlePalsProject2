
//AK
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
public class PuzzleReader{
    private static String csvFilePath = "./rolodex.csv";
    private static File csvFile = new File(csvFilePath);
    public static String readCSV(String puzInput){
        //puzInput i.e = "3x4"
        String categories; String items_in_categories; 
        String clue1; String clue2; String clue3; String clue4;
        Integer counter = 0;
        try{
            Scanner csvScanner=new Scanner(csvFile);
            while(csvScanner.hasNext()){
                String lineRead = csvScanner.nextLine();
                if(counter==0){
                    counter++;
                    continue; // skip the first line.
                }
                String[] splitLine = lineRead.split(",");
                // splitLine contains [row,col,c1,c2,c3,c4,l1,l2,l3,l4];
                // puzInput contains i.e 3x4
                char[] userPuzzleDimensions = puzInput.toCharArray();
                System.out.println(splitLine[2]); // clue 1
                System.out.println(splitLine[3]); // clue 2
                System.out.println(splitLine[4]); // clue 3
                System.out.println(splitLine[5]); // clue 4
                return("q"); // testing
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        return("h"); // testing
    }
}
//AK
