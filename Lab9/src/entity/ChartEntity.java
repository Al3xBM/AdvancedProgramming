package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(
                name="findByArtist",
                query = "select c from ChartEntity c where c.artistName = :artname"),
        @NamedQuery(
                name="artistRankings",
                query = "select c.artistName from ChartEntity c order by place"),
        @NamedQuery(
                name="albumByID",
                query = "select c from ChartEntity c where c.id = :id"),

})
@Table(name = "CHART", schema = "MUSICALBUMS", catalog = "")
public class ChartEntity {
    private Long place;
    private String albumName;
    private String artistName;

    @Id
    @Column(name = "PLACE")
    public Long getPlace() {
        return place;
    }

    public void setPlace(Long place) {
        this.place = place;
    }

    @Basic
    @Column(name = "ALBUM_NAME")
    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    @Basic
    @Column(name = "ARTIST_NAME")
    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChartEntity that = (ChartEntity) o;
        return Objects.equals(place, that.place) &&
                Objects.equals(albumName, that.albumName) &&
                Objects.equals(artistName, that.artistName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, albumName, artistName);
    }
}
