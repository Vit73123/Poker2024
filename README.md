# Тестовое 2024-2025  
В рамках этого тестового задания мы будем определять победителя в Покере.  
Вид покера - Техасский холдем.   
Карты обозначаются так: сначала номинал, потом - масть. Например: "2С" - двойка червей, "10D" - десятки бубей, пример руки игрока - "2C10D"  
Список мастей:
* ♣️ Трефы — C(lubs)
* ♦️ Бубны — (D)iamonds
* ♥️ Червы — (H)earts
* ♠️ Пики — (S)pades  

Список номиналов: 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A  
Пример готовой доски:  
* playerOne="6CQC"  
* playerTwo="5H2S"  
* flop="7C3D7D"  
* turn="9D"  
* river="JS"  

[Гайд по покерным комбинациям](https://pekarstas.com/kombinatsii-pokera/)  
Если не знаком с покером, то будь внимателен - побеждает сильнейшая комбинация из пяти карт. Они все могут быть на столе, а не у игроков, разберись с этой механикой вдумчиво :)  

## Задача
Cоздать свою реализацию [Dealer](src%2Fmain%2Fjava%2Forg%2Fexample%2FDealer.java), он должен уметь: 
* Корректно раздавать случайные карты из колоды (всего карт 52), см внимательно документацию в интерфейсе
* Определять исход раздачи на готовой доске (с ривером)
* В ходе определения победителя проверять, что на доске корректные карты, в противном случае выбрасывать [InvalidPokerBoardException](src%2Fmain%2Fjava%2Forg%2Fexample%2FInvalidPokerBoardException.java) с оcмысленным сообщением

## Как отправить решение?
* Тебе нужно создать собственный репозиторий на Github и добавить туда проект с решением.
* Ссылку на репозиторий отправь [в телеграмм @Marandyuk_Anatolii](https://t.me/marandyuk_anatolii) 
* Не забудь сделать репозиторий публичным
* Лучше не форкай этот репозиторий, чтобы другие участники не подглядывали в твое решение


## FAQ
#### Что, если я не знаю Java?
Начни с установки Intelij Idea и JDK. В данном случае тебе понадобится JDK 17 версии:  
- Intelij Idea **Community Edition** [отсюда](https://www.jetbrains.com/toolbox-app/),  надеюсь ты умеешь обходить блокировки, иначе в разработку нынче путь заказан
- В Intelij Idea File-> Project Structure -> SDK -> Download SDK -> 17 -> Amazon Coretto
- Еще можно [Скачать JDK](https://jdk.java.net/java-se-ri/17-MR1) тут, засетапить все необходимое [видео](https://www.youtube.com/watch?v=0OrVOHB42C4) (есть русские субтитры), [текст](https://www.freecodecamp.org/news/how-to-set-up-java-development-environment-a-comprehensive-guide/)

#### Я не умею работать с Git и GitHub  
Если ты не знаком с Git и Github, то тебе нужно ознакомиться с этой [статьей](http://maxsite.org/page/how-to-put-your-project-on-github-com), в ней описаны основы работы с Git.  
[Git: загрузить существующий проект на GitHub](https://www.youtube.com/watch?v=kO5u0PFdHUQ)  
[Отправка проекта на GitHub из IntelliJ IDEA](https://www.youtube.com/watch?v=zM6z57OtR2Q)

#### Тебе потребуется изучить некоторый теоретический минимум для решения этой задачи:
*  Основы синтаксиса Java, простые (примитивные) типы данных
*  Арифметические операции в java
*  Методы
*  Преобразование строки в число
*  Класс String, работа со строками
*  Циклы в Java
*  Логические операторы
*  Условные операторы, сравнение, switch case
*  Работа с консолью - ввод/вывод, классы Scanner и BufferedReader
*  Java и ООП
*  Обработка ошибок и создание своих исключений
  
Список универсальный, не факт, что прям все пригодится в рамках решения этого тестового, так что не передумай себя самого  

#### Помогут также следующие ресурсы:  
**ВНИМАНИЕ!** Не нужно смотреть все видео из плейлиста, не нужно проходить курс до конца и читать всю книгу! У тебя ограниченное количество времени на решение задачи, поэтому не трать время. Сверься со списком тем выше и посмотри выборочно материалы только по этим темам! Для того, чтобы сэкономить свое время, выбери один источник из предложенных и используй его для решения.
- [Трегулов, Програмания](https://www.youtube.com/watch?v=TQ_vwm4h0ro&list=PLqj7-hRTFl_rqruGcnd2V8SPbY0j9DzT5)
- [Канал Алишева](https://www.youtube.com/watch?v=ziOQ8wkmnSE&list=PLAma_mKffTOSUkXp26rgdnC0PicnmnDak)
- [Metanit. Руководство по языку программирования Java.](https://metanit.com/java/tutorial/)
- [Java для начинающих. Курс](https://ru.code-basics.com/languages/java)
- Гугл - основной инструмент для поиска информации.

#### Я написал код, а мне выдает какую-то ошибку
Часто написанный с ошибками код компилируется и при запуске может выбрасывать исключения, стектрейс которых выводится в консоль. Это нормально.  
Советы:  
- Для понимания природы этого исключения можно прочитать java doc этого исключения, либо просто загуглив исключение по названию первого исключения и тексту ошибки.
- Если по поиску теста ошибки ничего не находится, попробуй сократить текст ошибки, удалив из него текст, относящийся к конкретно к твоему приложению (например там могут быть указаны название написанных тобой методов или переменных).
- Если не понимаешь английского - пользуйся словарем/переводчиком.
- Не стесняйся гуглить текст ошибки.
- Если не работает большой кусок кода - попробуй отладить небольшую его часть.
- Мысленно пройдись последовательно по коду, записывая значения переменных и результат работы.
- Так же для отладки приложения и нахождения проблемный мест в среде разработки предсмотрен режим debug. Видео, показывающее основы использования debug режима в IntelliJ Idea [смотреть](https://www.youtube.com/watch?v=nIABqX19qFM)

#### Я не знаю, как подступиться к задаче
Если эта первая программа, которую ты пытаешься написать - постарайся отработать основы на более простых задачах (см материалы выше).  
Постарайся разбить большую задачу на маленькие подзадачи и решать их по отдельности - так гораздо проще. Или упрости задачу до варианта, который ты можешь решить, а потом дорабатывай до заданных требований. Ты можешь задавать вопросы [@Marandyuk_Anatolii](https://t.me/marandyuk_anatolii)
#### Успехов!




