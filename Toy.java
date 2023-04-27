import java.util.concurrent.ThreadLocalRandom;

/**
 * Toy
 */
public class Toy {
    protected static int id;

    protected final String NAME;
    protected final int ID;
    protected int count;
    protected int chance;

    static {
        Toy.id = 0;
    }

    public Toy(String name, int count) {
        this.NAME = name;
        this.count = count;
        this.chance = ThreadLocalRandom.current().nextInt(101);
        this.ID = ++Toy.id;
    }

    @Override
    public String toString() {
        return "Toy [NAME=" + NAME + ", ID=" + ID + ", count=" + count + ", chance=" + chance + "]";
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public void decCount() {
        this.count--;
    }

    public int getId() {
        return this.ID;
    }

    public int getCount() {
        return this.count;
    }

    public String getName() {
        return this.NAME;
    }

}