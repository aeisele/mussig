package com.andreaseisele.mussig.media;

import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;

public class JatMetaDataScannerTest {

    private static final String TEST_ARTIST = "An Artist";
    private static final String TEST_TITLE = "Some Song";
    private static final String TEST_TRACK_NO = "1";
    private static final String TEST_ALBUM = "The Album";
    private static final String TEST_YEAR = "1950";
    private static final String TEST_COMMENT = "Some Comment";

    private static final int TEST_LENGTH = 42;
    private static final int TEST_SAMPLE_RATE = 44100;

    @Test
    public void scanFile_id3v1() throws Exception {
        Path testMedia = getTestMedia("media_id3v1.mp3");
        JatMetaDataScanner scanner = new JatMetaDataScanner();
        MetaData metaData = scanner.scanFile(testMedia);
    }

    private Path getTestMedia(String name) throws URISyntaxException {
        return Path.of(getClass().getResource("/media/" + name).toURI());
    }

}