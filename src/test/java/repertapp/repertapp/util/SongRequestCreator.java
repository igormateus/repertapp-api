package repertapp.repertapp.util;

import repertapp.repertapp.domain.Song;
import repertapp.repertapp.payload.SongRequest;

public class SongRequestCreator {
    
    public static SongRequest createToBeSaved() {
        SongRequest songRequest = new SongRequest();

        Song song = SongCreator.createToBeSaved();

        songRequest.setArtist(song.getArtist());
        songRequest.setName(song.getName());
        songRequest.setSpotifyLink(song.getSpotifyLink());
        songRequest.setTone(song.getTone());
        songRequest.setYoutubeLink(song.getYoutubeLink());
        
        return songRequest;
    }

    public static SongRequest createToBeSaved(String number) {
        SongRequest songRequest = new SongRequest();

        Song song = SongCreator.createToBeSaved(number);

        songRequest.setArtist(song.getArtist());
        songRequest.setName(song.getName());
        songRequest.setSpotifyLink(song.getSpotifyLink());
        songRequest.setTone(song.getTone());
        songRequest.setYoutubeLink(song.getYoutubeLink());
        
        return songRequest;
    }
}
