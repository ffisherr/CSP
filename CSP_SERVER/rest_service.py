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


api.add_resource(UserById, '/auth/id/<user_id>')
api.add_resource(UserByLoginPassword, '/auth/user/')
api.add_resource(JobForTech, '/techno/id/<tech_id>')





if __name__ == '__main__':
	app.run(host='0.0.0.0', debug=True, port='5002')