# alfabank-test
Сервис выдачи GIF из Giphy на основании изменения курса валют.  

# Эндпоинты
Все выдается и запрашивается в формате JSON.
```
GET /alfabanktest/getmemymoney
```
Возвращает список доступных валют.
```
GET /alfabanktest/getgif/rich
```
Возвращает GIF, если курс изменился в положительную сторону.
```
GET /alfabanktest/getgif/broke
```
Возвращает GIF, если курс изменился в отрицательную сторону.

# Запуск
```
javac -jar /alfabank-test/build/libs/alfabank-test-0.0.1-SNAPSHOT.jar
```
