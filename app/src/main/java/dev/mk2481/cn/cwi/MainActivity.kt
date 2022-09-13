package dev.mk2481.cn.cwi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    private val fragmentContainerId = ViewCompat.generateViewId()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        val view = FragmentContainerView(this).apply {
            id = fragmentContainerId
        }
        setContentView(view)

        supportFragmentManager.commit {
            replace(view.id, MenuFragment())
        }

        supportFragmentManager.setFragmentResultListener("GO_COMPOSE_VIEW", this) { _, _ ->
            supportFragmentManager.commit {
                addToBackStack(null)
                replace(view.id, ComposeViewFragment())
            }
        }

        supportFragmentManager.setFragmentResultListener("GO_DETAIL", this) { _, _ ->
            supportFragmentManager.commit {
                addToBackStack(null)
                replace(view.id, DetailFragment())
            }
        }
    }
}
