package com.andreaseisele.mussig.persistence.repository;

import com.andreaseisele.mussig.persistence.model.tables.daos.ArtistsDao;
import com.andreaseisele.mussig.persistence.model.tables.pojos.Artist;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.truth.Truth.assertThat;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class JooqArtistsDaoIntegrationTest {

    @Autowired
    private ArtistsDao dao;

    @Test
    public void testSaveNewRecordAndRetrieveIt() {
        Artist artist = newArtist();

        dao.insert(artist);
        Artist saved = dao.findById(artist.getId());
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo(artist.getName());
    }

    @Test
    public void testSaveNewRecordAndUpdateIt() {
        Artist artist = newArtist();
        dao.insert(artist);

        artist.setName("updated artist");
        dao.update(artist);

        Artist found = dao.findById(artist.getId());
        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(artist.getId());
        assertThat(found.getName()).isEqualTo(artist.getName());
    }

    private Artist newArtist() {
        Artist artist = new Artist();
        artist.setName("test name");
        return artist;
    }

}