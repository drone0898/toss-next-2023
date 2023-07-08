package toss.next.drone0898.ui.activity

import androidx.activity.viewModels
import androidx.databinding.library.baseAdapters.BR
import toss.next.drone0898.R
import toss.next.drone0898.databinding.ActivityIntroBinding
import toss.next.drone0898.viewmodel.IntroViewModel

class IntroActivity: BaseActivity<ActivityIntroBinding>() {
    val viewModel by viewModels<IntroViewModel>()

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_intro
    }

    override fun bindingViewModel() {
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun initialize() {

    }

    override fun initBinding() {
        binding.nextBtn.setOnClickListener {
            startTargetActivity(MainActivity::class.java,null,false)
        }
    }

    override fun initEvent() {

    }
}