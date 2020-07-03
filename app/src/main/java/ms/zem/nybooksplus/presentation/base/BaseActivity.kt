package ms.zem.nybooksplus.presentation.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

open class BaseActivity: AppCompatActivity() {

    protected fun setupToolbar(toolbar: Toolbar, title: Int) {
        toolbar.title = getString(title)
        setSupportActionBar(toolbar)
    }

}