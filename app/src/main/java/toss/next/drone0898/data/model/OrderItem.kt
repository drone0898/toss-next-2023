package toss.next.drone0898.data.model

enum class IceAmount {
    LITTLE,
    NORMAL,
    LOT
}

data class OrderItem(
    val caffeItem: CaffeItem,
    val hot:Boolean,
    val caffeine:Boolean,
    val iceAmount: IceAmount
)