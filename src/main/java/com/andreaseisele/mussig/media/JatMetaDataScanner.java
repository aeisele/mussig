package com.andreaseisele.mussig.media;

import lombok.extern.slf4j.Slf4j;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import java.io.IOException;
import java.nio.file.Path;

@Slf4j
public class JatMetaDataScanner implements MetaDataScanner {

    @Override
    public MetaData scanFile(Path file) throws IOException {
        MetaData metaData = new MetaData();

        try {
            AudioFile audioFile = AudioFileIO.read(file.toFile());

            extractFromTags(metaData, audioFile.getTag());
            extractFromAudioHeader(metaData, audioFile.getAudioHeader());

        } catch (CannotReadException | TagException | ReadOnlyFileException | InvalidAudioFrameException e) {
            log.warn("error scanning meta data from [{}]", file, e);
        }

        return metaData;
    }

    private void extractFromTags(MetaData metaData, Tag tag) {
        metaData.setArtist(tag.getFirst(FieldKey.ARTIST));
        metaData.setAlbumName(tag.getFirst(FieldKey.ALBUM));
        metaData.setSongTitle(tag.getFirst(FieldKey.TITLE));
        metaData.setComment(tag.getFirst(FieldKey.COMMENT));
        metaData.setYear(tag.getFirst(FieldKey.YEAR));
        metaData.setTrack(tag.getFirst(FieldKey.TRACK));
        metaData.setDiscNo(tag.getFirst(FieldKey.DISC_NO));
        metaData.setComposer(tag.getFirst(FieldKey.COMPOSER));
    }

    private void extractFromAudioHeader(MetaData metaData, AudioHeader audioHeader) {
        metaData.setTrackLength(audioHeader.getTrackLength());
        metaData.setSampleRate(audioHeader.getSampleRateAsNumber());
    }

}
