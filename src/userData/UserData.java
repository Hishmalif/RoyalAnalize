package userData;

public class UserData {
    private static long heal;
    private static long dealtDamage;
    private static long recvdDamage;

    public static long getHeal() {
        return heal;
    }

    public static void setHeal(long heal) {
        UserData.heal = heal;
    }

    public static long getDealtDamage() {
        return dealtDamage;
    }

    public static void setDealtDamage(long dealtDamage) {
        UserData.dealtDamage = dealtDamage;
    }

    public static long getRecvdDamage() {
        return recvdDamage;
    }

    public static void setRecvdDamage(long recvdDamage) {
        UserData.recvdDamage = recvdDamage;
    }
}