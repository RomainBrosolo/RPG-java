package rpg;
import java.util.ArrayList;
import java.util.Scanner;

abstract class PNJ{
    protected ArrayList<Item> stock = new ArrayList();
    protected String ascii = "$";
    protected int x;
    protected int y;
    public PNJ( int y, int x) {
      this.x = x -1 ;
      this.y = y -1;
    }
    public void addStock(Item... items){
      for (Item item : items) {
        this.stock.add(item);
    }
    }


    public  void marchand( Player p1) {
      System.out.println("Bienvenue chez le marchand d'armes");
      System.out.println("Vous avez "+p1.argent+" © ");
      
      afficherStock();
      System.out.println("Si vous voulez sortir, entrez deux fois 'e'  ");
      Scanner clavier = new Scanner(System.in);
      String nextMove = clavier.nextLine();
      int nextInt;  
      switch(nextMove){
        case "1":
         nextInt = Integer.parseInt(nextMove);  
          System.out.println(
            "vous voulez acheter " +
            this.stock.get(nextInt - 1).nom +
            " à " +
            this.stock.get(nextInt - 1).prix +
            " © ?  (y/n)"
          );
          nextMove = clavier.nextLine();
          switch(nextMove){
            case "y":
              if (p1.argent >= this.stock.get(nextInt - 1).prix) {
                p1.argent -= this.stock.get(nextInt - 1).prix;
                p1.inventory.add(this.stock.get(nextInt - 1));
                this.stock.remove(this.stock.get(nextInt - 1));
                System.out.println(
                  "Vous avez acheté : " +
                  p1.inventory.get(p1.inventory.size() - 1).nom
                  );
                System.out.println("Bonne journée");
              } else {
                System.out.println("Vous n'avez pas assez d'argent, bonne journée");
              }
              break;
              case "n":
              System.out.println("D'accord, nous vous souhaitons une bonne journée");
              break;
              default:
              System.out.println("Entrez un resultat valide svp, sortez puis re-rentrez dans le magasin");
              break;  
          }
          break;
        case "2":
        nextInt = Integer.parseInt(nextMove);  

        System.out.println(
            "vous voulez acheter " +
            this.stock.get(nextInt - 1).nom +
            " à " +
            this.stock.get(nextInt - 1).prix +
            " © ?  (y/n)"
          );
          nextMove = clavier.nextLine();
          switch(nextMove){
            case "y":
            if (p1.argent >= this.stock.get(nextInt - 1).prix) {
                p1.argent -= this.stock.get(nextInt - 1).prix;
                p1.inventory.add(this.stock.get(nextInt - 1));
                this.stock.remove(this.stock.get(nextInt - 1));
                System.out.println(
                  "Vous avez acheté : " +
                  p1.inventory.get(p1.inventory.size() - 1).nom
                );
                System.out.println("Bon courage");
              } else {
                System.out.println("Vous n'avez pas assez d'argent, bon courage");
              }
              break;
              case "n":
              System.out.println("D'accord, nous vous souhaitons une bon courage");
              break;
            default:
            System.out.println("Veuillez entrer un résultat valide svp, sortez puis re-rentrez dans le magasin");
            break;  
          }
          break;
          default:
            System.out.println("je n'ais pas compris !");
          break;
        }
         nextMove = clavier.nextLine();
        switch(nextMove){
          default:
            break;  
        }
    }
    public  void afficherStock() {
      System.out.println("Selectionner un Item à acheter : ");
      for (int i = 0; i < this.stock.size(); i++) {
        System.out.println(
          "(" +
          (i+1) +
          ") - " +
          this.stock.get(i).nom +
          " :  " +
          this.stock.get(i).prix +
          "©"
        );
      }
    }
}