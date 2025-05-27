package utils;
import components.*;

public class Menu {

    private ArrayList<Produto> produtos = new ArrayList<>();

    public Menu() {
        Paiero Paiero = new Paiero();
        produtos.add(Paiero);
        Cigarro Cigarro = new Cigarro();
        produtos.add(Cigarro);
        Marijuana Marijuana = new Marijuana();
        produtos.add(Marijuana);
    }

    public void spawn() {
        letterSpawn();
        int op = -1;
        do {
            switch (op) {
                case 1:

                    break;

                default:
                    break;
            }
        } while (op!=0);
    }

    public void letterSpawn() {
        System.out.println(".___. .                      .     .                   ");
        System.out.println("  | _.|_  _. _. _.._.* _.   _| _   |   . . _.* _.._  _ ");
        System.out.println("  |(_][_)(_](_.(_][  |(_]  (_](/,  |___(_|(_.|(_][ )(_)");
    }

}
