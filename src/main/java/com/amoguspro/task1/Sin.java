package com.amoguspro.task1;

public class Sin {
    public static double calc(double x, int n) {
        double PI2 = Math.PI * 2;

        if (x >= 0) {
            while (x > PI2)
                x -= PI2;

        } else if (x < 0) {
            while (x < PI2)
                x += PI2;

        }

        double result = 0,  xx = x * x, pow = x, fact = 1;
        int sign = 1;

        for (int i = 1; i < n; i += 2) {
            fact /= i;
            result += sign * pow * fact;
            sign = -sign;
            fact /= (i + 1);
            pow *= xx;
        }

        return result;
    }
}
