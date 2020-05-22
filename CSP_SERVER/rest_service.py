import sqlite3
import json
from model import User, Application
from db import insertIntoTable
from flask import Flask, request
from flask_restful import Resource, Api
from flask_jsonpify import jsonify


app = Flask(__name__)
api = Api(app)


class UserById(Resource):
	def get(self, user_id):
		conn = sqlite3.connect('lbg.db')
		cursor = conn.cursor()
		user = cursor.execute('select * from users where id=%s'%int(user_id))
		for u in user:
			result = User(u)
		return jsonify(result.getFullInfo())


class UserByLoginPassword(Resource):
	def post(self):
		conn = sqlite3.connect('lbg.db')
		cursor = conn.cursor()
		login = request.json['login']
		passw = request.json['passw']
		user = cursor.execute('''select * from users where email="%s" and password="%s"'''
			% (login, passw))
		for u in user:
			result = User(u)
		return jsonify(result.getFullInfo())


class RegisterUser(Resource):
	def post(self):
		#try:
		conn       = sqlite3.connect('lbg.db')
		cursor     = conn.cursor()
		password    = request.json['password']
		FirstName     = request.json['firstName']
		SurName       = request.json['surName']
		SecondName    = request.json['secondName']
		email = request.json['email']
		print('select * from users \
		where firstName="%s" and secondName="%s" and surName="%s"' %
		 (FirstName, SurName, SecondName))
		users = cursor.execute('select * from users \
		where firstName="%s" and secondName="%s" and surName="%s"' %
		 (FirstName, SurName, SecondName)).fetchall()
		print(users)
		for u in users:
			result = User(u)
			print('im geerere')
			print(result.getFullInfo())
		if result.id >= 0:
			result.email = email
			result.password = password
			cursor.execute('update users set email="%s", password="%s" \
				where firstName="%s" and secondName="%s" and surName="%s"' %
				(email, password, FirstName, SurName, SecondName))
			conn.commit()
		print(result.getFullInfo())
		return jsonify(result.getFullInfo())		
	"""	except Exception as e:
			print('here3')
			print(e)
			uuarr = []
			for i in range(11):
				uuarr.append('')
			u = User(uuarr)
			u.id = -1
			return jsonify(u.getFullInfo())
"""

class JobForTech(Resource):
	def get(self, tech_id):
		conn = sqlite3.connect('lbg.db')
		cursor = conn.cursor()
		appl = cursor.execute('''select * from applications where userSolverId="%s"'''
			% (tech_id))
		for u in appl:
			result = Application(u)
		print(result.getFullInfo())
		return jsonify(result.getFullInfo())


class FindTechUsers(Resource):
	def get(self):
		conn = sqlite3.connect('lbg.db')
		cursor = conn.cursor()
		appl = cursor.execute('''select * from users where roleId="1"''')
		result = []
		for u in appl:
			result.append((User(u).getFullInfo()))
		print(result)
		return jsonify(result)


api.add_resource(UserById, '/auth/id/<user_id>')
api.add_resource(UserByLoginPassword, '/auth/user/')
api.add_resource(RegisterUser, '/auth/register/')
api.add_resource(JobForTech, '/techno/id/<tech_id>')
api.add_resource(FindTechUsers, '/techno/users/')


if __name__ == '__main__':
	app.run(host='0.0.0.0', debug=True, port='5002')