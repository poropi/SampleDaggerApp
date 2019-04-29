package jp.ne.poropi.sampledaggerapp.diclass

import javax.inject.Inject

/**
 * jp.ne.poropi.sampledaggerapp.diclass
 *
 * Created by porop on 2019/04/28.
 */
class Cclass @Inject constructor(private val a: Aclass, private val b: Bclass) {
    fun getValue(): String {
        return "これはCClassが出力しているよ→「${a.value}  ${b.value}」"
    }
}