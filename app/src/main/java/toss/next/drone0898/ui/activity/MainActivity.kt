package toss.next.drone0898.ui.activity

import android.content.Intent
import androidx.activity.viewModels
import androidx.databinding.library.baseAdapters.BR
import toss.next.drone0898.R
import toss.next.drone0898.data.model.CaffeItem
import toss.next.drone0898.databinding.ActivityMainBinding
import toss.next.drone0898.ui.MenuAdapter
import toss.next.drone0898.viewmodel.MainViewModel

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
        binding.backBtn.setOnClickListener {
            onClickBackBtn()
        }
        val adapter = MenuAdapter().apply {
            onClickCaffeItem = this@MainActivity::startDetailActivity
        }
        binding.menuListRecycler.adapter = adapter

        repeatOnStarted {
            viewModel.menu.collect {
                adapter.setItems(it)
            }
        }
        viewModel.fetchMenu()
    }

    override fun initEvent() {

    }
    private fun startDetailActivity(caffeItem: CaffeItem) {
        startActivity(
            Intent(this, MenuDetailActivity::class.java)
                .apply { putExtra(INTENT_PARAM_CAFFE_ITEM, caffeItem) }
        )
    }

    companion object {
        const val INTENT_PARAM_CAFFE_ITEM = "param_caffe_item"
    }
}