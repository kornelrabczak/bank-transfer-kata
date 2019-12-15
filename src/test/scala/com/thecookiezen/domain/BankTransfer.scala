package com.thecookiezen.domain

import com.thecookiezen.domain.Balance.EUR
import com.thecookiezen.domain.Bank.{Address, Deposit, Error, User, Withdraw}
import org.scalatest.{FunSuite, Matchers}

class BankTransfer extends FunSuite with Matchers {

  test("should fail deposit with error if there is not enough balance on the account") {
    val emptyAccount = Account.open(User("John", "Doe", 32, Address("Berlin", "Germany", "Uhlandstr", 15)))

    val balanceToWithdraw = Balance(EUR, 15)

    withdraw(emptyAccount)(balanceToWithdraw) shouldBe Left(s"Account balance ${emptyAccount.balance} is not sufficient for $balanceToWithdraw withdraw")
  }

  test("should take deposit if there is enough balance on the account") {
    val emptyAccount = Account.open(User("John", "Doe", 32, Address("Berlin", "Germany", "Uhlandstr", 15)))
      .copy(balance = Balance(EUR, 25))

    withdraw(emptyAccount)(Balance(EUR, 15)) shouldBe Right(emptyAccount.copy(balance = Balance(EUR, 10)))
  }

  test("make a deposit successful deposit") {
    val emptyAccount = Account.open(User("John", "Doe", 32, Address("Berlin", "Germany", "Uhlandstr", 15)))
      .copy(balance = Balance(EUR, 15))

    deposit(emptyAccount)(Balance(EUR, 15)) shouldBe Right(emptyAccount.copy(balance = Balance(EUR, 30)))
  }

  val withdraw: Withdraw = account => balance => {
    if (account.balance.amount.compare(balance.amount) >= 0)
      Right(account.copy(balance = Balance.subtract(account.balance, balance)))
    else
      Left[Error, Account](s"Account balance ${account.balance} is not sufficient for $balance withdraw")
  }

  val deposit: Deposit = account => balance => {
    Right(account.copy(balance = Balance.add(account.balance, balance))
