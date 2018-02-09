package application;

public class Song {
	
	public String name;
	public String artist;
	public String album;
	public String year;
	
	 public static String makeKey(String name, String artist) {
	        return (name + "\t" + artist).toUpperCase();
	    }
	
	public Song (String name, String artist, String album, String year){
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
			
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getArtist(){
		return artist;
	}
	
	public void setArtist(String artist){
		this.artist = artist;
	}
	
	public String getAlbum(){
		return album;
	}
	
	public void setAlbum(String album){
		this.album = album;
	}
	
	public String getYear(){
		return year;
	}
	
	public void setYear(String year){
		this.year = year;
	}
	
	public String toString(){
		return "Song name: " + name + " Artist: " + artist + " Album: " + album + " Year: " + year;
	}
	
	public String toStringForSave() {
        return name + "\t" + artist + "\t" + album + "\t" + year  + "\t" + "VERIFY_SONG_ENTRY\n";
    }

	
	 public String getKey () {
	        return makeKey(name, artist);
	    }
	
	 public int compareTo(Song Song) {
	        return this.getKey().compareTo(Song.getKey());
	    }
	
	

}
