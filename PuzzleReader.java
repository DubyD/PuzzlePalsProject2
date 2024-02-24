//AK
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
    private static File file3x4=new File(path3x4);
    private static File file4x4=new File(path4x4);
    private static File file4x5=new File(path4x5);
    public static String readCSV(String puzInput){
        //puzInput i.e = "3x4"
        String categories; String items_in_categories; 
        Integer counter = 0;
        ArrayList<String> topHeaders = new ArrayList<>();
        ArrayList<String> leftHeaders = new ArrayList<>();
        ArrayList<ArrayList<String>> topData = new ArrayList<>();
        ArrayList<ArrayList<String>> leftData = new ArrayList<>();
        ArrayList<String> clues = new ArrayList<String>();
        ArrayList<String[][]> puzzleSections = new ArrayList<>();
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

            String[][] currentSection = new String[Integer.parseInt(inputCoordinates[1])][Integer.parseInt(inputCoordinates[1])];
            int parseCounter=0;
            while(sectionScanner.hasNext()){
                String currentLine = sectionScanner.nextLine();
                if(currentLine.equals("")){
                    puzzleSections.add(currentSection);
                    System.out.println("reached empty.");
                    parseCounter=0;
                    currentSection=new String[Integer.parseInt(inputCoordinates[1])][Integer.parseInt(inputCoordinates[1])]; // clear the array.
                    continue;
                }
                String[] currentLineParsed = currentLine.split(",");
                //^^ => i.e [X,X,O,X,X];
                /*{1, 2, 3}, [[0,0],[0,1],0,2]
                  {4, 5, 6}, [[1,0],[1,1],[1,2]]
                  {7, 8, 9} */ //[2,0],[2,1],[2,2]
                for(int i=0;i<currentLineParsed.length;i++){
                    currentSection[parseCounter][i]=currentLineParsed[i];
                }
                parseCounter++;
            }
            puzzleSections.add(currentSection); // add after EOF :)

              //Print all sections {testing}
            for(String[][] x: puzzleSections){
                for(int i=0;i<x.length;i++){
                    System.out.println(x[i][0]+x[i][1]+x[i][2]+x[i][3]+x[i][4]+"\n");
                }
            }




            /*System.out.println(topHeaders);
            System.out.println(topData);
            System.out.println("\n");
            System.out.println(leftHeaders);
            System.out.println(leftData);
            System.out.println("\n");
            System.out.println(clues);*/
            // make the first label null/empty for empty cell parsing. - wd
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        return("h"); // temporary, should return a Puzzle.
    }




    public static String[] getAllSizes(){
        String [] sizesList = new String[4];
        try{
            Scanner csvScanner=new Scanner(csvFile);
            int counter=0;
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
                sizesList[counter]=firstNum.concat("x").concat(secondNum);
                counter++;
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        return(sizesList);
    }
}
//AK
