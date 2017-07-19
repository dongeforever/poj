package com.dova;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P1001
{

    public static byte[] parse(String str) {
        byte[] bytes = str.getBytes();
        byte[] res = new byte[bytes.length];
        int index =  res.length - 1;
        for (int i = bytes.length - 1 ; i >= 0; i--) {
            if (bytes[i] >= 48 && bytes[i] <= 57) {
                res[index--] = (byte)(bytes[i] - 48);
            }
        }
        return res;
    }

    public static void pow(String r, String n) {
        int index = r.indexOf('.');
        int xiaoshu = 0;
        if (index != -1) {
            xiaoshu += r.length() - 1 - index;
        }
        int nn = Integer.parseInt(n);
        xiaoshu = xiaoshu * nn;
        byte[] a = parse(r);
        byte[] mid = new byte[r.length() * nn];
        byte[] res = new byte[r.length() * nn + r.length() + 1];
        multipy(a, a, res);
        for (int i = 2; i < nn; i++) {
            System.arraycopy(res, res.length - mid.length, mid, 0, mid.length);
            Arrays.fill(res, (byte) 0);
            multipy(mid, a, res);
        }
        int currP = 0;
        byte[] bytes =  new byte[res.length + 1];
        Arrays.fill(bytes, (byte) '0');
        for (int i = 0; i < res.length; i++) {
            if (i == (res.length - xiaoshu)) {
                bytes[currP++] = '.';
            }
            bytes[currP++] = (byte) (res[i] + 48);
        }
        System.out.println(trimZero(new String(bytes)));
    }


    public static void multipy(byte[] a, byte[] b, byte[] res) {

        for (int i = b.length - 1; i >=0  ; i--) {
            int cha = b.length - 1 - i;
            for (int j = a.length - 1; j >= 0; j--) {
                int cha2 =  a.length - 1 - j;
                int index = res.length - 1 - cha - cha2;
                res[index] += b[i] * a[j];
            }
            carryPropagation(res);
        }
        carryPropagation(res);
    }

    public static void carryPropagation(byte[] a) {
        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i] >= 10) {
                a[i - 1] += a[i]/10;
                a[i] = (byte)(a[i] % 10);
            }
        }
    }

    public static String trimZero(String origin) {
        byte[] bytes = origin.getBytes();
        int i = 0, j = bytes.length - 1;
        for (; i < bytes.length; i++) {
            if (bytes[i] != '0') {
                break;
            }
        }
        while (j > i) {
            if (bytes[j] == '0') {
                j--;
            } else if (bytes[j] == '.') {
                j--;
                break;
            } else {
                break;
            }
        }
        return new String(bytes, i, j - i + 1);
    }

    public static void main (String args[]) throws Exception
    {
        BufferedReader stdin =
            new BufferedReader(
                new InputStreamReader(System.in));

        List<String> lines = new ArrayList<String>();
        String line;
        while (true) {
            line = stdin.readLine();
            if (line == null || line.length() == 0) break;
            lines.add(line);

        }

        for (String input : lines) {
            StringTokenizer st = new StringTokenizer(input);
            pow(st.nextToken(), st.nextToken());
        }
    }
}
