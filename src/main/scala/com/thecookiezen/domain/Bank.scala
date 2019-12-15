package com.thecookiezen.domain

object Bank {
  type Country = String
  type City = String

  case class User(firstName: String,
                  lastName: String,
                  age: Int,
                  address: Address)
  case class Address(city: City,
                     country: Country,
                     streetName: String,
                     streetNo: Int)

  type Error = String
  type ErrorOrAccount = Either[String, Account]
  type ErrorOr[A] = Either[String, A]
  type Deposit = Account => Balance => ErrorOrAccount
  type Withdraw = Account => Balance => ErrorOrAccount

  type Transfer = Balance => Withdraw => Deposit => ErrorOr[(Account, Account)]

  val withdraw: Withdraw = account =>
    balance => {
      if (account.balance.amount.compare(balance.amount) >= 0)
        Right(
          account.copy(balance = Balance.subtract(account.balance, balance)))
      else
        Left[Error, Account](
          s"Account balance ${account.balance} is not sufficient for $balance withdraw")
  }

  val deposit: Deposit = account =>
    balance => {
      Right(account.copy(balance = Balance.add(account.balance, balance)))
  }
}
