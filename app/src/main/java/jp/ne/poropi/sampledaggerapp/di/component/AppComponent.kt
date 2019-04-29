package jp.ne.poropi.sampledaggerapp.di.component

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import jp.ne.poropi.sampledaggerapp.activity.MainActivity
import jp.ne.poropi.sampledaggerapp.di.module.AppModule

/**
 * どこになんのモジュールを注入するかを定義
 * jp.ne.poropi.sampledaggerapp.di.component
 *
 * Created by porop on 2019/04/28.
 */
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}