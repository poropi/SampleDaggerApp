package jp.ne.poropi.sampledaggerapp.application

import android.app.Application
import jp.ne.poropi.sampledaggerapp.di.component.AppComponent
import jp.ne.poropi.sampledaggerapp.di.component.DaggerAppComponent

/**
 * jp.ne.poropi.sampledaggerapp.application
 *
 * Created by porop on 2019/04/28.
 */
class DaggerApplication: Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder().build()
    }
}