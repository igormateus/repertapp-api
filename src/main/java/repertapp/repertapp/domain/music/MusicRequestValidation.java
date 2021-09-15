package repertapp.repertapp.domain.music;

import javax.validation.Valid;

import repertapp.repertapp.domain.band.Band;
import repertapp.repertapp.domain.exception.ResourceAlreadyExists;
import repertapp.repertapp.domain.song.Song;

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
