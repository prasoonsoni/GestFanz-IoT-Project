from firebase import firebase
fan_state = True
firebase = firebase.FirebaseApplication('https://gestfanz-default-rtdb.firebaseio.com/', None)
firebase.put('/fan_state','isOn',fan_state)
print('State Updated')