/*
Author: Nicolas Rotondaro
*/

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Creating imaginary object to call methods with
        Main main = new Main();

        //Methods to run the script()
        main.removePreexisting();
        main.generateAlbum();
        main.runItBack();
    }

     //Converting text files into lists for java purposes
     public ArrayList generateLists(File fileName) {
        List generatedList = new ArrayList<>();

        try (BufferReader br = new BufferedReader(new FileReader(fileName))) {
            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                generatedList.add(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return generatedList;
    }

    //Deleting old files first
    public void removePreexisting() {
        if (todayList.exists()) {
            todayList.delete();
        } else {}
    }

    //Should replace file names with file location on local computer
    //Creating lists to be able to edit/write
    public List albumList = generateLists("Albums to Listen to at Work.txt");
    public List oldAlbumsList = generateLists("OldAlbums.txt");
    public List todayList = generateLists("Today.txt");

    //Random Number Generator
    public int getRandomNumber() {
        double randomNum = Math.random();
        randomNum = randomNum * albumList.size();
        int randomInt = (int) randomNum;
        return randomInt;
    }

    //Picking random album from list
    public String generateAlbum() {
        album = "";

        //Need to put filepath instead of name
        try (Stream<String> albumLines = Files.lines(Paths.get("Albums to Listen to at Work.txt"))) {
            album = albumLines.skip(getRandomNumber()).findFirst().get();
        }

        return album;
    }

    //Checking if album is present in oldAlbum list
    public void runItBack() {
        boolean oldSong = oldAlbumList.contains(album);

        //If present, find new album and try again
        if (oldSong == true) {
            generateAlbum();
            runItBack();
        } 
        //If not, add to todayList and oldAlbumsList
        else {
            oldAlbumsList.add(album);
            todayList.add(album);
        }
    }
}
