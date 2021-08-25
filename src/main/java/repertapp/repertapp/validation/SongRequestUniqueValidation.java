package repertapp.repertapp.validation;

import repertapp.repertapp.domain.Song;
import repertapp.repertapp.exception.ResourceAlreadyExists;
import repertapp.repertapp.payload.SongRequest;
import repertapp.repertapp.repository.SongRepository;

public class SongRequestUniqueValidation {

    private static SongRepository repository;

    public static void validePost(SongRequest songRequest, SongRepository songRepository) {
        repository = songRepository;

        checkSongArtistNameUnique(songRequest.getName(), songRequest.getArtist());
        
        checkYoutubeLinkUnique(songRequest.getYoutubeLink());

        checkSpotifyLinkUnique(songRequest.getSpotifyLink());      
        
    }

    public static void valideUpdate(Song song, SongRequest newSongRequest, SongRepository songRepository) {
        repository = songRepository;

        if (!(song.getArtist().equals(newSongRequest.getArtist()) && song.getName().equals(newSongRequest.getName())))
            checkSongArtistNameUnique(newSongRequest.getName(), newSongRequest.getArtist());

        if (newSongRequest.getYoutubeLink() != null && !song.getYoutubeLink().equals(newSongRequest.getYoutubeLink()))
            checkYoutubeLinkUnique(newSongRequest.getYoutubeLink());
        
        if (newSongRequest.getSpotifyLink() != null && song.getSpotifyLink().equals(newSongRequest.getYoutubeLink()))
            checkSpotifyLinkUnique(newSongRequest.getSpotifyLink());
    }

    private static void checkSongArtistNameUnique(String name, String artist) {
        if (!repository.findByNameAndArtist(name, artist).isEmpty()) 
            throw new ResourceAlreadyExists("Song", "name", name, "artist", artist);
    }

    private static void checkYoutubeLinkUnique(String youtubeLink) {
        if (!isNullOrEmpty(youtubeLink) && (!youtubeLink.isEmpty()) && (!repository.findByYoutubeLink(youtubeLink).isEmpty())) 
            throw new ResourceAlreadyExists("Song", "Youtube Link", youtubeLink);
    }

    private static void checkSpotifyLinkUnique(String spotifyLink) {
        if (!isNullOrEmpty(spotifyLink) && (!spotifyLink.isEmpty()) && (!repository.findByYoutubeLink(spotifyLink).isEmpty())) 
            throw new ResourceAlreadyExists("Song", "Spotify Link", spotifyLink);
    }

    private static boolean isNullOrEmpty(String value) {
        if ((value != null) && (!value.isEmpty()))
            return false;

        return true;
    }

    
    
}
