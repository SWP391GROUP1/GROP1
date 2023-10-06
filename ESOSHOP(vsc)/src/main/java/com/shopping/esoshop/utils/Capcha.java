/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.esoshop.utils;

import java.util.Random;

/**
 *
 * @author LINH
 */
public class Capcha {

    public static String createCapcha() {
        String capcha = "";
        String charter = "";
        String number = "";
        for (char i = 'a'; i < 'z'; i++) {
            charter += i;
        }
        for (int i = 0; i <= 9; i++) {
            number += i;
        }
        while (true) {
            int n;
            for (int i = 0; i < 6; i++) {
                Random r = new Random();
                n = r.nextInt(2);
                if (n == 0) {
                    n = r.nextInt(charter.length());
                    capcha += charter.charAt(n);
                } else if (n == 1) {
                    n = r.nextInt(number.length());
                    capcha += number.charAt(n);
                }
            }
            int num = 0;
            int cha = 0;
            for (int i = 0; i < capcha.length(); i++) {
                if ('a' <= capcha.charAt(i) && capcha.charAt(i) <= 'z') {
                    cha++;
                }
                if ('0' <= capcha.charAt(i) && capcha.charAt(i) <= '9') {
                    num++;
                }
            }
            if (num*cha!=0) {
                return capcha;
            }
        }
    }

}
