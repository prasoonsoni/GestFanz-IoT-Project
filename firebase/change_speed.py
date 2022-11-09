from firebase import firebase
fan_speed = 5
firebase = firebase.FirebaseApplication('https://gestfanz-default-rtdb.firebaseio.com/', None)
firebase.put('/fan_speed','fanSpeed',fan_speed)
print('Speed Updated')