package modelTest;
import tqs.prac.Main;
import tqs.prac.model.Board;
import tqs.prac.model.Cell;
import tqs.prac.model.GenRandom;
public class MockGenRandom extends GenRandom {

    private int[] values;
    private int index = 0;

    // Recibe la secuencia de valores que quieres devolver
    public MockGenRandom(int... values) {
        this.values = values;
    }

    @Override
    public int nextInt(int bound) {
        if (index >= values.length) {
            throw new IllegalStateException("MockGenRandom: no queden valores per retornar");
        }
        int retorn = values[index];
        index++;
        return retorn;

    }
}
