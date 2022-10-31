import org.apache.commons.lang3.StringUtils;
import example.Access;

public class StaticCallExample {
    String id;
    int[] ints = new int[20];

    StaticCallExample(String id) {
        this.id = id;
        this.ints[0] = 3;
    }

    String STRING = "TEST";

    public static void main(String[] args) {
        StringUtils.isNotBlank("");
        System.out.println(Access.NORMAL);
        System.out.println(STRING.isEmpty());
    }
}