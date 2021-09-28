package repertapp.repertapp.domain.music;

import javax.validation.Valid;

import repertapp.repertapp.core.exception.ResourceAlreadyExists;
import repertapp.repertapp.core.util.Util;

public class MusicRequestValidation {

    private static MusicRepository repository;

    public static void valideAdd(@Valid MusicPostRequestBody musicRequest, MusicRepository musicRepository) {
        repository = musicRepository;

        checkSongArtistNameUnique(musicRequest.getName(), musicRequest.getArtist());
        
        if (!Util.isNullOrEmpty(musicRequest.getYoutubeLink()))
            checkYoutubeLinkUnique(musicRequest.getYoutubeLink());

        if (!Util.isNullOrEmpty(musicRequest.getSpotifyLink()))
            checkSpotifyLinkUnique(musicRequest.getSpotifyLink());
    }

    public static void valideUpdate(@Valid MusicPutRequestBody musicRequest, Music music,
            MusicRepository musicRepository) {
        repository = musicRepository;

        if (!(music.getArtist().equals(musicRequest.getArtist()) && music.getName().equals(musicRequest.getName())))
            checkSongArtistNameUnique(musicRequest.getName(), musicRequest.getArtist());

        if (!Util.isNullOrEmpty(musicRequest.getYoutubeLink()) && !music.getYoutubeLink().equals(musicRequest.getYoutubeLink()))
            checkYoutubeLinkUnique(musicRequest.getYoutubeLink());
        
        if (!Util.isNullOrEmpty(musicRequest.getSpotifyLink()) && music.getSpotifyLink().equals(musicRequest.getSpotifyLink()))
            checkSpotifyLinkUnique(musicRequest.getSpotifyLink());
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
