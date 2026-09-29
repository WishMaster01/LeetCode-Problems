class Solution {
    public double myPow(double x, int n) {
        long exp = n;

        if(exp < 0) {
            x = 1 / x;
            exp = -exp;
        }

        return power(x, exp, 1);
    }

    private double power(double x, long n, double ans) {
        if(n == 0) return ans;

        if(n % 2 != 0) {
            ans *= x;
        }

        return power(x * x, n / 2, ans);
    }
}