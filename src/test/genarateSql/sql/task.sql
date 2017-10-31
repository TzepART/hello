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
