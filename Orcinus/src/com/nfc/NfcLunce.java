package com.nfc;

import android.widget.Toast;

/**
 * Created by acer on 14-6-4.
 */
public class NfcLunce {
    public static String name,company,phone,email,profession;

    public NfcLunce() {

    }

    public static void lunceSet(String str) {
        int nameIndex = str.indexOf("name");
        int phoneIndex = str.indexOf("phone");
        int companyIndex = str.indexOf("company");
        int emailIndex = str.indexOf("email");
        int professionIndex = str.indexOf("profession");
        NfcLunce.name = find(nameIndex, "name", str);
        NfcLunce.phone = find(phoneIndex, "phone", str);
        NfcLunce.company = find(companyIndex, "company", str);
        NfcLunce.email = find(emailIndex, "email", str);
        NfcLunce.profession = find(professionIndex, "profession", str);
        // 还一个冒号
    }
    public static String find(int index, String tmp, String str) {
        index += tmp.length()+1;
        String ans = "";
        for (int i = index; i < str.length(); i++) {
            if (str.charAt(i) == ';')
                break;
            ans += str.charAt(i);
        }
        System.out.println(ans);
        return ans;
    }
//    public static void main(String args[]) {
//        NfcLunce text = new NfcLunce();
//        text.lunceSet("name:1;phone:2;company:3;email:4;profession:5");
//    }
}
