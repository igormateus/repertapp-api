package repertapp.repertapp.validation;

import repertapp.repertapp.domain.Song;
import repertapp.repertapp.exception.ResourceAlreadyExists;
import repertapp.repertapp.repository.SongRepository;
import repertapp.repertapp.request.SongPostRequestBody;
import repertapp.repertapp.request.SongPutRequestBody;
import repertapp.repertapp.util.Util;

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
