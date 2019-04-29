# Dagger2利用時の備忘録

## 必要となる実装
以下の実装で可能。

- Componentクラス（必須）<br>
Daggerを利用するうえで、必ず必要なクラス。
本件では`AppComponent`クラスとして実装した。
https://github.com/poropi/SampleDaggerApp/blob/master/app/src/main/java/jp/ne/poropi/sampledaggerapp/di/component/AppComponent.kt


- Moduleクラス（必要なときに実装）<br>
必須ではないが、基本的にDIを意識したときには、多分実装するだろうクラス。
本件では、`AppModule`クラスとして実装した。
https://github.com/poropi/SampleDaggerApp/blob/master/app/src/main/java/jp/ne/poropi/sampledaggerapp/di/module/AppModule.kt<br>
実装する際、メソッドには`@Provides`アノテーション定義は必須となる。<br>
本件では、`@Named`も定義しているが、本件のような同一クラスのオブジェクトを返却するメソッドが２つ以上ある場合、
Daggerの注入元を区別する場合は必須となる。

- Instanceクラス（ただのシンプルで適当に作ってみたクラス）<br>
Daggerで注入したいクラスを以下の3クラス作ってみた。
https://github.com/poropi/SampleDaggerApp/blob/master/app/src/main/java/jp/ne/poropi/sampledaggerapp/diclass/Aclass.kt<br>
https://github.com/poropi/SampleDaggerApp/blob/master/app/src/main/java/jp/ne/poropi/sampledaggerapp/diclass/Bclass.kt<br>
https://github.com/poropi/SampleDaggerApp/blob/master/app/src/main/java/jp/ne/poropi/sampledaggerapp/diclass/Cclass.kt<br>
ぶっちゃけ、上記のインスタンスを注入するだけであれば、Moduleクラスは不要。<br>
コンストラクタに`@Inject`つけることで、本クラスの呼び出し元で注入してくれるようになる。

- Applicationクラス<br>
https://github.com/poropi/SampleDaggerApp/blob/master/app/src/main/java/jp/ne/poropi/sampledaggerapp/application/DaggerApplication.kt<br>
- Activityクラス<br>
https://github.com/poropi/SampleDaggerApp/blob/master/app/src/main/java/jp/ne/poropi/sampledaggerapp/activity/MainActivity.kt<br>
`DaggerAppComponent.builder().build().inject(this)`を実行することで、`@Inject`アノテーション付きの変数にインスタンスの注入をする。<br>
本件では、どのActivityでも注入することを前提としたので、`DaggerAppComponent.builder().build()`したコンポーネントのオブジェクトをApplicationクラスで保持し、Activityで呼び出すように実装した。

## 注入したときの動き
本件では、`MainActivity`の以下で注入している。
```
        // ここで注入する
        (application as? DaggerApplication)?.component?.inject(this)
```
では、注入されたオブジェクトは？というと、<br>
まず、`MainActivity`の以下、
```
    @Inject lateinit var cClass: Cclass
```
上記は単純に`Cclass`のインスタンスを注入するだけなので、`Module`に定義しなくても注入が可能。<br>
ただし、`Cclass`のコンストラクタに`@Inject`の定義は必要となる。<br>
`Cclass`のコンストラクタを見てみると、<br>
```
class Cclass @Inject constructor(private val a: Aclass, private val b: Bclass) {
```
引数に`Aclass`と`Bclass`が見えているが、この引数となっているオブジェクトにも同時に注入される。<br>
当然、`Aclass`と`Bclass`のコンストラクタにも`@Inject`の定義は必要。
<br>
<br>
ここまでは、単純な注入となるが、次は`Module`に定義したオブジェクトの注入。
```
    @field:[Inject Named("Aclass")]
    lateinit var aList: List<Aclass>
```
上記については、`AppModule`クラスに定義したメソッドから注入されるもの。
https://github.com/poropi/SampleDaggerApp/blob/527ceff07ab7729b864742eb1d354cbc2c512655/app/src/main/java/jp/ne/poropi/sampledaggerapp/di/module/AppModule.kt#L17-L34
