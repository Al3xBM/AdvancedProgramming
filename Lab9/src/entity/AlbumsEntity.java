package entity;

import javax.persistence.Id;
import java.util.Objects;

@javax.persistence.Entity
@javax.persistence.Table(name = "ALBUMS", schema = "MUSICALBUMS")
public class AlbumsEntity {
    private long id;
    private String name;
    private Long releaseYear;
    private long artist_id;

    @Id
    @javax.persistence.Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "ARTIST_ID")
    public long getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(long artist_id) {
        this.artist_id = artist_id;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "RELEASE_YEAR")
    public Long getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Long releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlbumsEntity that = (AlbumsEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (releaseYear != null ? !releaseYear.equals(that.releaseYear) : that.releaseYear != null) return false;
        if (artist_id != that.artist_id) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseYear, artist_id);
    }
}
