package repertapp.repertapp.service;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import repertapp.repertapp.domain.Song;
import repertapp.repertapp.payload.SongResponse;
import repertapp.repertapp.repository.SongRepository;
import repertapp.repertapp.repository.TagRepository;
import repertapp.repertapp.util.SongCreator;
import repertapp.repertapp.util.SongRequestCreator;
import repertapp.repertapp.util.SongResponseCreator;
import repertapp.repertapp.util.TagCreator;

@ExtendWith(SpringExtension.class)
public class SongServiceTest {

    @InjectMocks
    private SongService songService;

    @Mock
    private SongRepository songRepositoryMock;

    @Mock
    private TagRepository tagRepositoryMock;

    @BeforeEach
    public void setUp() {
        PageImpl<Song> songPage = new PageImpl<>(List.of(SongCreator.createValid()));

        BDDMockito.when(songRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(songPage);

        BDDMockito.when(songRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(SongCreator.createValid()));

        BDDMockito.when(tagRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(TagCreator.createValid()));

        BDDMockito.when(songRepositoryMock.findByName(ArgumentMatchers.anyString(), ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(songPage);

        BDDMockito.when(songRepositoryMock.findByNameAndArtist(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(SongCreator.createValid()));

        BDDMockito.when(songRepositoryMock.findByYoutubeLink(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(SongCreator.createValid()));

        BDDMockito.when(songRepositoryMock.findBySpotifyLink(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(SongCreator.createValid()));

        BDDMockito.when(songRepositoryMock.findDistinctSongsByTagsIn(ArgumentMatchers.anyList(), ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(songPage);

        BDDMockito.when(songRepositoryMock.save(ArgumentMatchers.any(Song.class)))
                .thenReturn(SongCreator.createValid());

        BDDMockito.doNothing().when(songRepositoryMock).delete(ArgumentMatchers.any(Song.class));

        BDDMockito.when(songRepositoryMock.existsByNameAndArtist(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(Boolean.TRUE);

        BDDMockito.when(songRepositoryMock.existsByYoutubeLink(ArgumentMatchers.anyString()))
                .thenReturn(Boolean.TRUE);

        BDDMockito.when(songRepositoryMock.existsBySpotifyLink(ArgumentMatchers.anyString()))
                .thenReturn(Boolean.TRUE);
    }

    @Test
    @DisplayName("getAllSongs returns a list of songs inside page object when successful")
    void getAllSongs_returnsListOfSongsInsidePageObject_whenSuccessful() {
        Page<Song> songPage = songService.getAllSongs(PageRequest.of(1, 1));
        
        Assertions.assertThat(songPage).isNotNull();
        Assertions.assertThat(songPage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(songPage.getContent().get(0))
                .isEqualTo(SongCreator.createValid());
    }

    @Test
    @DisplayName("getSong returns a song when successful")
    void getSong_returnsSong_whenSuccessful() {
        Song song = songService.getSong(SongCreator.createValid().getId());
        
        Assertions.assertThat(song).isNotNull();
        Assertions.assertThat(song.getId())
                .isNotNull()
                .isEqualTo(SongCreator.createValid().getId());
    }

    @Test
    @DisplayName("addSong returns a list of song inside page object when successful")
    void addSong_returnsListOfSongsInsidePageObject_whenSuccessful() {
        SongResponse songResponse = songService.addSong(SongRequestCreator.createToBeSaved());

        Assertions.assertThat(songResponse)
                .isNotNull()
                .isEqualTo(SongResponseCreator.createValid());
    }

    @Test
    @DisplayName("updateSong updates song when successful")
    void updateSong_updatesSong_whenSuccessful() {
        Assertions.assertThatCode(() -> songService.updateSong(
                SongCreator.createValid().getId(),
                SongRequestCreator.createToBeSaved()
        )).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("deleteSong removes song when successful")
    void deleteSong_removesSong_whenSuccessful() {
        Assertions.assertThatCode(() -> songService.deleteSong(SongCreator.createValid().getId()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("getSongsByTag returns a list of songs inside page object when successful")
    void getSongsByTag() {
        Page<Song> songPage = songService.getSongsByTag(List.of(TagCreator.createValid()), PageRequest.of(1, 1));
        
        Assertions.assertThat(songPage).isNotNull();
        Assertions.assertThat(songPage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(songPage.getContent().get(0))
                .isEqualTo(SongCreator.createValid());
    }
}
