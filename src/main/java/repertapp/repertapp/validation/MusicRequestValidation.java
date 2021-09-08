package repertapp.repertapp.validation;

import javax.validation.Valid;

import repertapp.repertapp.domain.Band;
import repertapp.repertapp.domain.Music;
import repertapp.repertapp.domain.Song;
import repertapp.repertapp.exception.ResourceAlreadyExists;
import repertapp.repertapp.repository.MusicRepository;
import repertapp.repertapp.request.MusicPostRequestBody;
import repertapp.repertapp.request.MusicPutRequestBody;

public class MusicRequestValidation {

    private static MusicRepository repository;

    public static void valideAdd(@Valid MusicPostRequestBody musicRequest, MusicRepository musicRepository) {
        repository = musicRepository;

        checkMusicBandSongUnique(musicRequest.getBand(), musicRequest.getSong());
    }

    public static void valideUpdate(@Valid MusicPutRequestBody musicRequest, Music music,
            MusicRepository musicRepository) {
        repository = musicRepository;

        if (!(music.getBand().equals(musicRequest.getBand()) && music.getSong().equals(musicRequest.getSong())))
            checkMusicBandSongUnique(musicRequest.getBand(), musicRequest.getSong());
    }

    private static void checkMusicBandSongUnique(Band band, Song song) {
        if (repository.existsByBandAndSong(band, song))
            throw new ResourceAlreadyExists("Music", "band_id", band.getId(), "song_id", song.getId());
    }
}
