package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.*;

public class LibModel {
	
    private final static String FILENAME = "SongLib.txt";
    
    public static LibModel theModel = null;

    public static void setTheModel(LibModel inputModel) {
        theModel = inputModel;
    }

    public static LibModel getTheModel () {
        return theModel;
    }

    Map<String, Song> mapOfSongs        = null;
    ObservableList<Song> itemsInList    = null;


    public LibModel () {
        mapOfSongs = new HashMap<>();
        itemsInList = FXCollections.observableArrayList();
        //
        retrieveFromFile();
    }


    public int addSong(String name, String artist, String album, String year) {
        String key = Song.makeKey(name, artist);
        //
        Song oneSong = mapOfSongs.get(key);
        //
        if (oneSong == null) {
            oneSong = new Song(name, artist, album, year);
            mapOfSongs.put(key, oneSong);
            return indexInsertedSorted(oneSong);
        }
        else {
            return -1;
        }
    }


    public int updateSong(int index, String name, String artist, String album, String year) {
        String oldKey = itemsInList.get(index).getKey();
        String newKey = Song.makeKey(name, artist);
        //
        Song oneSongWithOldKey = mapOfSongs.get(oldKey);
        //
        if (oneSongWithOldKey == null) {
            return -1;
        }
        else {
            if (oldKey.equals(newKey)) {
                //oneSongWithOldKey.setName(name);          //not changed as keys are the same
                //oneSongWithOldKey.setArtist(artist);
                oneSongWithOldKey.setAlbum(album);
                oneSongWithOldKey.setYear(year);
                return index;
            }
            else {
                Song SongWithNewKey = mapOfSongs.get(newKey);
                if (SongWithNewKey == null) {
                    oneSongWithOldKey.setName(name);
                    oneSongWithOldKey.setArtist(artist);
                    oneSongWithOldKey.setAlbum(album);
                    oneSongWithOldKey.setYear(year);
                    //
                    mapOfSongs.remove(oldKey);
                    mapOfSongs.put(newKey, oneSongWithOldKey);
                    //
                    itemsInList.remove(index);
                    return indexInsertedSorted(oneSongWithOldKey);
                }
                else {
                    return -1;
                }
            }
        }
    }


    public boolean deleteSong (int index) {
        String key = itemsInList.get(index).getKey();
        //
        Song Song = mapOfSongs.get(key);
        //
        if (Song != null) {
            mapOfSongs.remove(key);
            itemsInList.remove(index);
            return true;
        }
        else {
            return false;
        }
    }


    private int indexInsertedSorted (Song input) {
        if (itemsInList.isEmpty()) {
            itemsInList.add(input);
            return 0;
        }
        else {
            for (int i=0; i < itemsInList.size(); i++) {
                if (input.compareTo(itemsInList.get(i)) < 0) {
                    itemsInList.add(i, input);
                    return i;
                }
            }
            //
            itemsInList.add(input);
            return itemsInList.size() - 1;
        }
    }


    public ObservableList<Song> getItemsInList() {
        return itemsInList;
    }

    public int getItemCount () {
        return itemsInList.size();
    }


    public void storeToFile () {
        try {
        	Writer writer = new FileWriter(FILENAME);
        	//
        	for (Song oneSong : mapOfSongs.values()) {
        		writer.write(oneSong.toStringForSave());
        	}
        	//
        	writer.close();
        }
        catch(IOException i) {
            i.printStackTrace();
        }
    }
    
    
    public void retrieveFromFile() {
        try {
        	String line;
        	//
        	FileReader fileReader = new FileReader(FILENAME);
        	BufferedReader bufferedReader = new BufferedReader(fileReader);
        	//
        	while ((line = bufferedReader.readLine()) != null ) {
        		String[] temp = line.split("\\t");
        		if (temp.length == 5) {
        			if (temp[4].equals("VERIFY_SONG_ENTRY")) {
        				addSong(temp[0], temp[1], temp[2], temp[3]);
        			}
        		}
        	}
        	//
        	bufferedReader.close();
        	fileReader.close();
        }
        catch(IOException i) {
            //i.printStackTrace();
        }
    }
}