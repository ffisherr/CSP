import sqlite3
import json


def arrToString(arr):
	s = ''
	for col in arr:
		s += ''' '%s', ''' % col
	return s[:-2]


def createTable(cursor, tableName, cols):
	sql = 'create table if not exists %s (%s)' % (tableName, cols)
	print(sql)
	cursor.execute(sql)


def dropTable(cursor, tableName):
	sql = 'drop table %s' % tableName
	cursor.execute(sql)


def insertIntoTable(cursor, table, values):
	values = arrToString(values)
	print('insert into %s values(%s)'%(table, values))
	cursor.execute('insert into %s values(%s)'%(table, values))


def fillWithTestData(cursor):
	#cursor.execute('insert into users values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', ('0', '0', 'RT', '0', 'Андрей',  'Попов', 'Николаевич',  '1997-03-10', '2021-03-10', 'a@2.ru', '123'))
	#cursor.execute('insert into users values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', ('1', '1', 'RT', '3', 'Иван',    'Попов', 'Николаевич',  '1998-03-10', '2022-03-10', 'a@3.ru', '124'))
	#cursor.execute('insert into users values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', ('2', '2', 'RT', '0', 'Дмитрий', 'Попов', 'Николаевич',  '1999-03-10', '2023-03-10', 'a@4.ru', '125'))
	#cursor.execute('insert into users values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', ('3', '3', 'RT', '0', 'Алексей', 'Попов', 'Николаевич',  '1990-03-10', '2023-03-10', 'a@55.ru', '12876'))
	cursor.execute('insert into users values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', ('4', '1', 'RT', '3', 'Иван',    'Иванов', 'Николаевич',  '1990-03-10', '2023-03-10', 'a@31.ru', '123'))
	cursor.execute('insert into users values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', ('5', '1', 'RT', '3', 'Алексей', 'Петров', 'Николаевич',  '1990-03-10', '2023-03-10', 'a@32.ru', '123'))
	cursor.execute('insert into users values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', ('6', '1', 'RT', '3', 'Василий', 'Сидоров', 'Николаевич',  '1990-03-10', '2023-03-10', 'a@33.ru', '123'))
	
	#cursor.execute('insert into applications values(?, ?, ?, ?, ?, ?, ?, ?)', 
	#	('0', '2020-05-10', '2020-05-20', '0', '0', '2', '1',  '888'))



def dropTables(cursor):
	#dropTable(cursor, 'users')
	dropTable(cursor, 'applications')
	#dropTable(cursor, 'roles')
	#dropTable(cursor, 'calendar')
	#dropTable(cursor, 'messages')
	#dropTable(cursor, 'universities')


def createTables(cursor):
	#createTable(cursor, 'users', 'id int NOT NULL UNIQUE, roleId int, CompanyName text, ChiefId int, firstName text, surName text, secondName text, birthDate date, endOfContract date, email text NOT NULL UNIQUE, password text')
	#createTable(cursor, 'applications', 'id int NOT NULL UNIQUE, \
	#	whenCreated date, whenClosed date, Status int, equipmentId int, \
	#	userCreatorId int, userSolverId int, phoneNumber text')
	#createTable(cursor, 'roles', 'id int NOT NULL UNIQUE, role text')
	#createTable(cursor, 'calendar', 'id int NOT NULL UNIQUE, title text, dt datetime, \
	#	about text, tags json1')
	#createTable(cursor, 'messages', 'id int NOT NULL UNIQUE, message_text text, dt datetime,\
	#	sender_id int, sender_login text')
	#createTable(cursor, 'universities', 'id int not NULL UNIQUE, name text')
	fillWithTestData(cursor)

def main():
	conn  = sqlite3.connect('lbg.db')
	cursor = conn.cursor()
	#dropTables(cursor)
	createTables(cursor)
	conn.commit()
	

if __name__ == '__main__':
	main()
