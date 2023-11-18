
package Class_Utils;

import Class_Model.NhanVien_Model;



public class Auth {

    public static NhanVien_Model user = null;
 
    public static void clear() {
        Auth.user = null;
    }

    public static boolean isLogin() {
        return Auth.user != null;
    }

    public static boolean isManager() {
        return Auth.isLogin() && user.getVITRICONGVIEC();
    }
}
