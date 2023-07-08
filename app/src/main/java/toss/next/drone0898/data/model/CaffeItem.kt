package toss.next.drone0898.data.model

import java.io.Serializable

data class CaffeItem(
    val name:String,
    val category:String,
    val canChooseHotOrIce:Boolean,
    val canChooseDecaff:Boolean,
    val cost:Int
):Serializable{
    companion object{
        val EMPTY = CaffeItem("","",
            canChooseHotOrIce = false,
            canChooseDecaff = false,
            cost = 0
        )
    }
}
