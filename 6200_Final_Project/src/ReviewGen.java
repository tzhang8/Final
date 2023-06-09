import java.util.ArrayList;
import java.util.Random;

public class ReviewGen {

    private final String[] country = {"USA", "UK", "China", "India", "Australia", "Canada", "Mexico", "Japan", "Korea", "Thailand", "Russia"};
    private final int[] stars = {0, 1, 2, 3, 4, 5};
    public ReviewGen() {}
    public ArrayList<Review> generate_review() {

        ArrayList<Review> result = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            int c = rand.nextInt(11);
            int s = rand.nextInt(6);

            if (this.stars[s] < 3)
                result.add(new Neg_Review(this.country[c], this.stars[s]));
            else
                result.add(new Pos_Review(this.country[c], this.stars[s]));

        }
        return result;
    }

}
