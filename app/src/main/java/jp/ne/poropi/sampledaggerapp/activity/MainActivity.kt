package jp.ne.poropi.sampledaggerapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import jp.ne.poropi.sampledaggerapp.R
import jp.ne.poropi.sampledaggerapp.application.DaggerApplication
import jp.ne.poropi.sampledaggerapp.diclass.Aclass
import jp.ne.poropi.sampledaggerapp.diclass.Cclass
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppCompatActivity() {

    @Inject lateinit var cClass: Cclass

    @field:[Inject Named("Aclass")]
    lateinit var aList: List<Aclass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ここで注入する
        (application as? DaggerApplication)?.component?.inject(this)

        text_view.text = cClass.getValue()
        aList.forEach {
            Log.d(MainActivity::class.java.simpleName, "hirosawa test ${it.value}")
        }
    }
}
