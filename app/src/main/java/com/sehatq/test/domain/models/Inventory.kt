package com.sehatq.test.domain.models

data class Inventory(
        var id: Int,
        var inventoryName: String,
        var qty: Int,
        var expiryDate: String,
        var price: Double
)