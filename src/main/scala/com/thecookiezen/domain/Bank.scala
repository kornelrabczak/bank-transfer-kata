package com.thecookiezen.domain

import com.thecookiezen.domain.Balance.Amount

object Bank {
  type Country = String
  type City = String

  case class User(firstName: String, lastName: String, age: Int, address: Address)
  case class Address(city: City, country: Country, streetName: String, streetNo: Int)

  type Error = String
  type ErrorOrAccount = Either[String, Account]
  type Deposit = Account => Balance => ErrorOrAccount
  type Withdraw = Account => Balance => ErrorOrAccount

  type Transfer = Amount => Withdraw => Deposit
}
