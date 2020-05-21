import sqlite3
import json
from model import User, Event, Message, University
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
	def get(self, login, password):
		conn = sqlite3.connect('lbg.db')
		cursor = conn.cursor()
		user = cursor.execute('select * from users where email=%s and password=%s'
			% (login, password))
		for u in user:
			result = User(u)
		return jsonify(result.getFullInfo())


api.add_resource(UserById, '/auth/id/<user_id>')
api.add_resource(UserByLoginPassword, '/auth/user/<login>/passw/<passw>')



if __name__ == '__main__':
	app.run(host='0.0.0.0', debug=True, port='5002')