package toss.next.drone0898.data.model

data class Category(
    val name:String,
    val priority:Int,
    val itemList:List<CaffeItem>
){
    companion object{
        val EMPTY = Category("",0, listOf(CaffeItem.EMPTY))
    }
}
