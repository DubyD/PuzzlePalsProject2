/*
 *  Ahmed Khan
 * Program constructs a puzzle based on clues, labels, answer keys.
 */
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
public class PuzzleReader{
    private static String csvFilePath="./csv/rolodex.csv";
    private static String labelDataFilePath="./csv/labelHeading.csv";
    private static File csvFile=new File(csvFilePath);
    private static File labelData = new File(labelDataFilePath);
    private static String path3x4 = "./csv/3x4answerKey.csv";
    private static String path4x4 = "./csv/4x4answerKey.csv";
    private static String path4x5 = "./csv/4x5answerKey.csv";
    private static String path3x5 = "./csv/3x5answerKey.csv";
    private static File file3x4=new File(path3x4);
    private static File file4x4=new File(path4x4);
    private static File file4x5=new File(path4x5);
    private static File file3x5=new File(path3x5);


    private static String categories; 
    private static String items_in_categories; 
    private static Integer counter = 0;

    private static ArrayList<String> topHeaders = new ArrayList<>();
    // topHeaders = i.e [customers,guides]
    private static ArrayList<String> leftHeaders = new ArrayList<>();
    // same logic as topHeaders
    private static ArrayList<ArrayList<String>> topData = new ArrayList<>();
    // topData => [[customerdata,customerdata],[guidesdata,guidesdata]]
    private static ArrayList<ArrayList<String>> leftData = new ArrayList<>();
    // same logic as topData

    private static String leftInput;
    private static String rightInput;


    private static ArrayList<String> clues = new ArrayList<String>();
    private static ArrayList<String[][]> puzzleSections = new ArrayList<>();
    public static Puzzle readCSV(String puzInput){
        //puzInput i.e = "3x4"
        try{
            Scanner csvScanner=new Scanner(csvFile);
            String[] inputCoordinates=puzInput.split("x");
            // inputCoordinates = i.e  [4,5];
            // This is parsing what the user input.
            leftInput = inputCoordinates[0];
            rightInput=inputCoordinates[1];
            while(csvScanner.hasNext()){
                String lineRead = csvScanner.nextLine();
                if(counter==0){
                    counter++;
                    continue;
                }
                String[] splitLine = lineRead.split(",");
                // splitLine contains [row,col,clues...]
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
                    continue; // if it's the first line we should skip it.
                }
                String[] splitLine=lineRead.split(",");
                //= row,column,top or left, data true or false, headers.....
                String row=inputCoordinates[0];
                String column=inputCoordinates[1];
                String direction=splitLine[2];
                String dataBoolean=splitLine[3];
                // parsing of Header and Header Data
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

            // Depending on user Input we decide which csv file to scan.
            Scanner sectionScanner=null;
            if(inputCoordinates[0].equals("3")&&inputCoordinates[1].equals("4")){
                sectionScanner=new Scanner(file3x4);
            }
            if(inputCoordinates[0].equals("4")&&inputCoordinates[1].equals("4")){
                sectionScanner = new Scanner(file4x4);
            }
            if(inputCoordinates[0].equals("4")&&inputCoordinates[1].equals("5")){
                sectionScanner = new Scanner(file4x5);
            }
            if(inputCoordinates[0].equals("3")&&inputCoordinates[1].equals("5")){
                sectionScanner = new Scanner(file3x5);
            }

            // Scanning for answer key.
            String[][] currentSection = new String[Integer.parseInt(inputCoordinates[1])][Integer.parseInt(inputCoordinates[1])];
            int parseCounter=0;
            while(sectionScanner.hasNext()){
                String currentLine = sectionScanner.nextLine();
                if(currentLine.equals("")){
                    puzzleSections.add(currentSection);
                    parseCounter=0;
                    currentSection=new String[Integer.parseInt(inputCoordinates[1])][Integer.parseInt(inputCoordinates[1])]; // clear the array.
                    continue;
                }
                String[] currentLineParsed = currentLine.split(",");
                for(int i=0;i<currentLineParsed.length;i++){
                    currentSection[parseCounter][i]=currentLineParsed[i];
                }
                parseCounter++;
            }
            puzzleSections.add(currentSection); // add after EOF :)

              //Print all sections {testing}
            /*for(String[][] x: puzzleSections){
                for(int i=0;i<x.length;i++){
                    System.out.println(x[i][0]+x[i][1]+x[i][2]+x[i][3]+x[i][4]+"\n");
                }
            }*/
            /* 
            System.out.println(topHeaders);
            System.out.println(topData);
            System.out.println("\n");
            System.out.println(leftHeaders);
            System.out.println(leftData);
            System.out.println("\n");
            System.out.println(clues);*/
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }

        Puzzle returnPuzzle = new Puzzle(Integer.parseInt(leftInput),Integer.parseInt(rightInput));
        return(returnPuzzle);
    }


    public static ArrayList<String> getAllSizes(){
        ArrayList<String> sizesList = new ArrayList<>();
        try{
            Scanner csvScanner=new Scanner(csvFile);
            while(csvScanner.hasNext()){
                String csvLineRead = csvScanner.nextLine();
                String[] csvLineSplit = csvLineRead.split(",");
                String firstNum=csvLineSplit[0];
                try{
                    int x = Integer.parseInt(firstNum); // skip iteration if it's not a number.
                }catch(Exception e){
                    continue;
                }
                String secondNum=csvLineSplit[1];
                sizesList.add(firstNum.concat("x").concat(secondNum));
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        return(sizesList);
    }


    public static ArrayList<String[][]> getAnswerKey(){
      ArrayList<String[][]> ansKeyReturn = new ArrayList<>();
      ansKeyReturn.addAll(puzzleSections);
      puzzleSections.clear();
      return(ansKeyReturn);
    }

    public static ArrayList<String> getClues(){
            //Resets the Clue variable for multiple puzzles
        ArrayList<String> reply = new ArrayList<>();
        reply.addAll(clues);
        clues.removeAll(reply);
        return(reply);
    }
    public static ArrayList<ArrayList<String>> getTopData(){
        return(topData);
    }
    public static ArrayList<ArrayList<String>> getLeftData(){
        return(leftData);
    }
}
//AK
