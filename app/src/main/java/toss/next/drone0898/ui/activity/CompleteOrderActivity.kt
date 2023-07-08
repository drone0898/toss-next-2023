package toss.next.drone0898.ui.activity

import android.view.View
import androidx.activity.viewModels
import androidx.databinding.library.baseAdapters.BR
import toss.next.drone0898.R
import toss.next.drone0898.databinding.ActivityCompleteOrderBinding
import toss.next.drone0898.viewmodel.CompleteOrderViewModel

class CompleteOrderActivity: BaseActivity<ActivityCompleteOrderBinding>() {
    val viewModel by viewModels<CompleteOrderViewModel>()
    override fun getLayoutResourceId(): Int {
        return R.layout.activity_complete_order
    }

    override fun bindingViewModel() {
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun initialize() {

    }

    override fun initBinding() {
        TODO("Not yet implemented")
    }

    override fun initEvent() {
        TODO("Not yet implemented")
    }
}