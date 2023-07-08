package toss.next.drone0898.ui.activity

import android.os.Bundle
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
    }


    protected abstract fun bindingViewModel()
    protected abstract fun initialize() // 초기화
    protected abstract fun initBinding() // 데이터 바인딩
    protected abstract fun initEvent() // 이벤트 바인딩

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
}