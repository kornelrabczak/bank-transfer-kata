package com.thecookiezen.domain

import com.thecookiezen.domain.Balance.EUR
import com.thecookiezen.domain.Bank.User

case class Account(user: User, balance: Balance)

object Account {
  def open(user: User, balance: Balance = Balance(EUR, 0)): Account = new Account(user, balance)
}
