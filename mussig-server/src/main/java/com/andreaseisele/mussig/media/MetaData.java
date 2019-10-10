package com.andreaseisele.mussig.media;

import lombok.Data;

@Data
public class MetaData {

    private String artist;
    private String songTitle;
    private String track;
    private String albumName;
    private String year;
    private String comment;
    private String discNo;
    private String composer;

    private Integer trackLength;
    private Integer sampleRate;

}
