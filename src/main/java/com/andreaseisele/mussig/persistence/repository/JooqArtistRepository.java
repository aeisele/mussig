package com.andreaseisele.mussig.persistence.repository;

import com.andreaseisele.mussig.persistence.model.Artist;
import com.andreaseisele.mussig.persistence.model.tables.records.ArtistsRecord;
import org.jooq.DSLContext;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.andreaseisele.mussig.persistence.model.tables.Artists.ARTISTS;
import static java.util.stream.Collectors.toUnmodifiableList;

@Transactional(readOnly = true)
@Repository
public class JooqArtistRepository implements ArtistRepository {

    private final DSLContext dsl;

    private final ModelMapper mapper;

    public JooqArtistRepository(DSLContext dsl, ModelMapper mapper) {
        this.dsl = dsl;
        this.mapper = mapper;
    }

    @Override
    public Optional<Artist> findById(Long id) {
        return dsl.selectFrom(ARTISTS).where(ARTISTS.ID.eq(id)).fetchOptional().map(toArtist());
    }

    @Override
    public List<Artist> findAll() {
        var query = dsl.selectFrom(ARTISTS);
        try (var stream = query.stream()) {
            return stream.map(toArtist()).collect(toUnmodifiableList());
        }
    }

    @Transactional
    @Override
    public Artist saveOrUpdate(Artist entity) {
        final ArtistsRecord record = dsl.newRecord(ARTISTS);
        record.setName(entity.getName());

        record.store();

        return toArtist().apply(record);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        dsl.deleteFrom(ARTISTS).where(ARTISTS.ID.eq(id)).execute();
    }

    private Function<ArtistsRecord, Artist> toArtist() {
        return r -> mapper.map(r, Artist.class);
    }

}
