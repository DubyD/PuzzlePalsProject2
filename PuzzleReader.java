//AK
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
public class PuzzleReader{
    private static String csvFilePath="./rolodex.csv";
    private static String labelDataFilePath="./labelHeading.csv";
    private static File csvFile=new File(csvFilePath);
    private static File labelData = new File(labelDataFilePath);
    public static String readCSV(String puzInput){
        //puzInput i.e = "3x4"
        String categories; String items_in_categories; 
        Integer counter = 0;
        ArrayList<String> topHeaders = new ArrayList<>();
        ArrayList<String> leftHeaders = new ArrayList<>();
        ArrayList<ArrayList<String>> topData = new ArrayList<>();
        ArrayList<ArrayList<String>> leftData = new ArrayList<>();
        ArrayList<String> clues = new ArrayList<String>();
        try{
            Scanner csvScanner=new Scanner(csvFile);
            String[] inputCoordinates=puzInput.split("x");
            // inputCoordinates = i.e  [4,5];
            while(csvScanner.hasNext()){
                String lineRead = csvScanner.nextLine();
                if(counter==0){
                    counter++;
                    continue;
                }
                String[] splitLine = lineRead.split(",");
                // splitLine contains [row,col,clues...]
                char[] userPuzzleDimensions = puzInput.toCharArray();
                categories = splitLine[0];
                items_in_categories=splitLine[1];
                if(categories.equals(inputCoordinates[0]) && items_in_categories.equals(inputCoordinates[1])){ // this line contains our coordinates.
                    for(String x : splitLine){
                        if(x.length()>2){
                            clues.add(x);
                        }
                    }
                }
            }
            Scanner labelScanner = new Scanner(labelData);
            counter=0;
            while(labelScanner.hasNext()){
                String lineRead = labelScanner.nextLine();
                if(counter==0){
                    counter++;
                    continue;
                }
                String[] splitLine=lineRead.split(",");
                //= row,column,top or left, data true or false, headers.....
                String row=inputCoordinates[0];
                String column=inputCoordinates[1];
                String direction=splitLine[2];
                String dataBoolean=splitLine[3];
                // headers / data onwards from 4.
                if(row.equals(splitLine[0])&&column.equals(splitLine[1])){
                    // this is the line(s) we need.
                    if(direction.equals("top")&&dataBoolean.equals("false")){ // = top header
                        for(int i = 4;i<splitLine.length;i++){
                            topHeaders.add(splitLine[i]);

                        }
                    } else if(direction.equals("left")&&dataBoolean.equals("false")){ // = left header
                        for(int i=4;i<splitLine.length;i++){
                            leftHeaders.add(splitLine[i]);
                        }
                    } else if(direction.equals("top")&&dataBoolean.equals("true")){
                        ArrayList<String> currentString = new ArrayList<>();
                        for(int i=4;i<splitLine.length;i++){
                            if(!splitLine[i].equals("splitter")){ // while it's not splitter.
                                currentString.add(splitLine[i]);
                            }
                            if(splitLine[i].equals("splitter")){
                                // it's splitter
                                topData.add(new ArrayList<>(currentString));
                                currentString.clear();
                            }
                        }
                    } else if(direction.equals("left")&&dataBoolean.equals("true")){
                        ArrayList<String> currentString = new ArrayList<>();
                        for(int i=4;i<splitLine.length;i++){
                            if(!splitLine[i].equals("splitter")){ // while it's not splitter.
                                currentString.add(splitLine[i]);
                            }
                            if(splitLine[i].equals("splitter")){
                                // it's splitter
                                leftData.add(new ArrayList<>(currentString));
                                currentString.clear();
                            }
                        }
                    }

                }
            }
            System.out.println(topHeaders);
            System.out.println(topData);
            System.out.println("\n");
            System.out.println(leftHeaders);
            System.out.println(leftData);
            System.out.println("\n");
            System.out.println(clues);

            // make the first label null/empty for empty cell parsing. - wd
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        return("h"); // testing
    }
    public static String[] getAllSizes(){
        String [] sizesList = {"3x4","3x5","4x4","4x5"};
        return(sizesList);
    }
}
//AK
