package toss.next.drone0898.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseActivity <V: ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: V

    private val pressBackBtn = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onClickBackBtn()
        }
    }
    @LayoutRes
    protected abstract fun getLayoutResourceId():Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()

        binding = DataBindingUtil.setContentView(this, getLayoutResourceId())
        binding.lifecycleOwner = this
        bindingViewModel()
        initBinding()
        initEvent()
        this.onBackPressedDispatcher.addCallback(this, pressBackBtn)
    }

    protected abstract fun bindingViewModel()
    protected abstract fun initialize() // 초기화
    protected abstract fun initBinding() // 데이터 바인딩
    protected abstract fun initEvent() // 이벤트 바인딩
    fun onClickBackBtn() { finish() }

    fun LifecycleOwner.repeatOnStarted(block: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED,block)
        }
    }
    fun LifecycleOwner.repeatsOnStarted(blocks: List<suspend CoroutineScope.() -> Unit>) {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                blocks.forEach{block ->
                    launch{block()}
                }
            }
        }
    }
    open fun startTargetActivity(
        target: Class<*>,
        extraData: Bundle?,
        maintain: Boolean
    ) {
        val uri = intent.data
        val cIntent = Intent(this, target)
        if (extraData != null) {
            cIntent.putExtras(extraData)
        }
        if (uri != null) {
            cIntent.data = uri
        }
        if (!maintain) {
            cIntent.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_SINGLE_TOP or
                        Intent.FLAG_ACTIVITY_CLEAR_TOP
            )
        }
        startActivity(cIntent)
        if (!maintain) {
            finish()
        }
    }
}