import json


class User():
	def __init__(self, arg):
		self.id, self.roleId, self.CompanyName, \
		self.firstName, self.surName, self.secondName, \
		 self.birthDate, self.endOfContract, self.email, \
		 self.password = arg

	def getFullInfo(self):
		info = {'id': str(self.id), 
				'CompanyName': self.CompanyName,
				'password': self.password,
				'roleId': str(self.roleId),
				'firstName': self.firstName,
				'surName': self.surName,
				'secondName': self.secondName,
				'birthDate': self.birthDate,
				'endOfContract': self.endOfContract,
				'password': self.password,
				'email': self.email}
		return info

	def addToDB(self):
		result = []
		result.append(self.id)
		result.append(self.roleId)
		result.append(self.CompanyName)
		result.append(self.firstName)
		result.append(self.surName)
		result.append(self.secondName)
		result.append(self.birthDate)
		result.append(self.endOfContract)
		result.append(self.email)
		result.append(self.password)
		return result


class University():
	def __init__(self, arg):
		self.id, self.name = arg

	def getFullInfo(self):
		info = {'id': self.id, 
				'name': self.name,
				'status': 'success'}
		return info

	def addToDB(self):
		result = []
		result.append(self.id)
		result.append(self.name)
		return result


class Message():
	def __init__(self, arg):
		self.id, self.dt, self.sender_id, self.message_text, self.sender_login = arg

	def getFullInfo(self):
		info = {'id': self.id, 
				'sender_id': self.sender_id,
				'dt': str(self.dt),
				'message_text': self.message_text,
				'sender_login': self.sender_login,
				'status': 'success'}
		return info

	def addToDB(self):
		result = []
		result.append(self.id)
		result.append(self.dt)
		result.append(self.sender_id)
		result.append(self.message_text)
		result.append(self.sender_login)
		return result

class Event():
	def __init__(self, arg):
		self.id, self.title, self.dt, self.about, self.tags = arg

	def getFullInfo(self):
		info = {'id': self.id, 
				'title': self.title,
				'dt': self.dt,
				'about': self.about,
				'tags': json.loads(self.tags)["tags"],
				'status': 'success'}
		return info

	def addToDB(self):
		result = []
		result.append(self.id)
		result.append(self.title)
		result.append(self.dt)
		result.append(self.about)
		result.append(self.tags)
		return result
