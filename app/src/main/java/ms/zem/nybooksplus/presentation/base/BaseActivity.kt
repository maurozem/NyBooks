package ms.zem.nybooksplus.presentation.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.toolbar.*
import ms.zem.nybooksplus.R

open class BaseActivity: AppCompatActivity() {

    protected fun setupToolbar(toolbar: Toolbar, title: Int) {
        toolbar?.title = getString(title)
        setSupportActionBar(toolbar)
    }

}