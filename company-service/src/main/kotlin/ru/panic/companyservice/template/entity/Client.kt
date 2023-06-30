package ru.panic.companyservice.template.entity

data class Client(
    val id: Long? = null,
    val username: String? = null,
    val email: String? = null,
    val password: String? = null,
    val btc_balance: Double? = null,
    val eth_balance: Double? = null,
    val ltc_balance: Double? = null,
    val trx_balance: Double? = null,
    val ton_balance: Double? = null,
    val matic_balance: Double? = null,
    val xrp_balance: Double? = null,
    val tetherERC20_balance: Double? = null,
    val isAccountNonLocked: Boolean? = null,
    val registeredAt: Long? = null
)