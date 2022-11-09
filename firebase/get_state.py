from firebase import firebase
firebase = firebase.FirebaseApplication('https://gestfanz-default-rtdb.firebaseio.com/', None)
state_result = firebase.get('/fan_state', '')
print(state_result['isOn'])