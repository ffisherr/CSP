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
	def get(self):
		print('OKKKK')
		return "1"



api.add_resource(UserById, '/auth/id/')



if __name__ == '__main__':
	app.run(host='0.0.0.0', debug=True, port='5002')