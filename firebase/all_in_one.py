# importing firebase library
from firebase import firebase
# initializing firebase
firebase = firebase.FirebaseApplication('https://gestfanz-default-rtdb.firebaseio.com/', None)

# changing firebase speed
fan_speed = 5
firebase.put('/fan_speed','fanSpeed',fan_speed)
print('Speed Updated')

# changing state
fan_state = False
firebase.put('/fan_state','isOn',fan_state)
print('State Updated')

# getting speed
speed_result = firebase.get('/fan_speed', '')
print(speed_result['fanSpeed'])

# getting state
state_result = firebase.get('/fan_state', '')
print(state_result['isOn'])
