package com.example.nha_sach.controller;

import org.apache.commons.lang3.StringUtils;

public class demo {
    public static void main(String[] args) {
//        String name = "Sword Art Online, Jujustsu Kaise";
//
//        String list[] =  name.split(", ");
//        String code = "";
//        for (String a : list) {
//           String list2[] = a.split(" ");
//            for (String s: list2
//                 ) {
//                if (list.length == 1){
//                    code += s;
//                }else if(!s.equals("") && !s.equals(null) && !StringUtils.isNumeric(s) && !s.equals("-")) {
//                    System.out.println(String.valueOf(s.charAt(0)));
//                    code += String.valueOf(s.charAt(0));
//                }
//            }
//        }
//        System.out.println(code);
//        String haha = "ko456noi123";
//        String regex = "[^0-9]";
//        String[] code_last = haha.split(regex);
//        String index = "";
//        for (String s : code_last) {
//            index += s;
//        }
//        int i = Integer.parseInt(index) + 1;
//        code += i;
//        System.out.println(code);

//        Integer a = 1;
//        Integer b = a;
//        a = 2 ;
//        System.out.println(b);


        String name = "Du Lịch";
        String code = "HĐ7";
        String codeUpdate = "";
        String list[] =  name.split(" ");
        for (String s : list) { // For each để lấy ra từng từ đã được tách ra khỏi chuỗi name
            if (!s.equals("") && !s.equals(null)) { // Check điều kiện để lấy ra các chữ cái  đầu tiên của từng chữ vừa được tách ra = split
                codeUpdate += String.valueOf(s.charAt(0)); // Cộng từng chữ cái đầu tiên đấy lại với nhau tạo thành chuỗi mới rồi gán vào biến code
            }
        }
        System.out.println(codeUpdate);
        String regex = "[^0-9]";
        String index = ""; // Tạo ra 1 chuỗi index trống để tí nữa sẽ chứa các ptu số được cắt ra
        String[] old_code = code.split(regex); // Lấy ra phần số trong code cuối cùng của list Cate
        for (String s : old_code) {
            index += s; // Hứng vào chuỗi index phần số vừa đc lấy ra từ chuỗi
        }
        codeUpdate += index; // cộng chuỗi để hoàn thành code
        System.out.println(codeUpdate);
    }
}
