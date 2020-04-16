public class Chart {
    int place;
    int album_id;
    String album_name;
    String artist_name;

    public Chart() {}

    public Chart(int place, int album_id, String album_name, String artist_name) {
        this.place = place;
        this.album_id = album_id;
        this.album_name = album_name;
        this.artist_name = artist_name;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }
}
