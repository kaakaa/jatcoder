package jp.ne.sakura.uhideyuki.jatcoder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ModintTest {

    @Test
    void modIntAdd(){
        final int mod = 13;
        Modint.setMod(mod);
        Modint.setPrime(true); // 13 is a prime number

        for (int x = 0; x < 20; x++){
            for (int y = 0; y < 20; y++){
                final Modint a = new Modint(x);
                final Modint b = new Modint(y);
                final Modint c = a.add(b);
                assertEquals((x + y) % mod, c.val());
            }
        }
    }

    @Test
    void dynamicModIntAdd(){
        final int mod = 12;
        for (int x = 0; x < 20; x++){
            for (int y = 0; y < 20; y++){
                final Modint.DynamicModint a = new Modint.DynamicModint(x, mod);
                final Modint.DynamicModint b = new Modint.DynamicModint(y, mod);
                final Modint.DynamicModint c = a.add(b);
                assertEquals((x + y) % mod, c.val());
            }
        }
    }

    @Test
    void modIntSub(){
        final int mod = 13;
        Modint.setMod(mod);
        Modint.setPrime(true); // 13 is a prime number

        for (int x = 0; x < 20; x++){
            for (int y = 0; y < 100; y++){
                final Modint a = new Modint(x);
                final Modint b = new Modint(y);
                final Modint c = a.sub(b);
                assertEquals(((x - y) % mod + mod) % mod, c.val());
            }
        }
    }

    @Test
    void dynamicModIntSub(){
        final int mod = 12;
        for (int x = 0; x < 20; x++){
            for (int y = 0; y < 100; y++){
                final Modint.DynamicModint a = new Modint.DynamicModint(x, mod);
                final Modint.DynamicModint b = new Modint.DynamicModint(y, mod);
                final Modint.DynamicModint c = a.sub(b);
                assertEquals(((x - y ) % mod + mod) % mod, c.val());
            }
        }
    }

    @Test
    void modIntMul(){
        final int mod = 13;
        Modint.setMod(mod);
        Modint.setPrime(true); // 13 is a prime number

        for (int x = 0; x < 20; x++){
            for (int y = 0; y < 20; y++){
                final Modint a = new Modint(x);
                final Modint b = new Modint(y);
                final Modint c = a.mul(b);
                assertEquals((x * y) % mod, c.val());
            }
        }
    }

    @Test
    void dynamicModIntMul(){
        final int mod = 12;
        for (int x = 0; x < 20; x++){
            for (int y = 0; y < 20; y++){
                final Modint.DynamicModint a = new Modint.DynamicModint(x, mod);
                final Modint.DynamicModint b = new Modint.DynamicModint(y, mod);
                final Modint.DynamicModint c = a.mul(b);
                assertEquals((x * y) % mod, c.val());
            }
        }
    }

    @Test
    void modIntDiv(){
        final int mod = 13;
        Modint.setMod(mod);
        Modint.setPrime(true); // 13 is a prime number

        for (int x = 0; x < 20; x++){
            for (int y = 0; y < 20; y++){
                if (y % mod == 0) continue;
                final Modint a = new Modint(x);
                final Modint b = new Modint(y);
                final Modint c = a.div(b);
                assertEquals(x % mod, c.val() * y % mod);
            }
        }
    }

    @Test
    void dynamicModIntDiv(){
        // prime = false で計算するが、mod は素数にしておく
        // y と mod が互いに素である必要があるため
        final int mod = 13;
        for (int x = 0; x < 20; x++){
            for (int y = 0; y < 20; y++){
                if (y % mod == 0) continue;
                final Modint.DynamicModint a = new Modint.DynamicModint(x, mod);
                final Modint.DynamicModint b = new Modint.DynamicModint(y, mod);
                final Modint.DynamicModint c = a.div(b);
                assertEquals(x % mod, c.val() * y % mod);
            }
        }
    }

    @Test
    void modintMod(){
        final int mod = 13;
        final Modint x = new Modint();
        Modint.setMod(mod);
        assertEquals(mod, x.mod());
    }

    @Test
    void dynamicModintMod(){
        final int mod = 13;
        final Modint.DynamicModint x = new Modint.DynamicModint(0, mod);
        assertEquals(mod, x.mod());
    }

    @Test
    void modintInv(){
        final int mod = 13;
        Modint.setMod(mod);
        for (int i = 1; i < mod; i++){
            final Modint x = new Modint(i);
            final Modint y = x.inv();
            assertEquals(1, x.mul(y).val());
        }
    }

    @Test
    void dynamicModintInv(){
        final int mod = 13;
        for (int i = 1; i < mod; i++){
            final Modint.DynamicModint x = new Modint.DynamicModint(i, mod);
            final Modint.DynamicModint y = x.inv();
            assertEquals(1, x.mul(y).val());
        }
    }

    @Test
    void modintPow(){
        final int mod = 13;
        long x = 1;
        long a = 9;
        int p = 100;
        for (int i = 0; i < p; i++){
            x *= a;
            x %= mod;
        }
        Modint.setMod(13);
        final Modint y = new Modint((int) a).pow(p);
        assertEquals(x, y.val());
    }

    @Test
    void dynamicModintPow(){
        final int mod = 13;
        long x = 1;
        long a = 9;
        int p = 100;
        for (int i = 0; i < p; i++){
            x *= a;
            x %= mod;
        }
        final Modint.DynamicModint y = new Modint.DynamicModint((int) a, mod).pow(p);
        assertEquals(x, y.val());
    }
}
