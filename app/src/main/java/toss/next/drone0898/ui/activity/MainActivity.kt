package toss.next.drone0898.ui.activity

import androidx.activity.viewModels
import androidx.databinding.library.baseAdapters.BR
import dagger.hilt.android.AndroidEntryPoint
import toss.next.drone0898.R
import toss.next.drone0898.databinding.ActivityMainBinding
import toss.next.drone0898.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    val viewModel by viewModels<MainViewModel>()
    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }
    override fun bindingViewModel() {
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun initialize() {

    }

    override fun initBinding() {

    }

    override fun initEvent() {

    }
}