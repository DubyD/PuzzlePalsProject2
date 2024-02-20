
//AK
import java.io.File;
import java.util.Scanner;
public class PuzzleReader {
    private String csvFilePath;
    private File csvFile;
    private Scanner csvScanner;
    public PuzzleReader(){
        this.csvFilePath="./Rolodex.csv";
        this.csvFile= new File(csvFilePath);
        this.csvScanner=new Scanner(csvFile);
    }
    public static Puzzle readCSV(puzInput){

    }
}
//AK
