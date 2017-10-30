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

/*Размер штрафа заданного клиента.*/

/*Размер самого большого штрафа*/

/*Три самые популярные книги*/