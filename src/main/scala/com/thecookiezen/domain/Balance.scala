package com.thecookiezen.domain

import com.thecookiezen.domain.Balance.{Amount, Currency}

case class Balance(currency: Currency, amount: Amount)

object Balance {
  type Amount = BigDecimal

  sealed trait Currency
  case object EUR extends Currency
  case object USD extends Currency

  val add: (Balance, Balance) => Balance = {
    case (Balance(currency, amount), Balance(_, amount2)) => Balance(currency, amount + amount2)
  }

  val subtract: (Balance, Balance) => Balance = {
    case (Balance(currency, amount), Balance(_, amount2)) => Balance(currency, amount - amount2)
  }
}