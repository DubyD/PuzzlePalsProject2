import java.io.File;
import java.util.Scanner;
public class PuzzleReader {
    private String csvFilePath;
    private File csvFile;
    private Scanner csvScanner;
    public PuzzleReader(){
        csvFilePath="./Rolodex.csv";
        csvFile= new File(csvFilePath);
        csvScanner=new Scanner(csvFile);
    }
    public static Puzzle readCSV(puzInput){

    }
}
