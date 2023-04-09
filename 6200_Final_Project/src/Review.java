public class Review {

    private final String country;
    private final int star;

    public Review(String c, int s) {
        country = c;
        star = s;
    }

    public String get_country() {
        return this.country;
    }

    public int get_star() {
        return this.star;
    }

}
