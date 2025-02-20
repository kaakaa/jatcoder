package jp.ne.sakura.uhideyuki.jatcoder;

public class Modint implements Comparable<Modint> {
    final private long modintBase;
    final private boolean prime;
    private long value = 0;

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(final Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof Modint) {
            final Modint m = (Modint) obj;
            return value == m.getValue();
        }
        return super.equals(obj);
    }

    @Override
    public int compareTo(final Modint m){
        return Long.valueOf(value).compareTo(Long.valueOf(m.getValue()));
    }

    @Override
    public int hashCode(){
        return Long.valueOf(value).hashCode();
    }

    public static class Builder {
        final private long modintBase;
        final private boolean prime;

        public Builder(final int mod, final boolean prime){
            this.modintBase = mod;
            this.prime = prime;
        }

        public Builder(final int mod){
            // よく使われる mod に対してのみ prime を設定
            this(mod, (mod == 998244353) || (mod == 1000000007));
        }

        public Modint build(){
            return new Modint(this);
        }

        public Modint build(final int v){
            return new Modint(this, v);
        }
    }

    private Modint(final Builder builder){
        this.modintBase = builder.modintBase;
        this.prime = builder.prime;
    }

    private Modint(final Builder builder, final int v){
        this(builder);
        setValue(v);
    }

    private Modint(final Modint x){
        this.modintBase = x.modintBase;
        this.prime = x.prime;
        this.value = x.value;
    }

    public Modint setValue(final int v){
        value = (long) v % modintBase;
        return this;
    }

    private Modint setRaw(final long v){
        value = v;
        return this;
    }

    public int getValue(){
        return (int) value;
    }

    public int mod(){
        return (int) modintBase;
    }

    public Modint add(final Modint x){
        final long m = modintBase;
        assert m == x.mod();
        return new Modint(this).setRaw((this.getValue() + x.getValue()) % m);
    }

    public Modint sub(final Modint x){
        final long m = modintBase;
        assert m == x.mod();
        return new Modint(this)
                .setRaw((((getValue() - x.getValue()) % m) + m) % m);
    }

    public Modint mul(final Modint x){
        final long m = modintBase;
        assert m == x.mod();
        return new Modint(this).setRaw(((long) getValue() * x.getValue()) % m);
    }

    public Modint div(final Modint x){
        final long m = modintBase;
        assert m == x.mod();
        assert prime == x.prime;
        return new Modint(this).setRaw(((long) getValue() * x.inv().getValue()) % m);
    }
    public Modint inv(){
        final long m = modintBase;
        if (prime){
            assert value != 0;
            return this.pow(m - 2);
        } else {
            final long[] eg = invGcd(value, m);
            assert eg[0] == 1;
            return new Modint(this).setRaw(eg[1]);
        }
    }

    private static long[] invGcd(long a, final long b){
        a %= b;
        if (a == 0) return new long[]{b, 0};

        long s = b, t = a;
        long m0 = 0, m1 = 1;

        while (t != 0){
            long u = s / t;
            s -= t * u;
            m0 -= m1 * u;

            var tmp = s;
            s = t;
            t = tmp;
            tmp = m0;
            m0 = m1;
            m1 = tmp;
        }
        if (m0 < 0) m0 += b / s;
        return new long[]{s, m0};
    }

    public Modint pow(long n){
        assert n >= 0;
        final long m = modintBase;
        long a = 1, x = value;
        while (n > 0){
            if ((n & 1) != 0) a = a * x % m;
            x = x * x % m;
            n >>= 1;
        }
        return new Modint(this).setRaw(a);
    }
}
