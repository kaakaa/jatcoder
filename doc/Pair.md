# Pair
２つの異なる型の値を保持する「組」を表現します。

`Pair` は `Comparable` であるため、`PriorityQueue` の要素や `HashMap` のキーとして使えます。
ただし、格納される２つの値は、いずれも `Comparable` でなければなりません。

## コンストラクタ
```
Pair<L, R> pair = new Pair<L, R>(L a, R b)
```

## メンバ変数
###  first

```
L pair.first
```

１つめの要素

### second

```
R pair.second
```

２つめの要素
