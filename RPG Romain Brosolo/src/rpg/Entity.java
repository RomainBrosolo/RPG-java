package rpg;

abstract class Entity {
    protected int hp;
    protected int attaque;
    protected int defense;
    protected int mana;
    protected String ascii;
    protected String nom;
    protected int x;
    protected int y;
    protected boolean alive;
    protected Item equipedWeapon;
    public Entity(String name,int hp, int attaque, int defense,int mana, String ascii, int y, int x, Item weapon) {
        this.hp = hp;
        this.equipedWeapon = weapon;
        this.attaque = attaque + equipedWeapon.dommage;
        this.defense = defense;
        this.mana = mana;
        this.ascii = ascii;
        this.x = x - 1;
        this.y = y -1;
        this.alive = true;
        this.nom= name;
    }
    public void changeWeapons(Item weapons){
        this.attaque = attaque - equipedWeapon.dommage;
        this.equipedWeapon.equiped = false;
        this.equipedWeapon = weapons;
        this.equipedWeapon.equiped = true;
        this.attaque = attaque + equipedWeapon.dommage;

    }
    public void attackEntity(Entity target) {
        target.getHurt(this.attaque);
    } 
    
    public void magicEntity(Entity target,Player p1) {
        if(p1.mana>=5){
            
            target.getHurt(p1.attaqueMagique);
        }else{
            System.out.println("Vous n'avez pas assez de mana");
        }
    } 
    
    public void getHurt(int attaque) {
        this.hp -= attaque;
    }
    
   
}


