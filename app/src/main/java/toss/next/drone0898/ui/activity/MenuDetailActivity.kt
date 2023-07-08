package toss.next.drone0898.ui.activity

import android.os.Build
import androidx.activity.viewModels
import androidx.databinding.library.baseAdapters.BR
import toss.next.drone0898.R
import toss.next.drone0898.data.model.CaffeItem
import toss.next.drone0898.databinding.ActivityMenuDetailBinding
import toss.next.drone0898.viewmodel.MenuDetailViewModel

class MenuDetailActivity: BaseActivity<ActivityMenuDetailBinding>() {
    val viewModel by viewModels<MenuDetailViewModel>()

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_menu_detail
    }
    override fun bindingViewModel() {
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun initialize() {

    }

    override fun initBinding() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            binding.setVariable(BR.item,intent.getSerializableExtra(MainActivity.INTENT_PARAM_CAFFE_ITEM, CaffeItem::class.java))
        }else{
            binding.setVariable(BR.item,intent.getSerializableExtra(MainActivity.INTENT_PARAM_CAFFE_ITEM))
        }
        binding.backBtn.setOnClickListener {
            onClickBackBtn()
        }
    }

    override fun initEvent() {

    }
}