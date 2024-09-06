package online.jutter.kztlibrary.domain.exceptions

import java.lang.Exception

class LoginNotFoundException: Exception("Логин не найден")

class InvalidPasswordException: Exception("Пароль указан не верно")

class InvalidLoginException: Exception("Некорректный логин")

class LoginContainsInStorageException: Exception("Логин занят")