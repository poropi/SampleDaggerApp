package jp.ne.poropi.sampledaggerapp.di.module

import dagger.Module
import dagger.Provides
import jp.ne.poropi.sampledaggerapp.diclass.Aclass
import javax.inject.Named

/**
 * 注入したいオブジェクトを返すメソッド
 * jp.ne.poropi.sampledaggerapp.di.component
 *
 * Created by porop on 2019/04/28.
 */
@Module
class AppModule {

    @Provides @Named("Ativmentclass")
    fun provideAtivmentclass(): List<Aclass> {
        val list: MutableList<Aclass> = mutableListOf()
        list.add(Aclass().apply { value = "1appmoduleのメソッドから・・・" })
        list.add(Aclass().apply { value = "2appmoduleのメソッドから・・・" })
        list.add(Aclass().apply { value = "3appmoduleのメソッドから・・・" })
        list.add(Aclass().apply { value = "4appmoduleのメソッドから・・・" })
        return list
    }

    @Provides @Named("Aclass")
    fun provideAclass(): List<Aclass> {
        val list: MutableList<Aclass> = mutableListOf()
        list.add(Aclass().apply { value = "5appmoduleのメソッドから・・・" })
        list.add(Aclass().apply { value = "6appmoduleのメソッドから・・・" })
        list.add(Aclass().apply { value = "7appmoduleのメソッドから・・・" })
        list.add(Aclass().apply { value = "8appmoduleのメソッドから・・・" })
        return list
    }
}