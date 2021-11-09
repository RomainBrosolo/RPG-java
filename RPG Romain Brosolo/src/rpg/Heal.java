package rpg;

abstract class Heal {
    protected String ascii = "♥";
    protected int x;
    protected int y;
    
    public Heal(int y, int x) {
      this.x = x -1 ;
      this.y = y -1;
    }
    
    public void heal(Player player){
        while(player.hp<player.hpMax){
        	player.hp +=1;
        }
        while(player.mana<player.manaMax){
        	player.mana +=1;
        }
        System.out.println("Vos stats ont été restaurées");
    }
}
