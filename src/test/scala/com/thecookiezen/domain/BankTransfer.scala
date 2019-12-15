package com.thecookiezen.domain

import com.thecookiezen.domain.Balance.EUR
import com.thecookiezen.domain.Bank.{Address, Deposit, Error, User, Withdraw, deposit, withdraw}
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
}
