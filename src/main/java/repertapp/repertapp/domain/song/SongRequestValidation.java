package repertapp.repertapp.domain.song;

import repertapp.repertapp.core.util.Util;
import repertapp.repertapp.domain.exception.ResourceAlreadyExists;

public class SongRequestValidation {

    private static SongRepository repository;

    public static void valideAdd(SongPostRequestBody songRequest, SongRepository songRepository) {
        repository = songRepository;

        checkSongArtistNameUnique(songRequest.getName(), songRequest.getArtist());
        
        if (!Util.isNullOrEmpty(songRequest.getYoutubeLink()))
            checkYoutubeLinkUnique(songRequest.getYoutubeLink());

        if (!Util.isNullOrEmpty(songRequest.getSpotifyLink()))
            checkSpotifyLinkUnique(songRequest.getSpotifyLink());
    }

    public static void valideUpdate(SongPutRequestBody songRequest, Song song, SongRepository songRepository) {
        repository = songRepository;

        if (!(song.getArtist().equals(songRequest.getArtist()) && song.getName().equals(songRequest.getName())))
            checkSongArtistNameUnique(songRequest.getName(), songRequest.getArtist());

        if (!Util.isNullOrEmpty(songRequest.getYoutubeLink()) && !song.getYoutubeLink().equals(songRequest.getYoutubeLink()))
            checkYoutubeLinkUnique(songRequest.getYoutubeLink());
        
        if (!Util.isNullOrEmpty(songRequest.getSpotifyLink()) && song.getSpotifyLink().equals(songRequest.getSpotifyLink()))
            checkSpotifyLinkUnique(songRequest.getSpotifyLink());
    }

    private static void checkSongArtistNameUnique(String name, String artist) {
        if (repository.existsByNameAndArtist(name, artist))
            throw new ResourceAlreadyExists("Song", "name", name, "artist", artist);
    }

    private static void checkYoutubeLinkUnique(String youtubeLink) {
        if (repository.existsByYoutubeLink(youtubeLink))
            throw new ResourceAlreadyExists("Song", "Youtube Link", youtubeLink);
    }

    private static void checkSpotifyLinkUnique(String spotifyLink) {
        if (repository.existsBySpotifyLink(spotifyLink)) 
            throw new ResourceAlreadyExists("Song", "Spotify Link", spotifyLink);
    }
    
}
