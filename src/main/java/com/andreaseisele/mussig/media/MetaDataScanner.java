package com.andreaseisele.mussig.media;

import java.io.IOException;
import java.nio.file.Path;

public interface MetaDataScanner {

    MetaData scanFile(Path file) throws IOException;

}
