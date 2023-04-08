public class User implements Comparable<User>{

    final private String name;
    private String password;
    public User(String n, String p) {
        name = n;
        password = p;
    }

    public String get_name() {
        return this.name;
    }

    public String get_password() {
        return this.password;
    }

    public void set_password(String new_p) {
        this.password = new_p;
    }

    @Override
    public int compareTo(User o) {

        if (this.name.equals(o.get_name()) && this.password.equals(o.get_password()))
            return 0;
        else if (this.name.equals(o.get_name()))
            return -1;
        else
            return 1;
    }
}
