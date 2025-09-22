public class Main {
    public static void main(String[] args){
        Bank Raiffen = new Bank("Raiffeisenbank", 1);
        Human karel = new Human("Karel", "Novák", 24);

        karel.newAccount(Raiffen, -10000);

        System.out.println(Raiffen.getClientCount());
        System.out.println(Raiffen.getTotalBalance());
        System.out.println(karel.getAccount(1).isInDebt());
    }
}