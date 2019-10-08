package com.andreaseisele.mussig.media;

import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;

import static com.google.common.truth.Truth.assertThat;

public class JatMetaDataScannerTest {

    private static final String TEST_ARTIST = "An Artist";
    private static final String TEST_TITLE = "Some Song";
    private static final String TEST_TRACK_NO = "1";
    private static final String TEST_ALBUM = "The Album";
    private static final String TEST_YEAR = "1950";
    private static final String TEST_COMMENT = "Some Comment";
    private static final String TEST_DISC_NO = "1";
    private static final String TEST_COMPOSER = "The Composer";

    private static final int TEST_LENGTH = 53;
    private static final int TEST_SAMPLE_RATE = 44100;

    @Test
    public void scanFile_id3v1() throws Exception {
        Path testMedia = getTestMedia("media_id3v1.mp3");

        JatMetaDataScanner scanner = new JatMetaDataScanner();
        MetaData metaData = scanner.scanFile(testMedia);

        assertBasicMetaData(metaData);
    }

    @Test
    public void scanFile_id3v2() throws Exception {
        Path testMedia = getTestMedia("media_id3v2.mp3");

        JatMetaDataScanner scanner = new JatMetaDataScanner();
        MetaData metaData = scanner.scanFile(testMedia);

        assertBasicMetaData(metaData);
        assertExtendedMetaData(metaData);
    }

    private void assertBasicMetaData(MetaData metaData) {
        assertThat(metaData.getArtist()).isEqualTo(TEST_ARTIST);
        assertThat(metaData.getSongTitle()).isEqualTo(TEST_TITLE);
        assertThat(metaData.getTrack()).isEqualTo(TEST_TRACK_NO);
        assertThat(metaData.getAlbumName()).isEqualTo(TEST_ALBUM);
        assertThat(metaData.getYear()).isEqualTo(TEST_YEAR);
        assertThat(metaData.getComment()).isEqualTo(TEST_COMMENT);
        assertThat(metaData.getTrackLength()).isEqualTo(TEST_LENGTH);
        assertThat(metaData.getSampleRate()).isEqualTo(TEST_SAMPLE_RATE);
    }

    private void assertExtendedMetaData(MetaData metaData) {
        assertThat(metaData.getDiscNo()).isEqualTo(TEST_DISC_NO);
        assertThat(metaData.getComposer()).isEqualTo(TEST_COMPOSER);
    }

    private Path getTestMedia(String name) throws URISyntaxException {
        return Path.of(getClass().getResource("/media/" + name).toURI());
    }

}