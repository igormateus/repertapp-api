package repertapp.repertapp.validation;

import repertapp.repertapp.exception.ResourceAlreadyExists;
import repertapp.repertapp.payload.SongRequest;
import repertapp.repertapp.repository.SongRepository;

public class SongRequestUniqueValidation {

    public static void valide(SongRequest songRequest, SongRepository songRepository) {

        // Check song_artist_name_unique
        if (!songRepository.findByNameAndArtist(songRequest.getName(), songRequest.getArtist()).isEmpty()) 
            throw new ResourceAlreadyExists("Song", "name", songRequest.getName(), "artist", songRequest.getArtist());

        // Check song_youtube_link_unique
        if (!isNullOrEmpty(songRequest.getYoutubeLink()) && (!songRequest.getYoutubeLink().isEmpty()) && (!songRepository.findByYoutubeLink(songRequest.getYoutubeLink()).isEmpty())) 
            throw new ResourceAlreadyExists("Song", "Youtube Link", songRequest.getYoutubeLink());

        // Check song_spotify_link_unique
        if (!isNullOrEmpty(songRequest.getSpotifyLink()) && (!songRepository.findBySpotifyLink(songRequest.getSpotifyLink()).isEmpty())) 
            throw new ResourceAlreadyExists("Song", "Spotify Link", songRequest.getSpotifyLink());
    }

    private static boolean isNullOrEmpty(String value) {
        if ((value != null) && (!value.isEmpty()))
            return false;

        return true;
    }
    
}
