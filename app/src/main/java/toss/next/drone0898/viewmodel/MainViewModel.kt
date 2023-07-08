package toss.next.drone0898.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import toss.next.drone0898.data.model.CaffeItem
import toss.next.drone0898.data.model.Category

class MainViewModel: ViewModel() {

    private val _menu: MutableStateFlow<List<Any>> = MutableStateFlow(listOf(Category.EMPTY))
    val menu:MutableStateFlow<List<Any>> = _menu
    fun fetchMenu(){
        val categories = listOf(
            Category("Coffee", 1, listOf(
                CaffeItem("아메리카노", "Coffee", true, true, 1000),
                CaffeItem("카페라떼", "Coffee", true, true, 1500),
                CaffeItem("카푸치노", "Coffee", true, true, 2000)
            )),
            Category("Beverage", 2, listOf(
                CaffeItem("오렌지에이드", "Beverage", true, false, 2500),
                CaffeItem("망고에이드", "Beverage", true, false, 2500)
            )),
            Category("Tea", 3, listOf(
                CaffeItem("얼그레이티", "Tea", false, true, 1000),
                CaffeItem("루이보스티", "Tea", false, true, 2000)
            )),
            Category("Dessert", 4, listOf(
                CaffeItem("치즈케이크", "Dessert", false, false, 3000),
                CaffeItem("마들렌", "Dessert", false, false, 1000),
                CaffeItem("휘낭시에", "Dessert", false, false, 1500)
            ))
        ).sortedBy { it.priority }

        val combinedMenu = mutableListOf<Any>()

        categories.forEach { category ->
            val sortedItems = category.itemList.sortedBy { it.cost }
            combinedMenu.add(category.copy(itemList = sortedItems))
            combinedMenu.addAll(sortedItems)
        }

        _menu.value = combinedMenu
    }
}