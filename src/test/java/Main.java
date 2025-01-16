import jpize.util.array.ObjectList;
import jpize.util.math.vector.Vec2f;

public class Main {

    public static void main(String[] args) {
        final ObjectList<Vec2f> list = new ObjectList<>();
        list.add(new Vec2f(1F));
        list.add(new Vec2f(2F));
        list.add(new Vec2f(3F));
        System.out.println(list);
    }

}
