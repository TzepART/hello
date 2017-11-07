/*однотабличная выборка*/
/*Вывести всех читателей*/
SELECT * FROM library.clients;

/*Вывести все книги, которых нет в наличии*/
SELECT * FROM library.books where cnt = 0;

/*Вывести всех читателей, упорядочив в алфавитном порядке по фамилии*/
SELECT * FROM library.clients order by family;

/*Вывести всех читателей, упорядочив в алфавитном порядке по фамилии и в обратном порядке по имени*/
SELECT * FROM library.clients order by family, name DESC;

/*Вывести все строки из журнала библиотекаря дата возврата которых меньше некоторой даты*/
SELECT * FROM library.journal WHERE date_return_real < '2017-10-23 00:05:20' AND date_return_real <> '0000-00-00 00:00:00';

/*Вывести все книги, у которых в наименовании есть слово «Редкая»*/
SELECT * FROM library.books WHERE name LIKE "%Редкая%";

/*Посчитать количество книг, которых нет в наличии*/
SELECT count(*) FROM library.books where cnt = 0;

/*выборка с подзапросами*/
/*Вывести все книги типа «Уникальные»*/
SELECT * FROM library.books as books WHERE books.type_id IN (SELECT id FROM library.book_types as type WHERE type.name='Уникальные');

/*Вывести все операции с книгами типа «уникальные»*/
SELECT * FROM library.journal as journal WHERE journal.book_id IN
	(SELECT id FROM library.books as books WHERE books.type_id IN
		(SELECT id FROM library.book_types as type WHERE type.name='Уникальные')
);

/*Вывести все книги типа «уникальные», которые на руках у читателей*/
SELECT * FROM library.journal as journal WHERE  journal.date_return_real = '0000-00-00 00:00:00' AND journal.book_id IN
	(SELECT id FROM library.books as books WHERE books.type_id IN
		(SELECT id FROM library.book_types as type WHERE type.name='Уникальные')
);

/*склеивание таблиц (join)*/
/*Вывеси журнал библиотекаря и читателей*/
SELECT clients.id as clients_id, clients.name as clients_name, clients.family as clients_family,
journal.id as journal_id, journal.book_id,  journal.client_id,  journal.ddate,  journal.date_return,  journal.date_return_real
FROM library.journal as journal INNER JOIN library.clients as clients ON journal.client_id = clients.id;

/*Вывести журнал библиотекаря и читателей, включая читателей, которые не брали книг*/
SELECT * FROM library.journal as journal RIGHT JOIN library.clients as clients ON journal.client_id = clients.id;

/*Вывести журнал библиотекаря, читателей, включая читателей, которые не брали книг и книги, включая книги, которых не выдавали*/
SELECT * FROM library.clients as clients RIGHT JOIN
	(SELECT books.name as books_name, books.cnt as books_count,
		journal.id as journal_id, journal.book_id as books_d,  journal.client_id as client_id,  journal.ddate,  journal.date_return,  journal.date_return_real
		FROM library.journal as journal RIGHT JOIN library.books ON journal.book_id=books.id) as jb ON jb.client_id=clients.id;

/*для реализации проекта*/
/*Число книг на руках у заданного клиента.*/
SELECT count(*) as count_not_return_books FROM library.journal as journal WHERE journal.client_id = '7' AND journal.date_return_real = '0000-00-00 00:00:00';

/*Размер штрафа заданного клиента.*/
--1. Получить все записи журнала для пользователя
--2. Вычислить разницу по времени между (если вернул, то врменем возращения, если нет то сегодняшней датой) и временем когда нужно вернуть
--3. Если количество дней положительно то добавляем в выборку + сумма за просрочку за день
--4. Пермножить дни на сумму
--5. Получить общую сумму
SELECT SUM(book_types.fine*books_clients.day_diff) as full_fine FROM library.book_types as book_types inner join (
    SELECT DATEDIFF(IF(journal.date_return_real <> '0000-00-00 00:00:00', journal.date_return_real, CURDATE()),journal.date_return) AS day_diff,
        IF(journal.date_return_real <> '0000-00-00 00:00:00', journal.date_return_real, CURDATE()) as date_return_upd,
         books.type_id as book_type_id
     FROM library.journal as journal inner join library.books as books on journal.book_id = books.id where client_id='10'
) as books_clients on book_types.id = books_clients.book_type_id where books_clients.day_diff > 0 order by full_fine DESC;


/*Размер самого большого штрафа*/
--1. Получить все записи журнала для пользователя
--2. Вычислить разницу по времени между (если вернул, то врменем возращения, если нет то сегодняшней датой) и временем когда нужно вернуть
SELECT IF(journal.date_return_real <> '0000-00-00 00:00:00', journal.date_return_real, CURDATE()) as date_return_upd,
         journal.book_id as book_id,
         journal.client_id as client_id,
         journal.ddate as ddate,
         journal.date_return as date_return
     FROM library.journal as journal where journal.client_id='8';
--3. Разницу дней между временем когда нужно было сдать и когда сдал + добавляем информацию о книге
SELECT DATEDIFF(IF(journal.date_return_real <> '0000-00-00 00:00:00', journal.date_return_real, CURDATE()),journal.date_return) AS day_diff,
        IF(journal.date_return_real <> '0000-00-00 00:00:00', journal.date_return_real, CURDATE()) as date_return_upd,
         books.id as book_id,
         books.name as book_name,
         books.cnt as book_cnt,
         books.type_id as book_type_id,
         journal.client_id as client_id,
         journal.ddate as ddate,
         journal.date_return as date_return
     FROM library.journal as journal inner join library.books as books on journal.book_id = books.id where client_id='10';
--4. Если количество дней положительно то добавляем в выборку + сумма за просрочку за день
SELECT * FROM library.book_types as book_types inner join (
    SELECT DATEDIFF(IF(journal.date_return_real <> '0000-00-00 00:00:00', journal.date_return_real, CURDATE()),journal.date_return) AS day_diff,
        IF(journal.date_return_real <> '0000-00-00 00:00:00', journal.date_return_real, CURDATE()) as date_return_upd,
         books.id as book_id,
         books.name as book_name,
         books.cnt as book_cnt,
         books.type_id as book_type_id,
         journal.client_id as client_id,
         journal.ddate as ddate,
         journal.date_return as date_return
     FROM library.journal as journal inner join library.books as books on journal.book_id = books.id where client_id='10'
) as books_clients on book_types.id = books_clients.book_type_id where books_clients.day_diff > 0;
--5. Пермножить дни на сумму
--6. Сортируем
--7. Береем верхнее значение
SELECT book_types.fine*books_clients.day_diff as full_fine, book_types.fine, books_clients.day_diff FROM library.book_types as book_types inner join (
    SELECT DATEDIFF(IF(journal.date_return_real <> '0000-00-00 00:00:00', journal.date_return_real, CURDATE()),journal.date_return) AS day_diff,
        IF(journal.date_return_real <> '0000-00-00 00:00:00', journal.date_return_real, CURDATE()) as date_return_upd,
         books.type_id as book_type_id
     FROM library.journal as journal inner join library.books as books on journal.book_id = books.id where client_id='10'
) as books_clients on book_types.id = books_clients.book_type_id where books_clients.day_diff > 0 order by full_fine DESC limit 1;

/*Три самые популярные книги*/
 SELECT journal.book_id as book_id, count(journal.book_id) as count_books, books.name FROM library.journal as  journal
		INNER JOIN library.books as books on books.id = journal.book_id GROUP by book_id order by count_books DESC LIMIT 3;

/*Вставка данных*/
/*однотабличная вставка*/
--Добавить нового клиента
INSERT INTO library.clients (`id`, `family`, `name`, `passport`) VALUES (46, 'Кузнецов', 'Аркадий', '1234567898');
--Выдать клиенту из п1 две книги
INSERT INTO library.journal (`ddate`, `book_id`, `client_id`, `date_return`)
select NOW(),1,46,DATE_ADD(NOW(),INTERVAL 60 DAY)
union
select NOW(),2,46,DATE_ADD(NOW(), INTERVAL 60 DAY)

/*многотабличная вставка в рамках транзакции*/
--Добавить в рамках транзакции клиента, книгу и запись в журнал библиотекаря о выдачи книги этому клиенту
delimiter $$
create procedure addClientAndBookTransaction()
BEGIN
	START TRANSACTION;
	INSERT INTO `library`.`clients` (`id`, `family`, `name`, `passport`) VALUES ('50', 'Иванов', 'Иван', '1111111111');
	INSERT INTO `library`.`books` (`id`, `name`, `cnt`, `type_id`) VALUES ('50', 'Обычная книга 1', '3', '1');
	INSERT INTO library.journal (`ddate`, `book_id`, `client_id`, `date_return`) select NOW(),50,50,DATE_ADD(NOW(),INTERVAL 60 DAY);
END$$
delimiter ;

call addClientAndBookTransaction();
drop procedure addClientAndBookTransaction;

--Добавить запись в журнал, в случае, если книг у данного клиента больше 10, транзакцию откатить
delimiter $$
create procedure addBookTransaction()
BEGIN
	START TRANSACTION;
	INSERT INTO library.journal (`ddate`, `book_id`, `client_id`, `date_return`)
	select NOW(),4,46,DATE_ADD(NOW(),INTERVAL 60 DAY);
	SELECT COUNT(*) INTO @book_rows from library.journal where journal.client_id=46;
    IF (@book_rows<10) THEN
        COMMIT;
    ELSE
       ROLLBACK;
    END IF;
END$$
delimiter ;

call addBookTransaction();
drop procedure addBookTransaction;


/*Удаление данных*/
/*удаление по фильтру и удаление из связанных таблиц*/
--Удалить выдачи из журнала, где реальная дата возврата меньше некоторой даты
DELETE FROM library.journal as journal WHERE journal.date_return_real < '2017-10-31 00:05:21' and journal.date_return_real <> '2017-08-12 00:05:20';
--Удалить клиента и все, связанные с ним, записи в журнале
delimiter $$
create procedure removeClientWithNote(clientId INT)
begin
	DELETE FROM library.journal WHERE client_id = clientId;
    DELETE FROM library.clients WHERE id = clientId;
END$$
delimiter ;

call removeClientWithNote(1);
drop procedure removeClientWithNote;

--Удалить книги, не имеющие ссылок из записей в журнале
delimiter $$
create procedure removeUnLinkBook()
begin
	DELETE FROM library.books WHERE id NOT IN (
	    select DISTINCT book_id from library.journal
	);
END$$
delimiter ;

call removeUnLinkBook();
drop procedure removeUnLinkBook;

/*удаление в рамках транзакции*/
--Удалить в рамках транзакции книгу и записи о ее выдаче
delimiter $$
create procedure removeBookTransaction(bookId INT)
begin
    START TRANSACTION;
		DELETE FROM library.journal WHERE book_id = bookId;
		DELETE FROM library.books WHERE id = bookId;
    COMMIT;
END$$
delimiter ;

call removeBookTransaction(1);
drop procedure removeBookTransaction;
--то же, что и п1, но транзакцию откатить
delimiter $$
create procedure removeBookAndRevertTransaction(bookId INT)
begin
    START TRANSACTION;
		DELETE FROM library.journal WHERE book_id = bookId;
		DELETE FROM library.books WHERE id = bookId;
    ROLLBACK;
END$$
delimiter ;

call removeBookAndRevertTransaction(1);
drop procedure removeBookAndRevertTransaction;

/*Модификация данных*/
/*модификация по фильтру*/
--Изменить количество книг с заданным наименованием
delimiter $$
create procedure changeBooksCountByName(bookName VARCHAR(50), newCount INT)
begin
	UPDATE library.books SET cnt=newCount WHERE name=bookName;
END$$
delimiter ;

call changeBooksCountByName('Обычная книга 2', 10);
drop procedure changeBooksCountByName;

--Изменить реальную дату возврата заданной книги
delimiter $$
create procedure changeRealReturnDateBook(bookId INT, newDateReturn datetime)
begin
    UPDATE library.journal SET date_return_real=newDateReturn WHERE book_id=bookId;
END$$
delimiter ;

call changeRealReturnDateBook(3, '2017-11-04 01:07:48');
drop procedure changeRealReturnDateBook;

/*модификация в рамках транзакции*/
--В рамках транзакции поменять заданную книгу во всех записях журнала на другую и удалить ее.
delimiter $$
create procedure setNewBookTransaction(oldBookId INT, newBookId INT)
begin
    START TRANSACTION;
        UPDATE library.journal SET book_id=newBookId WHERE book_id=oldBookId;
		DELETE FROM library.books WHERE id = oldBookId;
    COMMIT;
END$$
delimiter ;

call setNewBookTransaction(12,13);
drop procedure setNewBookTransaction;

--то же, что и п1, но транзакцию откатить
delimiter $$
create procedure setNewBookAndRevertTransaction(oldBookId INT, newBookId INT)
begin
    START TRANSACTION;
        UPDATE library.journal SET book_id=newBookId WHERE book_id=oldBookId;
		DELETE FROM library.books WHERE id = oldBookId;
    ROLLBACK;
END$$
delimiter ;

call setNewBookAndRevertTransaction(13,14);
drop procedure setNewBookAndRevertTransaction;
