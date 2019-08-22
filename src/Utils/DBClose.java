package Utils;

public class DBClose {
    public static void closeall(AutoCloseable...autoCloseables) {
        for (AutoCloseable autoCloseable:autoCloseables) {
           if (autoCloseable!=null) {
               try {
                   autoCloseable.close();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
        }
    }
}
